package pkgMain;

import java.io.FileOutputStream;

import java.io.ObjectOutputStream;
import java.util.ArrayList;

import java.util.HashMap;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/** Garden Class to handle methods dealing with plants
 * @author Chris Tiso and Geoff Bonnanzio
 */

public class Garden implements java.io.Serializable{
		int length;
		int width;
		double size;
		String shading;
		String soil_moist;
		String soil_ph;
		HashMap<String,Plant> chosenPlants;
		HashMap<ImageView,Plant> selectPlants;
		Image garden;
		ArrayList<Plant> saved;

		ArrayList<Plant> gardenPlantsList;
		HashMap<String,Wildlife> gardenWildlife;
		

		
		/** Constructor for garden.Called From model constructor
		 * @author Chris Tiso and Geoff Bonnanzio
		 */		
	
		Garden(int length, int width){
			this.length = length;
			this.width = width;
			chosenPlants = new HashMap<String,Plant>();
			
			selectPlants=new HashMap<ImageView,Plant>();
			saved=new ArrayList<Plant>();

			gardenPlantsList = new ArrayList<Plant>();
			gardenWildlife = new HashMap<String,Wildlife>();
		}
		
		
		
		/** Place plant Function from ASCII garden. Needs to be updated to work with new application
		 * @author Chris Tiso and Geoff Bonnanzio
		 */
		
		boolean placePlant(HashMap<String,Plant> plantMap,String name,int x,int y){
			//returns true if plant is added successfully
			//if(checkLocation(x,y) == true) {
			//	return false;
			//}
			//else {
			Plant tmpPlant = plantMap.get(name);
			chosenPlants.put(name,tmpPlant);
			return true;
			
		}
		
		
		/** Adds Plant a HashMap for use from other functions, and to arrayList that is going to be saved
		 * @author Chris Tiso
		 * @author Geoffrey Bonnanzio
		 * @param X and Y Locations, and the plant
		 */
		void addPlant(double x,double y, Plant p) {
			p.setX(x);
			p.setY(y);
			selectPlants.put(p.iv, p);
			saved.add(new Plant(p.name,p.xloc,p.yloc,p.scrollPaneShift,p.plantInfo,p.sci_name,p.shading,p.flowering_period,p.soil_ph,p.wildlife,p.wildlifeList,p.soil_moist,p.area));
			gardenPlantsList.add(p);
		}
		
		/** Adds plants to HashMap, called when loading from save files
		 * @author Chris Tiso 
		 * @author Geoffrey Bonnanzio
		 * @param X and Y Locations, and the plant with image view
		 */
		void addPlant2(double x,double y, Plant p,ImageView iv) {
			//p.setX(x);
			//p.setY(y);
			selectPlants.put(iv, p);
				
		}
		
		/** addWildlife puts a name key with a wildlife object in a HashMap
		 * @author Madi Freed
		 * @param String, Wildlife
		 */	
		void addWildlife(String name, Wildlife wild) {
			gardenWildlife.put(name, wild);
		}
		
		/** removeAllWildlife clears the gardenWildlife list
		 * @author Madi Freed
		 */
		void removeAllWildlife() {
			gardenWildlife.clear();
		}
		
		/** When a plant that is already on the garden is moved, the locations are updated
		 * @author Chris Tiso and Geoff Bonnanzio
		 * @param X and Y Locations of Plant
		 * @param Plant that is being moved on the garden
		 * 
		 */
		void movePlant(double x,double y, Plant pOnGarden) {
			int index=0;

			for(Plant P:saved) {
				if(P.xloc==pOnGarden.xloc&&P.yloc==pOnGarden.yloc&&P.name==P.name) {
					P.setX(x);
					P.setY(y);
				}
			}

			pOnGarden.setX(x);
			pOnGarden.setY(y);
			
			
		}
	
		
		
}
