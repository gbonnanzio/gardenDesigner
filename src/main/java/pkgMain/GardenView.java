package pkgMain;

import java.util.ArrayList;
import java.util.Collection;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;





/** View Class for main garden Screen
 * @author Chris Tiso and Geoff Bonnanzio
 */
public class GardenView {
	//constants 
	private static final int spImgHeight = 100;
	private static final int spImgWidth = 150;
	private static final int scrollPaneSpacing = 10;
	private static final int scrollPaneWidth = 250;
	private static final int plantsToAdd = 40;
	public static final int imgXShift = 575;
	public static final int imgYShift = 350; 
	private static final int startPaneLocX = 100;
	private static final int startPaneLocY = 50;
	private static final double gardenSize = 800.0; //length and width
	private static final double ddButtWidth = 100;
	private static final double ddButtHeight = 50;
	private static final double leftSideSize = 100;
	private final double WIDTH = 1150;
	private final double HEIGHT = 800;
	//properties
	public BorderPane mainPane;
	public StackPane center;
	public ScrollPane scrollPane;
	public VBox spVBox;
	public Scene scene;
	public Stage viewStage;
	public ImageView currIV;
	ArrayList<ImageView> imagesInScrollPane = new ArrayList<ImageView>();
	public boolean dropDownActive = false;
	StackPane dropDownPane;
	ArrayList<Plant> plantsInScrollPane = new ArrayList<Plant>();
	public VBox dropDown;
	public boolean forceExit=false;
	public double xFtPerPix;
	public double yFtPerPix;
	
   /** Constructor to set up scene at the beginning of the runtime
	* @author Chris Tiso and Geoff Bonnanzio
	* @param imc  Controller Instance
	*/
	public GardenView(Controller imc){
	    	// create mainPane and scene
	    	mainPane = new BorderPane();
	    	scrollPane = placePlantImages(imc);
	    	center=new StackPane();
	    	spVBox = new VBox();
		    mainPane.setCenter(center);
	    	scene = new Scene(mainPane, WIDTH, HEIGHT);
	    	
	    	//viewStage.setScene(scene);
	}
	/** Function Called when switching to main garden screen from the controller
	 *@author Chris Tiso 
	 *@param imc  Controller instance
	 */
	public void viewView(Controller imc) {
		imc.primStage.setX(startPaneLocX);
		imc.primStage.setY(startPaneLocY);
		setupPanes(imc);
		
	}
	
   /** Adds Pictures of Images to the Scroll Pane
	*@param imc    Controller instance
	*@author Geoff Bonnanzio
	*@return scrollPane containing images 
	*/
	public ScrollPane placePlantImages(Controller imc) {
			//System.out.println("get here");
			ScrollPane scrollPane = new ScrollPane();
			spVBox = new VBox();
			spVBox.setSpacing(scrollPaneSpacing);
			Collection<Plant> allPlants = (imc.model.plantMap.values());

			int count = 0;

			for(Plant plant:allPlants) {
				count++;
				if(count>plantsToAdd) {
					break;
				}
				// format ImageView for size
				//plant.iv.setPreserveRatio(true);
				plant.iv.setFitWidth(spImgWidth);
			    plant.iv.setFitHeight(spImgHeight);
			    // set ImageView to be draggable and releasable
			    plant.iv.setOnMouseDragged(event -> imc.drag(event));
			    plant.iv.setOnMouseReleased(event -> imc.release(event));
			    // add plants to scrollpane
			    spVBox.getChildren().add(plant.iv);
			    plantsInScrollPane.add(plant);
			    imagesInScrollPane.add(plant.iv);
			    // associate indexing of scrollpane with plant 
				plant.setScrollShift(count-1);
			}
			// StackPane to hold vbox and set as child of scrollpane
			StackPane tmpPane = new StackPane();
			tmpPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
			spVBox.setPrefWidth(scrollPaneSpacing);
			tmpPane.getChildren().add(spVBox);
			StackPane.setAlignment(spVBox,Pos.TOP_LEFT);
			tmpPane.setPrefWidth(scrollPaneWidth);
			scrollPane.setContent(tmpPane);
			scrollPane.setPrefViewportHeight(HEIGHT);
			scrollPane.setFitToWidth(true);
			
			return scrollPane;
	}
		
   /**Makes the scene for the class
	*@author Geoff Bonnanzio and Chris Tiso
	*@param viewImc1    Controller Instance
	*/
	public void setupPanes(Controller viewImc1) {
			
		        mainPane.setLeft(scrollPane);

		        //center=new StackPane(viewImc1.garden); try without garden first
		        //center=new StackPane();
		        //mainPane.setCenter(center);
		        center.getChildren().add(viewImc1.garden);
				
		        Button animal=new Button("WildLife");
				animal.setMaxWidth(Double.MAX_VALUE);
				animal.setOnAction(e->viewImc1.viewWildlife());
				
				Button decor=new Button("Decorations");
				decor.setMaxWidth(Double.MAX_VALUE);
				decor.setOnAction(e->viewImc1.viewDecorScreen());
				
				VBox layout1 = new VBox();
				layout1.setStyle("fx-border-color: red");
				layout1.setAlignment(Pos.BASELINE_RIGHT);
				layout1.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
				
				Button save=new Button("Save Garden");
				save.setMaxWidth(Double.MAX_VALUE);
				save.setOnAction(e->viewImc1.viewSave());
				
				Button load=new Button("Load Save Files");
				load.setMaxWidth(Double.MAX_VALUE);
				load.setOnAction(e->viewImc1.viewLoad());
				
				Button bloom=new Button("Bloom Calendar");
				bloom.setMaxWidth(Double.MAX_VALUE);
				bloom.setOnAction(e->viewImc1.viewBloom());
				
				
				Button scoreB = new Button("Score");
				scoreB.setMaxWidth(Double.MAX_VALUE);
				scoreB.setOnAction(e -> viewImc1.viewScore());
				
				Button helpB = new Button("Info");
				helpB.setMaxWidth(Double.MAX_VALUE);
				helpB.setOnAction(e -> viewImc1.viewHelp());
				
				layout1.getChildren().addAll(animal,decor,bloom,save,load,scoreB,helpB);
				mainPane.setRight(layout1);
				viewImc1.primStage.setTitle("Main Garden");
				viewImc1.primStage.setScene(scene);
	  }
	/** resetScrollPane is called each time a plant is taken from the scroll pane to the
	 * and added to the garden 
	 * @param imc    Controller object to access the garden in model 
	 * @param replacedIndx    index of the plant in the scrollpane that needs to be replaced 
	 * @author Geoffrey Bonnanzio 
	 */
	 public void resetScrollPane(Controller imc,int replacedIndx) {
		 spVBox = new VBox();
		 spVBox.setSpacing(scrollPaneSpacing);
		 for(ImageView iv:imagesInScrollPane) {
			 	// format ImageView for size
				//iv.setPreserveRatio(true);
			    iv.setFitHeight(spImgHeight);
			    iv.setFitWidth(spImgWidth);
			   
			    // set ImageView to be draggable and releasable
			    iv.setOnMouseDragged(event -> imc.drag(event));
			    iv.setOnMouseReleased(event -> imc.release(event));
			    // add ImageView to vbox 
			    spVBox.getChildren().add(iv);		        
			}
		 	// StackPane to hold vbox and set as child of scrollpane
		 	StackPane tmpPane = new StackPane();
		 	tmpPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
			spVBox.setPrefWidth(scrollPaneSpacing);
			tmpPane.getChildren().add(spVBox);
			StackPane.setAlignment(spVBox,Pos.TOP_LEFT);
			tmpPane.setPrefWidth(scrollPaneWidth);
			scrollPane = new ScrollPane();	
			scrollPane.setContent(tmpPane);
			scrollPane.setFitToWidth(true);
			scrollPane.setContent(tmpPane);
			mainPane.setLeft(scrollPane);
	 }

    /** Sets the X location of the plant ImageViews on the actual screen 
	 * handling the x and y locations respectively 
	 * @param x   double variable representing location of image local to screen 
	 * @param img  the ImageView that is being translated
	 * @author Geoffrey Bonnanzio
	 */
	public void setX(double x,ImageView img) {
		//img.setLayoutX(0);
		//System.out.println("x layout before set: " + img.getLayoutX());
		img.setTranslateX(x);
		//System.out.println("x layout after set:  " + img.getLayoutX());
	}
	/** sets the Y location of the plant ImageViews on the actual screen 
	 * handling the x and y locations respectively 
	 * @param y double representing location of image local to screen 
	 * @param img  the ImageView that is being translated
	 * @author Geoffrey Bonnanzio
	 */
    public void setY(double y,ImageView img) {
    	//img.setLayoutY(0);
    	//System.out.println("y layout before set: " + img.getLayoutY());
    	img.setTranslateY(y);
    	//System.out.println("y layout after set:  " + img.getLayoutY());
	}
    

    
    public Scene getScene() {return this.scene;}

   /** Called when a plant is dragged onto, or moved, on the garden. Sets up listeners for the image view
	*@param plant  the plant itself
	*@param imc   Controller instance
	*@param x  double for x location of ImageView
	*@param y double for  y location of ImageView
	*@param img ImageView for which we are setting up listeners
	*@param firstDrop  boolean to check if this plant is already on the garden
	*@author Geoff Bonnanzio and Chris Tiso
	*/
    public void addStack(Plant plant,Controller imc,double x,double y,ImageView img,boolean firstDrop) {
    	plant.determineLengths();
    	
    	double resizeImgX = plant.xLen/xFtPerPix;
    	plant.numXPixels = resizeImgX;
    	double resizeImgY = plant.yLen/yFtPerPix;
    	plant.numYPixels = resizeImgY;
    	img.setFitWidth((int)resizeImgX);
    	img.setFitHeight((int)resizeImgY);

    	if(firstDrop) {
    		System.out.println("drop val: x " + x);
    		System.out.println("drop val: y " +y);
    		//img.setTranslateX(0);
    		//img.setTranslateY(0);
    		//System.out.println("add stack x: " + x + " y:" + y);
    		img.setTranslateX(x-(WIDTH-gardenSize+scrollPaneWidth)); // - evaluates to 600
    		System.out.println(img.getLayoutX());
    		System.out.println(img.getLayoutY());
    		img.setTranslateY(y-imc.shiftVal-(WIDTH-gardenSize)+img.getLayoutY()); // - evaluates to 350	
    		
    	}
    	else {
    		img.setTranslateX(x);
    		img.setTranslateY(y);
    	} 		
    		//imc.model.setX(x-(WIDTH-gardenSize+scrollPaneWidth));
    		//imc.model.setY(y-imc.shiftVal-(WIDTH-gardenSize)+img.getLayoutY());
    		//imc.model.currGarden.addPlant(imc.model.getX(),imc.model.getY(),plant);
    		img.setOnMouseDragged(event -> imc.drag(event));
		    img.setOnMouseReleased(event -> imc.release(event));
		    img.setOnMouseEntered(event -> imc.hoverEnter(event));
		    img.setOnMouseExited(event -> imc.hoverExit(event));
		    StackPane tmpPane2 = new StackPane(img);	
	    	tmpPane2.setPickOnBounds(false);
	    	removeDropDown();
		    center.getChildren().add(img);
    }
    
   /** When plants are loaded from the save file, this places the images on the garden
	* @author Chris Tiso 
	* @param x double variable for x location of the plant
	* @param y double variable for y location of the plant 
	* @param img  Image View of plant
	*/
    public void loadStack(double x,double y,ImageView img) {
    	img.setTranslateX(x-(WIDTH-gardenSize+scrollPaneWidth));
    	img.setTranslateY(y);
    	StackPane tmpPane = new StackPane(img);
    	tmpPane.setPickOnBounds(false);
    	center.getChildren().add(img);
    }
    /** makePlantDropDown is called when a hover is initiated over an 
     * an ImageView of a plant that is currently in the garden. This method 
     * creates a drop down menu to delete the plant or get more information on it
     * @param imc  Controller object to access the current garden
     * @param iv the ImageView object that is currently being hovered over.
     * @author Geoffrey Bonnanzio
     */
    public void makePlantDropDown(Controller imc,ImageView iv) {
    	center.getChildren().remove(dropDown);
    	// vbox to hold buttons and label 
    	dropDown = new VBox();
    	//dropDown.setMaxWidth(imgHeight/2);
    	dropDown.setMaxSize(spImgHeight, spImgHeight);
    	String cssLayout = "-fx-border-color: black;\n" +
                "-fx-border-insets: 5;\n" +
                "-fx-border-width: 3;\n";
    	dropDown.setStyle(cssLayout);
    	// label and buttons created to add to vbox
    	
    	Button info =new Button("More info");
    	info.setMaxSize(spImgHeight, spImgHeight/2);
    	
    	info.setOnAction(event -> imc.viewMoreInfo(iv));
    	Button delete =new Button("Delete Plant");
    	delete.setMaxSize(spImgHeight, spImgHeight/2);
    	
    	// associate button with ImageView and Plant for action
    	ImageButton deleteButton = new ImageButton(delete,iv);
    	Garden myGarden = imc.model.currGarden;
    	deleteButton.findPlantFromIV(myGarden.selectPlants);
    	delete.setOnAction(event -> imc.deletePlantFromGarden(deleteButton));
    	delete.setMaxSize(ddButtWidth, ddButtHeight);
    	
    	//make label based off of found plant
    	Label plantLabel = new Label(deleteButton.plant.name);
    	plantLabel.setWrapText(true);
    	StackPane labelPane = new StackPane(plantLabel);
    	labelPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
    	
    	//add drop down to screen 
    	dropDown.getChildren().addAll(labelPane,info,delete);

    	Bounds imgBounds = iv.localToScene(iv.getBoundsInLocal());
    	double x = iv.getBoundsInParent().getMinX();
    	double y = iv.getBoundsInParent().getMinY();
    	dropDown.setTranslateX(x-(gardenSize-scrollPaneWidth-leftSideSize)+10); // additional 10 for overlap (evaluates to 440) 
    	dropDown.setTranslateY(y-(WIDTH-gardenSize)-5); //additional 5 pixels for overlap (evaluates to 355)
    	dropDown.setOnMouseEntered(event -> imc.hoverEnterDropDown(event));
    	dropDown.setOnMouseExited(event -> imc.hoverExitDropDown(event));
    	center.getChildren().add(dropDown);
    	//dropDown.relocate(x, y);
    	dropDownActive = false;
    }
    
    /** deletePlantDropDown is called when the ImageView of a plant is exited to 
     * remove the dropDown from the screen. This only executes if the position
     * of the mouse is outside the bounds of the ImageView 
     * @param iv ImageView that triggered exit 
     * @param event  Event for mouse position
     * @author Geoffrey Bonnanzio and Chris Tiso
     */
    public void deletePlantDropDown(ImageView iv,MouseEvent event) {
    	//catches exit through bottom of the ImageView
    	if(event.getY()==spImgHeight) {
    		center.getChildren().remove(dropDown);
    	}
    	//checking position to ensure actual exit and prevent shutter
    	else if(!iv.getLayoutBounds().contains(event.getX(), event.getY())){
    		center.getChildren().remove(dropDown);
    	}
    }
    /** removeDropDown is similar to deletePlantDropDown but handles the case 
     * in which the user exits the drop down by exiting the drop down and 
     * not through the ImageView. Removes drop down from screen 
     * @author Geoffrey Bonnanzio
     */
    public void removeDropDown() {
    	center.getChildren().remove(dropDown);
    }
	public void removePlantHighlights(Controller imc) {
		center.getChildren().remove(imc.bloom.coverPane);
		try {
			for(StackPane sp:imc.bloom.bgPanes) {
				imc.view.center.getChildren().remove(sp);
			}
		}
		catch(Exception e){}
		center.setMouseTransparent(false);
	}
	/** Used to set properties of GardenView when loading picture from files
     * @param x  x coordinate
     * @param y  y coordinate
     * @author Chris Tiso
     */
	public void setFtPerPix(double x,double y) {
		this.xFtPerPix = x/gardenSize;
		this.yFtPerPix = y/gardenSize;
	}
	/** getter for height of original image in scroll pane
    * @author Geoffrey Bonnanzio
    * @return the height of original image in scroll pane
    */
	public double getSpImgHeight() {
		return spImgHeight;
	}
	/** getter for width of original image in scroll pane
	 * @author Geoffrey Bonnanzio
	 * @return the width of original image in scroll pane
	 */
	public double getSpImgWidth() {
		return spImgWidth;
	}
	/** getter for width of total scroll pane
	 * @author Geoffrey Bonnanzio
	 * @return return the total width of scroll pane
	 */
	public double getSpWidth() {
		return scrollPaneWidth;
	}
	
}

   	



