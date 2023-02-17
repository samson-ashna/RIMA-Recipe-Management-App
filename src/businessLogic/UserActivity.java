package businessLogic;

import objects.User;
import persistence.UserDAOImpl;
import persistence.UsersDAO;

/**
 * Class to keep track of the user's activity regarding their username and password
 */
public class UserActivity {
	
	public static User currentUser;

	//User database is accessed through the data access object, UsersDAO.
	static UsersDAO usersinfo = new UserDAOImpl();

	/**
	 * This method checks whether the user with the name userName exists in the users database.
	 * @param userName
	 * @return
	 */
	public static boolean checkUserName(String userName) {
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
	public static boolean checkPassword(String userName,String password) {
		return usersinfo.get(userName).checkPassword(password);
	}
	
	/**
	 * The current logged in user gets their information from the database
	 * @return
	 */
	public static User getCurrentUser() {
		for(User user : usersinfo.getAll()) {
			if(user.loggedIn) {
				return user;
			}
		}
		return null;
	}
}
