package spotifyObjects;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Artist Class
 * @author eau
 *
 */
@JsonIgnoreProperties(value = { "external_urls", "followers", "genres", "images", "popularity"})
public class Artist {
	@JsonProperty("href")
	public String href;

	@JsonProperty("id")
	public String id;

	@JsonProperty("name")
	public String name;

	@JsonProperty("type")
	public String type;

	@JsonProperty("uri")
	public String uri;
	
	public Artist() {
		
	}
	public Artist(String href, String id, String name, String type, String uri) {
		super();
		this.href = href;
		this.id = id;
		this.name = name;
		this.type = type;
		this.uri = uri;
	}
	
}
