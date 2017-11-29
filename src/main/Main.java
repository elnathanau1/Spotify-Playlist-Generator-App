package main;

import display.*;
import spotifyObjects.*;
import ai.*;


//get OAuth Token here: https://developer.spotify.com/web-api/console/get-album/

public class Main {
	public static String accessToken = "BQD4c83UNHR0MxafH7ef2BpGVbx7u8K0R3umNJcjMobkXbS79CfqJjFELMtIeXtPi1JpUT4klKj7NPMY7WxlD2b9wem6OP4uUWx9Ve1H12zqJ6q6wFHyReCbvORq5AyCFCYZyRXTYgxt3kLUNOt8RJFgUG68M2_r_XehnLftYKtP1RFHX8jIqZfLSv7dlGJ5Juuj1BwmgREPcds2KZrfjdiShWME2Be45ZXTM6mrzKpwXYFG-hoDj3N88VWiqh3GKAmDNBTyvdkb";
	
	public static void main(String[] args) {
		
		Track root = API.getTrack("spotify:track:5pLpkaIRobcvPnUmclNv6o", accessToken);
		
		Agent agent = new Agent(root, accessToken);
		
		Playlist newPlaylist = agent.createPlaylist(50, "TestPlaylist");

		API.playbackPlayPlaylist(newPlaylist.uri, accessToken);
		
		
	}
}
