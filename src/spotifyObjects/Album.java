package spotifyObjects;
import com.fasterxml.jackson.annotation.*;

/**
 * Album class
 * @author eau
 *
 */

@JsonIgnoreProperties(ignoreUnknown=true)
public class Album {
	//The type of the album: one of "album", "single", or "compilation". 
	@JsonProperty("album_type")
	public String album_type;

	//The artists of the album. Each artist object includes a link in href to more detailed information about the artist.
	@JsonProperty("artists")
	public Artist[] artists;

	//The markets in which the album is available: ISO 3166-1 alpha-2 country codes. Note that an album is considered available in a market when at least 1 of its tracks is available in that market.
	@JsonProperty("available_markets")
	public String[] available_markets;

	//A list of the genres used to classify the album. For example: "Prog Rock", "Post-Grunge". (If not yet classified, the array is empty.) 
	@JsonProperty("genres")
	public String[] genres;

	//A link to the Web API endpoint providing full details of the album.
	@JsonProperty("href")
	public String href;

	//The Spotify ID for the album. 
	@JsonProperty("id")
	public String id;

	//The cover art for the album in various sizes, widest first.
	@JsonProperty("images")
	public Image[] images;

	//The label for the album.
	@JsonProperty("label")
	public String label;

	//The name of the album. In case of an album takedown, the value may be an empty string.
	@JsonProperty("name")
	public String name;

	//The popularity of the album. The value will be between 0 and 100, with 100 being the most popular. The popularity is calculated from the popularity of the album's individual tracks.
	@JsonProperty("popularity")
	public int popularity;

	//The tracks of the album.
	@JsonProperty("tracks")
	public Paging tracks;

	//The object type: "album"
	@JsonProperty("type")
	public String type;

	//The Spotify URI for the album.
	@JsonProperty("uri")
	public String uri;

	public Album() {

	}

	public String toString() {
		String returnString = "";
		returnString += "Name: " + name + "\n";
		if(artists != null) {
			returnString += "Artist: " + getArtistsString() + "\n";
		}

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

}
