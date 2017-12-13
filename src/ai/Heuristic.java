package ai;

import java.util.ArrayList;
import java.util.Arrays;

import display.GUI;
import spotifyObjects.*;
import main.*;

/**
 * Class with methods to determine the heuristic value of a track
 * @author eau
 *
 */
public class Heuristic {
	//order of sliders in settings arraylist is the order here
	public static double maxGenrePoints = 2;
	public static double maxTempoPoints = 3;
	public static double maxValencePoints = 5;
	public static double maxDancabilityPoints = 3;
	public static double maxAcousticnessPoints = 3;
	public static double maxEnergyPoints = 3;
	public static double maxNoise = 1;
	public static double maxPopularityPoints = 2;
	public static double maxIndexPoints = 2;
	
	public static double maxVariance = 0.5;
	
	/**
	 * Uses utility functions to determine how many points for heuristic
	 * @param rootTrack
	 * @param testTrack
	 * @return
	 */
	public static double getHeuristic(Track rootTrack, Track testTrack) {
		double returnValue = 0.0;

		returnValue += getGenrePoints(testTrack);
		returnValue += getTempoPoints(rootTrack, testTrack);
		returnValue += getEnergyPoints(rootTrack, testTrack);
		returnValue += getValencePoints(rootTrack, testTrack);
		returnValue += getDancabilityPoints(rootTrack, testTrack);
		returnValue += getAcousticnessPoints(rootTrack, testTrack);
		returnValue += testTrack.popularity/100.0 * maxPopularityPoints;
		returnValue += (Main.agent.artistDepth - testTrack.artistDepth)/Main.agent.artistDepth * maxIndexPoints;
		returnValue += Math.random() * maxNoise;

		return returnValue;
	}

	

	/**
	 * Checks how many of the selected genres are in testTrack
	 * @param testTrack
	 * @return
	 */
	private static double getGenrePoints(Track testTrack) {
		double points = 0.0;
		if(testTrack.genres != null) {
			for(String genre : testTrack.genres) {
				if(GUI.data.inputGenres.contains(genre)) {
					points += maxGenrePoints/GUI.data.inputGenres.size();
				}
			}
		}
		return points;
	}
	
	/**
	 * Finds difference in tempo, assigns point value
	 * @param rootTrack
	 * @param testTrack
	 * @return
	 */
	private static double getTempoPoints(Track rootTrack, Track testTrack) {
		double points = 0.0;
		
		if(rootTrack.audioFeatures != null && testTrack.audioFeatures != null) {
			double rootTempo = rootTrack.audioFeatures.tempo;
			double testTempo = testTrack.audioFeatures.tempo;
			double diff = Math.abs(rootTempo - testTempo);
			double maxDiff = 60;
			points += (maxDiff - diff)/maxDiff * maxTempoPoints;
			if(points < 0) {
				points = 0;
			}
		}
		
		return points;
	}
	
	/**
	 * Finds difference in energy, assigns point value
	 * @param rootTrack
	 * @param testTrack
	 * @return
	 */
	private static double getEnergyPoints(Track rootTrack, Track testTrack) {
		double points = 0.0;
		
		if(rootTrack.audioFeatures != null && testTrack.audioFeatures != null) {
			double rootEnergy = rootTrack.audioFeatures.energy;
			double testEnergy = testTrack.audioFeatures.energy;
			double diff = Math.abs(rootEnergy - testEnergy);
			double maxDiff = maxVariance;
			points += (maxDiff - diff)/maxDiff * maxEnergyPoints;
			if(points < 0) {
				points = 0;
			}
		}
		
		return points;
	}
	
	/**
	 * Finds difference in valence, assigns point value
	 * @param rootTrack
	 * @param testTrack
	 * @return
	 */
	private static double getValencePoints(Track rootTrack, Track testTrack) {
		double points = 0.0;
		
		if(rootTrack.audioFeatures != null && testTrack.audioFeatures != null) {
			double rootValence = rootTrack.audioFeatures.valence;
			double testValence = testTrack.audioFeatures.valence;
			double diff = Math.abs(rootValence - testValence);
			double maxDiff = maxVariance;
			points += (maxDiff - diff)/maxDiff * maxValencePoints;
			if(points < 0) {
				points = 0;
			}
		}
		
		return points;
	}
	
	/**
	 * Finds difference in dancability, assigns point value
	 * @param rootTrack
	 * @param testTrack
	 * @return
	 */
	private static double getDancabilityPoints(Track rootTrack, Track testTrack) {
		double points = 0.0;
		
		if(rootTrack.audioFeatures != null && testTrack.audioFeatures != null) {
			double rootDancability = rootTrack.audioFeatures.danceability;
			double testDancability = testTrack.audioFeatures.danceability;
			double diff = Math.abs(rootDancability - testDancability);
			double maxDiff = maxVariance;
			points += (maxDiff - diff)/maxDiff * maxDancabilityPoints;
			if(points < 0) {
				points = 0;
			}
		}
		
		return points;
	}
	
	/**
	 * Finds difference in acousticness, assigns point value
	 * @param rootTrack
	 * @param testTrack
	 * @return
	 */
	private static double getAcousticnessPoints(Track rootTrack, Track testTrack) {
		double points = 0.0;
		
		if(rootTrack.audioFeatures != null && testTrack.audioFeatures != null) {
			double rootAcousticness = rootTrack.audioFeatures.acousticness;
			double testAcousticness = testTrack.audioFeatures.acousticness;
			double diff = Math.abs(rootAcousticness - testAcousticness);
			double maxDiff = maxVariance;
			points += (maxDiff - diff)/maxDiff * maxAcousticnessPoints;
			if(points < 0) {
				points = 0;
			}
		}
		
		return points;
	}
}
