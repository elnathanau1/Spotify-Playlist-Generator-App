package spotifyObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class SeveralAudioFeatures {
	
	@JsonProperty("audio_features")
	public AudioFeatures[] audio_features;
	
	public SeveralAudioFeatures() {
		
	}
}
