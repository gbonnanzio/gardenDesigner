package pkgMain;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.application.Platform;
import javafx.geometry.Insets;

/**Class to handle Score screen
 * @author Madi Freed
 */

public class ScoreView {
	private Scene scene;
	private final double WIDTH = 1000;
	private final double HEIGHT = 700;
	public ScoreView() {}
	
   /**Method Called from controller to view the screen
	* @param imc Controller Instance
	* @param garden  Object representing the garden
	* @param garden2 ImageView representing the garden
	* @author Madi Freed
	*/
	public void scoreScreen(Controller imc, Object garden, ImageView garden2) {
		
		Text title = new Text();
        title.setText("Your Garden: " + "\n" + imc.scoreM.calcScore(imc, garden, garden2) + "\n" + imc.scoreM.calcSize(imc, garden, garden2));
        title.setFont(Font.font("Green",FontWeight.BOLD, 20));
        title.setFill(Color.DODGERBLUE);
		
		
		Text scoreValue = new Text();
        scoreValue.setText("Your score is out of 8. \n 4 possible points from Seasons ratings, 4 possible points from Size ratings\n" + "Your Score: " + imc.scoreM.score(imc));
        scoreValue.setFont(Font.font("Green",FontWeight.BOLD, 20));
        scoreValue.setFill(Color.DODGERBLUE);
		
		//Button button = new Button("Done");
		//button.setOnAction(e -> Platform.exit());
		
		BorderPane main = new BorderPane();
		main.setTop(title);
		main.setCenter(scoreValue);
		main.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		
		//scene = new Scene(main, WIDTH, HEIGHT);
		//sc.scoreStage.setScene(scene);
		//sc.scoreStage.show();
		//imc.primStage.setScene(scene);
		
    	Stage newWindow = new Stage();
        Scene scene = new Scene(main, WIDTH, HEIGHT);
        newWindow.setScene(scene);
        
        newWindow.show();
		
	}
	
}
