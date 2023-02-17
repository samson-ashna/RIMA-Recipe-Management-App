package domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class UserActivityTest {
	UserActivity useractivity;

	@Test
    public void checkPasswordTest() {
		String password = "secret-pass";
		String username = "";
		UserActivity useractivity = new UserActivity();
		boolean useractivityactual = useractivity.checkPassword(username, password);
	    boolean useractivityexpected = true;
	    assertEquals(useractivityexpected,useractivityactual);
	}
	
	@Test
    public void getCurrentUserTest() {
		return;
	}
}
	
//	@Test
//  public void checkPasswordTest() {
//		UserActivity useractivity = new UserActivity();
//      String password = "secret-pass";
//      useractivity.checkPassword("some-random-username", "some-random-secret-password");
//      String useractivityactual = useractivity.checkPassword(password);
//      assertNotSame(password, useractivityactual);
//      boolean useractivityexpected = useractivity.checkPassword(useractivityactual, useractivityexpected);
//      assertEquals(useractivityexpected, password);
//  }

