package pkgMain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/** Plant class to handle the info. Constructors, getters and setters
 * @author Chris Tiso and Geoff Bonannzio 
 */

public class Plant implements Serializable{
	//is this the same file
		public Double area;
		public double xLen; //ft
		public double yLen; //ft
		public double xloc;
		public double yloc;
		public Image img;
		public ImageView iv;
		String name;
		String sci_name;
		String img_path;
		String shading;
		String soil_moist;
		String flowering_period;
		String soil_ph;
		String wildlife;
		int scroll=110;
		ArrayList<String> wildlifeList;
		public double numXPixels;
		public double numYPixels;
		ArrayList<String> plantInfo;
		boolean inGarden = false;
		public double scrollPaneShift;
		//constructors
		
		/** Plant's constructor used to setup each plant's properties.
		 * @param String the plant's name
		 * @param double variable for the plant's x's location
		 * @param double variable for the plant's y's location
		 * @param double variable for the value of the plant's shift distance
		 * @param list of plant's properties (info)
		 * @param string for the plant's scientific name
		 * @param string for the plant's flowering period
		 * @param string for the soil pH value
		 * @param string for the wildlife element
		 * @param list for all the elements in wildlife
		 * @param string for the soil's moist state
		 * @author Chris Tiso
		 * @return None
		 */
		Plant(String name,double xloc2,double yloc2,double shift,ArrayList<String>plantInfo,
		String sci_name,
		String shading,
		String flowering_period,
		String soil_ph,
		String wildlife,
		ArrayList<String> wildlifeList, String soil_moist,double Are){
			this.name = name;
			this.xloc = xloc2;
			this.yloc = yloc2;
			this.scrollPaneShift=shift;
			this.plantInfo = plantInfo;
			this.name = name;
			this.sci_name = sci_name;
			this.shading = shading;
			this.soil_moist = soil_moist;
			this.flowering_period = flowering_period;
			this.soil_ph = soil_ph;
			this.wildlife = wildlife;
			this.wildlifeList=wildlifeList;
			this.area=Are;
		}
		
		/** Plant's constructor setting up specific plant's properties given list of info.
		 * @author Chris Tiso and Geoff Bonannzio 
		 * @param List containing plant's properties
		 * @return None
		 */
		Plant(ArrayList<String> plantInfo){
			//System.out.println("1");
			this.plantInfo = plantInfo;
			//System.out.println("2");
			this.name = plantInfo.get(0);
			//System.out.println("3");
			this.sci_name = plantInfo.get(1);
			this.img_path = "/" + plantInfo.get(2).replace("\\","/");
			//System.out.println("4");
			this.img = new Image(this.img_path);
			this.iv = new ImageView(this.img);
			//System.out.println("5");
			this.shading = plantInfo.get(3);
			this.soil_moist = plantInfo.get(4);
			this.flowering_period = plantInfo.get(5);
			this.soil_ph = plantInfo.get(6);
			//System.out.println("6");
			this.wildlife = plantInfo.get(7);	
			//System.out.println(plantInfo.get(8));
			this.area = Double.valueOf(plantInfo.get(8));
			//System.out.println(this.area);
			wildlifeToList();
		}
		
		/** Method used to determine length of area
		 * @author Chris Tiso and Geoff Bonannzio 
		 */
		public void determineLengths() {
			//System.out.println("!"+area);
			yLen = Math.sqrt(area/1.5);
			xLen = area/yLen;
		
		}
	   /** Getter for Plant Info
		* @author Geoff Bonnanzio
		* @return ArrayList of String for plant info
		*/
		public ArrayList<String> getPlantInfo() {
			return this.plantInfo;
		}
	   /** Getter For Wildlife info
		* @author Madi Freed
		* @return String for wildlife
		*/
		public String getPlantWildlife() {
			return this.wildlife;
		}
		
	   /** method used to attribute current wildlife's list to created array with space
		* delimiter
		*/
		public void wildlifeToList() {
			this.wildlifeList = new ArrayList<String>(Arrays.asList(this.wildlife.split(" ")));			
		}
		
	   /** Printing Function for Plants
		* @author Chris Tiso and Geoff Bonnanzio
		*/
		public String toString() {
			return this.name;
		}
		
	   /** Sets X Location of plant
		* @param newX  double for new x location
		* @author Chris Tiso and Geoff Bonnanzio
		*/
		public void setX(double newX) {
			this.xloc = newX;
		}
		
	   /** Sets Y Location of plant
		* @param newY   double for new y location
		* @author Chris Tiso and Geoff Bonnanzio
		*/
		public void setY(double newY) {
			this.yloc = newY;
		}
		
	   /** Sets how much an imageView should be shifted depending on its location in scrollPane
		* @param shift int representing shift value
		* @author Chris Tiso
		*/
		public void setScrollShift(int shift) {
			this.scrollPaneShift=shift*scroll;
		}
		
	   /** Sets parameter inGarden
		* @author Geoff Bonnanzio
		*/
		public void setInGarden() {
			this.inGarden = true;
		}
	   /** Used to compare plants when adding to hashMap
		* @param other Object used for comparison
		* @author Chris Tiso and Geoff Bonnanzio
		*/
		@Override
		public boolean equals(Object other) {
			Plant plant = (Plant)other;
			return this.name.equals(plant.name);
		}
	   /** Override of hashcode function
		* @author Chris Tiso and Geoff Bonnanzio
		* @return overriden hashcode value
		*/
		@Override
		  public int hashCode(){
		    return this.name.hashCode();
		  }
		
	}
