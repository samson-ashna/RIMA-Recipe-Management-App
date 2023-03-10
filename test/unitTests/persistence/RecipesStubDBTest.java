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
import persistence.DatabaseAccess;

class RecipesStubDBTest {

	static RecipesStubDB recipesDB;
	static Recipes recipe;
	static ArrayList<Recipes> expected= new ArrayList<>();
	@BeforeAll
	static void init() {
    	DatabaseAccess.databaseType =1;

		recipesDB = new RecipesStubDB();
		recipe = new Recipes("cookie",23,34);
		
		for(Recipes r: recipesDB.getAll()) {
			expected.add(r);
		}
		recipesDB.add(recipe);
		expected.add(recipe);
	}
	@Test
	void testGetUser() {
		assertEquals(recipe,recipesDB.get("cookie"));
	}
	
	@Test
	void testGetUser2() {
		assertNull(recipesDB.get("brownies"));
		
	}
	@Test
	void testGetAllUsers() {
		assertIterableEquals(expected,recipesDB.getAll());
	}
	@Test
	void testAdd() {
		Recipes recipe2 = new Recipes("pasta",23,34);
		recipesDB.add(recipe2);
		expected.add(recipe2);
		assertTrue(recipesDB.getAll().contains(recipe2));
		
	}
	@Test
	void testRemove() {
		recipesDB.remove(recipe);
		assertFalse(recipesDB.getAll().contains(recipe));
	}	

}
