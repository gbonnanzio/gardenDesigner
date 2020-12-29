package pkgMain;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import pkgMain.Controller;

/** Class handles view for the Save files screen of the Application
 * @author Chris Tiso and Geoff Bonnanzio
 */ 
public class SaveFilesView {
	private Scene scene; 
	
	Stage viewStage;
	//private ImageView imgIV;
	private static int WIDTH = 1000;
	private static int HEIGHT = 175;
	private static int buttonSpace = 10;
	
    /** The method called when switching to this screen from the main Garden Screen. Creates the scene and sets up the buttons. Buttons activate saving to specific save files 
	* @param imc  Controller Instance
	* @author Chris Tiso
	*/
	public void SaveFilesView(Controller imc) {
		viewStage = new Stage();
		
		Button button2 = new Button("Save 1");
		Button button4 = new Button("Save 2");
		Button button5 = new Button("Save 3");
		Button button6 = new Button("Save 4");
		StackPane mainPane = new StackPane();
		//Button button7= new Button("Back");
		//adds items to vbox 
		button2.setOnAction(e -> imc.model.Save("File1.ser"));
		button4.setOnAction(e -> imc.model.Save("File2.ser"));
		button5.setOnAction(e -> imc.model.Save("File3.ser"));
		button6.setOnAction(e -> imc.model.Save("File4.ser"));
		//button7.setOnAction(e->imc.viewGarden());
		HBox layout2 = new HBox(buttonSpace);
		layout2.getChildren().addAll(button2,button4,button5,button6);
		layout2.setAlignment(Pos.CENTER);
		
		// set background color 
		mainPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
	    // create a title for the scene
		Text sceneTitle = new Text();
		sceneTitle.setText("Save Your Garden");
        sceneTitle.setFont(Font.font("tmp",FontWeight.BOLD, 25));
		sceneTitle.setFill(Color.DODGERBLUE);
		StackPane titleStack = new StackPane(sceneTitle);
        titleStack.setAlignment(Pos.TOP_CENTER);
		titleStack.setPickOnBounds(false);

		// add title and buttons to screen 
		mainPane.getChildren().addAll(layout2,titleStack);
		scene = new Scene(mainPane,WIDTH,HEIGHT);
		viewStage.setScene(scene);
		viewStage.show();
	}
	
	  /** Closing the stage
		*/
	public void closeStage() {
		viewStage.close();
	}
}
