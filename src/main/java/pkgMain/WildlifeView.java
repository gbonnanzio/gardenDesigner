package pkgMain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;



/** WildlifeView Class to handle WildLife screen
 * @author Madi Freed
 */
public class WildlifeView {
	
	//ArrayList<String> wildlifeOptions;
	
	public Scene scene; 
	
	public BorderPane main;
	public ScrollPane scrollPane;
	public VBox spVBox;

	private final double WIDTH = 600;
	private final double HEIGHT = 500;
	
	/** Constructor for WildlifeView
	 * @author Madi Freed
	 */
	public WildlifeView() {}	
	
    /**Method Called from controller to View Screen
	* @author Madi Freed
	* @param imc  Controller Instance
	*/
	public void viewWild(Controller imc) {
		scrollPane = placeWildlifeImages(imc);
		setupPanes(imc);
	}
	
   /** placeWildlifeImages puts wildlife images from the current Garden into a scroll pane
	* @author Madi Freed
	* @param imc  Controller Instance
	* @return scrollpane containing wildlife images
	*/
	public ScrollPane placeWildlifeImages(Controller imc) {
		ScrollPane scrollPane = new ScrollPane();
		FlowPane flow = new FlowPane();
		flow.setHgap(50);
		flow.setVgap(50);
		
		//Collection<Wildlife> allWildlife = (imc.model.wildlifeMap.values());
		Collection<Wildlife> allWildlife = (imc.model.currGarden.gardenWildlife.values());
		
		int count = 0;
		System.out.println(allWildlife.size());
		
		for(Wildlife wildlife:allWildlife) {
			count++;
			if(count>16) {
				break;
			}
			wildlife.iv.setPreserveRatio(true);
		    wildlife.iv.setFitHeight(100);
		    
		    BorderPane tmpBorderPane = new BorderPane();
		    
		    Button wildButton = new Button();
		    wildButton.setGraphic(wildlife.iv);
		    wildButton.setOnAction(event -> wildlifeInfoPopUp(wildlife));
		    Text wildLabel = new Text(wildlife.name);
		    wildLabel.setFont(Font.font("Green",FontWeight.BOLD,11));
	        wildLabel.setFill(Color.DODGERBLUE);
		    
			tmpBorderPane.setLeft(wildButton);
			//tmpBorderPane.setLeft(wildlife.iv);
			tmpBorderPane.setBottom(wildLabel);
			tmpBorderPane.setAlignment(wildLabel, Pos.BOTTOM_CENTER);
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
	* @param imc  Controller Instance
	*/
	public void setupPanes(Controller imc) {
		//canvas = new Canvas(1000, 700);
		
		//Pane root = new Pane(canvas);
		
    	Text l1 = new Text();
        l1.setText("Wildlife Attracted by Plants");
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
		
		scene = new Scene(main, WIDTH, HEIGHT);
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
	
	   /**wildlifeInfoPopUp displays the screen containing specific wildlife 
		* @author Madi Freed
		* @param w   Wildlife
		*/
	public void wildlifeInfoPopUp(Wildlife w) {
		ObservableList<String> data = FXCollections.observableArrayList();

		ListView<String> listView = new ListView<String>(data);

		data.addAll(w.specificsList);

		listView.setItems(data);
		
		StackPane root = new StackPane();
        root.getChildren().add(listView);

        Scene scene = new Scene(root, 300, 350);
		Stage newWindow = new Stage();
        newWindow.setScene(scene);
        newWindow.setTitle(w.name);
        newWindow.show();
		
	}

}
