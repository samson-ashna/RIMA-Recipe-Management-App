package test.integrationTests.persistence;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import objects.Ingredient;
import objects.Recipes;
import objects.User;

class DatabasesTest {
	static DatabaseAccess access;
	static DAO<Recipes> db;
	static UsersDAO dbUsers;
	static Recipes recipe1;
	static Recipes recipe2;
	static User user;
	static User user2;
	static Recipes r;
	@BeforeAll
	static void setUp() {
		//database_setup.sql under database folder has to be run first, each time this test file is run.
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
		recipe1 = new Recipes("Fish",2,3);
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
	void editFavouritesTest() {
		for(Recipes r: dbUsers.getRecipes(user)) {
			if(r.getName().strip().equals(recipe1.getName())) {
				dbUsers.editFavorites(r, 1);
			}
		}
		assertTrue(dbUsers.getFavoriteList(user).size()==1);
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
	@Test
	void editPlannerTest() {
		user.getWeekPlanner().get("Monday").addToPlanner("Monday", "Breakfast","Cake");
		dbUsers.editPlanner(user,"Monday","Breakfast", "Cake"); 
		assertTrue(dbUsers.get(user.getName()).getWeekPlanner().get("Monday").getBreakfast().contains("Cake"));
	}
	@Test
	void editShoppingListTest() {
		boolean item = dbUsers.get(user.getName()).getShoppingList().contains("Salt");
		dbUsers.editShoppingList("Salt", user.getName());
		assertFalse(dbUsers.get(user.getName()).getShoppingList().contains("Salt")==item);
	}
	@Test
	void editAllergyTest() {
		dbUsers.editAllergy(user, "Milk", 1);
		assertTrue(dbUsers.get(user.getName()).getUserAllergies().getAllergies().get("Milk")==1);
	}
	@Test
	void updateIngredientTest() {
		ArrayList<Ingredient> lst = new ArrayList<Ingredient>();
		Ingredient i = new Ingredient("pasta", 20.00, LocalDate.now(), 20, 30, user.getName());
		i.setUser(user);
		lst.add(i);
		user.addIngredientToCollection(i);
		dbUsers.updateIngredients(user, user.getIngredients());
		boolean found = false;
		for(Ingredient ingredient: dbUsers.getIngredients(user)) {
			if(ingredient.getName().strip().equals(i.getName())) {found=true;}
		}
		assertTrue(found);
	}
}
