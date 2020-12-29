package pkgMain;
import java.io.BufferedReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

/** This Model class handles the data associated with the program. Upon start 
 *  of the program model will create a HashMap of all plants 
 * with data found in the CSV. The HashMap is where plant information will
 *  continually be pulled from for adding new plants to garden
 * @author Geoffrey Bonnanzio
 */
public class Model implements Serializable{
	int length;
	int height;
	double size;
	private static int numPlantsToLoad = 40;
	double currx=0;
	double curry=0;
	public double xGridBoxLen;
	public double yGridBoxLen;
	public Garden currGarden;
	public Plant currPlant;
	public Plant replacementPlant;
	public int replacementIndx;
	String csvName = "Plant_Data.csv";
	String csvWildlifeName = "Wildlife_Data.csv";
	HashMap<String,Plant> plantMap = readCSV();
	HashMap<String,Wildlife> wildlifeMap = readWildlifeCSV();
	ArrayList<Garden> saveGardens = new ArrayList<Garden>();
	


	/** Model constructor
	 * @author Chris Tiso
	 */
	public Model(){
		currGarden = new Garden(1,1);
	}
	
	
	/** Sets Height and Length for the garden
	 * @param x  length
	 * @param y  height
	 * @author Chris Tiso
	 */
	public void setSize(int x,int y) {
		this.length=x;
		this.height=y;
		this.currGarden.length=x;
		this.currGarden.width=y;
	}
	/** Gets Height
	 * @param none 
	 * @author Chris Tiso
	 */
	int getHeight(){
		return this.height;
	}
	/** Gets Length
	 * @param none 
	 * @author Chris Tiso
	 */
	int getLength(){
		return this.length;
	}
	/** Gets Current X location
	 * @param none
	 * @author Chris Tiso
	 */
	double getX() {
		return currx;
	}
	/** Gets Current Y location
	 * @param none
	 * @author Chris Tiso
	 */
	double getY() {
		return curry;
	}
   /** Sets current x location
	* @param double x
	* @author Chris Tiso
	*/
	void setX(double x) {
		currx=x;
	}
   /** Sets current y location
	* @param double y
	* @author Chris Tiso
	*/
	void setY(double y) {
		curry=y;
	}
	/** Sets Current Plant
	 * @param Plant
	 * @author Geoffrey Bonnanzio 
	 */
	void setCurrPlant(Plant plant) {
		currPlant = plant;
	}
   /** Sets Replacement Index of the Scroll Pane
	* @param int index
	* @author Geoffrey Bonnanzio
	*/
	void setScrollPaneIndex(int indx) {
		replacementIndx = indx;
	}
    /** Sets Conditions of the garden
	* @param String Soil Conditions,String Shading, String Moisture
	* @author Chris Tiso 
	*/
	void setConditions(String a,String b,String c) {
		this.currGarden.soil_ph=a;
		this.currGarden.shading=b;
		this.currGarden.soil_moist=c;
	}
	
	public void setXGridBoxLen(double x) {
		this.xGridBoxLen = x;
	}
	
	public void setYGridBoxLen(double y) {
		this.yGridBoxLen = y;
	}
	/**  makeGarden creates a Garden object with the specified length and width and adds it to
	 *  a list of Gardens to be saved upon creation
	 * @param None
	 * @author Geoffrey Bonnanzio and Chris Tiso
	 */
	Garden makeGarden(int length, int width){
		Garden tmpGarden = new Garden(length,width);
		currGarden = tmpGarden;
		saveGardens.add(tmpGarden);
		return tmpGarden;
	}
	/** makeReplacementPlant gets the plant associated with the scroll pane 
	 * ImageView and creates a duplicate of it to be added to the screen 
	 * also stores the index of the scrollpane image that needs to be replaced
	 * @param indx of scroll pane image that is being replaced 
	 * @param oldPlant a Plant object representing a copy of the plant that will be added to the screen 
	 * @author Geoffrey Bonnanzio
	 */
	public void makeReplacementPlant(int indx,Plant oldPlant) {
		replacementIndx = indx;
		replacementPlant = new Plant(oldPlant.plantInfo);
	}
	
	
	/** readCSV reads the CSV that contains all of the data associated with each 
	 * plant and creates Plant object for each line that it takes in 
	 * @author Geoffrey Bonnanzio
	 * @return Plant object for each line taken in
	 */
	public HashMap<String,Plant> readCSV(){
		HashMap<String,Plant> plantData = new HashMap<String,Plant>();
		try {
			File csvFile = new File(csvName);
			FileReader fr = new FileReader(csvFile);
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			line = br.readLine();
			String delimiter = ",";
			ArrayList<String> currPlantInfo = new ArrayList<String>();
			int count = 0;
			while(line != null) {
				count++;
				if(count>numPlantsToLoad) {break;}
				try {
					currPlantInfo = new ArrayList<String>(Arrays.asList(line.split(delimiter)));
					
					//System.out.println(currPlantInfo);
					Plant tmpPlant = new Plant(currPlantInfo);
					plantData.put(tmpPlant.name,tmpPlant);
				}
				catch(Exception e){
					line = br.readLine();
					continue;
				}
			
				line = br.readLine();
			}	
				br.close();
		}catch (IOException ioe) {
			ioe.printStackTrace();
		}
		
		return plantData;
	}
	/** Saves the arrayList of all the plants on the garden and their locations. 
	 * @param File Name
	 * @author Chris Tiso
	 */
	void Save(String file) {
		try {
			FileOutputStream fos=new FileOutputStream(file);
			ObjectOutputStream oos=new ObjectOutputStream(fos);
			for(Plant p:currGarden.saved) {
				p.img = null;
				p.iv = null;
			}
			oos.writeObject(currGarden.saved);
			oos.close();
			
		}catch(Exception ex) {
			System.out.println("Exception thrown during Save: "+ex.toString());
		} 
	}
	/** Loads all the plants on the garden from the saved ArrayList. Then adds plants to the Hashmap of plants as well as adding the imageviews to the stack pane
	 * @param File Name,Controller Instance
	 * @author Chris Tiso
	 */
	void Load(Controller imc,String file) {
		try {
			FileInputStream fis=new FileInputStream(file);
			ObjectInputStream ois=new ObjectInputStream(fis);
			currGarden.saved.clear();
			currGarden.saved=(ArrayList<Plant>) ois.readObject();
			
			//System.out.println(currGarden.saved);
			currGarden.selectPlants.clear();
			Image image = new Image("/BlankGarden.PNG");
			ImageView center=new ImageView(image);
			center.setFitWidth(800);
            center.setFitHeight(800);
            imc.garden=center;
            //imc.view.mainPane.setCenter(new StackPane());
            imc.view.center.getChildren().add(center);
			
			for(Plant p:currGarden.saved) {
				//Plant cpyPlant = new Plant(p.name,p.xloc,p.yloc,p.scrollPaneShift,p.plantInfo,p.sci_name,p.shading,p.flowering_period,p.soil_ph,p.wildlife,p.wildlifeList,p.soil_moist,p.area);
				//System.out.println(plantMap.get(p.name).iv.getImage());
				String imgPath = plantMap.get(p.name).img_path;
				p.img_path = imgPath;
				//cpyPlant.img = new Image(cpyPlant.img_path);
				Image tmpImage = new Image(imgPath);
				p.iv = new ImageView(tmpImage);
				//System.out.println(p);
				//System.out.println(tmp);
				//ImageView tmp2 = new ImageView(tmp);
				//System.out.println(tmp2);
				//tmp2.setPreserveRatio(true);
				//System.out.println(p.xloc+" "+p.yloc+" "+p.scrollPaneShift);
			    imc.releaseLoad(p.iv,p);
			    
			}
			
			ois.close();
			imc.closeLoadScreen();
			//imc.viewGarden();
			
		}catch(Exception ex) {
			System.out.println("Exception thrown during Load: "+ex.toString());
		} 
	}
	
	public void scoreAlgorithm(Garden g) {}
	/**
	 * readWildlifeCSV reads Wildlife_Data.csv and creates Wildlife objects
	 * @author Madi Freed
	 * @return data containing Wildlife
	 */
	public HashMap<String,Wildlife> readWildlifeCSV(){

		HashMap<String,Wildlife> wildlifeData = new HashMap<String,Wildlife>();
		try {
			File csvFile = new File("Wildlife_Data.csv");
			FileReader fr = new FileReader(csvFile);
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			String delimiter = ",";
			ArrayList<String> currWildlifeInfo = new ArrayList<String>();
			int count = 0;
			while(line != null) {
				try {
					currWildlifeInfo = new ArrayList<String>(Arrays.asList(line.split(delimiter)));
					Wildlife tmpWildlife = new Wildlife(currWildlifeInfo);
					wildlifeData.put(tmpWildlife.name,tmpWildlife);
					line = br.readLine();
					count++;
				}
				catch(Exception e){
					line = br.readLine();
					continue;
				}
				if(count>16) {break;}
			}
			br.close();
		}catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return wildlifeData;
	}
	
	/**
	 * getGardenWildlife adds the wildlife attracted to the current 
	 * plants in the garden to a HashMap
	 * @author Madi Freed
	 */
	public void getGardenWildlife() {
		for (Plant p: currGarden.gardenPlantsList) {
			for (String w: p.wildlifeList) {
				currGarden.addWildlife(w, wildlifeMap.get(w));
			}
		}
	}
	/**
	 * getter for the number of plants loaded into the scroll pane
	 * @author Geoffrey Bonnanzio
	 * @return int for the number of plants loaded into scroll pane
	 */
	public int getNumPlantsLoad() {
		return numPlantsToLoad;
	}



}
