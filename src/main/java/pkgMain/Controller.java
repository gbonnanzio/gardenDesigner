package pkgMain;
import java.util.*;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.stage.Stage;


/** This is the main Controller for the whole application. In here will be the methods that will allow different View classes to talk to each other, as well as get information 
 * from the Model class
 * @author  team 8
 */

public class Controller extends Application{
	
		GardenView view;
		Model model;
		private final boolean DEBUG = true;
		Paint paint;
		Stage primStage;
		ImageView garden;
		WildlifeView wild;
		LoadFilesView load;
		BloomCalendarView bloom;
		DecorationsView dec;
		ScoreView score;
		ScoreModel scoreM;
		String s;
		SaveFilesView save;
		HomePageView home;
		boolean dragging = true;
		boolean stillOnImg = false;
		boolean grid=true;
		boolean firstDrag = true;
		double diff = 0;
		double xStart;
		double yStart;
		paintbuttons info;
		newdecor decorations;
		public double shiftVal;
		HelpView help;

		PopUpView pop;

		DecorationsView decorScreen;

		
		
		@Override
		public void start(Stage stage) throws InterruptedException {
			model = new Model();
			paint=new Paint();
			primStage=stage;
			bloom=new BloomCalendarView();
			view = new GardenView(this);
			dec=new DecorationsView();
			wild=new WildlifeView();
			load=new LoadFilesView();
			score=new ScoreView();
			scoreM=new ScoreModel();
			save=new SaveFilesView();
			help=new HelpView();
			pop=new PopUpView();
			
			//paint.SimplePaintView(this);
			info = new paintbuttons();
			decorations = new newdecor();
			home=new HomePageView(this);
			
			decorScreen=new DecorationsView();
			
			stage.show();	
		}
		
		/** Loads Drawing Screen  
		 * @author Chris Tiso
		 */
		
		public void viewDraw() {
			paint.SimplePaintView(this, garden);
		}
		
		
		/** sets Image of garden then Loads garden
		 * @author Chris Tiso
		 */
	
		public void viewGarden() {
			paint.setGarden(this);
			view.viewView(this);
		}
		/** Shows HelpScreen
		 * @author Chris Tiso
		 */
		public void viewHelp() {
			help.showHelp(this);
		}
		
		/** Loads Garden from file chosing screen  
		 * @author Chris Tiso
		 */
		public void viewGardenUpload() {
			view.viewView(this);
		}
		
		/** Shows Wildlife screen
		 * @author Madi Freed
		 */
		public void viewWildlife() {
			
			model.getGardenWildlife();
			wild.viewWild(this);
		}
		
		/** Shows Decoration screen
		 * @author Madi Freed
		 */
		public void viewDecorScreen() {
			decorScreen.viewDecor();
		}

		/** Shows Load File Screen 
		 * @author Chris Tiso
		 */
	
		public void viewLoad() {
			load.showLoadFileView(this);
		}
		
		
		/** Shows Bloom Calendar Screen
		 * @author Geoff Bonnanzio
		 */
		public void viewBloom() {
			grid=true;
			bloom.BloomView(this);
		}
		
		/** Shows Decorations Screen
		 * @author Chris Tiso
		 */
		
		public void viewDec() {
			grid=false;
		}
		
		/** Shows Score Screen
		 * @author Chris Tiso
		 */
		
		public void viewScore() {
			s = scoreM.calcScore(this, null, garden);
			score.scoreScreen(this, null, garden);
		}
		
		/** Shows Save Screen
		 * @author Chris Tiso
		 */
		
		public void viewSave() {
			save.SaveFilesView(this);
		}
		
		
		/** Shows More InfoScreen
		 * @param iv    ImageView
		 * @author Prithvi Arora
		 */
		
		public void viewMoreInfo(ImageView iv) {
            info.start(primStage, this, iv);
        }
		
			
		public void viewDecorations(ImageView iv) throws Exception {
            decorations.start(primStage);

}

		/** 
		 * drag handles a drag event on an image click either in the scroll pane 
		 * or in the garden. This method will move the image across the screen as 
		 * it is dragged and stores the positions of the images in the Plant object
		 * each ImageView is associated with.
		 * @param event      the MouseEvent that triggered drag on the ImageView
		 * @author Geoffrey Bonnanzio 
		 * @author Chris Tiso
		 */
		public void drag(MouseEvent event) {
			// Image being dragged
	        ImageView n = (ImageView)event.getSource();
	        // disable hover while dragging
	        n.setMouseTransparent(true);
	        n.setFitHeight(view.getSpImgHeight());
	        n.setFitWidth(view.getSpImgWidth());
	        // get index of image clicked 
        	int scrollPaneIndx = view.imagesInScrollPane.indexOf(n);
        	
        	// if the plant is already in the garden 
	        if(scrollPaneIndx == -1) {
	        	view.removeDropDown();
	        	model.setScrollPaneIndex(-1);
	        	// plant movement
	        	model.setX(event.getSceneX()-n.getLayoutX()-view.getSpWidth()-view.getSpImgWidth()/2);
		        model.setY(event.getSceneY()-n.getLayoutY()-view.getSpImgHeight()/2);
		        view.setX(event.getSceneX()-n.getLayoutX()-view.getSpWidth()-view.getSpImgWidth()/2,n);
		        view.setY(event.getSceneY()-n.getLayoutY()-view.getSpImgHeight()/2,n);
		        
	        }
	        // if the plant is coming from the scroll pane
	        else {
	        	Plant ivPlant = view.plantsInScrollPane.get(scrollPaneIndx);
	        	model.setCurrPlant(ivPlant);
	        	
	        	// make a replacement plant and store replacement index in model
	        	model.makeReplacementPlant(scrollPaneIndx,ivPlant);
	        	model.setX(event.getSceneX());
	        	n.toFront();
	        	shiftVal = (3590)*view.scrollPane.getVvalue();
	        	model.setX(event.getSceneX()-75);
		        model.setY(event.getSceneY()-n.getLayoutY()+shiftVal-50);
		        view.setX(event.getSceneX()-75,n);
		        view.setY(event.getSceneY()-n.getLayoutY()+shiftVal-50,n);
		        //n.toFront();
	        	
	        }
		}
		/** 
		 * release handles the event of when an image is released from drag into the garden
		 * This method also deals with replacing the scroll pane images and adding the new image
		 * to the screen, as well as the associated plant to the current garden list and the saved list.
		 * @param event    MouseEvent that triggered the release
		 * @author Chris Tiso 
		 * @author Geoffrey Bonnanzio 
		 */
		public void release(MouseEvent event) {
			firstDrag = true;
			// get image clicked
			ImageView n = (ImageView)event.getSource();
			// re-enable mouse events to this node from drag disable
			n.setMouseTransparent(false);
			// remove drop down if on the screen 
        	view.removeDropDown();
        	//System.out.println("mouse location: x: "+ event.getSceneX()+" y: "+event.getSceneY());
        	
        	//n.setPreserveRatio(true);
        	
        	//for testing currently
        	
        	//n.setFitHeight(100);
        	
        	
        	// if the plant is already in the garden
        	if(model.replacementIndx == -1) {
        		
        		Plant tmpPlant = model.currGarden.selectPlants.get(n);
        		// change coordinates of plant 
        		view.addStack(tmpPlant,this,model.getX(),model.getY(),n,false);
				model.currGarden.movePlant(model.getX(),model.getY(),tmpPlant);
				//view.addStack(tmpPlant,this,model.getX(),model.getY(),n,false);
				model.setX(0);
				model.setY(0);
				
			}
			else {
				// remove plant and imageview from scrollpane and add replacement plant
				Plant gardenPlant = view.plantsInScrollPane.remove(model.replacementIndx); 
				view.imagesInScrollPane.remove(model.replacementIndx); 
				view.plantsInScrollPane.add(model.replacementIndx,model.replacementPlant);
				view.imagesInScrollPane.add(model.replacementIndx,model.replacementPlant.iv);
			
				// reset the scrollpane to show all the plants
				view.resetScrollPane(this,model.replacementIndx);
				// add released image to screen
				view.addStack(gardenPlant,this,model.getX(),model.getY(),n,true);
				// add plant to garden 
				model.currGarden.addPlant(model.getX(),model.getY(),gardenPlant);
				//model.currGarden.gardenPlantsList.add(gardenPlant);

				model.setX(0);
				model.setY(0);
				
			}
        	
		}
		/**
		 * ReleaseLoad does the same action as release but is used when loading from saveFile
		 * @param iv     ImageView of plant, 
		 * @param p      the plant itself
		 * @author Christopher Tiso 
		 */
		public void releaseLoad(ImageView iv,Plant p) {
			System.out.println("gets to release load");
			//model.currGarden.addPlant2(p.xloc,p.yloc,p,iv);
			model.currGarden.selectPlants.put(iv, p);
			System.out.println(p.xloc);
			System.out.println(p.yloc);
			System.out.println("^^^^");
			view.addStack(p,this,p.xloc,p.yloc+4290,iv,true);
			//System.out.println("gets to release load");
			model.setX(0);
			model.setY(0);
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
		
		/** hoverEnter is called when the mouse is hovered over a
		 * plant currently in the garden and calls for a drop down menu 
		 * to be created
		 * @param event      MouseEvent for entering an ImageView
		 * @author Geoffrey Bonnanzio
		 */
		public void hoverEnter(MouseEvent event) {
			ImageView n = (ImageView)event.getSource();	
			view.makePlantDropDown(this,n);
		}
		
		/** hoverExit is called when the mouse leaves an ImageView of a
		 * plant currently in the garden and calls for a drop down menu 
		 * to be deleted
		 * @param event   MouseEvent for exiting an ImageView
		 * @author Geoffrey Bonnanzio
		 */
		public void hoverExit(MouseEvent event) {
			ImageView n = (ImageView)event.getSource();
			view.deletePlantDropDown(n,event);
		}
		
		/** hoverEnterDropDown is called when the mouse is hovered over the drop down
		 * menu that was created upon hovering over the ImageView to keep 
		 * the drop down menu open 
		 * @param event    MouseEvent for entering the drop down
		 * @author Geoffrey Bonnanzio and Christopher Tiso
		 */
		public void hoverEnterDropDown(MouseEvent event) {
			VBox n = (VBox)event.getSource();
			view.dropDownActive = true;
		}
		
		/** hoverExitDropDown is called when the mouse exits the drop down
		 * menu that was created in case the user leaves through the drop down
		 * menu instead of through the ImageView 
		 * @param event   MouseEvent for entering the drop down
		 * @author Geoffrey Bonnanzio and Christopher Tiso
		 */
		public void hoverExitDropDown(MouseEvent event) {
			VBox n = (VBox)event.getSource();
			view.removeDropDown();
		}
		
		/** This method is called when a gridPane is clicked on inside the Bloom Calander Screen
		 * @param event   Mouse Event
		 * @author Geoffrey Bonnanzio
		 */
		public void clickGridPane(MouseEvent event) {
		    Node clickedNode = event.getPickResult().getIntersectedNode();
		    if (clickedNode != bloom.mainGridPane) {
		        // click on descendant node
		    	Node parent = clickedNode.getParent();
		        while (parent != bloom.mainGridPane) {
		            clickedNode = parent;
		            parent = clickedNode.getParent();
		        }
		        Integer colIndex = GridPane.getColumnIndex(clickedNode);
		        Integer rowIndex = GridPane.getRowIndex(clickedNode);
		        
		        ArrayList<Plant> plantIvsInMonth = bloom.getPlantsForMonth(model.currGarden,rowIndex,colIndex);
		       
		        bloom.monthView(this,model.currGarden,plantIvsInMonth,rowIndex,colIndex); 
		    }
		}
		
	
		/** This method is called when a the mouse is clicked. Depending on which screen it will call different methods
		 * @author Geoff Bonnanzio
		 * @author Prithvi Arora
		 * @author Christopher Tiso
		 * @return EventHandler for clicked item
		 * 
		 */
		public EventHandler getHandlerForClick() {
			return event -> clickGridPane((MouseEvent) event);
		}
		/** This method sets the click handler
		 * @param gp    Gridpane used to setup click handler
		 * @author Geoff Bonnanzio 
		 * @author Prithvi Arora
		 */
		public void setHandlerForClick(GridPane gp) {
			if(grid) {
			gp.setOnMouseClicked(event -> clickGridPane(event));	}
		}
		
	   /** Deletes plant from garden when a plants delete button is pressed
		* @param tmpButton   The button Being pressed
		* @author Geoff Bonnanzio
		*/
		public void deletePlantFromGarden(ImageButton tmpButton) {
			Plant p = tmpButton.plant;
			
			// deleted in previous commit
			model.currGarden.gardenPlantsList.remove(p);
			model.currGarden.chosenPlants.remove(p.name);
			
			model.currGarden.selectPlants.remove(p.iv);
			int indx = model.currGarden.saved.indexOf(p);
			model.currGarden.saved.remove(indx);
			view.center.getChildren().remove(tmpButton.iv);
			//view.center.getChildren().remove(view.dropDown);
			//view.center.getChildren().remove(view.dropDown);
		}
		
		/** bcExit is called when the bloom calendar page is exited
		 * in order to remove the highlights over some of the plants
		 * @author Geoffrey Bonnanzio
		 */
		public void bcExit() {
			view.removePlantHighlights(this);
		}
		
		/**  closeLoadScreen is called when the loading screen is exited
		 * to remove the stage from the user's screen
		 * @author Geoffrey Bonnanzio 
 		 * @author Christopher Tiso 
		 */
		public void closeLoadScreen() {
			load.closeStage();
		}
		/** closeSaveScreen is called when the save screen is exited
		 * to remove the stage from the user's screen
		 * @author Geoffrey Bonnanzio 
 		 * @author Christopher Tiso 
		 */
		public void closeSaveScreen() {
			save.closeStage();
		}
		
		public static void main(String[] args) {
	        launch(args);
	    }
}