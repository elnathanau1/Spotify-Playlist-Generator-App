package spotifyObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Public User
 * @author eau
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class User {
	@JsonProperty("display_name")
	public String display_name;
	
	@JsonProperty("followers")
	public Followers followers;

	@JsonProperty("href")
	public String href;

	@JsonProperty("id")
	public String id;

	@JsonProperty("images")
	public Image[] images;

	@JsonProperty("type")
	public String type;

	@JsonProperty("uri")
	public String uri;
	
	public User() {
		
	}
	
}
