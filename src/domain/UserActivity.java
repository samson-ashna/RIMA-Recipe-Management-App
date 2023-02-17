package domain;

import objects.User;
import persistence.UserDAOImpl;
import persistence.UsersDAO;

//created a class to keep track of the user's activity regarding their username and password
public class UserActivity {
	static User currentUser;
	//User database is accessed through the data access object, UsersDAO.
	static UsersDAO usersinfo = new UserDAOImpl();
	//this method checks whether the user with the name userName exists in the users database.
	public static boolean checkUserName(String userName) {
		boolean userExists = false;
		if(usersinfo.get(userName)!=null) {
			userExists = true;
		}
		return userExists;
	}
	
	//this method checks whether the password matches the user's login username saved in the database
	public static boolean checkPassword(String userName,String password) {
		return usersinfo.get(userName).checkPassword(password);
	}
	
	//the current logged in user gets their information from the database
	public static User getCurrentUser() {
		for(User user : usersinfo.getAll()) {
			if(user.loggedIn) {
				return user;
			}
		}
		return null;
	}
}
