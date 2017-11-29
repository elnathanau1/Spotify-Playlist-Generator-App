package ai;

import spotifyObjects.*;

import java.util.*;

import main.*;

/**
 * Agent class responsible for making playlist decisions
 * @author eau
 *
 */
public class Agent {
	public Track rootTrack;					// track that the agent should use as the control
	public String accessToken;				// Token necessary for API calls

	/**
	 * Constructor
	 * @param root
	 * @param newAccessToken
	 */
	public Agent(Track root, String newAccessToken) {
		rootTrack = root;
		accessToken = newAccessToken;
	}

	/**
	 * Creates playlist with numTracks of tracks
	 * @param numTracks
	 * @return
	 */
	public Playlist createPlaylist(int numTracks, String name) {
		// Create a playlist with name
		Playlist newPlaylist = API.createPlaylist("spotify:user:125980338", accessToken, name);

		// create an arrayList of potential tracks
		ArrayList<Track> potentialTracks = getPotentialTracks();

		// add audio features to the potential tracks 100 tracks at a time (minimize api calls to not hit limit)
		int a = 0;
		int b = 0;
		while(b < potentialTracks.size() - 1) {
			if(a + 99 >= potentialTracks.size()) {
				b = potentialTracks.size() - 1;
			}
			else {
				b = a + 99;
			}
			ArrayList<Track>tempList = new ArrayList<Track>(potentialTracks.subList(a, b));
			AudioFeatures[] audioFeaturesToAdd = API.getSeveralAudioFeatures(tempList, accessToken);
			for(int i = 0; i < audioFeaturesToAdd.length; i++) {
				potentialTracks.get(a+i).audioFeatures = audioFeaturesToAdd[i];
			}
			a = b + 1;
			
		}

		// add audioFeatures for rootTrack
		rootTrack.audioFeatures = API.getAudioFeatures(rootTrack.uri, accessToken);
		
		// set the heuristic value for each track
		for(Track testTrack : potentialTracks) {
			testTrack.heuristicValue = Heuristic.getHeuristic(rootTrack, testTrack);
		}

		//Order the potentialTracks arrayList, tracks with higher heuristic value will be in the front, and lower heuristic values at the back
		Collections.sort(potentialTracks, new Comparator<Track>() {
			@Override
			public int compare(Track trackOne, Track trackTwo) {
				return -1 * Double.compare(trackOne.heuristicValue, trackTwo.heuristicValue);
			}
		});

		
		//add top numTracks to playlist, 100 at a time to reduce api calls
		int numTracksLeft = numTracks;
		while(numTracksLeft > 0) {
			ArrayList<Track> tracksToAdd = new ArrayList<Track>();
			if(numTracksLeft < 100) {
				tracksToAdd = new ArrayList<Track>(potentialTracks.subList(0,numTracksLeft));
			}
			else {
				tracksToAdd = new ArrayList<Track>(potentialTracks.subList(0, 100));
			}
			numTracksLeft -= tracksToAdd.size();
			potentialTracks.removeAll(tracksToAdd);
			API.addTracksToPlaylist(newPlaylist.uri, tracksToAdd, accessToken);
		}

		return newPlaylist;
	}

	/**
	 * Returns a list of potential tracks
	 * @return
	 */
	private ArrayList<Track> getPotentialTracks() {
		ArrayList<Track> potentialTracks = new ArrayList<Track>();

		ArrayList<ArrayList<Artist>> artistTree = createArtistTree(3);			//3 is the depth of the tree, make larger if more tracks are necessary
		for(ArrayList<Artist> layer : artistTree) {
			for(Artist artist : layer) {
				//Adds the artists top tracks
				Track[] topTracks = API.getArtistTopTracks(artist.uri, accessToken);
				
				//ADD MORE THINGS HERE
				
				for(Track track : topTracks) {
					potentialTracks.add(track);
				}
			}
		}

		return potentialTracks;
	}

	/**
	 * Creates a representation of a tree of artists + their related artists. Each index represents a layer in the tree 
	 * (ex: artistTree[0] = first layer aka root, artistTree[1] = second layer aka root's related artists, etc)
	 * @return
	 */
	private ArrayList<ArrayList<Artist>> createArtistTree(int depth) {
		//init
		ArrayList<ArrayList<Artist>> artistTree = new ArrayList<ArrayList<Artist>>();
		artistTree.add(new ArrayList<Artist>());
		ArrayList<String> addedArtists = new ArrayList<String>();

		//set up root node[s]
		for(int i = 0; i < rootTrack.artists.length; i++) {
			artistTree.get(0).add(rootTrack.artists[i]);
			addedArtists.add(rootTrack.artists[i].uri);
		}

		//add layers
		for(int i = 1; i < depth; i++) {
			artistTree.add(new ArrayList<Artist>());
			for(Artist tempArtist : artistTree.get(i-1)) {
				Artist[] relatedArtistsArray = API.getRelatedArtists(tempArtist.uri, accessToken);
				for(int j = 0; j < relatedArtistsArray.length*.75; j++) {
					Artist relatedArtist = relatedArtistsArray[j];
					if(!addedArtists.contains(relatedArtist.uri)) {
						artistTree.get(i).add(relatedArtist);
						addedArtists.add(relatedArtist.uri);
					}
				}
			}
		}

		return artistTree;

	}

}
