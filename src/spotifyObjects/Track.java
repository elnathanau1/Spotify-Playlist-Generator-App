package spotifyObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Track class
 * @author eau
 *
 */
@JsonIgnoreProperties(value = {"external_ids", "album", "external_urls", "popularity"})
public class Track {
	
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

	@JsonProperty("preview_url")
	public String preview_url;

	@JsonProperty("track_number")
	public int track_number;

	@JsonProperty("type")
	public String type;

	@JsonProperty("uri")
	public String uri;
	
	public Track() {
		
	}
	
	public Track(Artist[] artists, String[] available_markets, int disc_number, int duration_ms, boolean explicit,
			String href, String id, boolean is_playable, String name, String preview_url, int track_number, String type,
			String uri) {
		super();
		this.artists = artists;
		this.available_markets = available_markets;
		this.disc_number = disc_number;
		this.duration_ms = duration_ms;
		this.explicit = explicit;
		this.href = href;
		this.id = id;
		this.is_playable = is_playable;
		this.name = name;
		this.preview_url = preview_url;
		this.track_number = track_number;
		this.type = type;
		this.uri = uri;
	}
}
