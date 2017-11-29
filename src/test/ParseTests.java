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
	public String accessToken = Main.accessToken;
	
	@Before
	public void setUp() {
	}
	
	@Test
	public void getAlbumTest(){
		Album testAlbum = API.getAlbum("spotify:album:34eR6ev1GGZHmxNTEfRobW", accessToken);
		assertEquals(testAlbum.album_type.equals("album"), true);
		assertEquals(testAlbum.name.equals("Homesick"), true);
		assertEquals(testAlbum.uri.equals("spotify:album:34eR6ev1GGZHmxNTEfRobW"), true);
		
	}
	
	@Test
	public void getArtistTest() {
		Artist testArtist = API.getArtist("spotify:artist:46gyXjRIvN1NL1eCB8GBxo", accessToken);
		assertEquals(testArtist.name.equals("All Time Low"), true);
		assertEquals(testArtist.uri.equals("spotify:artist:46gyXjRIvN1NL1eCB8GBxo"), true);
	}
	
	@Test
	public void getTrackTest() {
		Track testTrack = API.getTrack("spotify:track:312ePP5VEANLDNNiW1cPh8", accessToken);
		assertEquals(testTrack.name.equals("Rescue"), true);
		assertEquals(testTrack.uri.equals("spotify:track:312ePP5VEANLDNNiW1cPh8"), true);
	}
}
