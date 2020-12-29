package pkgMain;

import javafx.application.Application;
import javafx.stage.Stage;

/**Test Controller to Run Score View class
 * @author Madi Freed
 */
public class ScoreController extends Application {
	ScoreView sV;
	ScoreModel sM;
	public Stage scoreStage;
	String score;
	
	/** Method for beginning of program's execution
	 * @param stage  Stage instance
	 */
	public void start(Stage stage) {
		sM = new ScoreModel();
		sV = new ScoreView();
		scoreStage = stage;
		
		//score = sM.calcScore();
		
		//sV.scoreScreen(this);
		
	}
	
	/** main method for launching the program
	 * @param args  array of strings(for main method)
	 */
	public static void main(String[] args) {
        launch(args);
    }

}
