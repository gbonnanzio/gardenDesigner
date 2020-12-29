package pkgMain;
import java.util.*;
import java.lang.*;


import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import pkgMain.Model;
import pkgMain.View;
import pkgMain.Paint;

public class Controller extends Application{
		View view;
		Model model;
		private final boolean DEBUG = true;
		Paint paint;
		Stage primStage;
		ImageView garden;
		
		@Override
		public void start(Stage stage) throws InterruptedException {
			paint=new Paint();
			primStage=stage;
			
	        model = new Model();
			view = new View();
			paint.SimplePaintView(this);
			System.out.print("gets here");
	        
	        
			
			stage.show();	
		}
		public void viewGarden() {
			paint.setGarden(this);
			view.viewView(this);
		}
		public void drag(MouseEvent event) {
			
			Node n = (Node)event.getSource();
			if (DEBUG) System.out.println("ic mouse drag tx: " + n.getTranslateX() + ", ex: " + event.getX() );
			model.setX(model.getX() + event.getX()); //event.getX() is the amount of horiz drag
			model.setY(model.getY() + event.getY());
			view.setX( model.getX() );
			view.setY( model.getY() );
			
			
			//n.setTranslateX(n.getTranslateX() + event.getX()); //not MVC! what problem does this create?
			//n.setTranslateY(n.getTranslateY() + event.getY());
		}
		public void release(MouseEvent event) {
			//Node n = (Node)event.getSource();
			Node n = (Node)event.getSource();
			System.out.println("Exited");
			view.addNewPanes(this);
			view.currIV.setOnMouseDragged(this.getHandlerForDrag());
			view.currIV.setOnMouseReleased(this.getHandlerForExit());
		}
		public EventHandler getHandlerForDrag() {
			
			return event -> drag((MouseEvent) event);
		}
		public EventHandler getHandlerForExit() {
			return event -> release((MouseEvent) event);
		}
		
		
		public void setHandlerForDrag(ImageView iv1) {
			iv1.setOnMouseDragged(event -> drag(event));		
		}
		public void setHandlerForExit(ImageView iv1) {
			iv1.setOnMouseDragExited(event -> release(event));		
		}
		
	/*	public void tellModelStartingCoords(double x, double y) {
			model.setX(view.getImgStartingX());
			model.setY(view.getImgStartingY());
			if (DEBUG) System.out.println("start: " + model.getX() + " " + model.getY());
		}
	*/
		public double getStartingX() {
			return model.getX();
		}
		public double getStartingY() {
			return model.getY();
		}
		
		public static void main(String[] args) {
	        launch(args);
	    }
		
		
   
}
