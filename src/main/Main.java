package main;

//get OAuth Token here: https://developer.spotify.com/web-api/console/get-album/

public class Main {
	public static void main(String[] args) {
		String accessToken = "BQCALyKCfhNCf3CXo_UGomZQuO_aPw7BTnfj3lBCTAHi1R9Hvq74tCYdde0siZ33wb7TYiKrFwrZrR1TJapr1zeJnfkqKpkwRTAv1QyYy589L14RDkY6NagePI8OOWDBhD7lC98hng3T_NWn_OTD-X5whkNtIG_gmAabKEHcOge8kfnOICEvfKOvsRBFUVq40RdqElLNnieIyiqv1dmxJNA2NLoMprWSZhdfyTMcI5-cX1lyvOXg5A7PGaUp3hQDkhhnJdteURIl";
		
		API api = new API(accessToken);
		api.getAlbum("spotify:album:34eR6ev1GGZHmxNTEfRobW");
	}
}
