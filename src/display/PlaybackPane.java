package display;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import main.*;
import spotifyObjects.*;

public class PlaybackPane extends JPanel{
	private static final long serialVersionUID = 1L;
	public JLabel selectedTrackNameLabel;
	public JLabel selectedTrackArtistLabel;
	public JLabel selectedTrackAlbumLabel;
	public JLabel selectedTrackGenresLabel;
	public JButton playButton;
	public JButton pauseButton;
	public JButton addButton;
	public JButton removeButton;
	public int mode;						//0 for added tracks, 1 for recommended tracks


	/**
	 * Constructor. Uses mode to determine how to set up this pane
	 * @param newMode
	 */
	public PlaybackPane(int newMode) {
		super();
		mode = newMode;

		initComponents();
		addActionListener();
		addComponents();
	}

	/**
	 * Initializes each component
	 */
	private void initComponents() {
		selectedTrackNameLabel = new JLabel("Name: " + GUI.data.selectedTrackName);
		selectedTrackArtistLabel = new JLabel("Artist: " + GUI.data.selectedTrackArtist);
		selectedTrackAlbumLabel = new JLabel("Album: " + GUI.data.selectedTrackAlbum);
		selectedTrackGenresLabel = new JLabel("Genres: " + GUI.data.selectedTrackGenres);

		playButton = new JButton("Play");			//maybe replace these with images later?
		pauseButton = new JButton("Pause");
		addButton = new JButton("Add to playlist");
		removeButton = new JButton("Remove from playlist");
	}

	/**
	 * Adds the action listeners for each button
	 */
	private void addActionListener() {
		playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<String> queue = new ArrayList<String>();
				queue.add(GUI.data.selectedTrackURI);
				API.playbackPlayTracks(queue, Main.accessToken);
				GUI.data.playbackPlaying = true;
				pauseButton.setText("Pause");
			}

		});

		pauseButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(GUI.data.playbackPlaying) {
					API.playlistPause(Main.accessToken);
					pauseButton.setText("Resume");
				}
				else {
					API.playbackResume(Main.accessToken);
					pauseButton.setText("Pause");
				}
				GUI.data.playbackPlaying = !GUI.data.playbackPlaying;
			}

		});

		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<Track> song = new ArrayList<Track>();
				song.add(API.getTrack(GUI.data.selectedTrackURI, Main.accessToken));
				API.addTracksToPlaylist(GUI.data.playlist.uri, song, Main.accessToken);

				for(int i = 0; i < GUI.panelThree.trackGridPanel.tracks.size(); i++) {
					Track testTrack = GUI.panelThree.trackGridPanel.tracks.get(i);
					if(testTrack.uri == GUI.data.selectedTrackURI) {
						GUI.panelTwo.trackGridPanel.tracks.add(testTrack);
						GUI.panelThree.trackGridPanel.tracks.remove(i);
						GUI.panelTwo.trackGridPanel.reloadData();
						GUI.panelThree.trackGridPanel.reloadData();
					}
				}
			}
		});

		removeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				API.removeTrack(GUI.data.playlist.uri, GUI.data.selectedTrackURI, Main.accessToken);

				for(int i = 0; i < GUI.panelTwo.trackGridPanel.tracks.size(); i++) {
					Track testTrack = GUI.panelTwo.trackGridPanel.tracks.get(i);
					if(testTrack.uri == GUI.data.selectedTrackURI) {
						GUI.panelTwo.trackGridPanel.tracks.remove(i);
						GUI.panelTwo.trackGridPanel.reloadData();
					}
				}

			}
		});
	}


	/**
	 * Adds the components into the panel
	 */
	private void addComponents() {
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.VERTICAL;
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 0;
		add(selectedTrackNameLabel, c);

		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 1;
		add(selectedTrackArtistLabel, c);


		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 2;
		add(selectedTrackAlbumLabel, c);
		//		
		//		c.gridwidth = 3;
		//		c.gridx = 1;
		//		c.gridy = 3;
		//		add(selectedTrackGenresLabel, c);

		c.gridx = 2;
		c.gridy = 0;
		add(playButton, c);

		c.gridx = 2;
		c.gridy = 1;
		add(pauseButton, c);

		c.gridx = 2;
		c.gridy = 2;
		if(mode == 1) {
			add(addButton, c);
		}
		else if(mode == 0) {
			add(removeButton, c);
		}


	}
}
