package spotifyObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class TopTracks {
	
	@JsonProperty("tracks")
	public Track[] tracks;
	
	public TopTracks() {
		
	}
}
