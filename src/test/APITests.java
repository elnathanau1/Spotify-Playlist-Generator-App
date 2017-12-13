package test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import main.API;
import main.Main;
import spotifyObjects.*;

/**
 * Tests for API.java class
 * @author eau
 *
 */
public class APITests {
	public String accessToken = Main.accessToken;
	
	@Before
	public void setUp() {
		
	}
	
	/**
	 * Tests the getAlbum() method
	 */
	@Test
	public void getAlbumTest(){
		Album testAlbum = API.getAlbum("spotify:album:7Csd473gctS7rL0YUhjlkX", accessToken);
		assertEquals(testAlbum.album_type, "album");
		assertEquals(testAlbum.artists.length, 1);
		assertEquals(testAlbum.artists[0].name, "Newsboys");
		assertEquals(testAlbum.name, "God's Not Dead");
		assertEquals(testAlbum.uri, "spotify:album:7Csd473gctS7rL0YUhjlkX");
		
	}
	
	/**
	 * Tests the getArtist() method
	 */
	@Test
	public void getArtistTest() {
		Artist testArtist = API.getArtist("spotify:artist:46gyXjRIvN1NL1eCB8GBxo", accessToken);
		assertEquals(testArtist.name, "All Time Low");
		assertEquals(testArtist.type, "artist");
		assertEquals(testArtist.uri, "spotify:artist:46gyXjRIvN1NL1eCB8GBxo");
	}
	
	/**
	 * Tests the getRelatedArtists() method
	 */
	@Test
	public void getRelatedArtistTest() {
		Artist[] testArtists = API.getRelatedArtists("spotify:artist:6FBDaR13swtiWwGhX1WQsP", accessToken);
		assertEquals(testArtists.length, 20);
		
		boolean foundArtist1 = false;
		boolean foundArtist2 = false;
		boolean foundArtist3 = false;
		
		for(int i = 0; i < testArtists.length; i++) {
			Artist testArtist = testArtists[i];
			if(testArtist.name.equals("Box Car Racer")) {
				foundArtist1 = true;
			}
			else if(testArtist.name.equals("Yellowcard")) {
				foundArtist2 = true;
			}
			else if(testArtist.name.equals("Green Day")) {
				foundArtist3 = true;
			}
		}
		
		assertEquals(foundArtist1 && foundArtist2 && foundArtist3, true);
	}
	
	/**
	 * Tests the getArtistTopTracks() method
	 */
	@Test
	public void getArtistTopTracksTest() {
		Track[] topTracks = API.getArtistTopTracks("spotify:artist:5v61OSg53KaQxGMpErkBNp", accessToken);
		assertEquals(topTracks.length, 10);
		assertEquals(topTracks[0].uri, "spotify:track:5sTVykpRs4eiZKn96bZogj");
		assertEquals(topTracks[1].uri, "spotify:track:6VSmHLLvYAAkzrYFT3LloU");
		assertEquals(topTracks[2].uri, "spotify:track:3EuiMoY8lTjAvIqNf8Uyr3");
	}
	
	/**
	 * Tests the getTrack() method
	 */
	@Test
	public void getTrackTest() {
		Track testTrack = API.getTrack("spotify:track:312ePP5VEANLDNNiW1cPh8", accessToken);
		assertEquals(testTrack.name, "Rescue");
		assertEquals(testTrack.uri, "spotify:track:312ePP5VEANLDNNiW1cPh8");
	}
	
	/**
	 * Tests the getAudioFeatures() method
	 */
	@Test
	public void getAudioFeaturesTest() {
		Track testTrack = API.getTrack("spotify:track:7yCPwWs66K8Ba5lFuU2bcx", accessToken);
		testTrack.addAudioFeatures(accessToken);
		assertEquals(testTrack.audioFeatures.danceability, 0.439, 0.001);
		assertEquals(testTrack.audioFeatures.energy, 0.891, 0.001);
		assertEquals(testTrack.audioFeatures.uri, testTrack.uri);
	}
	
	/**
	 * Tests the getSeveralAudioFeatures() method
	 */
	@Test
	public void getSeveralAudioFeaturesTest() {
		Track[] topTracks = API.getArtistTopTracks("spotify:artist:5v61OSg53KaQxGMpErkBNp", accessToken);
		ArrayList<Track> tracks = new ArrayList<Track>();
		for(Track track : topTracks) {
			tracks.add(track);
		}
		AudioFeatures[] audioFeatures = API.getSeveralAudioFeatures(tracks, accessToken);
		
		assertEquals(audioFeatures.length, 10);
		assertEquals(audioFeatures[0].uri, "spotify:track:5sTVykpRs4eiZKn96bZogj");
	}
	
	/**
	 * Tests the getPlaylist() method
	 */
	@Test
	public void getPlaylistTest() {
		Playlist testPlaylist = API.getPlaylist("spotify:user:125980338:playlist:7mL6o1kKqrpBm52wkuzoqj", accessToken);
		assertEquals(testPlaylist.tracks.items.length, 8);
		assertEquals(testPlaylist.name, "CS 242 FINAL PLAYLIST TEST");
		assertEquals(testPlaylist.uri, "spotify:user:125980338:playlist:7mL6o1kKqrpBm52wkuzoqj");
		assertEquals(testPlaylist.tracks.items[0].track.uri, "spotify:track:6ZimCGmTrqKyUnidBoLG13");
	}

	/**
	 * Tests the getCurrentUser() method
	 */
	@Test
	public void getCurrentUserTest() {
		CurrentUser user = API.getCurrentUser(accessToken);
		assertEquals(user.display_name, "Elnathan Au");
		assertEquals(user.uri, "spotify:user:125980338");
	}

	/**
	 * Tests the createPlaylist() and addTracksToPlaylist() methods
	 */
	@Test
	public void createPlaylistAddTracksTest() {
		Playlist newPlaylist = API.createPlaylist("spotify:user:125980338", accessToken, "createPlaylistTest", true, false, "Hello World");
		newPlaylist = API.getPlaylist(newPlaylist.uri, accessToken);
		assertEquals(newPlaylist.description, "Hello World");
		assertEquals(newPlaylist.collaborative, false);
		assertEquals(newPlaylist.name, "createPlaylistTest");
		assertEquals(newPlaylist.tracks.items.length, 0);

		Track[] topTracks = API.getArtistTopTracks("spotify:artist:5v61OSg53KaQxGMpErkBNp", accessToken);
		ArrayList<Track> tracks = new ArrayList<Track>();
		for(Track track : topTracks) {
			tracks.add(track);
		}
		
		API.addTracksToPlaylist(newPlaylist.uri, tracks, accessToken);
		newPlaylist = API.getPlaylist(newPlaylist.uri, accessToken);
		assertEquals(newPlaylist.tracks.items.length, 10);
		
	}

	@Test
	public void playbackTests() {
		//Might need manual test plan
	}
}
