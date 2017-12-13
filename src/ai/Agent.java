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

	public int randomTracks = 2;
	public int artistDepth = 3;

	public int numTracks;
	public String name;
	public boolean isPublic;
	public boolean collaborative;
	public String description;
	public ArrayList<Track> addedTracks;
	public ArrayList<Track> potentialTracks;
	public ArrayList<String> addedArtists;

	/**
	 * Default Constructor
	 */
	public Agent() {
		rootTrack = null;
		accessToken = Main.accessToken;
		addedTracks = new ArrayList<Track>();
		potentialTracks = new ArrayList<Track>();
		setDefaults();
	}

	/**
	 * Set default values for variables
	 */
	private void setDefaults() {
		numTracks = 10;
		name = "Default playlist name";
		isPublic = false;
		collaborative = false;
		description = "";
	}

	/**
	 * Constructor with root tracks
	 * @param root
	 * @param newAccessToken
	 */
	public Agent(Track root, String newAccessToken) {
		rootTrack = root;
		accessToken = newAccessToken;
		addedTracks = new ArrayList<Track>();
		potentialTracks = new ArrayList<Track>();
		setDefaults();
	}

	/**
	 * Creates playlist with numTracks of tracks
	 * @param numTracks
	 * @return
	 */
	public Playlist createPlaylist() {
		// Create a playlist with params
		CurrentUser user = API.getCurrentUser(accessToken);
		Playlist newPlaylist = API.createPlaylist(user.uri, accessToken, name, isPublic, collaborative, description);

		// create an arrayList of potential tracks
		potentialTracks = getPotentialTracks();

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
			if(audioFeaturesToAdd != null) {
				for(int i = 0; i < audioFeaturesToAdd.length; i++) {
					potentialTracks.get(a+i).audioFeatures = audioFeaturesToAdd[i];
				}
			}
			a = b + 1;

		}

		// add audioFeatures for rootTrack
		rootTrack.audioFeatures = API.getAudioFeatures(rootTrack.uri, accessToken);
		//		rootTrack.genres = API.getArtist(rootTrack.artists[0].uri, accessToken).genres;

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
			addedTracks.addAll(tracksToAdd);
		}

		return newPlaylist;
	}

	/**
	 * Returns a list of potential tracks
	 * @return
	 */
	private ArrayList<Track> getPotentialTracks() {
		ArrayList<Track> potentialTracks = new ArrayList<Track>();

		ArrayList<ArrayList<Artist>> artistTree = createArtistTree(artistDepth);			//3 is the depth of the tree, make larger if more tracks are necessary
		for(int i = 0; i < artistTree.size(); i++) {
			ArrayList<Artist> layer = artistTree.get(i);
			for(Artist artist : layer) {
				//Adds the artists top tracks
				Track[] topTracks = API.getArtistTopTracks(artist.uri, accessToken);
				if(topTracks != null) {
					for(Track track : topTracks) {
						track.genres = artist.genres;
						track.artistDepth = i;
						potentialTracks.add(track);
					}
				}

				for(int a = 0; a < randomTracks; a++) {
					Track track = getRandomTrack(artist);
					if(track != null) {
						track.genres = artist.genres;
						track.artistDepth = i;
						potentialTracks.add(track);
					}

				}

			}
		}

		return potentialTracks;
	}

	public Track getRandomTrack(Artist artist) {
		Album[] albums = API.getArtistsAlbums(artist.uri, accessToken);
		if(albums != null) {
			Album album = albums[(int)(Math.random() * albums.length)];
			Track[] tracks = API.getAlbumTracks(album.uri, accessToken);
			if(tracks != null) {
				Track track = tracks[(int) (Math.random() * tracks.length)];
				track = API.getTrack(track.uri, accessToken);
				return track;
			}
		}

		return null;
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
		addedArtists = new ArrayList<String>();

		//set up root node[s]
		for(int i = 0; i < rootTrack.artists.length; i++) {
			artistTree.get(0).add(API.getArtist(rootTrack.artists[i].uri, accessToken));
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

	/**
	 * Easy access method to set multiple vars at once
	 * @param numTracks
	 * @param name
	 * @param isPublic
	 * @param collab
	 * @param desc
	 */
	public void setPlaylistSettings(int numTracks, String name, boolean isPublic, boolean collab, String desc) {
		setNumTracks(numTracks);
		setName(name);
		setPublic(isPublic);
		setCollaborative(collab);
		setDescription(desc);
	}

	public Track getRootTrack() {
		return rootTrack;
	}

	public void setRootTrack(Track rootTrack) {
		this.rootTrack = rootTrack;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public int getNumTracks() {
		return numTracks;
	}

	public void setNumTracks(int numTracks) {
		this.numTracks = numTracks;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public boolean isCollaborative() {
		return collaborative;
	}

	public void setCollaborative(boolean collaborative) {
		this.collaborative = collaborative;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
