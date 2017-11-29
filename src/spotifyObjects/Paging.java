package spotifyObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Paging {
	@JsonProperty("href")
	public String href;
	
	@JsonProperty("items")
	public PlaylistTrack[] items;		//note: supposedly for any object, this is for our purposes

	@JsonProperty("limit")
	public int limit;

	@JsonProperty("next")
	public String next;

	@JsonProperty("offset")
	public int offset;

	@JsonProperty("previous")
	public String previous;

	@JsonProperty("total")
	public int total;
	
	public Paging() {
		
	}
}
