package test.unitTests.persistence;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import objects.Recipes;
import objects.User;
import persistence.DatabaseAccess;

class UsersStubDBTest {
	
	static UsersStubDB usersDB;
	static User user;
	static Recipes recipe;
	static ArrayList<User> expected= new ArrayList<>();
	static ArrayList<Recipes> expectedRecipes= new ArrayList<>();
	@BeforeAll
	static void init() {
    	DatabaseAccess.databaseType =1;

		usersDB = new UsersStubDB();
		user = new User("Tom", "123");
		usersDB.add(user);
		expected.add(user);
		recipe = new Recipes("cookie",23,34);
		expectedRecipes.add(recipe);
		user.addRecipeToCollection(recipe);
		
	}
	@Test
	void testGetUser() {
		assertEquals(user,usersDB.get("Tom"));
	}
	
	@Test
	void testGetUser2() {
		assertNull(usersDB.get("Sara"));
		
	}
	@Test
	void testGetAllUsers() {
		assertIterableEquals(expected,usersDB.getAll());
	}
	@Test
	void testAdd() {
		User user2 = new User("Jake", "123");
		usersDB.add(user2);
		expected.add(user2);
		assertTrue(usersDB.getAll().contains(user2));
		
	}
	@Test
	void testRemove() {
		usersDB.remove(user);
		assertFalse(usersDB.getAll().contains(user));
	}	
	@Test
	void testGetRecipes() {
		assertIterableEquals(expectedRecipes,usersDB.getRecipes(user));
	}
	@Test
	void testAddRecipes() {
		Recipes recipe2 = new Recipes("cake",23,34);
		expectedRecipes.add(recipe2);
		usersDB.addRecipes(user,recipe2);
		assertTrue(usersDB.getRecipes(user).contains(recipe2));
	}
	@Test
	void testRemoveRecipes() {
		usersDB.removeRecipes(user,recipe);
		expectedRecipes.remove(recipe);
		assertFalse(usersDB.getRecipes(user).contains(recipe));
	}
	
}
