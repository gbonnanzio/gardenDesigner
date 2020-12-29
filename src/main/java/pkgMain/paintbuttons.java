package pkgMain;

import java.awt.TextField;

import java.util.ArrayList;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.HashMap;


import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/** This is just a class for testing out new View classes without connecting 
 * them to the main controller yet. 
 * @author Prithvi Arora
 */
public class paintbuttons extends Application {
	
   /** Sets up popup table for moreinfo 
	* @author Prithvi Arora
	* @param primaryStage  Stage instance
	* @param imc  Controller instance
	* @param iv   View instance
	*/
    public void start(final Stage primaryStage, Controller imc, ImageView iv) {
 
 

                
            	
            	
            	VBox vbox = new VBox();
            	vbox.setAlignment(Pos.CENTER);
            	Plant currPlant = imc.model.currGarden.selectPlants.get(iv);
            	
            	ArrayList<String> plantinfo = new ArrayList<String>();
            	
            	ArrayList<String> labels = new ArrayList<String>();
            	labels.add("Name: ");
            	labels.add("Scientific Name: ");
            	labels.add("Shading: ");
            	labels.add("Soil Moisture: ");
            	labels.add("Flowering Period: ");
            	labels.add("Soil PH: ");
            	labels.add("Wildlife: ");
            	
            	final ObservableList<String> data = FXCollections.observableArrayList(plantinfo);

				for (String s : currPlant.plantInfo) {
					System.out.println(s);
					plantinfo.add(s);			
					
				}

				System.out.println(plantinfo);
				plantinfo.remove(2);
				
				int counter = 0;
				
				for (String a : labels) {
					a = a + plantinfo.get(counter);
					counter += 1;
					Text t = new Text();
					t.setText(a);
					t.setFont(Font.font("Green",FontWeight.BOLD,11));
			        t.setFill(Color.DODGERBLUE);
					vbox.getChildren().addAll(t);
					
				}
				
		        
                StackPane secondaryLayout = new StackPane();
                secondaryLayout.getChildren().addAll(vbox);
                secondaryLayout.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
                Scene secondScene = new Scene(secondaryLayout, 1200, 200);
                
                // New window (Stage)
                Stage newWindow = new Stage();
                newWindow.setTitle("More Information");
                newWindow.setScene(secondScene);
 
                
                newWindow.show();
            	
       
    }
    
    
 
    public static void main(String[] args) {
        launch(args);
    }

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
	
	
