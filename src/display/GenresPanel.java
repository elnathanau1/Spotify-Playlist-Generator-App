package display;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;

import main.*;
import spotifyObjects.*;

public class GenresPanel extends JPanel{
	public JTextField inputTrack;
	public JTextField accessToken;
	public JButton setTrack;
	public ArrayList<JCheckBox> genres;
	public JButton addGenres;

	public GenresPanel() {
		super();

		initComponents();
		addActionListener();
		addComponents();
	}

	private void initComponents() {
		inputTrack = new JTextField("Input Spotify URI here");
		accessToken = new JTextField("Input access token here");
		setTrack = new JButton("Get genres");
		genres = new ArrayList<JCheckBox>();
		addGenres = new JButton("Add these genres");
	}

	private void addActionListener() {
		setTrack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				genres = new ArrayList<JCheckBox>();

				String inputTrackURI = inputTrack.getText();
				Main.accessToken = accessToken.getText();

				GUI.panelOne.rootTextField.setText(inputTrack.getText());
				GUI.panelOne.accessTextField.setText(accessToken.getText());

				Track inputTrack = API.getTrack(inputTrackURI, Main.accessToken);
				Artist inputArtist = API.getArtist(inputTrack.artists[0].uri, Main.accessToken);

				if(inputArtist.genres != null) {
					for(String genre : inputArtist.genres) {
						genres.add(new JCheckBox(genre));
					}
				}

				reloadComponents();
				setTrack.setEnabled(false);
			}
		});

		addGenres.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				for(JCheckBox checkBox : genres) {
					if(checkBox.isSelected()) {
						GUI.data.inputGenres.add(checkBox.getText());
					}
				}
				addGenres.setEnabled(false);
				GUI.panelFive.tabPane.setSelectedIndex(1);
				System.out.println(GUI.data.inputGenres.toString());
			}

		});
	}

	private void addComponents() {
		this.setPreferredSize(new Dimension(500,500));
		setLayout(new GridLayout(15, 1));
		add(inputTrack);
		add(accessToken);
		add(setTrack);

	}

	private void reloadComponents() {
		for(int i = 0; i < 11; i++) {
			if(i < genres.size()) {
				add(genres.get(i));
			}
		}
		add(addGenres);
	}

}
