package spotifyObjects;
import com.fasterxml.jackson.annotation.*;

/**
 * Album class
 * @author eau
 *
 */

@JsonIgnoreProperties(value = { "external_urls", "copyrights", "external_ids", "genres", "label", "popularity", "release_date", "release_date_precision", "tracks"})
public class Album {
	@JsonProperty("album_type")
	public String album_type;

	@JsonProperty("artists")
	public Artist[] artists;

	@JsonProperty("available_markets")
	public String[] available_markets;

	@JsonProperty("href")
	public String href;

	@JsonProperty("id")
	public String id;

	@JsonProperty("images")
	public Image[] images;
	
	@JsonProperty("name")
	public String name;

	@JsonProperty("type")
	public String type;

	@JsonProperty("uri")
	public String uri;
	
	public Album() {
		
	}
	
	public Album(String albumType, Artist[] artists, String[] available_markets, String href, String id, Image[] images,
			String name, String type, String string) {
		super();
		this.album_type = albumType;
		this.artists = artists;
		this.available_markets = available_markets;
		this.href = href;
		this.id = id;
		this.images = images;
		this.name = name;
		this.type = type;
		this.uri = string;
	}
	
	
}
