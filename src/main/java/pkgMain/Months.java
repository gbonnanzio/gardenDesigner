package pkgMain;

import javafx.scene.paint.Color;

/**
 * Class used to correspond a month with a season and where that month is located on the GridPane. used in BloomCalendarView
 * @author Geoffrey Bonnanzio
 */

public class Months {
	//properties
	public String name;
	public String season;
	public Color color; 
	public int row;
	public int col;
	
	//Constructor 
	
	/** Constructor used to set the month's attributes
	 * @param name String for month
	 * @param season String for season of the year
	 * @param color String for color
	 * @param row int for number of row
	 * @param col int for number of col
	 */
	Months(String name, String season, Color color, int row, int col){
		this.name = name;
		this.season = season;
		this.color = color;
		this.row = row;
		this.col = col;
	}
	
}
