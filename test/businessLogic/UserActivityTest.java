package businessLogic;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import businessLogic.UserActivity;
import objects.User;
import persistence.DatabaseAccess;
import persistence.UsersDAO;


class UserActivityTest {
	UserActivity activity = new UserActivity();
	DatabaseAccess access = new DatabaseAccess();
	UsersDAO dao = access.usersDB();
	User user = new User("a","12");
	@BeforeEach
	void init() {
		dao.add(user);
		user.loggedIn = true;
		UserActivity.setCurrentUser(user);
	}
	@Test
	void testCheckUserName() {
		assertTrue(activity.checkUserName("a"));
	}
	@Test
	void testCheckUserName2() {
		assertFalse(activity.checkUserName("A"));
	}
	@Test
	void testCheckPassword() {
		assertTrue(activity.checkPassword(user.getName(),"12"));
	}
	@Test
	void testCheckPassword2() {
		assertFalse(activity.checkPassword(user.getName(),"42"));
	}
	@Test
	void testGetCurrentUser() {
		assertEquals(user, UserActivity.getCurrentUser());
	}

	@Test
    public void checkPasswordTest() {
		String password = "secret-pass";
		String username = "sdf";
		User user2 = new User(username,password);
		dao.add(user2);
		boolean useractivityactual = activity.checkPassword(username, password);
	    boolean useractivityexpected = true;
	    assertEquals(useractivityexpected,useractivityactual);
	}
}
