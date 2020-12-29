package pkgMain;

import java.util.HashMap;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;


/**
 * This class is used to associate a plant in the garden with the ImageView of that plant
 * with buttons so that on a button press the object can be passed and handled.
 * @author Geoffrey Bonnanzio
 */
public class ImageButton{
	
	
	public Button mainButton;
	public Plant plant;
	public ImageView iv;
	
	//constructor
	public ImageButton(Button btn,ImageView iv) {
		this.mainButton = btn;
		this.iv = iv;
	}
	/** findPlantFromIV determines the plant object within the current garden 
	 * that is associated with the ImageView of that object and assigns it
	 * to the plant property 
	 * @param plantMap  HashMap that has keys as each ImageView in the current garden
	 * with the plant as the key value 
	 * @author Geoffrey Bonnanzio
	 */
	public void findPlantFromIV(HashMap<ImageView,Plant> plantMap) {
		plant = plantMap.get(iv);
	}
}