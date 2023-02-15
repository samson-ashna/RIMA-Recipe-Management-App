package domain;

import objects.User;
import persistence.UserDAOImpl;
import persistence.UsersDAO;

public class UserActivity {
	static User currentUser;
	static UsersDAO usersinfo = new UserDAOImpl();
	public static boolean checkUserName(String userName) {
		boolean userExists = false;
		if(usersinfo.get(userName)!=null) {
			userExists = true;
		}
		return userExists;
	}
	public static boolean checkPassword(String userName,String password) {
		return usersinfo.get(userName).checkPassword(password);
	}
	
	public static User getCurrentUser() {
		for(User user : usersinfo.getAll()) {
			if(user.loggedIn) {
				return user;
			}
		}
		return null;
	}
}
