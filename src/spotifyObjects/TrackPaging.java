package spotifyObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class TrackPaging {
	@JsonProperty("items")
	public Track[] items;
	
	public TrackPaging() {
		
	}
}
