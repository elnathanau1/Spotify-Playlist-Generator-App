package main;

import java.io.*;
import java.net.*;
import com.fasterxml.jackson.databind.*;

import spotifyObjects.*;

/**
 * Class that deals with making URL connections
 * @author eau
 *
 */
public class API {
	private 	String client_id = "8f67fb6690ca4bc480f7b6661933028c";
	private String client_secret = "5a18b096a3404aacb09821aa96aee806";
	private String accessToken;
	
	public API(String token) {
		accessToken = "Bearer " + token;
	}
	
	
	public Album getAlbum(String input) {
		URI uri = new URI(input);
		try {
			URL url = new URL("https://api.spotify.com/v1/albums/" + uri.album);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Authorization", accessToken);
			
			ObjectMapper objectMapper = new ObjectMapper();

			InputStream inputStream = conn.getInputStream();

			Album album = objectMapper.readValue(inputStream, Album.class);
			
			return album;

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
		return null;
	}
	
	public Artist getArtist(String input) {
		URI uri = new URI(input);
		try {
			URL url = new URL("https://api.spotify.com/v1/artists/" + uri.artist);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Authorization", accessToken);
			
			ObjectMapper objectMapper = new ObjectMapper();

			InputStream inputStream = conn.getInputStream();

			Artist artist = objectMapper.readValue(inputStream, Artist.class);
			
			return artist;

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
		return null;
	}
	
	public Track getTrack(String input) {
		URI uri = new URI(input);
		try {
			URL url = new URL("https://api.spotify.com/v1/tracks/" + uri.track);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Authorization", accessToken);
			
			ObjectMapper objectMapper = new ObjectMapper();

			InputStream inputStream = conn.getInputStream();

			Track track = objectMapper.readValue(inputStream, Track.class);
			
			return track;

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}
		return null;
	}
	
}
