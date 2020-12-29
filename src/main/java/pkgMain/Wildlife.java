package pkgMain;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.application.Application;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//import javafx.scene.control.ScrollPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/** Wildlife Class to handle WildLife data
 * @author Madi Freed
 */
public class Wildlife{
	
	String img_path;
	public Image img;
	public ImageView iv;
	String name;
	String wildlifeSpecifics;
	ArrayList<String> wildlifeInfo;
	ArrayList<String> specificsList;

	/** Wildlife Class's constructor to initialize wildlife's elements
	 * @author Madi Freed
	 * @param List of string for wildlife's elements
	 */
	Wildlife(ArrayList<String> wildlifeInfo) {
		//System.out.println("wildlife created");
		this.wildlifeInfo = wildlifeInfo;
		//System.out.println("wildlifeInfo set");
		this.name = wildlifeInfo.get(0);
		//System.out.println("wildlife name set");
		this.img_path = "/" + wildlifeInfo.get(1).replace("\\","/");
		//System.out.println("wildlife img path set");
		this.img = new Image(this.img_path);
		//System.out.println("wildlife Image set");
		this.iv = new ImageView(this.img);
		//System.out.println("    " + this.name);
		//System.out.println("    " + this.img_path);
		this.wildlifeSpecifics = wildlifeInfo.get(2);
		specificsToList();
	}
	
	/** specificsToList converts the String data from the Wildlife csv into a list of specific wildlife
	 * @author Madi Freed
	 */
	public void specificsToList() {
		this.specificsList = new ArrayList<String>(Arrays.asList(this.wildlifeSpecifics.split("/")));			
	}
	
	/** Printing name of current's wildlife's animal
	 * @author Madi Freed
	 */
	public void printWildlife() {
		System.out.println(this.name);
	}
	
}
