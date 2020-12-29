package pkgMain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javafx.geometry.Pos;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/** This is the view class for the Bloom Calendar page. In this
 *  class methods handle the panes for Bloom Calendar page and what determine 
 * what images need to be added to each month when clicked received from
 *  Controller and the current garden from the Model
 *  @author Geoff Bonnanzio, Chris Tiso
 */
public class BloomCalendarView {
	// BloomCalendarView Properties
	//width and height of page
	private final double WIDTH = 700;
	private final double HEIGHT = 400;
	//mthe scene will be created around mainPane 
	public StackPane mainPane;
	public GridPane mainGridPane;
	public Scene scene;
	public Stage viewStage;
	public Button backButton;
	public ImageView currIV = new ImageView();
	public HashMap<String,Months> monthMap = new HashMap<String,Months>();
	public ArrayList<ArrayList<String>> TwoDMonths;
	public ArrayList<Color> monthColors;
	ArrayList<String> seasons;
	public ArrayList<StackPane> bgPanes;
	public StackPane currMonthHL = new StackPane();
	public StackPane coverPane; 
	// void constructor
	public BloomCalendarView() {}
	
	/** BloomView is called when the user wants to go to the Bloom Calendar page. This function sets the scene for the BC, and adds it to the stage
	 * @param imc     Controller instance 
	 * @author Geoffrey Bonnanzio
	 */
	public void BloomView(Controller imc){
		imc.view.center.setMouseTransparent(true);
		//viewStage = imc.primStage;
    	viewStage = new Stage();
    	//viewStage.setX(600);
    	//viewStage.setY(600);
    	viewStage.setOnCloseRequest(event -> imc.bcExit());
    	viewStage.setX(800);
    	viewStage.setY(300);
		// create mainPane and scene
		mainPane = new StackPane();
    	mainGridPane = new GridPane();
    	//Button back=new Button("Back");
    	//back.setOnAction(e->imc.viewGarden());
    	//mainPane.setLeft(back);
    	mainGridPane.setPadding(new Insets(12));
    	
    	//create calendar 
    	ArrayList<String> first = new ArrayList<String>(Arrays.asList("January","February","March","April"));
    	ArrayList<String> second = new ArrayList<String>(Arrays.asList("May","June","July","August"));
    	ArrayList<String> third = new ArrayList<String>(Arrays.asList("September","October","November","December"));
    	TwoDMonths = new ArrayList<ArrayList<String>>(Arrays.asList(first,second,third));
    	monthColors = new ArrayList<Color>(Arrays.asList(Color.MEDIUMPURPLE,Color.CORNFLOWERBLUE,Color.LIGHTSEAGREEN,Color.MEDIUMSEAGREEN,Color.CHARTREUSE,Color.GREENYELLOW,Color.GOLD,Color.ORANGE,Color.LIGHTSALMON,Color.TOMATO,Color.CRIMSON,Color.DARKORCHID));
    	seasons = new ArrayList<String>(Arrays.asList("mid-winter","late-winter","early-spring","mid-spring","late-spring","early-summer","mid-summer","late-summer","early-fall","mid-fall","late-fall","late-winter"));
    	
    	//width and height of each calendar box
    	double width = 75;
    	double height = 75;
    	//index represents number of months we've added 
    	int currIndx = 0;
    	// create panes for each month and add them to the mainPane
    	for(int i = 0; i<3; i++) {
    		for(int j = 0;j<4;j++) {
    			StackPane tmpPane = new StackPane();
    			Rectangle tmpRect = new Rectangle(width,height);
    			Months currMonth = new Months(TwoDMonths.get(i).get(j),seasons.get(currIndx),monthColors.get(currIndx),i,j);
    			monthMap.put(currMonth.name,currMonth);
    			tmpRect.setFill(monthColors.get(currIndx));
    			Label tmpLabel = new Label(TwoDMonths.get(i).get(j));
    			tmpPane.getChildren().addAll(tmpRect,tmpLabel);
    			mainGridPane.add(tmpPane, j, i);
    	    	currIndx++;
    		}
    	}
    	//StackPane tmpSP = new StackPane();
    	mainPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
    	Text sceneTitle = new Text();
        sceneTitle.setText("Bloom Calendar");
        sceneTitle.setFont(Font.font("Green",FontWeight.BOLD, 25));
        sceneTitle.setFill(Color.DODGERBLUE);
        StackPane titleStack = new StackPane(sceneTitle);
        titleStack.setAlignment(Pos.TOP_CENTER);
        titleStack.setPickOnBounds(false);
    	//place mainPane in the center of the 
    	mainGridPane.setAlignment(Pos.CENTER);
    	mainPane.getChildren().addAll(mainGridPane,titleStack);
    	showBloomPage();
    	//set listener for a mouse click 
    	//currIV.setOnMouseClicked(imc.getHandlerForClick());
		imc.setHandlerForClick(mainGridPane);
	}
	
	
	
	/** showBloomPage is called to display the main bloom calendar page 
	 * @author Geoffrey Bonnanzio
	 */
	public void showBloomPage() {
		//creates new scene and adds it to the stage 
		Scene scene = new Scene(mainPane, WIDTH, HEIGHT);
    	viewStage.setScene(scene);
    	viewStage.show();
	}
	
	/** reShowBloomPage is called to display the main bloom calendar
	 *  page after returning from an individual month
	 * @author Geoffrey Bonnanzio
	 */
	public void reShowBloomPage() {
		//display the bloom calendar page 
		Scene scene = new Scene(mainPane, WIDTH, HEIGHT);
    	viewStage.setScene(scene);
	}
	
	/** setIV is called to create an image view and format the size for each plant that appears when a month is called
	 * @param plant     a file path to the image displayed
	 * @author Geoffrey Bonnanzio
	 * @return ImageView, the formatted image 
	 */
	public ImageView setIV(Plant plant) {
    	
    	ImageView mvIV = new ImageView(plant.img);
    	
    	
    	mvIV.setPreserveRatio(true);
    	mvIV.setFitHeight(100);
    	mvIV.setPreserveRatio(true);
    	mvIV.setFitHeight(100);
    	
    	return mvIV;
    }
	
	/** findMonthClicked determines which month corresponds to the coordinates of the GridPane clicked
	 * @param rowClick       row of month clicked
	 * @param colClick       column of month clicked
	 * @return String for the season
	 * @author Geoffrey Bonnanzio
	 */
	public String findMonthClicked(int rowClick, int colClick) {
		String monthName = TwoDMonths.get(rowClick).get(colClick);
		String season = monthMap.get(monthName).season;
		return season;
	}
	
	/** getPlantsForMonth determines the names of plants that are in the month clicked
	 * @param garden     allows for access to plants in current garden (chosenPlants)
	 * @param rowClick   row of month clicked
	 * @param colClick   column of month clicked
	 * @return  ArrayList containing the plants in the month
	 * @author Geoffrey Bonnanzio
	 */
	public ArrayList<Plant> getPlantsForMonth(Garden garden, int rowClick, int colClick){
		ArrayList<Plant> plantsInMonth = new ArrayList<Plant>();
		String season = findMonthClicked(rowClick,colClick);
		ArrayList<Plant> plantsInGarden = new ArrayList<Plant>(garden.selectPlants.values());
		for(Plant plant:plantsInGarden) {
			if(plant.flowering_period.contains(season)) {
				plantsInMonth.add(plant);
			}
		}
		return plantsInMonth;
	}
	
	/** monthView creates the scene that will be viewed when each month is clicked on 
	 * @param imc           instance of our controller to access model
	 * @param currGarden    allows for access to plants in current garden (chosenPlants)
	 * @param namesInMonth  Collection of Strings of each plant name that bloom in that month
	 * @param rowClick   row of month clicked
	 * @param colClick   column of month clicked
	 * @author Geoffrey Bonnanzio
	 */
	public void monthView(Controller imc, Garden currGarden, ArrayList<Plant> namesInMonth, int rowClick, int colClick) {
		//tmpMainPane will be the main pane added to the scene 
		//BorderPane tmpMainPane = new BorderPane();
		//FlowPane mainFlow = new FlowPane();
		imc.view.removePlantHighlights(imc);
		removeHighlight();
		for (Node node : mainGridPane.getChildren()) {
	        if(mainGridPane.getRowIndex(node) == rowClick && mainGridPane.getColumnIndex(node) == colClick) {
	            currMonthHL = (StackPane)node;
	            break;
	        }
	    }
		String spLayout = "-fx-border-color: yellow;\n" +
    						"-fx-border-insets: 3;\n" +
    						"-fx-border-width: 6;\n";
		currMonthHL.setStyle(spLayout);
		bgPanes = new ArrayList<StackPane>();
		//iterate through each plant in the month and create and image of them that is added to the scene
		for(Plant p:namesInMonth) {
			StackPane bgPane = new StackPane();
			
			bgPanes.add(bgPane);
			String cssLayout = 	"-fx-border-color: yellow;\n" +
	                			"-fx-border-insets: 5;\n" +
	                			"-fx-border-width: 10;\n";
	    	bgPane.setStyle(cssLayout);
	    	Bounds imgBounds = p.iv.localToParent(p.iv.getBoundsInLocal());
	    	double xMin = p.iv.getBoundsInParent().getMinX();
	    	double yMin = p.iv.getBoundsInParent().getMinY();
	    	double xMax = p.iv.getBoundsInParent().getMaxX();
	    	double yMax = p.iv.getBoundsInParent().getMaxY();
	    	double xSize = xMax - xMin;
	    	double ySize = yMax - yMin;
	    	
	    	System.out.println("xMin: " + xMin + " yMin: " + yMin);
	    	System.out.println("xMax: " + xMax + " yMax: " + yMax);
	    	bgPane.setMaxSize(xMax-xMin+20, yMax-yMin+20);
	      	System.out.println(bgPane.getLayoutX());
	      	System.out.println(bgPane.getLayoutY());
	    	bgPane.setTranslateX(xMin-400+xSize/2);
	    	bgPane.setTranslateY(yMin-400+ySize/2);
	    	
	    	
	    	
	    	imc.view.center.getChildren().add(bgPane);
			
		}
		coverPane = new StackPane();
		coverPane.setPickOnBounds(true);
		imc.view.center.getChildren().add(coverPane);
	}
	/** method used to make the current month's highlight disappear 
	 */
	public void removeHighlight() {
		currMonthHL.setStyle(null);
	}
	
}