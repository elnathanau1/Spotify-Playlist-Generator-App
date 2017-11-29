package main;


/**
 * Object that represents the identifier for spotify URI (ex: spotify:user:125980338:playlist:7mL6o1kKqrpBm52wkuzoqj)
 * @author eau
 *
 */
public class SpotifyURI {
	public String user;
	public String playlist;
	public String artist;
	public String album;
	public String track;
	
	/**
	 * Constructor
	 * @param uri
	 */
	public SpotifyURI(String uri) {
		parseUri(uri);
	}
	
	/**
	 * Decodes the uri code, sets the variables to the respective strings
	 * @param uri
	 */
	private void parseUri(String uri) {
		String[] inputArray = uri.split(":");
		for(int i = 0; i < inputArray.length; i++) {
			String temp = inputArray[i];
			if(temp.equals("user")) {
				user = inputArray[i+1];
			}
			else if(temp.equals("playlist")) {
				playlist = inputArray[i+1];
			}
			else if(temp.equals("artist")) {
				artist = inputArray[i+1];
			}
			else if(temp.equals("album")) {
				album = inputArray[i+1];
			}
			else if(temp.equals("track")) {
				track = inputArray[i+1];
			}
		}
	}
}
