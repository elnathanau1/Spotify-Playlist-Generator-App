package display;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;

import ai.Heuristic;
import main.*;
import spotifyObjects.*;

public class HeuristicSettings extends JPanel{
	public ArrayList<JSlider> sliders;
	public ArrayList<JLabel> labels;
	public JButton saveButton;
	
	public HeuristicSettings() {
		super();
		
		initComponents();
		addActionListener();
		addComponents();
	}
	
	private void initComponents() {
		setUpSliders();
		setUpLabels();
		saveButton = new JButton("Save settings");
	}
	
	private void addActionListener(){
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//set values
				Heuristic.maxGenrePoints = sliders.get(0).getValue();
				Heuristic.maxTempoPoints = sliders.get(1).getValue();
				Heuristic.maxValencePoints = sliders.get(2).getValue();
				Heuristic.maxDancabilityPoints = sliders.get(3).getValue();
				Heuristic.maxAcousticnessPoints = sliders.get(4).getValue();
				Heuristic.maxEnergyPoints = sliders.get(5).getValue();
				Heuristic.maxNoise = sliders.get(6).getValue();
				Heuristic.maxPopularityPoints = sliders.get(7).getValue();
				Heuristic.maxIndexPoints = sliders.get(8).getValue();
				Main.agent.artistDepth = sliders.get(9).getValue();
				
				GUI.changeTab(0);
			}
		});
	}
	
	private void setUpSliders() {
		sliders = new ArrayList<JSlider>();
		sliders.add(new JSlider(JSlider.HORIZONTAL, 0, 10, (int) Heuristic.maxGenrePoints)); 
		sliders.add(new JSlider(JSlider.HORIZONTAL, 0, 10, (int) Heuristic.maxTempoPoints)); 
		sliders.add(new JSlider(JSlider.HORIZONTAL, 0, 10, (int) Heuristic.maxValencePoints)); 
		sliders.add(new JSlider(JSlider.HORIZONTAL, 0, 10, (int) Heuristic.maxDancabilityPoints)); 
		sliders.add(new JSlider(JSlider.HORIZONTAL, 0, 10, (int) Heuristic.maxAcousticnessPoints)); 
		sliders.add(new JSlider(JSlider.HORIZONTAL, 0, 10, (int) Heuristic.maxEnergyPoints)); 
		sliders.add(new JSlider(JSlider.HORIZONTAL, 0, 10, (int) Heuristic.maxNoise)); 
		sliders.add(new JSlider(JSlider.HORIZONTAL, 0, 10, (int) Heuristic.maxPopularityPoints)); 
		sliders.add(new JSlider(JSlider.HORIZONTAL, 0, 10, (int) Heuristic.maxIndexPoints)); 
		sliders.add(new JSlider(JSlider.HORIZONTAL, 2, 4, 3)); 
		
		for(JSlider slider : sliders) {
			slider.setMajorTickSpacing(1);
			slider.setPaintLabels(true);
		}
		
	}
	
	private void setUpLabels() {
		labels = new ArrayList<JLabel>();
		labels.add(new JLabel("Genre Points:"));
		labels.add(new JLabel("Tempo Points:"));
		labels.add(new JLabel("Valence Points:"));
		labels.add(new JLabel("Dancability Points:"));
		labels.add(new JLabel("Acousticness Points:"));
		labels.add(new JLabel("Energy Points:"));
		labels.add(new JLabel("Noise Points:"));
		labels.add(new JLabel("Popularity Points:"));
		labels.add(new JLabel("Index Points:"));
		labels.add(new JLabel("Artist Tree Depth: "));
	}
	
	private void addComponents() {
		this.setPreferredSize(new Dimension(500,500));
		setLayout(new GridLayout(sliders.size() + 3, 2));
		for(int i = 0; i < sliders.size(); i++) {
			add(labels.get(i));
			add(sliders.get(i));
		}
		add(saveButton);
	}
}
