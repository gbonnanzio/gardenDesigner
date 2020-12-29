package pkgMain;
	
import javafx.application.Application;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;



/** Controller to Run HomePageView class
 * @author Cheickna Sacko
 */

public class HomePageController extends Application {
	
	private static int WIDTH = 800;
	private static int HEIGHT = 600;
	private static int VERTICAL = 800;
	private static int HORIZONTAL = 600;

	HomePageView homepageView;
	public Stage primStage;
	
	@Override
	public void start(Stage primaryStage)throws InterruptedException {
		primStage=primaryStage;
		primStage.setTitle("*Garden Design Application*");
		//homepageView = new HomePageView(this); 

	}
	
	
	
	
	public static void main(String[] args) {
		launch(args);
	}
}



