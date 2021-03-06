package display;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import spotifyObjects.*;

/**
 * Panel class that holds the table of tracks
 * @author eau
 *
 */
public class PlaylistTablePanel extends JPanel{
	/**
	 * NOTE TO SELF: WHEN CHANGING COLUMNS, MAKE SURE THE FIX THE INDICES IN reloadData(), Actionlistener, and setTable()
	 * Sorting for tempo still broken
	 * Selected track only works when sorted by heuristic value
	 */
	private static final long serialVersionUID = 1L;
	private String[] columnNames = { "Title", "Artist", "Album", "Heuristic Value", "Popularity", "Tempo", "Valence", "Energy", "Genres", "Spotify URI"};
	private Object[][] data;
	private JTable table;
	private JScrollPane scrollPane;
	public ArrayList<Track> tracks;

	/**
	 * Constructor
	 */
	public PlaylistTablePanel() {
		super(new GridLayout(1, 0));

		data = new Object[0][columnNames.length];
		tracks = new ArrayList<Track>();

		table = new JTable(data, columnNames);
		setTable();

		//Create the scroll pane and add the table to it.
		scrollPane = new JScrollPane(table);

		//Add the scroll pane to this panel.
		add(scrollPane);

	}

	/**
	 * Load the table with a new set of tracks
	 * @param newTracks
	 */
	public void reloadData(ArrayList<Track> newTracks) {
		tracks = newTracks;
		reloadData();
	}

	/**
	 * Loads the table with the tracks already saved (could be changed from outside source)
	 */
	public void reloadData() {
		int totalTracks = tracks.size();
		data = new Object[totalTracks][columnNames.length];
		for(int i = 0; i < totalTracks; i++) {
			Track track = tracks.get(i);
			data[i][0] = track.name;
			if(track.artists != null) {
				data[i][1] = track.artists[0].name;				//just using first artist atm
			}
			if(track.album != null) {
				data[i][2] = track.album.name;
			}
			data[i][3] = Math.round(track.heuristicValue * 1000)/1000.0;
			data[i][4] = track.popularity;
			if(track.audioFeatures != null) {
				data[i][5] = track.audioFeatures.tempo;		//sorting for tempo not working
				data[i][6] = track.audioFeatures.valence;
				data[i][7] = track.audioFeatures.energy;
			}
			if(track.genres != null) {
				data[i][8] = Arrays.toString(track.genres);
			}
			data[i][9] = track.uri;
		}

		remove(scrollPane);
		table = new JTable(data, columnNames);
		setTable();

		//add row listeners
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				//change gui selected track data
				GUI.data.selectedTrackURI = (String) data[table.getSelectedRow()][9];
				GUI.data.selectedTrackName = (String) data[table.getSelectedRow()][0];
				GUI.data.selectedTrackArtist = (String) data[table.getSelectedRow()][1];
				GUI.data.selectedTrackAlbum = (String) data[table.getSelectedRow()][2];
				GUI.data.selectedTrackGenres = (String) data[table.getSelectedRow()][8];
				GUI.panelTwo.reloadPlaybackText();
				GUI.panelThree.reloadPlaybackText();
			}	
		});

		//Create the scroll pane and add the table to it.
		scrollPane = new JScrollPane(table);
		add(scrollPane);

	}

	/**
	 * method to change visible settings
	 */
	private void setTable() {
		table.removeColumn(table.getColumnModel().getColumn(9));
		table.setAutoCreateRowSorter(true);
		//		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		//		table.getColumnModel().getColumn(1).setPreferredWidth(50);
		//		table.getColumnModel().getColumn(2).setPreferredWidth(50);
		//		table.getColumnModel().getColumn(3).setPreferredWidth(6);
		//		table.getColumnModel().getColumn(4).setPreferredWidth(6);
		//		table.getColumnModel().getColumn(5).setPreferredWidth(6);
		//		table.getColumnModel().getColumn(6).setPreferredWidth(6);
		//		table.getColumnModel().getColumn(7).setPreferredWidth(300);
	}

}
