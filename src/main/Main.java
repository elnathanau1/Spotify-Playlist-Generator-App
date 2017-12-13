package main;

import display.*;
import spotifyObjects.Artist;
import spotifyObjects.Track;
import ai.*;


//get OAuth Token here: https://developer.spotify.com/web-api/console/get-album/

public class Main {
	public static String accessToken = "BQD9B-8PhF8K8GkRFZaUXZfCtaO8hTR_PWSdoakhqNlJrK8hK2eC3laW_JZ7FyAgehOMMNASWahJfv7n5C6Llj3BKvwTZbloSWyLeIkmhcJLbOavVDLWfV7EAzymqnwdU8r2aDyoLEcQsDHrhG2CpTWfFfSFF2YUl6-j3VSopmGwVkgEmhGgqHJXxc6QbBgKP8TmPFmHDFpozViW9WM950-PML5x_yAaIS_W4mn9XP7dMKrPeymAeV5SHyMPCrfPhyPCHxNOZQ";
	
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
