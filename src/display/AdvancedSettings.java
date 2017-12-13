package display;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;

import main.*;
import spotifyObjects.*;

public class AdvancedSettings extends JPanel{
	public JTabbedPane tabPane;
	public GenresPanel genresPanel;
	public HeuristicSettings heuristicSettingsPanel;

	public AdvancedSettings() {
		super();
		
		initComponents();
		addComponents();
	}
	
	private void initComponents() {
		genresPanel = new GenresPanel();
		heuristicSettingsPanel = new HeuristicSettings();
		tabPane = new JTabbedPane();
		
	}
	
	private void addComponents() {
		tabPane.add("Genre Settings", genresPanel);
		tabPane.add("Heuristic Settings", heuristicSettingsPanel);
		add(tabPane);
	}

}
