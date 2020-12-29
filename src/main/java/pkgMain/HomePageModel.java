package pkgMain;
/** Model Class used to obtain user's attributes. Not connected to main yet.
 * @author Cheickna Sacko
 */
public class HomePageModel {

	private String username;
	
	/** HomePageModel constructor to set up the username 
	* @author Cheickna Sacko
	* @param username String for the username
	*/
	public HomePageModel(String username) {
		this.username = username;
	}

	
   /** Getter function to return the username
	* @author Cheickna Sacko
	* @return the username
	*/
	public String getUsername() {
		return username;
	}

}
