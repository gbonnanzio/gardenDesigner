package pkgMain;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
 

/**
 * This is the moreinfo class handling the additional information on each plants. 
 * @author Prithvi Arora
 */
public class moreinfo extends Application {
 
   

	@Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("CheckBox Experiment 1");
    	
   
    	
    	CheckBox checkBox1 = new CheckBox("Tiles");
    	CheckBox checkBox2 = new CheckBox("Tiki Torch");
    	CheckBox checkBox3 = new CheckBox("Big Stones");
    	CheckBox checkBox4 = new CheckBox("Little Stones");
    	CheckBox checkBox5 = new CheckBox("Path Lighting");
    	CheckBox checkBox6 = new CheckBox("Small Lights");
    	CheckBox checkBox7 = new CheckBox("Light Post");
    	CheckBox checkBox8 = new CheckBox("Motion Lights");
    	CheckBox checkBox9 = new CheckBox("Bird Bath");
    	CheckBox checkBox10 = new CheckBox("Wind Chimes");
    	CheckBox checkBox11 = new CheckBox("Pinwheel");
    	CheckBox checkBox12 = new CheckBox("Bird Feeder");
    	
    	VBox vbox = new VBox(checkBox1,checkBox2,checkBox3,checkBox4,checkBox5,checkBox6,checkBox7,checkBox8,checkBox9,checkBox10,checkBox11,checkBox12);

        Scene scene = new Scene(vbox, 200, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    
        
    }
    	
	public static void main(String[] args) {
        Application.launch(args);
    }

} 