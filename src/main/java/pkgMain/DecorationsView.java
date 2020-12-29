package pkgMain;

import java.util.Collection;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
 
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import javafx.scene.Scene;


/**
 * This is the view class for the Decorations page. In this class, we launch the screen containing
 * the choosen decoration images.
 * @author Madi Freed
 */
public class DecorationsView {
	
	public ScrollPane scrollPane;
	private final double WIDTH = 600;
	private final double HEIGHT = 500;
	HashMap<String, Decorations> decorMap;
	ArrayList<String> decorList = new ArrayList<String>(Arrays.asList("Big Stones", "Bird Bath", "Birdfeeder", "Light Post", "Little Stones", "Motion Lights",
			"Path Lighting", "Pinwheels", "Small Lights", "Tiki Torch", "Tiles", "Wind Chimes"));
	
	/** DecorationsView Class's constructor
	 * @author Madi Freed
	 */
	public DecorationsView() {}
	
	/** getDecorImages creates instances of each Decoration and adds them to a HashMap
	* @author Madi Freed
	* @return HashMap containing decoration names and images
	*/
	public HashMap<String,Decorations> getDecorImages() {
		HashMap<String,Decorations> map = new HashMap<String,Decorations>();
		for (String s: decorList) {
			Decorations d = new Decorations(s);
			map.put(s, d);
		}
		return map;
	}
	
    /**Method Called from controller to View Screen
	* @author Madi Freed
	*/
	public void viewDecor() {
		decorMap = getDecorImages();
		scrollPane = placeDecorImages();
		setupPanes();
	}
	
	/** placeDecorImages puts decoration images into a scroll pane
	* @author Madi Freed
	* @return scrollpane containing wildlife images
	*/
	public ScrollPane placeDecorImages() {
		ScrollPane scrollPane = new ScrollPane();
		FlowPane flow = new FlowPane();
		flow.setHgap(50);
		flow.setVgap(50);
		
		//Collection<Wildlife> allWildlife = (imc.model.wildlifeMap.values());
		Collection<Decorations> allDecor = (decorMap.values());
		
		int count = 0;
		System.out.println(allDecor.size());
		
		for(Decorations decor: allDecor) {
			count++;
			if(count>16) {
				break;
			}
			decor.iv.setPreserveRatio(true);
		    decor.iv.setFitHeight(100);
		    
		    BorderPane tmpBorderPane = new BorderPane();
		    
		    
		    Text decorLabel = new Text(decor.name);
		    decorLabel.setFont(Font.font("Green",FontWeight.BOLD,11));
	        decorLabel.setFill(Color.DODGERBLUE);
		    
			tmpBorderPane.setLeft(decor.iv);
			tmpBorderPane.setBottom(decorLabel);
			tmpBorderPane.setAlignment(decorLabel, Pos.BOTTOM_CENTER);
			flow.getChildren().add(tmpBorderPane);
			//spVBox.getChildren().add(tmpBorderPane);
		    
		    //spVBox.getChildren().add(wildlife.iv);
		
				
	        
		}
		
		//flow.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		
		StackPane tmpPane = new StackPane();
		tmpPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));


		//tmpPane.getChildren().add(spVBox);
		tmpPane.getChildren().add(flow);
		//StackPane.setAlignment(spVBox,Pos.TOP_LEFT);
		StackPane.setAlignment(flow,Pos.TOP_LEFT);
		tmpPane.setPrefWidth(250);
		
		//tmpPane.setMargin(vbox, new Insets(10,10,10,10));
		
		scrollPane.setContent(tmpPane);
		//scrollPane.setPrefSize(200,100);
		//scrollPane.setPadding(new Insets(0,10,0,10));
		//scrollPane.setPrefViewportWidth(150);
		scrollPane.setFitToWidth(true);
		
		//scrollPane.setPannable(true);
		
		return scrollPane;
	}
	
   /**setUpPanes makes the scene for the class
	* @author Madi Freed
	*/
	public void setupPanes() {
		//canvas = new Canvas(1000, 700);
		
		//Pane root = new Pane(canvas);
		
    	Text l1 = new Text();
        l1.setText("Decorations");
        l1.setFont(Font.font("Green",FontWeight.BOLD, 30));
        l1.setFill(Color.DODGERBLUE);
     
		
		//buttons
		//Button b1 = new Button("Done");
		//button actions
		//b1.setOnAction(e -> imc.viewGarden()); //for testing
		
		VBox layout1 = new VBox();
		layout1.getChildren().addAll(l1);
		
		//VBox layout2 = new VBox();
		//layout2.getChildren().addAll(b1);
		
		
		BorderPane main = new BorderPane();
		main.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));

		
		//Rectangle rect = new Rectangle(200,200,Color.RED);
		//ScrollPane sp = new ScrollPane();
		//sp.setPrefSize(120, 120);
		//sp.setContent(rect);
		
		Scene scene = new Scene(main, WIDTH, HEIGHT);
		main.setTop(layout1);
		//main.setBottom(layout2);
		main.setCenter(scrollPane);
		//main.setCenter(sp);
		//main.setCenter(root);
		
		 // New window (Stage)
        Stage newWindow = new Stage();
        
        //newWindow.setTitle("More Wildlife Information");
        newWindow.setScene(scene);

        newWindow.show();
		
		//imc.primStage.setScene(scene);
		//imc.primStage.show();
		
	}

} 
