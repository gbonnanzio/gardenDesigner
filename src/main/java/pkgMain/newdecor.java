package pkgMain;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javafx.scene.control.*; 
import javafx.scene.layout.*; 
import javafx.collections.*; 
import javafx.scene.paint.Color;
/**
 * This is the class used to manage the decorations of the garden.
 * 
 * @author Prithvi Arora
 */

public class newdecor extends Application {
	
	@Override
    public void start(Stage primaryStage) throws Exception {

		
		ArrayList<String>decs = new ArrayList<>();
        primaryStage.setTitle("CheckBox Experiment 1");
    	

   
    	
    	CheckBox checkBox1 = new CheckBox("Tiles");
    	checkBox1.setStyle("-fx-text-fill: DODGERBLUE");
    	CheckBox checkBox2 = new CheckBox("Tiki Torch");
    	checkBox2.setStyle("-fx-text-fill: DODGERBLUE");
    	CheckBox checkBox3 = new CheckBox("Big Stones");
    	checkBox3.setStyle("-fx-text-fill: DODGERBLUE");
    	CheckBox checkBox4 = new CheckBox("Little Stones");
    	checkBox4.setStyle("-fx-text-fill: DODGERBLUE");
    	CheckBox checkBox5 = new CheckBox("Path Lighting");
    	checkBox5.setStyle("-fx-text-fill: DODGERBLUE");
    	CheckBox checkBox6 = new CheckBox("Small Lights");
    	checkBox6.setStyle("-fx-text-fill: DODGERBLUE");
    	CheckBox checkBox7 = new CheckBox("Light Post");
    	checkBox7.setStyle("-fx-text-fill: DODGERBLUE");
    	CheckBox checkBox8 = new CheckBox("Motion Lights");
    	checkBox8.setStyle("-fx-text-fill: DODGERBLUE");
    	CheckBox checkBox9 = new CheckBox("Bird Bath");
    	checkBox9.setStyle("-fx-text-fill: DODGERBLUE");
    	CheckBox checkBox10 = new CheckBox("Wind Chimes");
    	checkBox10.setStyle("-fx-text-fill: DODGERBLUE");
    	CheckBox checkBox11 = new CheckBox("Pinwheel");
    	checkBox11.setStyle("-fx-text-fill: DODGERBLUE");
    	CheckBox checkBox12 = new CheckBox("Bird Feeder");
    	checkBox12.setStyle("-fx-text-fill: DODGERBLUE");
    	
    	
    	
    	checkBox1.selectedProperty().addListener(
    		      (ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
    		         decs.add("Tiles");
    		         System.out.println(decs);
    		      });
       	checkBox2.selectedProperty().addListener(
  		      (ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
  		         decs.add("Tiki Torch");
  		         System.out.println(decs);
  		      });
       	checkBox3.selectedProperty().addListener(
  		      (ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
  		         decs.add("Big Stones");
  		         System.out.println(decs);
  		      });
       	checkBox4.selectedProperty().addListener(
  		      (ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
  		         decs.add("Little Stones");
  		         System.out.println(decs);
  		      });
       	checkBox5.selectedProperty().addListener(
  		      (ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
  		         decs.add("Path Lighting");
  		         System.out.println(decs);
  		      });
       	checkBox6.selectedProperty().addListener(
  		      (ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
  		         decs.add("Small Lights");
  		         System.out.println(decs);
  		      });
       	checkBox7.selectedProperty().addListener(
  		      (ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
  		         decs.add("Light Post");
  		         System.out.println(decs);
  		      });
       	checkBox8.selectedProperty().addListener(
  		      (ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
  		         decs.add("Motion Lights");
  		         System.out.println(decs);
  		      });
       	checkBox9.selectedProperty().addListener(
  		      (ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
  		         decs.add("Bird Bath");
  		         System.out.println(decs);
  		      });
       	checkBox10.selectedProperty().addListener(
  		      (ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
  		         decs.add("Wind Chimes");
  		         System.out.println(decs);
  		      });
       	checkBox11.selectedProperty().addListener(
  		      (ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
  		         decs.add("Pinwheel");
  		         System.out.println(decs);
  		      });
       	checkBox12.selectedProperty().addListener(
  		      (ObservableValue<? extends Boolean> ov, Boolean old_val, Boolean new_val) -> {
  		         decs.add("Bird Feeder");
  		         System.out.println(decs);
  		      });

    	System.out.println("all decs: " + decs);
    	for(String dec: decs) {
    		if(dec == "Tile") {
    			
    		}
    	}
    	VBox vbox = new VBox(checkBox1,checkBox2,checkBox3,checkBox4,checkBox5,checkBox6,checkBox7,checkBox8,checkBox9,checkBox10,checkBox11,checkBox12);
    	vbox.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
    	Stage newWindow = new Stage();
        Scene scene = new Scene(vbox, 200, 250);
        newWindow.setScene(scene);
        
        newWindow.show();
        
    }

	public static void main(String[] args) {
		Application.launch(args);
	}

}