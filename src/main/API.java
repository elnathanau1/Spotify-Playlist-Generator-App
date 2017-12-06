package main;

import java.io.*;
import java.net.*;
import java.util.*;

import org.apache.http.*;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.*;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import spotifyObjects.*;

/**
 * Class that deals with making URL connections
 * @author eau
 *
 */
public class API {
	//	private 	String client_id = "8f67fb6690ca4bc480f7b6661933028c";
	//	private String client_secret = "5a18b096a3404aacb09821aa96aee806";

	/**
	 * Returns Album object from SpotifyURI string
	 * @param input
	 * @param accessToken
	 * @return
	 */
	public static Album getAlbum(String input, String accessToken){
		SpotifyURI spotifyURI = new SpotifyURI(input);

		try {
			HttpGet httpget = new HttpGet("https://api.spotify.com/v1/albums/" + spotifyURI.album);
			httpget.setHeader("Accept", "application/json");
			httpget.setHeader("Authorization", "Bearer " + accessToken);

			CloseableHttpClient httpclient = HttpClients.createDefault();
			CloseableHttpResponse response = httpclient.execute(httpget);

			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream instream = entity.getContent();
				ObjectMapper objectMapper = new ObjectMapper();

				Album album = objectMapper.readValue(instream, Album.class);

				return album;
			}
			response.close();
		} 
		catch(Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * Returns Artist object from SpotifyURI string
	 * @param input
	 * @param accessToken
	 * @return
	 */
	public static Artist getArtist(String input, String accessToken) {
		SpotifyURI spotifyURI = new SpotifyURI(input);
		try {
			URL url = new URL("https://api.spotify.com/v1/artists/" + spotifyURI.artist);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Authorization", "Bearer " + accessToken);

			ObjectMapper objectMapper = new ObjectMapper();
			InputStream inputStream = conn.getInputStream();

			Artist artist = objectMapper.readValue(inputStream, Artist.class);

			return artist;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Returns array of Artist objects from the related artists of the Artist represented by the SpotifyURI input
	 * @param input
	 * @param accessToken
	 * @return
	 */
	public static Artist[] getRelatedArtists(String input, String accessToken) {
		SpotifyURI spotifyURI = new SpotifyURI(input);

		try {
			HttpGet httpget = new HttpGet("https://api.spotify.com/v1/artists/" + spotifyURI.artist + "/related-artists");
			httpget.setHeader("Accept", "application/json");
			httpget.setHeader("Authorization", "Bearer " + accessToken);

			CloseableHttpClient httpclient = HttpClients.createDefault();
			CloseableHttpResponse response = httpclient.execute(httpget);

			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream inputStream = entity.getContent();
				//				printStream(inputStream);
				ObjectMapper objectMapper = new ObjectMapper();

				Artist[] artist = objectMapper.readValue(inputStream, RelatedArtists.class).artists;

				return artist;
			}
			response.close();
		} 
		catch(Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Returns array of Track objects from the top tracks of the Artist represented by the SpotifyURI input
	 * @param input
	 * @param accessToken
	 * @return
	 */
	public static Track[] getArtistTopTracks(String input, String accessToken) {
		SpotifyURI spotifyURI = new SpotifyURI(input);

		try {
			HttpGet httpget = new HttpGet("https://api.spotify.com/v1/artists/" + spotifyURI.artist + "/top-tracks?country=US");
			httpget.setHeader("Accept", "application/json");
			httpget.setHeader("Authorization", "Bearer " + accessToken);

			CloseableHttpClient httpclient = HttpClients.createDefault();
			CloseableHttpResponse response = httpclient.execute(httpget);

			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream inputStream = entity.getContent();
				ObjectMapper objectMapper = new ObjectMapper();

				Track[] tracks = objectMapper.readValue(inputStream, TopTracks.class).tracks;

				return tracks;
			}
			response.close();
		} 
		catch(Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Returns an arrayList of AudioFeature objects from an ArrayList of Tracks
	 * @param tracks
	 * @param accessToken
	 * @return
	 */
	public static Artist[] getSeveralArtists(ArrayList<String> uris, String accessToken) {
		try {
			String url = "https://api.spotify.com/v1/artists?ids=";
			for(int i = 0; i < uris.size(); i++) {
				url += uris.get(i);
				if(i < uris.size() - 1) {
					url += ",";
				}
			}
			HttpGet httpget = new HttpGet(url);
			httpget.setHeader("Accept", "application/json");
			httpget.setHeader("Authorization", "Bearer " + accessToken);

			CloseableHttpClient httpclient = HttpClients.createDefault();
			CloseableHttpResponse response = httpclient.execute(httpget);

			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream inputStream = entity.getContent();
				ObjectMapper objectMapper = new ObjectMapper();

				Artist[] artists = objectMapper.readValue(inputStream, RelatedArtists.class).artists;
				System.out.println("!!!");
				return artists;
			}
			response.close();
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Returns Track object from SpotifyURI string
	 * @param input
	 * @param accessToken
	 * @return
	 */
	public static Track getTrack(String input, String accessToken) {
		SpotifyURI spotifyURI = new SpotifyURI(input);
		try {
			URL url = new URL("https://api.spotify.com/v1/tracks/" + spotifyURI.track);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Authorization", "Bearer " + accessToken);

			ObjectMapper objectMapper = new ObjectMapper();
			InputStream inputStream = conn.getInputStream();

			Track track = objectMapper.readValue(inputStream, Track.class);

			return track;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Returns AudioFeatures object of Track represented by SpotifyURI input
	 * @param input
	 * @param accessToken
	 * @return
	 */
	public static AudioFeatures getAudioFeatures(String input, String accessToken) {
		SpotifyURI spotifyURI = new SpotifyURI(input);
		try {
			URL url = new URL("https://api.spotify.com/v1/audio-features/" + spotifyURI.track);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Authorization", "Bearer " + accessToken);

			ObjectMapper objectMapper = new ObjectMapper();
			InputStream inputStream = conn.getInputStream();

			AudioFeatures audioFeatures = objectMapper.readValue(inputStream, AudioFeatures.class);

			return audioFeatures;


		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * Returns an arrayList of AudioFeature objects from an ArrayList of Tracks
	 * @param tracks
	 * @param accessToken
	 * @return
	 */
	public static AudioFeatures[] getSeveralAudioFeatures(ArrayList<Track> tracks, String accessToken) {
		try {
			String url = "https://api.spotify.com/v1/audio-features?ids=";
			for(int i = 0; i < tracks.size(); i++) {
				url += tracks.get(i).id;
				if(i < tracks.size() - 1) {
					url += ",";
				}
			}
			HttpGet httpget = new HttpGet(url);
			httpget.setHeader("Accept", "application/json");
			httpget.setHeader("Authorization", "Bearer " + accessToken);

			CloseableHttpClient httpclient = HttpClients.createDefault();
			CloseableHttpResponse response = httpclient.execute(httpget);

			HttpEntity entity = response.getEntity();
			if (entity != null) {
				InputStream inputStream = entity.getContent();
				ObjectMapper objectMapper = new ObjectMapper();

				AudioFeatures[] audioFeatures = objectMapper.readValue(inputStream, SeveralAudioFeatures.class).audio_features;

				return audioFeatures;
			}
			response.close();
		} 
		catch(Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Returns Playlist object from SpotifyURI string
	 * @param input
	 * @param accessToken
	 * @return
	 */
	public static Playlist getPlaylist(String input, String accessToken) {
		SpotifyURI spotifyURI = new SpotifyURI(input);
		try {
			URL url = new URL("https://api.spotify.com/v1/users/" + spotifyURI.user + "/playlists/" + spotifyURI.playlist);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Authorization", "Bearer " + accessToken);

			ObjectMapper objectMapper = new ObjectMapper();
			InputStream inputStream = conn.getInputStream();

			Playlist playlist = objectMapper.readValue(inputStream, Playlist.class);

			return playlist;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

	/**
	 * Returns User object from SpotifyURI string
	 * @param input
	 * @param accessToken
	 * @return
	 */
	public static User getUser(String input, String accessToken) {
		SpotifyURI spotifyURI = new SpotifyURI(input);
		try {
			URL url = new URL("https://api.spotify.com/v1/users/" + spotifyURI.user);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Authorization", "Bearer " + accessToken);

			ObjectMapper objectMapper = new ObjectMapper();
			InputStream inputStream = conn.getInputStream();

			User user = objectMapper.readValue(inputStream, User.class);

			return user;


		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * Returns CurrentUser (which extends User) object, from the user that accessToken is from
	 * @param accessToken
	 * @return
	 */
	public static CurrentUser getCurrentUser(String accessToken) {
		try {
			URL url = new URL("https://api.spotify.com/v1/me");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Authorization", "Bearer " + accessToken);

			ObjectMapper objectMapper = new ObjectMapper();
			InputStream inputStream = conn.getInputStream();

			CurrentUser currentUser = objectMapper.readValue(inputStream, CurrentUser.class);

			return currentUser;


		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * Creates a Playlist with name, default settings for rest of parameters. Returns the Playlist object of new playlist
	 * @param input
	 * @param accessToken
	 * @param name
	 * @return
	 */
	public static Playlist createPlaylist(String input, String accessToken, String name) {
		return createPlaylist(input, accessToken, name, true, false, "");
	}

	/**
	 * Creates a Playlist w/ the given parameters, returns the Playlist object of new playlist
	 * @param input
	 * @param accessToken
	 * @param name
	 * @param isPublic
	 * @param collaborative
	 * @param description
	 * @return
	 */
	public static Playlist createPlaylist(String input, String accessToken, String name, boolean isPublic, boolean collaborative, String description) {
		//Decode Spotify URI
		SpotifyURI spotifyURI = new SpotifyURI(input);

		try {
			//Set up url, set headers
			HttpPost httpPost = new HttpPost("https://api.spotify.com/v1/users/" + spotifyURI.user + "/playlists");
			httpPost.setHeader("Authorization", "Bearer " + accessToken);
			httpPost.setHeader("Content-Type", "application/json");

			//Set up request body in JSON format
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode objectNode = mapper.createObjectNode();
			objectNode.put("name", name);
			objectNode.put("public", isPublic);
			objectNode.put("collaborative", collaborative);
			objectNode.put("description", description);

			//Set request body
			StringEntity stringEntity = new StringEntity(objectNode.toString());
			httpPost.setEntity(stringEntity);

			//Create client, run.
			CloseableHttpClient httpclient = HttpClients.createDefault();
			CloseableHttpResponse response = httpclient.execute(httpPost);

			//Get response
			HttpEntity responseEntity = response.getEntity();

			//Read input, convert from JSON to playlist
			if (responseEntity != null) {
				InputStream instream = responseEntity.getContent();
				ObjectMapper objectMapper = new ObjectMapper();

				Playlist playlist = objectMapper.readValue(instream, Playlist.class);

				return playlist;
			}
			response.close();
		} 
		catch(Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * Takes in an array of Track objects, and adds them to playlist represented by SpotifyURI input
	 * @param input
	 * @param tracksToAdd
	 * @param accessToken
	 */
	public static void addTracksToPlaylist(String input, ArrayList<Track> tracksToAdd, String accessToken) {
		//Decode Spotify URI
		SpotifyURI spotifyURI = new SpotifyURI(input);

		//rip the URIs from tracksToAdd, put them into a new String[]
		String[] trackURIs = new String[tracksToAdd.size()];
		for(int i = 0; i < tracksToAdd.size(); i++) {
			trackURIs[i] = tracksToAdd.get(i).uri;
		}

		try {
			//Set up url, set headers
			HttpPost httpPost = new HttpPost("https://api.spotify.com/v1/users/" + spotifyURI.user + "/playlists/" + spotifyURI.playlist + "/tracks");
			httpPost.setHeader("Authorization", "Bearer " + accessToken);
			httpPost.setHeader("Content-Type", "application/json");

			//Set up request body in JSON format
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode objectNode = mapper.createObjectNode();
			ArrayNode array = mapper.valueToTree(trackURIs);
			objectNode.putArray("uris").addAll(array);

			//Set request body
			StringEntity stringEntity = new StringEntity(objectNode.toString());
			httpPost.setEntity(stringEntity);

			//Create client, run.
			CloseableHttpClient httpclient = HttpClients.createDefault();
			CloseableHttpResponse response = httpclient.execute(httpPost);

			response.close();
		} 
		catch(Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Takes in an array of Track objects, and adds them to playlist represented by SpotifyURI input
	 * @param input
	 * @param tracksToAdd
	 * @param accessToken
	 */
	public static void removeTrack(String input, String trackURI, String accessToken) {
		//Decode Spotify URI
		SpotifyURI spotifyURI = new SpotifyURI(input);

		try {
			//Set up url, set headers
			HttpDeleteWithBody httpDelete = new HttpDeleteWithBody("https://api.spotify.com/v1/users/" + spotifyURI.user + "/playlists/" + spotifyURI.playlist + "/tracks");
			httpDelete.setHeader("Authorization", "Bearer " + accessToken);
			httpDelete.setHeader("Content-Type", "application/json");

			//Set up request body in JSON format
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode objectNode = mapper.createObjectNode();
			objectNode.put("uri", trackURI);
			ObjectNode[] objectNodes = {objectNode};

			ArrayNode array = mapper.valueToTree(objectNodes);
			objectNode.putArray("tracks").addAll(array);

			//Set request body
			StringEntity stringEntity = new StringEntity(objectNode.toString());
			httpDelete.setEntity(stringEntity);

			//Create client, run.
			CloseableHttpClient httpclient = HttpClients.createDefault();
			CloseableHttpResponse response = httpclient.execute(httpDelete);

			response.close();
		} 
		catch(Exception e) {
			e.printStackTrace();
		}

	}

	
	
	/**
	 * Skips to the next track in Spotify's playback (in other app)
	 * @param accessToken
	 */
	public static void playbackSkipNext(String accessToken) {
		try {
			//Set up url, set headers
			HttpPost httpPost = new HttpPost("https://api.spotify.com/v1/me/player/next");
			httpPost.setHeader("Authorization", "Bearer " + accessToken);

			//Create client, run.
			CloseableHttpClient httpclient = HttpClients.createDefault();
			CloseableHttpResponse response = httpclient.execute(httpPost);

			response.close();
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Skips to the previous track in Spotify's playback (in other app)
	 * @param accessToken
	 */
	public static void playbackSkipPrev(String accessToken) {
		try {
			//Set up url, set headers
			HttpPost httpPost = new HttpPost("https://api.spotify.com/v1/me/player/previous");
			httpPost.setHeader("Authorization", "Bearer " + accessToken);

			//Create client, run.
			CloseableHttpClient httpclient = HttpClients.createDefault();
			CloseableHttpResponse response = httpclient.execute(httpPost);

			response.close();
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Plays the playlist/album that playlistURI represents in Spotify's playback (in other app)
	 * @param playlistURI
	 * @param accessToken
	 */
	public static void playbackPlayPlaylist(String playlistURI, String accessToken) {
		try {
			//Set up url, set headers
			HttpPut httpPut = new HttpPut("https://api.spotify.com/v1/me/player/play");
			httpPut.setHeader("Authorization", "Bearer " + accessToken);

			//Set up request body in JSON format
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode objectNode = mapper.createObjectNode();
			objectNode.put("context_uri", playlistURI);

			//Set request body
			StringEntity stringEntity = new StringEntity(objectNode.toString());
			httpPut.setEntity(stringEntity);

			//Create client, run.
			CloseableHttpClient httpclient = HttpClients.createDefault();
			CloseableHttpResponse response = httpclient.execute(httpPut);

			response.close();
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static void playbackResume(String accessToken) {
		try {
			//Set up url, set headers
			HttpPut httpPut = new HttpPut("https://api.spotify.com/v1/me/player/play");
			httpPut.setHeader("Authorization", "Bearer " + accessToken);

			//Create client, run.
			CloseableHttpClient httpclient = HttpClients.createDefault();
			CloseableHttpResponse response = httpclient.execute(httpPut);

			response.close();
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Plays the tracks within queueURIs
	 * @param playlistURI
	 * @param accessToken
	 */
	public static void playbackPlayTracks(ArrayList<String> queueURIs, String accessToken) {
		//rip the URIs from tracksToAdd, put them into a new String[]
		String[] trackURIs = new String[queueURIs.size()];
		for(int i = 0; i < queueURIs.size(); i++) {
			trackURIs[i] = queueURIs.get(i);
		}
		try {
			//Set up url, set headers
			HttpPut httpPut = new HttpPut("https://api.spotify.com/v1/me/player/play");
			httpPut.setHeader("Authorization", "Bearer " + accessToken);

			//Set up request body in JSON format
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode objectNode = mapper.createObjectNode();
			ArrayNode array = mapper.valueToTree(trackURIs);
			objectNode.putArray("uris").addAll(array);

			//Set request body
			StringEntity stringEntity = new StringEntity(objectNode.toString());
			httpPut.setEntity(stringEntity);

			//Create client, run.
			CloseableHttpClient httpclient = HttpClients.createDefault();
			CloseableHttpResponse response = httpclient.execute(httpPut);

			response.close();
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void playlistPause(String accessToken) {
		try {
			//Set up url, set headers
			HttpPut httpPut = new HttpPut("https://api.spotify.com/v1/me/player/pause");
			httpPut.setHeader("Authorization", "Bearer " + accessToken);

			//Create client, run.
			CloseableHttpClient httpclient = HttpClients.createDefault();
			CloseableHttpResponse response = httpclient.execute(httpPut);

			response.close();
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Method used for testing purposes, prints JSON from input stream
	 * @param instream
	 */
	private static void printStream(InputStream instream) {
		Scanner s = new Scanner(instream).useDelimiter("\\A");
		String result = s.hasNext() ? s.next() : "";
		System.out.println(result);
	}
}

//from https://daweini.wordpress.com/2013/12/20/apache-httpclient-send-entity-body-in-a-http-delete-request/
class HttpDeleteWithBody extends HttpEntityEnclosingRequestBase {
  public static final String METHOD_NAME = "DELETE";

  public String getMethod() {
      return METHOD_NAME;
  }

  public HttpDeleteWithBody(final String uri) {
      super();
      setURI(URI.create(uri));
  }

  public HttpDeleteWithBody(final URI uri) {
      super();
      setURI(uri);
  }

  public HttpDeleteWithBody() {
      super();
  }
}
