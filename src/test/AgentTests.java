package test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import main.*;
import spotifyObjects.*;
import ai.*;

/**
 * Tests for the Agent.java class
 * @author eau
 *
 */
public class AgentTests {
	public String accessToken = Main.accessToken;
	
	@Before
	public void setUp() {
		
	}
	
	/**
	 * tests Agent.java's constructor
	 */
	@Test
	public void constructorTest(){
		Track rootTrack = API.getTrack("spotify:track:20X9OeC606XNwEtDBOym5u", accessToken);
		Agent testAgent = new Agent(rootTrack, accessToken);
		assertEquals(testAgent.rootTrack, rootTrack);
		assertEquals(testAgent.accessToken, accessToken);
		
	}
	
	/**
	 * test for the createPlaylist() method. Warning: Will create a playlist in spotify account
	 */
	@Test
	public void createPlaylistTest() {
		Track rootTrack = API.getTrack("spotify:track:20X9OeC606XNwEtDBOym5u", accessToken);
		Agent testAgent = new Agent(rootTrack, accessToken);
		testAgent.setPlaylistSettings(53, "Playlist Test", false, false, "");
		Playlist testPlaylist = testAgent.createPlaylist();
		String spotifyURI = testPlaylist.uri;
		Playlist comparePlaylist = API.getPlaylist(spotifyURI, accessToken);
		
		assertEquals(comparePlaylist.name, "Playlist Test");
		assertEquals(comparePlaylist.tracks.items.length, 53);	//need to use comparePlaylist because the playlist returned from createPlaylist will not have paging object set up
	}
}
