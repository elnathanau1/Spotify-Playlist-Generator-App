package main;

import display.*;
import ai.*;


//get OAuth Token here: https://developer.spotify.com/web-api/console/get-album/

public class Main {
	public static String accessToken = "BQBbE5YCyU_SUdKJMtp7yPcdtMFAqGkzF9NO1YoOOmuBzNt7mgJxxIUnWhfVFpQ6wjP6Ow5NWhqH5U12DhKTQMYsdHwYzGuczUFCtszATmh4RDjoDfEoNIL91cmX28TjmdUd2vqdIDv5vVv_nm6chlpNuPS8XDp96rTPOMOZT77CWTJEDxbrde1Ynp5T2sLOPTbdgy1Yxz5gFJhneihOGfkj2N6HZWYb9EeEUpRzmlbjO0_OnxAwIbZNkV32bd_4qPGg0Zaeyg";
	
	public static Thread t2;
	public static Agent agent;
	
	public static void main(String[] args) {

		agent = new Agent();
		
		GUI gui = new GUI(1250, 1000);
		
		// set up the thread for creating playlists
		t2 = new Thread(new Runnable(){
			public void run() {
				//create the playlist, set the gui data
				GUI.data.playlist = agent.createPlaylist();
				GUI.data.potentialTracks = agent.potentialTracks;
				GUI.data.addedTracks = agent.addedTracks;
				
				//set playback gui data
				GUI.data.selectedTrackName = agent.rootTrack.name;
				GUI.data.selectedTrackArtist = agent.rootTrack.artists[0].name;
				GUI.data.selectedTrackAlbum = agent.rootTrack.album.name;
				GUI.data.selectedTrackURI = agent.rootTrack.uri;
				
				//reload the recommended tracks tab
				GUI.panelTwo.trackGridPanel.reloadData(GUI.data.addedTracks);
				GUI.panelThree.trackGridPanel.reloadData(GUI.data.potentialTracks);
				
				//let user know that other tabs are now open
				GUI.unlockTabs();
				GUI.panelOne.statusTextField.setText("Playlist created. Tabs unlocked");
			}
		}); 	
		
		
	}
}
