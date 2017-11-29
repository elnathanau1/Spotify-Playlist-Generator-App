package spotifyObjects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Current User
 * @author eau
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class CurrentUser extends User{
	@JsonProperty("birthdate")
	public String birthdate;

	@JsonProperty("country")
	public String country;
	
	@JsonProperty("email")
	public String email;
	
	@JsonProperty("product")
	public String product;	
	
	public CurrentUser() {
		
	}

}
