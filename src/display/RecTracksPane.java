package display;

import java.awt.*;
import javax.swing.*;

/**
 * Panel that holds both playlist table and playback panel
 * @author eau
 *
 */
public class RecTracksPane extends JPanel{
	private static final long serialVersionUID = 1L;
	public PlaylistTablePanel trackGridPanel;
	public PlaybackPane playbackPane;
	public int mode;					//0 for added tracks, 1 for recommended tracks
	
	/**
	 * Constructor, passes mode to playlist table panel
	 * @param newMode
	 */
	public RecTracksPane(int newMode) {
		super();
		mode = newMode;
		
		initComponents();
		addComponents();
	}
	
	/**
	 * Initializes the components
	 */
	private void initComponents() {
		trackGridPanel = new PlaylistTablePanel();
		playbackPane = new PlaybackPane(mode);
	}
	
	/**
	 * Adds the components in the layout
	 */
	private void addComponents() {
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1.0;
		c.weighty = 0.9;
		c.gridx = 0;
		c.gridy = 0;
		add(trackGridPanel, c);
		
		c.weighty = 0.1;
		c.gridx = 0;
		c.gridy = 1;
		add(playbackPane, c);
	}
	
	/**
	 * Reloads the labels with the proper text when things are changed
	 */
	public void reloadPlaybackText() {
		playbackPane.selectedTrackNameLabel.setText("Name: " + GUI.data.selectedTrackName);
		playbackPane.selectedTrackArtistLabel.setText("Artist: " + GUI.data.selectedTrackArtist);
		playbackPane.selectedTrackAlbumLabel.setText("Album: " + GUI.data.selectedTrackAlbum);
		playbackPane.selectedTrackGenresLabel.setText("Genres: " + GUI.data.selectedTrackGenres);
	}
	
}
