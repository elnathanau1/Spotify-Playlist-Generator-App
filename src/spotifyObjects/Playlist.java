package spotifyObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Playlist {
	@JsonProperty("collaborative")
	public boolean collaborative;

	@JsonProperty("description")
	public String description;

	@JsonProperty("followers")
	public Followers followers;

	@JsonProperty("href")
	public String href;

	@JsonProperty("id")
	public String id;

	@JsonProperty("images")
	public Image[] images;

	@JsonProperty("name")
	public String name;

	@JsonProperty("owner")
	public User owner;

	@JsonProperty("snapshot_id")
	public String snapshot_id;

	@JsonProperty("tracks")
	public Paging tracks;

	@JsonProperty("type")
	public String type;

	@JsonProperty("uri")
	public String uri;
	
	
	public Playlist() {
		
	}
	
	public String toString() {
		String returnString = "";
		returnString += "Name: " + name + "\n";
		returnString += "Owner: " + owner.display_name + "\n";
		
		return returnString;
	}

}