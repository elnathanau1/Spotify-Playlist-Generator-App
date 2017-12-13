package spotifyObjects;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import main.API;


/**
 * Track class
 * @author eau
 *
 */

@JsonIgnoreProperties(ignoreUnknown=true)
public class Track {
	
	@JsonProperty("album")
	public Album album;
	
	@JsonProperty("artists")
	public Artist[] artists;
	
	@JsonProperty("available_markets")
	public String[] available_markets;

	@JsonProperty("disc_number")
	public int disc_number;

	@JsonProperty("duration_ms")
	public int duration_ms;

	@JsonProperty("explicit")
	public boolean explicit;

	@JsonProperty("href")
	public String href;

	@JsonProperty("id")
	public String id;

	@JsonProperty("is_playable")
	public boolean is_playable;

	@JsonProperty("name")
	public String name;
	
	@JsonProperty("popularity")
	public int popularity;

	@JsonProperty("preview_url")
	public String preview_url;

	@JsonProperty("track_number")
	public int track_number;

	@JsonProperty("type")
	public String type;

	@JsonProperty("uri")
	public String uri;
	
	public AudioFeatures audioFeatures;
	
	public double heuristicValue;
	
	public String[] genres;
	
	public int artistDepth;
	
	public Track() {
		
	}
	
	public String toString() {
		String returnString = "";
		returnString += "Name: " + name + "\n";
		returnString += "Artist: " + getArtistsString() + "\n";
		returnString += "Album: " + album.name + "\n";
		
		return returnString;
	}
	
	private String getArtistsString() {
		String returnString = "[";
		for(int i = 0; i < artists.length; i++) {
			returnString += artists[i].name;
			if(i < artists.length - 1) {
				returnString += ", ";
			}
		}
		returnString += "]";
		
		return returnString;
	}
	
	public String toFullString() {
		String returnString = toString();
		returnString += "Available Markets: " + Arrays.toString(available_markets) + "\n";
		returnString += "Disc Number: " + disc_number + "\n";
		returnString += "Track_number: " + track_number + "\n";
		returnString += "Duration (ms): " + duration_ms + "\n";
		returnString += "Explicit: " + explicit + "\n";
		returnString += "href: " + href + "\n";
		returnString += "id: " + id + "\n";
		returnString += "Is playable: " + is_playable + "\n";
		returnString += "Popularity: " + popularity + "\n";
		returnString += "preview_url: " + preview_url + "\n";
		returnString += "type: " + type + "\n";
		returnString += "uri: " + uri + "\n";

		return returnString;
	}

	public void addAudioFeatures(String accessToken) {
		audioFeatures = API.getAudioFeatures(uri, accessToken);
	}
	
	public boolean equals(Track otherTrack) {
		if(uri.equals(otherTrack.uri)) {
			return true;
		}
		else {
			return false;
		}
	}
}
