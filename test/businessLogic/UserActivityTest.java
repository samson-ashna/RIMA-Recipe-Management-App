package domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import objects.User;
import persistence.UserDAOImpl;
import persistence.UsersDAO;


class UserActivityTest {
	
	UsersDAO dao = new UserDAOImpl();
	User user = new User("a","12");
	@BeforeEach
	void init() {
		dao.add(user);
		user.loggedIn = true;
	}
	@Test
	void testCheckUserName() {
		assertTrue(UserActivity.checkUserName("a"));
	}
	@Test
	void testCheckUserName2() {
		assertFalse(UserActivity.checkUserName("A"));
	}
	@Test
	void testCheckPassword() {
		assertTrue(UserActivity.checkPassword(user.getName(),"12"));
	}
	@Test
	void testCheckPassword2() {
		assertFalse(UserActivity.checkPassword(user.getName(),"42"));
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
		boolean useractivityactual = UserActivity.checkPassword(username, password);
	    boolean useractivityexpected = true;
	    assertEquals(useractivityexpected,useractivityactual);
	}
}
