package pkgMain;


import javafx.application.Application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import pkgMain.Controller;


/**This class allows the user to draw on the screen. With help from https://math.hws.edu/javanotes/source/chapter6/SimplePaint.java with changes for our project
 * @author Chris Tiso
 */
public class Paint {
	private final Color[] palette = { Color.BLACK };
	private int currentColorNum = 0; 
	private double prevX, prevY; 
	private boolean dragging; 
	private Canvas canvas;
	private GraphicsContext g; 
	private Scene scene, scene2;
	private boolean cleared = true;
	private boolean done = true;
	private Stage currStage;
	private Image img;
	
   /** getter used to return the scene
	*@author Chris Tiso
	*@return the scene
	*/
	public Scene getDrawScene() {
		return scene;
	}
   /**Creates the Scene and listens for mouse inputs
	* @param controller Controller Instance 
	* @param iv Image View 
	* @author Chris Tiso and cited website
	*/
	public void SimplePaintView(Controller controller, ImageView iv) {
		controller.primStage.setX(100);
		controller.primStage.setY(50);
		canvas = new Canvas(800, 800);
		g = canvas.getGraphicsContext2D();
		clearAndDrawPalette(controller);
		//createGrid(controller);
		
		canvas.setOnMousePressed(e -> mousePressed(e));
		canvas.setOnMouseDragged(e -> mouseDragged(e));
		canvas.setOnMouseReleased(e -> mouseReleased(e));

		Pane root = new Pane(canvas);
		
		Text sceneTitle = new Text();
        sceneTitle.setText("Please draw your garden");
        sceneTitle.setFont(Font.font("tmp",FontWeight.BOLD, 15));
        sceneTitle.setFill(Color.DODGERBLUE);
		
    	Text sceneTitle2 = new Text();
        sceneTitle2.setText("and set your conditions");
        sceneTitle2.setFont(Font.font("tmp",FontWeight.BOLD, 15));
        sceneTitle2.setFill(Color.DODGERBLUE);
        VBox words=new VBox();
        words.getChildren().addAll(sceneTitle,sceneTitle2);
		//buttons
		Button button1 = new Button("Done");
		Button button3 = new Button("Clear");
		Button button4 = new Button("Decorations");
		
		//gives buttons actions
		//button1.setOnAction(e -> Platform.exit());
		
		ComboBox soilPH=new ComboBox();
		soilPH.getItems().addAll("Acidic","Adaptable","Acidic to Adaptable");
		soilPH.setValue("Soil pH (Choose One):");
		
		ComboBox moisture=new ComboBox();
		moisture.getItems().addAll("Average","Average to Moist","Moist","Average to Dry","Dry");
		moisture.setValue("Soil Moisture (Choose One):");
		
		ComboBox shade=new ComboBox();
		shade.getItems().addAll("Full Sun","Filtered-Shade to Full-Sun","Filtered Shade");
		shade.setValue("Shading (Choose One):");
		
		 // create the data to show in the CheckComboBox 

		Text error=new Text();
		//button1.setOnAction(e -> controller.viewGarden());
		button1.setOnAction(new EventHandler<ActionEvent>() {
	            @Override
	            public void handle(ActionEvent e) {
	                if(true) {
	                	controller.model.setConditions(soilPH.getValue().toString(),shade.getValue().toString(),moisture.getValue().toString());
                        controller.viewGarden();
	                }
	            	/*
	            	if (soilPH.getValue().toString() != "Soil pH (Choose One):" &&shade.getValue().toString() != "Shading (Choose One):"&& moisture.getValue().toString() != "Soil Moisture (Choose One):"){
	                        controller.model.setConditions(soilPH.getValue().toString(),shade.getValue().toString(),moisture.getValue().toString());
	                        controller.viewGarden();
	                }
	                */
	                else {
	                     error.setText("Please Choose Conditions of Garden");
	                     error.setFont(Font.font ("Green",FontWeight.BOLD, 12));
	                     error.setFill(Color.RED);
	                }
	            }
	        });
		
		button3.setOnAction(e -> clearAndDrawPalette(controller));
		VBox layout1 = new VBox(20);
		
		button4.setOnAction(e-> {
			try {
				controller.viewDecorations(iv);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		//adds items to vbox 
		layout1.getChildren().addAll(words, button1, button3, button4,soilPH,moisture,shade,error);
		
		BorderPane main=new BorderPane();
		main.setLeft(layout1);
		main.setCenter(root);
		main.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		scene = new Scene(main);
		
		controller.primStage.setScene(getDrawScene());
		controller.primStage.setResizable(false);
		controller.primStage.setTitle("Draw Garden");
		
		//stage.setScene(scene);
		//controller.primStage.show();
	}
   /**Contructor to set up paint object
	*@author Chris Tiso
	*/
	public Paint() {
		canvas = new Canvas(1000, 700);
		g = canvas.getGraphicsContext2D();

	}
	/** Gets Canvas
	 * @author Chris Tiso
	 * @return the canvas
	 */
	public Canvas getCanvas() {
		return canvas;
	}
	/** Getter used to return the done property
	 * @author Chris Tiso
	 * @return the done property
	 */
	public boolean getDone() {
		return done;
	}

	/** Changes canvas to color wherever mouse is being dragged
	 * @param imc  Controller Instance
	 * @author Chris Tiso
	 */
	public void clearAndDrawPalette(Controller imc) {
		cleared=true;
		int width = (int) canvas.getWidth(); 
		int height = (int) canvas.getHeight(); 

		g.setFill(Color.GREEN);
		g.fillRect(0, 0, width, height);
		
		createGrid(imc);
		g.setStroke(Color.BLACK);
		g.setLineWidth(3);
		g.strokeRect(1.5, 1.5, width - 3, height - 3);
	
		
	}

	/** Mouse event handler while drawing on the canvas
	 * @param evt  Mouse event
	 * @author Chris Tiso
	 */
	public void mousePressed(MouseEvent evt) {

		if (dragging == true) // Ignore mouse presses that occur
			return; // when user is already drawing a curve.

		int x = (int) evt.getX(); // x-coordinate where the user clicked.
		int y = (int) evt.getY(); // y-coordinate where the user clicked.

		int width = (int) canvas.getWidth(); // Width of the canvas.
		int height = (int) canvas.getHeight(); // Height of the canvas.
		
		 
		if (x > 3 && x < width - 10 && y > 3 && y < height - 3 && cleared) {
			// The user has clicked on the white drawing area.
			// Start drawing a curve from the point (x,y).
			prevX = x;
			prevY = y;
			dragging = true;
			g.setLineWidth(3); // Use a 2-pixel-wide line for drawing.
			g.setStroke(palette[0]);
		}

	}
	/**Stops user from being able to draw more than once unless clear button is pressed. Also saves image of the drawing
	 *@param evt  Mouse Event
	 *@author Chris Tiso
	 */
	public void mouseReleased(MouseEvent evt) {
		dragging = false;
		//this.img=canvas.snapshot(new SnapshotParameters(), new WritableImage(850,800));
	}
	
   /** Sets image of garden in the controller
    * @param imc  Controller Instance 
    * @author Chris Tiso
	*/
	public void setGarden(Controller imc) {
		g.setLineWidth(0);
		//createGrid(imc);
		SnapshotParameters snap=new SnapshotParameters();
		//snap.setFill(Color.LIGHTGREEN);
		//g.setFill(Color.LIGHTGREEN);
		
		this.img=canvas.snapshot(snap, new WritableImage(800,800));
		imc.model.currGarden.garden=this.img;
		imc.garden=new ImageView(this.img);
	}
	/** Handler Function when dragging mouse
	 * @param evt  Mouse Event
	 * @author Chris Tiso
	 */
	public void mouseDragged(MouseEvent evt) {

		if (dragging == false)
			return; // Nothing to do because the user isn't drawing.

		double x = evt.getX(); // x-coordinate of mouse.
		double y = evt.getY(); // y-coordinate of mouse.

		if (x < 3) // Adjust the value of x,
			x = 3; // to make sure it's in
		if (x > canvas.getWidth() -10) // the drawing area.
			x = (int) canvas.getWidth() -10;

		if (y < 3) // Adjust the value of y,
			y = 3; // to make sure it's in
		if (y > canvas.getHeight() - 4) // the drawing area.
			y = canvas.getHeight() - 4;

		g.strokeLine(prevX, prevY, x, y); // Draw the line.

		prevX = x; // Get ready for the next line segment in the curve.
		prevY = y;
		cleared = false;
	}

	/** Function used to create the paint's grid 
	 * @param imc   Controller instance
	 * @author Chris Tiso
	 */
	public void createGrid(Controller imc) {
		g.setLineWidth(2);
		g.setStroke(Color.LIGHTGRAY);
		int xLines=8;
		int yLines=8;
		double xBoxLen =  imc.model.getLength()/xLines;
		double yBoxLen =  imc.model.getLength()/yLines;
		//imc.model.setXGridBoxLen(xBoxLen);
		//imc.model.setYGridBoxLen(yBoxLen);
		//System.out.println(imc.model.getLength());
		//System.out.println(imc.model.getHeight());
		imc.view.xFtPerPix = imc.model.getLength()/800.0;
		imc.view.yFtPerPix = imc.model.getHeight()/800.0;
		for(int i=0;i<=xLines;i++) {
			int x=i*100;
			g.moveTo(x, 0);
			g.lineTo(x, 800);
			g.stroke();
		}
		for(int j=0;j<=yLines;j++) {
			int y=j*100;
			g.moveTo(0, y);
			g.lineTo(800, y);
			g.stroke();
		}
		
	}

}
