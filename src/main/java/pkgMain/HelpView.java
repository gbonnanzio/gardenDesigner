package pkgMain;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
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


/** HelpView Class for helpful information about
 * current Garden customer might want to know
 * @author Chris Tiso and Geoff Bonnanzio
 */

public class HelpView {
	Stage viewStage;
	//private ImageView imgIV;
	private static int WIDTH = 1000;
	private static int HEIGHT = 175;
	private static int buttonSpace = 10;
	private Canvas canvas; 
	private Scene scene;
	
	public HelpView() {
		
	}
	
	/** Function to show the helpView screen
	 * containing helpful information.
	 * @param imc  Controller instance
	 */
	public void showHelp(Controller imc) {
		viewStage = new Stage();
		StackPane mainPane = new StackPane();
		
		VBox layout2 = new VBox(buttonSpace);
		layout2.setAlignment(Pos.CENTER);
		Label area=new Label();
		double ftPerBox=imc.model.currGarden.length*imc.model.currGarden.width/64.0;
		area.setText("Each Box on the Garden is "+ftPerBox+" square feet");
		Label soil=new Label();
		soil.setText("Your Soil Moisture is set to: "+imc.model.currGarden.soil_moist);
		Label shade=new Label();
		shade.setText("Your Shading is set to: "+imc.model.currGarden.shading);
		Label pH=new Label();
		pH.setText("Your Soil pH is set to: "+imc.model.currGarden.soil_ph);
		
		
		mainPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		Text sceneTitle = new Text();
        sceneTitle.setText("Info about your Garden");
        sceneTitle.setFont(Font.font("tmp",FontWeight.BOLD, 25));
        sceneTitle.setFill(Color.DODGERBLUE);
        StackPane titleStack = new StackPane(sceneTitle);
        titleStack.setAlignment(Pos.TOP_CENTER);
        titleStack.setPickOnBounds(false);
        layout2.getChildren().addAll(area,soil,shade,pH);
        mainPane.getChildren().addAll(layout2,titleStack);
		scene = new Scene(mainPane,WIDTH,HEIGHT);
    	viewStage.setScene(scene);
    	viewStage.show();
	}
	
}
