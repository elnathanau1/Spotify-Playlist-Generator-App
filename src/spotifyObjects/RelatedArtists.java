package spotifyObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class RelatedArtists {
	
	@JsonProperty("artists")
	public Artist[] artists;
	
	public RelatedArtists() {
		
	}
}
