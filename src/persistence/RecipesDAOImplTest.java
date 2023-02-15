package persistence;

import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import org.junit.jupiter.api.*;
import objects.*;

class RecipesDAOImplTest {

	static RecipesDAOImpl recipesDB;
	static Recipes recipe;
	static ArrayList<Recipes> expected= new ArrayList<>();
	@BeforeAll
	static void init() {
		recipesDB = new RecipesDAOImpl();
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
