package pkgMain;

import java.util.ArrayList;
import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.ImageView;


/** Model Class to handle calculation for the score method. Not finished as it hasn't been
 *  decided how this will work yet
 * @author Madi Freed
 * @author Cheickna Sacko
 * @author Prithvi Arora
 */
public class ScoreModel {
	
	public String score(Controller imc) {
		int score = 0;
		int newSeason = 0;
		int sizeCount = 0;
		int sizeCounter = 0;

		ArrayList<String> seasons = new ArrayList<String>();
		ArrayList<Plant> plants = new ArrayList<Plant>();
		ArrayList<Double> sizes = new ArrayList<Double>();

		for(Plant p: imc.model.currGarden.saved) {
			seasons.add(p.flowering_period);
			sizes.add(p.area);
		}
		
		for(String season: seasons) {
			if(seasons.get(0) != season) {
				newSeason += 1;
			}

		}
		
		for(Double size: sizes) {
			if(sizes.get(0) != size) {
				sizeCount += 1;
			}

		}

		
		if (newSeason <=1) {
			score +=1;
		}
		else if (newSeason >1 && newSeason <3 ) {
			score+=3;
		}
		else if (newSeason == 4) {
			score+=4;
		}
		if (sizeCount <=1) {
			score +=1;
		}
		else if (sizeCount >1 && sizeCount <3 ) {
			score+=3;
		}
		else if (sizeCount == 4) {
			score+=4;
		}
		String finalScore = Integer.toString(score);
		
		return finalScore;
				
	}
	
	/**
	 * Method used to calculate plant's score
	 * @param imc  Controller Instance
	 * @param garden  the object representing the garden
	 * @param garden2 ImageView representing the garden
	 * @author Madi Freed
	 * @author Cheickna Sacko
	 * @author Prithvi Arora
	 * @return String representing the score result
	 */
	public String calcScore(Controller imc, Object garden, ImageView garden2) {	
		
		int score;
		String poor;
		String good;
		String Excellent;
		int newSeason = 0;
		int sizeCount = 0;

		ArrayList<String> seasons = new ArrayList<String>();
		ArrayList<Plant> plants = new ArrayList<Plant>();
		
		for(Plant p: imc.model.currGarden.saved) {
			seasons.add(p.flowering_period);
		}
				
		for(String season: seasons) {
			if(seasons.get(0) != season) {
				newSeason += 1;
			}

		}
		
		poor = "You only have 1 season in which your plants will bloom. This will make a poor garden. 1pt";
		good = "You have 2-3 seasons in which your plants will bloom. This will make a good garden. 3pt";
		Excellent = "Your garden will have plants in bloom during all seasons. This will make an excellent garden! 4pt";
		
		if (newSeason <=1 ) {
			return poor;
		}
		else if (newSeason >1 && newSeason <3 ) {

			return good;
		}
		else {
			return Excellent;
		}
	}
	
	
	
	/**
	 * Method used to calculate plant's score
	 * @param imc  Controller Instance
	 * @param garden  the object representing the garden
	 * @param garden2 ImageView representing the garden
	 * @return String representing the score result
	 */
public String calcSize(Controller imc, Object garden, ImageView garden2) {	
		
		int score;
		String poor;
		String good;
		String Excellent;
		int newSeason = 0;
		int sizeCount = 0;

		ArrayList<String> seasons = new ArrayList<String>();
		ArrayList<Plant> plants = new ArrayList<Plant>();
		ArrayList<Double> sizes = new ArrayList<Double>();

		
		for(Plant p: imc.model.currGarden.saved) {
			sizes.add(p.area);
		}
				
		for(Double size: sizes) {
			if(sizes.get(0) != size) {
				sizeCount += 1;
			}

		}
		
		poor = "You only have 1 size of plants. This will make a poor garden. 1pt";
		good = "You have 2-3 different sizes of plants. This will make a good garden. 3pt";
		Excellent = "You have 4 different sizes of plants. This will make an excellent garden! 4pt";
		
		if (sizeCount <=1 ) {
			return poor;
		}
		else if (sizeCount >1 && sizeCount <3 ) {

			return good;
		}
		else {
			return Excellent;
		}
	}

	
}
