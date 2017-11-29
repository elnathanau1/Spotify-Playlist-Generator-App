package spotifyObjects;

import java.util.Arrays;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Artist Class
 * @author eau
 *
 */

@JsonIgnoreProperties(ignoreUnknown=true)
public class Artist {
	//Information about the followers of the artist.
	@JsonProperty("followers")
	public Followers followers;

	//A list of the genres the artist is associated with. For example: "Prog Rock", "Post-Grunge". (If not yet classified, the array is empty.) 
	@JsonProperty("genres")
	public String[] genres;

	//A link to the Web API endpoint providing full details of the artist.
	@JsonProperty("href")
	public String href;

	//The Spotify ID for the artist. 
	@JsonProperty("id")
	public String id;

	//Images of the artist in various sizes, widest first.
	@JsonProperty("images")
	public Image[] images;

	//The name of the artist
	@JsonProperty("name")
	public String name;

	//The popularity of the artist. The value will be between 0 and 100, with 100 being the most popular. The artist's popularity is calculated from the popularity of all the artist's tracks.
	@JsonProperty("popularity")
	public int popularity;

	//The object type: "artist"
	@JsonProperty("type")
	public String type;

	//The Spotify URI for the artist.
	@JsonProperty("uri")
	public String uri;


	public Artist() {
		//Empty, Artist is constructed through Jackson
	}

	public String toString() {
		String returnString = "";
		returnString += "Name: " + name + "\n";

		return returnString;
	}

	public String toFullString() {
		String returnString = "";
		returnString += "Name: " + name + "\n";
		returnString += "uri: " + uri + "\n";
		returnString += "type: " + type + "\n";
		returnString += "id: " + id + "\n";
		returnString += "href: " + href + "\n";
		returnString += "popularity: " + popularity + "\n";
		returnString += "genres: " + Arrays.toString(genres) + "\n";

		return returnString;
	}

}
