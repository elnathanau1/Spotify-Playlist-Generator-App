package display;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import main.*;
import spotifyObjects.*;

public class StartPane extends JPanel{
	private static final long serialVersionUID = 1L;
	public JLabel rootLabel;
	public JLabel accessLabel;
	public JLabel nameLabel;
	public JLabel numTracksLabel;
	public JLabel descriptionLabel;
	public JCheckBox publicCheckBox;
	public JCheckBox collabCheckBox;
	public JButton createButton;
	public JTextField rootTextField;
	public JTextField accessTextField;
	public JTextField nameTextField;
	public JTextField numTracksTextField;
	public JTextField descriptionTextField;
	public JTextField statusTextField;

	/**
	 * Constructor
	 */
	public StartPane() {
		super();

		initComponents();
		addActionListener();
		addComponents();
	}

	/**
	 * Initializes the components
	 */
	private void initComponents() {
		rootLabel = new JLabel("Root Track: ");
		accessLabel = new JLabel("Access Token: ");
		nameLabel = new JLabel("Playlist Name: ");
		numTracksLabel = new JLabel("Number of Tracks: ");
		descriptionLabel = new JLabel("Playlist Description: ");

		publicCheckBox = new JCheckBox("Public");
		collabCheckBox = new JCheckBox("Collaborative");

		rootTextField = new JTextField("Spotify URI of root track");
		accessTextField = new JTextField("Acquired at https://developer.spotify.com");
		nameTextField = new JTextField("[insert name here]");
		descriptionTextField = new JTextField("[insert description here]");
		numTracksTextField = new JTextField("[insert num tracks here]");
		statusTextField = new JTextField("Status: Tabs locked until playlist created");
		statusTextField.setEditable(false);

		createButton = new JButton("Create Playlist!");
	}

	/**
	 * Adds the actionlisteners for any buttons
	 */
	private void addActionListener() {
		createButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				statusTextField.setText("Status: Playlist being created...");
				Main.accessToken = accessTextField.getText();
				String rootTrackURI = rootTextField.getText();
				String name = nameTextField.getText();
				String desc = descriptionTextField.getText();
				int numTracks = Integer.parseInt(numTracksTextField.getText());
				boolean publicCheck = publicCheckBox.isSelected();			//add check, cannot be public can collab
				boolean collabCheck = collabCheckBox.isSelected();

				Track root = API.getTrack(rootTrackURI, Main.accessToken);
				if(root == null) {
					statusTextField.setText("Status: root track could not be acquired");
				}
				else {
					createButton.setEnabled(false);
					Main.agent.setRootTrack(root);
					Main.agent.setAccessToken(Main.accessToken);
					Main.agent.setPlaylistSettings(numTracks, name, publicCheck, collabCheck, desc);
					Main.t2.start();
					
				}

			}

		});
	}

	/**
	 * Adds the components to the layout
	 */
	private void addComponents() {
		setLayout(new GridLayout(7, 2));
		add(rootLabel);
		add(rootTextField);
		add(accessLabel);
		add(accessTextField);
		add(nameLabel);
		add(nameTextField);
		add(numTracksLabel);
		add(numTracksTextField);
		add(descriptionLabel);
		add(descriptionTextField);
		add(publicCheckBox);
		add(collabCheckBox);
		add(statusTextField);
		add(createButton);
	}

}
