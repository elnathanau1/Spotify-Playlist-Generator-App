package test;

import static org.junit.Assert.assertEquals;

import org.junit.*;
import main.*;
import spotifyObjects.*;

/**
 * Test class for object creators
 * @author eau
 *
 */
public class ParseTests {
	public String accessToken = "BQCALyKCfhNCf3CXo_UGomZQuO_aPw7BTnfj3lBCTAHi1R9Hvq74tCYdde0siZ33wb7TYiKrFwrZrR1TJapr1zeJnfkqKpkwRTAv1QyYy589L14RDkY6NagePI8OOWDBhD7lC98hng3T_NWn_OTD-X5whkNtIG_gmAabKEHcOge8kfnOICEvfKOvsRBFUVq40RdqElLNnieIyiqv1dmxJNA2NLoMprWSZhdfyTMcI5-cX1lyvOXg5A7PGaUp3hQDkhhnJdteURIl";
	public API api;
	
	@Before
	public void setUp() {
		api = new API(accessToken);
	}
	
	@Test
	public void getAlbumTest(){
		Album testAlbum = api.getAlbum("spotify:album:34eR6ev1GGZHmxNTEfRobW");
		assertEquals(testAlbum.album_type.equals("album"), true);
		assertEquals(testAlbum.name.equals("Homesick"), true);
		assertEquals(testAlbum.uri.equals("spotify:album:34eR6ev1GGZHmxNTEfRobW"), true);
		
	}
	
	@Test
	public void getArtistTest() {
		Artist testArtist = api.getArtist("spotify:artist:46gyXjRIvN1NL1eCB8GBxo");
		assertEquals(testArtist.name.equals("All Time Low"), true);
		assertEquals(testArtist.uri.equals("spotify:artist:46gyXjRIvN1NL1eCB8GBxo"), true);
	}
	
	@Test
	public void getTrackTest() {
		Track testTrack = api.getTrack("spotify:track:312ePP5VEANLDNNiW1cPh8");
		assertEquals(testTrack.name.equals("Rescue"), true);
		assertEquals(testTrack.uri.equals("spotify:track:312ePP5VEANLDNNiW1cPh8"), true);
	}
}
