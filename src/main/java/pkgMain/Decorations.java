package pkgMain;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/** Decorations Class to handle Decorations images
 * @author Madi Freed
 */
public class Decorations {

	String img_path;
	public Image img;
	public ImageView iv;
	String name;
	
	/** Decorations Class's constructor to initialize decoration's elements
	 * @author Madi Freed
	 * @param s String representing name of Decoration
	 */
	public Decorations(String s) {
		this.name = s;
		this.img_path = "/decorations/" + s.replace(" ", "_") + ".jpg";
		this.img = new Image(this.img_path);
		this.iv = new ImageView(this.img);
	}
	
}
