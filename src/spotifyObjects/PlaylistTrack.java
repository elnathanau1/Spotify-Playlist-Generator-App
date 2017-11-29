package spotifyObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class PlaylistTrack{
	@JsonProperty("added_at")
	public String added_at;
	
	@JsonProperty("added_by")
	public User added_by;

	@JsonProperty("is_local")
	public boolean is_local;

	@JsonProperty("track")
	public Track track;
	
	public PlaylistTrack() {
		
	}

}
