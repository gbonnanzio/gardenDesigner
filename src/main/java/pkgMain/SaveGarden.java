package pkgMain;

import java.util.ArrayList;
/** This class is used to handle the data manipulated for saving's purposes
 *  @author Chris Tiso
 */

public class SaveGarden {
	int length;
	int width;
	double size;
	String shading;
	String soil_moist;
	String soil_ph;
	ArrayList<Plant> saved;
	
	/** Class's constructor attributing values to each attributes
	 *  @author Chris Tiso
	 *  @param x  int for length
	 *  @param y  int for width
	 *  @param shade  string for shade's state
	 *  @param moist  string for moist's state
	 *  @param pH  string for pH's condition
	 *  @param file  Arraylist for file contaning plants
	 */
	public SaveGarden(int x, int y, String shade,String moist,String pH,ArrayList<Plant> file) {
		this.length=x;
		this.width=y;
		this.shading=shade;
		this.soil_moist=moist;
		this.soil_ph=pH;
		this.saved=file;
	}
}
