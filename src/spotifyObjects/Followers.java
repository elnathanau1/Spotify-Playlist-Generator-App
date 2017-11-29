package spotifyObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Followers {
	@JsonProperty("href")
	public String href;

	@JsonProperty("total")
	public int total;
	
	public Followers() {
		
	}
}
