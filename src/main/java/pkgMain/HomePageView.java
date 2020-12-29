package pkgMain;

import javafx.scene.paint.Color;


import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
//import com.sun.prism.Image;

import javafx.geometry.Orientation;

import java.awt.event.ActionListener;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import pkgMain.Controller;
/** Class to handle HomePageView
 * @author Chris Tiso 
 */
public class HomePageView {
	
	private Text sceneTitle;
	private TilePane tPane;
	public Scene scene;
	public GridPane pane;
	private static int WIDTH = 1000;
	private static int HEIGHT = 175;
	private static int VERTICAL = 0;
	private static int HORIZONTAL = 20;
	final FileChooser fileChooser = new FileChooser();
	private Desktop desktop = Desktop.getDesktop();
	
	
   /** HomePageView constructor to set up the screen's properties
	* @author Chris Tiso 
	* @param imc  Controller Instance
	*/
	public HomePageView(Controller imc) {
		pane = new GridPane();
		pane.setVgap(VERTICAL);
		pane.setHgap(HORIZONTAL);
		pane.setPadding(new Insets(12));
		//pane.setGridLinesVisible(true);
//		buttons		labels and fields
		Label usernameLabel1 = new Label("Length(ft):");//x
		TextField usernameField1 = new TextField();
		usernameField1.setPromptText("Enter Length:");
		usernameLabel1.setTextFill(Color.BLACK);
		Label usernameLabel2 = new Label("Width(ft):");//y
		TextField usernameField2 = new TextField();
		usernameField2.setPromptText("Enter Width:");
		usernameLabel2.setTextFill(Color.BLACK);
		
		Button enterBtn = new Button("Draw Garden");
		enterBtn.setMaxWidth(Double.MAX_VALUE);
		enterBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			    public void handle(ActionEvent e) {
			        if (usernameField1.getText() != null && usernameField1.getText() !=null) {
			            try {
			            	int x=Integer.parseInt(usernameField1.getText());
			            	int y=Integer.parseInt(usernameField2.getText());
			            	if(x>0 &&y>0) {
			            		imc.model.setSize(x,y);
			            		imc.viewDraw();
			            	}
			        
			            }catch(NumberFormatException error) {
			            	usernameField1.clear();
			            	usernameField2.clear();
			            	usernameField1.setPromptText("Enter Positive Integer");
			            	usernameField2.setPromptText("Enter Positive Integer");
			            }
			        } else {
			        	usernameField1.clear();
		            	usernameField2.clear();
			        	usernameField1.setPromptText("Enter Positive Integer");
		            	usernameField2.setPromptText("Enter Positive Integer");
			        }
			     }
			 });
		
		Button loadBtn=new Button("Load Save Files");
		loadBtn.setOnAction(e->imc.viewLoad());
		//loadBtn.setMaxWidth(Double.MAX_VALUE);
		Button picture=new Button("Upload Picture");
		picture.setOnAction(new EventHandler<ActionEvent>() {
	                @Override
	                public void handle(ActionEvent e) {
	                    File file = fileChooser.showOpenDialog(imc.primStage);
	                    if (file != null) {
	                    	String imagepath = null;
							try {
								imagepath = file.toURI().toURL().toString();
							} catch (MalformedURLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
	                        System.out.println("file:"+imagepath);
	                        Image image = new Image(imagepath);
	                        imc.model.currGarden.garden=image;
	                        
	                        ImageView imageView = new ImageView(image);
	                        //imc.model.setSize(100, 100);
	                        imageView.setFitWidth(800);
	                        imageView.setFitHeight(800);
	                        //imc.view.xFtPerPix=1.0/8.0;
	                        //imc.view.yFtPerPix=1.0/8.0;
	                        imc.garden=imageView;
	                        
	                        if (usernameField1.getText() != null && usernameField1.getText() !=null) {
	    			            try {
	    			            	int x=Integer.parseInt(usernameField1.getText());
	    			            	int y=Integer.parseInt(usernameField2.getText());
	    			            	if(x>0 &&y>0) {
	    			            		imc.model.setSize(x,y);
	    			            		imc.view.setFtPerPix(x, y);
	    			            		imc.pop.PicLoadView(imc);
	    			            		imc.viewGardenUpload();
	    			            	}
	    			            	else {
	    			            		usernameField1.clear();
		    			            	usernameField2.clear();
		    			            	usernameField1.setPromptText("Enter Positive Integer");
		    			            	usernameField2.setPromptText("Enter Positive Integer");
	    			            	}
	    			            	
	    			            }catch(NumberFormatException error) {
	    			            	usernameField1.clear();
	    			            	usernameField2.clear();
	    			            	usernameField1.setPromptText("Enter Positive Integer");
	    			            	usernameField2.setPromptText("Enter Positive Integer");
	    			            }
	    			        } else {
	    			        	usernameField1.clear();
	    		            	usernameField2.clear();
	    			        	usernameField1.setPromptText("Enter Positive Integer");
	    		            	usernameField2.setPromptText("Enter Positive Integer");
	    			        }
	                      
	                        
	                   
	                    }
	                }

					private void openFile(File file) {
						   try {
					            desktop.open(file);
					        } catch (IOException ex) {
					            Logger.getLogger(
					                FileChooser.class.getName()).log(
					                    Level.SEVERE, null, ex
					                );
					        }
					    }
						

	            });
		//picture.setMaxWidth(100);
		
//		labels and fields
		
		
		sceneTitle = new Text();
		sceneTitle.setText("Welcome to the Delaware Garden Designer!");
		
		sceneTitle.setFont(Font.font("Green", FontWeight.BOLD, 25));
		sceneTitle.setFill(Color.DODGERBLUE);

		
		pane.add(sceneTitle, 2, 2);
		pane.add(usernameLabel1, 3, 0);
		pane.add(usernameField1, 3, 1);
		pane.add(usernameLabel2, 3, 3);
		pane.add(usernameField2, 3, 4);
		pane.add(enterBtn,4,3);
		pane.add(loadBtn,4,1);
		pane.add(picture,4,2);
		StackPane stack=new StackPane();
	
		stack.setBackground(new Background(new BackgroundFill(Color.LIGHTGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
		 stack.getChildren().add(pane);
		//tPane.getChildren().addAll(enterBtn);
		
		 
		//pane.add(tPane, 0, 3, 2, 1);
		scene=new Scene(stack,WIDTH,HEIGHT);
		imc.primStage.setScene(scene);
		imc.primStage.show();
		
	}
	
}
