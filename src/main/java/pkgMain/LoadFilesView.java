package pkgMain;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
//import javafx.scene.control.ScrollPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
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

/**Class handles view for the Load files screen of the Application
 * @author Prithvi Arora and Chris Tiso
 */

public class LoadFilesView {
	private Canvas canvas; // canvas on which everything is drawn
	private Scene scene;
	final FileChooser fileChooser = new FileChooser();
	private Desktop desktop = Desktop.getDesktop();
	Stage viewStage;
	//private ImageView imgIV;
	private static int WIDTH = 1000;
	private static int HEIGHT = 175;
	private static int buttonSpace = 10;
	
   /** The method called when swithcing to this screen from the main Garden Screen. Creates the scene and sets up the buttons. Buttons will load specific files
	* @param imc   Controller Instance
	* @author Chris Tiso and Geoffrey Bonnanzio and Prithvi Arora
	*/
	public void showLoadFileView(Controller imc) {
		// create a new stage for load file view
		viewStage = new Stage();
		// create buttons to go on stage
		Button button2 = new Button("Load 1");
		Button button4 = new Button("Load 2");
		Button button5 = new Button("Load 3");
		Button button6 = new Button("Load 4");
		
		// mainPane will be added to new scene
		StackPane mainPane = new StackPane();
	
		// buttons for saving
		button2.setOnAction(e -> imc.model.Load(imc,"File1.ser"));
		button4.setOnAction(e -> imc.model.Load(imc,"File2.ser"));
		button5.setOnAction(e -> imc.model.Load(imc,"File3.ser"));
		button6.setOnAction(e -> imc.model.Load(imc,"File4.ser"));
		HBox layout2 = new HBox(buttonSpace);
		layout2.getChildren().addAll(button2,button4,button5,button6);
		layout2.setAlignment(Pos.CENTER);
		
		// set background color 
		mainPane.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
    	// create a title for the scene
		Text sceneTitle = new Text();
        sceneTitle.setText("Load a Previously Saved Garden");
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
	/** closeStage is called to close the loadView stage
	 * @author Geoffrey Bonnanzio and Christopher Tiso 
	 */
	public void closeStage() {
		viewStage.close();
	}


	
	

}

