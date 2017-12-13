package display;

import java.util.ArrayList;

import spotifyObjects.*;

/**
 * Class to hold all the data in the GUI that might want to be used across classes
 * @author eau
 *
 */
public class GUIData {
	public Playlist playlist;
	public ArrayList<Track> potentialTracks;
	public ArrayList<Track> addedTracks;
	public String selectedTrackName;
	public String selectedTrackArtist;
	public String selectedTrackAlbum;
	public String selectedTrackURI;
	public String selectedTrackGenres;
	public boolean playbackPlaying;
	public ArrayList<String> inputGenres;

	public GUIData() {
		playlist = null;
		potentialTracks = new ArrayList<Track>();
		addedTracks = new ArrayList<Track>();
		selectedTrackName = "";
		selectedTrackArtist = "";
		selectedTrackAlbum = "";
		selectedTrackGenres = "";
		selectedTrackURI = "";
		playbackPlaying = false;
		inputGenres = new ArrayList<String>();
	}
}
