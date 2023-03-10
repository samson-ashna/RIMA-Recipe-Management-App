package database;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import objects.Recipes;
import objects.User;
import persistence.DAO;
import persistence.DatabaseAccess;
import persistence.UsersDAO;

class DatabasesTest {
	DatabaseAccess access;
	DAO<Recipes> db;
	UsersDAO dbUsers;
	Recipes recipe1;
	Recipes recipe2;
	User user;
	User user2;
	Recipes r;
	@BeforeEach
	void setUp() {
		access = new DatabaseAccess();
		DatabaseAccess.databaseType =0;
		db = access.recipesDB();
		dbUsers=access.usersDB();
		//One of the recipes in database:From https://tasty.co/recipe/3-ingredient-teriyaki-chicken
		recipe2 = new Recipes("Teriyaki Chicken",48,20);
		String ingredients2 = "2 lb chicken thighs sliced into chunks,1 cup soy sauce(240 ml),1/2 cup brown sugar(110 g)";
		recipe2.setIngredients(ingredients2);
		recipe2.setInstructions("Sear the chicken thighs evenly in a pan, then flip.\r\n"
				+ "Add the soy sauce and brown sugar, stirring and bringing to a boil.\r\n"
				+ "Stir until the sauce has reduced and evenly glazes the chicken.");
		recipe2.setID(2);
		r = new Recipes("Cake",20,30);
		r.setID(3);
		recipe1 = new Recipes("Fish",2,3);
		recipe1.setID(4);
		user = new User("user1","1");
		user2 = new User("user2","2");
	}
	@Test 
	void testGetRecipeDB() {
		assertEquals(2,db.getAll().size());
	}
	@Test
	void testAddRecipe() {
		db.add(r);
		assertEquals(3,db.getAll().size());
	}
	@Test
	void testRemoveRecipe() {
		db.remove(r);
		assertEquals(2,db.getAll().size());
	}
	
	@Test 
	void testgetRecipeByNameTest() {
		assertEquals(1,db.get("Baked Potato").getRecipeID());
	}
	
	@Test
	void testAddUser() {
		dbUsers.add(user);
		assertEquals(1,dbUsers.getAll().size());
	}
	@Test
	void testGetUser() {
		assertTrue(dbUsers.get("user1").getPassword().equals(user.getPassword()));
	}
	@Test 
	void testAddUserRecipe1() {
		assertEquals(0,dbUsers.getRecipes(user).size());
	}
	@Test 
	void testAddUserRecipe2() {
		dbUsers.addRecipes(user, recipe1);
		assertEquals(1,dbUsers.getRecipes(user).size());
	}
	@Test
	void testRemoveRecipes() {
		dbUsers.removeRecipes(user,recipe1);
		assertEquals(0,dbUsers.getRecipes(user).size());
	}
	@Test
	void testEditUser() {
		dbUsers.edit("user1","user2","1");
		assertNull(dbUsers.get("user1"));
	}	
}
