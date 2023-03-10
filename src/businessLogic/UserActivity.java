package businessLogic;

import objects.Recipes;
import objects.User;
import persistence.UsersStubDB;
import persistence.DatabaseAccess;
import persistence.DAO;
import persistence.UsersDAO;

/**
 * Class to keep track of the user's activity regarding their username and password
 */
public class UserActivity {
	
	public static User currentUser;
	public static int RecipeIDs=2;

	//User database is accessed through the data access object, UsersDAO.
	DatabaseAccess access = new DatabaseAccess();
	UsersDAO usersinfo = access.usersDB();

	/**
	 * This method checks whether the user with the name userName exists in the users database.
	 * @param userName
	 * @return
	 */
	public boolean checkUserName(String userName) {
		boolean userExists = false;
		if(usersinfo.get(userName)!=null) {
			userExists = true;
		}
		return userExists;
	}
	
	/**
	 * This method checks whether the password matches the user's login username saved in the database
	 * @param userName
	 * @param password
	 * @return
	 */
	public boolean checkPassword(String userName,String password) {
		return usersinfo.get(userName).checkPassword(password);
	}
	
	/**
	 * The current logged in user gets their information from the database
	 * @return
	 */
	public static User getCurrentUser() {
		return currentUser;
	}
	public static void setCurrentUser(User u) {
		currentUser =u;
	}
}
