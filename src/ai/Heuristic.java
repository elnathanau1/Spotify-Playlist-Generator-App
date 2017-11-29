package ai;

import java.util.Arrays;

import spotifyObjects.*;

/**
 * Class with methods to determine the heuristic value of a track
 * @author eau
 *
 */
public class Heuristic {
	public static double getHeuristic(Track rootTrack, Track testTrack) {
		double returnValue = 0.0;

		//Get values
		double diffAudioFeatures = getAudioFeaturesDiff(rootTrack, testTrack);
		double diffGenres = getGenreDiff(rootTrack, testTrack);

		//weigh them
		double audioFeaturesValue = -1.0;
		double genresValue = 1.0;


		//sum values
		returnValue += diffAudioFeatures * audioFeaturesValue;


		return returnValue;
	}

	private static double getAudioFeaturesDiff(Track rootTrack, Track testTrack) {
		if(testTrack.audioFeatures == null) {
			System.out.println(testTrack.name + " does not have any audio features");
			return 0;
		}
		double returnValue = 0.0;

		//Get difference in values
		double diffAcousticness = Math.abs(rootTrack.audioFeatures.acousticness - testTrack.audioFeatures.acousticness);
		double diffDanceability = Math.abs(rootTrack.audioFeatures.danceability - testTrack.audioFeatures.danceability);
		double diffEnergy = Math.abs(rootTrack.audioFeatures.energy - testTrack.audioFeatures.energy);
		double diffInstrumentalness = Math.abs(rootTrack.audioFeatures.instrumentalness - testTrack.audioFeatures.instrumentalness);
		double diffLiveness = Math.abs(rootTrack.audioFeatures.liveness - testTrack.audioFeatures.liveness);
		double diffLoudness = Math.abs(rootTrack.audioFeatures.loudness - testTrack.audioFeatures.loudness);
		double diffMode = Math.abs(rootTrack.audioFeatures.mode - testTrack.audioFeatures.mode);
		double diffSpeechiness = Math.abs(rootTrack.audioFeatures.speechiness - testTrack.audioFeatures.speechiness);
		double diffTempo = Math.abs(rootTrack.audioFeatures.tempo - testTrack.audioFeatures.tempo);
		double diffValence = Math.abs(rootTrack.audioFeatures.valence - testTrack.audioFeatures.valence);

		//Weight each one depending on value
		double acousticnessValue = 2.0;
		double danceabilityValue = 2.0;
		double energyValue = 2.0;
		double instrumentalnessValue = 2.0;
		double livenessValue = 2.0;
		double loudnessValue = 2.0;
		double modeValue = 2.0;
		double speechinessValue = 2.0;
		double tempoValue = 1.0/200;
		double valenceValue = 1.0;

		//sum it all
		returnValue += diffAcousticness * acousticnessValue;
		returnValue += diffDanceability * danceabilityValue;
		returnValue += diffEnergy * energyValue;
		returnValue += diffInstrumentalness * instrumentalnessValue;
		returnValue += diffLiveness * livenessValue;
		returnValue += diffLoudness * loudnessValue;
		returnValue += diffMode * modeValue;
		returnValue += diffSpeechiness * speechinessValue;
		returnValue += diffTempo * tempoValue;
		returnValue += diffValence * valenceValue;

		return returnValue;
	}

	private static double getGenreDiff(Track rootTrack, Track testTrack) {
		double numGenresSame = 0;
		
		String[] rootGenres = rootTrack.artists[0].genres;
		String[] testGenres = testTrack.artists[0].genres;
		if(rootGenres != null & testGenres != null) {
			for(String genre : testGenres) {
				if(Arrays.binarySearch(rootGenres, genre) > 0) {
					numGenresSame++;
				}
			}
		}

		return numGenresSame*0.5;
	}
}
