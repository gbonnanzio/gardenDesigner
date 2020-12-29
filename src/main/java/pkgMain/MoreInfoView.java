package pkgMain;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;


/** This is the view class used to display additional information on each plant.
 * @author Madi Freed    
 */

public class MoreInfoView {

    public BorderPane main;
    public VBox vbox;
    public Scene scene;

    private final double WIDTH = 1000;
    private final double HEIGHT = 700;

    public MoreInfoView() {}

    /** infoScreen sets up a screen with plant data
     * @param imc  Controller Instance
     * @param iv   ImageView for image
     * @author Madi Freed    
     */
    
    public void infoScreen(Controller imc, ImageView iv) {
        vbox = new VBox();

        Plant currPlant = imc.model.currGarden.selectPlants.get(iv);

        for (String s: currPlant.plantInfo) {
            System.out.println(s);

            Text t = new Text();
            t.setText(s);
            vbox.getChildren().add(t);
        }

        Button b1 = new Button("Exit");
        b1.setOnAction(e -> imc.viewGarden()); 

        main = new BorderPane();
        scene = new Scene(main, WIDTH, HEIGHT);

        main.setCenter(vbox);
        main.setBottom(b1);

        imc.primStage.setScene(scene);
    }

}