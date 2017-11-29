package spotifyObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Image class
 * @author eau
 *
 */

@JsonIgnoreProperties(ignoreUnknown=true)
public class Image {

	@JsonProperty("height")
	public int height;

	@JsonProperty("url")
	public String url;
	
	@JsonProperty("width")
	public int width;
	
	public Image() {
		
	}
	public Image(int height, String url, int width) {
		super();
		this.height = height;
		this.url = url;
		this.width = width;
	}
	
}
