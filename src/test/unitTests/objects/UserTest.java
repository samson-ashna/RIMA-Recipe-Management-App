package test.unitTests.objects;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import objects.Recipes;
import objects.User;
import persistence.DatabaseAccess;

class UserTest {
	
	User user;
	ArrayList<Recipes> expected;
	Recipes r;
	@BeforeEach
	void init() {
    	DatabaseAccess.databaseType =1;

		user=new User("jake","123");
		expected= new ArrayList<>();
		r = new Recipes("name",1,2);
		expected.add(r);
	}
	
	@Test
	void testGetName() {
		String expected= "jake";
		assertEquals(expected, user.getName(),"checking is correct user is returned by name");
	}
	@Test
	void testCheckPassword() {
		String expected = "123";
		assertTrue(user.checkPassword(expected));
	}
	@Test
	void testCheckPassword2() {
		String expected = "12fg";
		assertFalse(user.checkPassword(expected));
	}
	@Test
	void testsetPassword() {
		String newPass = "45";
		user.setPassword("45");
		assertTrue(user.checkPassword(newPass));
	}
	@Test
	void testSetName() {
		String expected = "user1";
		user.setName(expected);
		assertEquals(expected,user.getName());
	}
	@Test
	void testaddRecipeToCollection() {
		user.addRecipeToCollection(r);
		assertIterableEquals(expected,user.getRecipeCollection());
		
	}
	@Test
	void removeRecipeFromCollection() {
		user.removeRecipeFromCollection(r);
		assertTrue(user.getRecipeCollection().size()==0);
	}
	

}
