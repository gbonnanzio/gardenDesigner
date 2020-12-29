package pkgMain;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/** This class is used to create a pop up screen for the Garden
* characteristics such as pH etc...
* @author Chris Tiso
*/

public class PopUpView {
	private Scene scene; 
	
	Stage viewStage;
	//private ImageView imgIV;
	private static int WIDTH = 1000;
	private static int HEIGHT = 175;
	private static int buttonSpace = 10;
	
	public PopUpView() {
		
	}
	
	
	/** Function used to show the popup screen
	 * for garden's characteristics
	* @param imc Controller instance
	* @author Chris Tiso
	*/
	public void PicLoadView(Controller imc) {
		viewStage = new Stage();
		StackPane mainPane = new StackPane();
		HBox layout2 = new HBox(buttonSpace);
		Button button1 = new Button("Done");
		ComboBox soilPH=new ComboBox();
		soilPH.getItems().addAll("Acidic","Adaptable","Acidic to Adaptable");
		soilPH.setValue("Soil pH (Choose One):");
		
		ComboBox moisture=new ComboBox();
		moisture.getItems().addAll("Average","Average to Moist","Moist","Average to Dry","Dry");
		moisture.setValue("Soil Moisture (Choose One):");
		
		ComboBox shade=new ComboBox();
		shade.getItems().addAll("Full Sun","Filtered-Shade to Full-Sun","Filtered Shade");
		shade.setValue("Shading (Choose One):");
		Text error=new Text();
		
		//button1.setOnAction(e -> controller.viewGarden());
		button1.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent e) {
	               
	            	if (soilPH.getValue().toString() != "Soil pH (Choose One):" &&shade.getValue().toString() != "Shading (Choose One):"&& moisture.getValue().toString() != "Soil Moisture (Choose One):"){
	                        imc.model.setConditions(soilPH.getValue().toString(),shade.getValue().toString(),moisture.getValue().toString());
	                        closeStage();
	                }
	                
	                else {
	                     error.setText("Please Choose Conditions of Garden");
	                     error.setFont(Font.font ("Green",FontWeight.BOLD, 12));
	                     error.setFill(Color.RED);
	                }
	            }
	        });
		layout2.getChildren().addAll(soilPH,moisture,shade,button1);
		layout2.setAlignment(Pos.CENTER);
		mainPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
	    // create a title for the scene
		Text sceneTitle = new Text();
		sceneTitle.setText("Choose Your Settings");
        sceneTitle.setFont(Font.font("tmp",FontWeight.BOLD, 25));
		sceneTitle.setFill(Color.DODGERBLUE);
		StackPane titleStack = new StackPane(sceneTitle);
		StackPane errorStack=new StackPane(error);
		errorStack.setAlignment(Pos.BOTTOM_CENTER);
		errorStack.setPickOnBounds(false);
        titleStack.setAlignment(Pos.TOP_CENTER);
		titleStack.setPickOnBounds(false);

		// add title and buttons to screen 
		mainPane.getChildren().addAll(layout2,titleStack,errorStack);
		scene = new Scene(mainPane,WIDTH,HEIGHT);
		viewStage.setScene(scene);
		viewStage.setAlwaysOnTop(true);
		
		viewStage.show();
		
	}
	public void closeStage() {
		viewStage.close();
	}
}
