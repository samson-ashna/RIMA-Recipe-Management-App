package test.unitTests.objects;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import persistence.DatabaseAccess;

class IngredientTest {

	
	Ingredient ingredient;
	
	@BeforeEach
	void init() {
    	DatabaseAccess.databaseType =1;
		ingredient = new Ingredient("pasta", 20.00, LocalDate.now(), 20, 30, "user");
	}
	@Test
	void getExpirationTest() {
		assertTrue(ingredient.getExpiration().equals(LocalDate.now()));
	}
	@Test
	void setExpirationTest() {
		ingredient.setExpiration(LocalDate.now().plusDays(1));
		assertFalse(ingredient.getExpiration().equals(LocalDate.now()));
	}
	@Test
	void getNameTest() {
		assertTrue(ingredient.getName().equals("pasta"));
	}
	@Test
	void setNameTest() {
		ingredient.setName("fish");
		assertFalse(ingredient.getName().equals("pasta"));
	}
	@Test
	void getProteinTest() {
		assertTrue(ingredient.getProtein()==20);
	}
	@Test
	void setProteinTest() {
		ingredient.setProtein(2);
		assertFalse(ingredient.getProtein()==20);
	}
	@Test
	void getCarbsTest() {
		assertTrue(ingredient.getCarbs()==30);
	}
	@Test
	void setCarbsTest() {
		ingredient.setCarbs(2);
		assertFalse(ingredient.getCarbs()==30);
	}
	@Test
	void getCostTest() {
		assertTrue(ingredient.getCost()==20);
	}
	@Test
	void setCostTest() {
		ingredient.setCost(2);
		assertFalse(ingredient.getCost()==20);
	}
	@Test
	void getUserTest() {
		assertTrue(ingredient.getUser().equals("user"));
	}
	@Test
	void setUserTest() {
		User u = new User("User2","2");
		assertFalse(ingredient.setUser(u));
	}
	@Test
	void ingredientsToJSONTest() {
		String actual= ingredient.ingredientToJSON();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH);;
		String expected = "{\"name\": \"pasta\", \"cost\": \"20.0\", \"expiration\": \""+format.format(LocalDate.now()) +"\", \"protein\": \"20\", \"carbs\": \"30\", \"user\": \"user\"}";
		assertEquals(expected,actual);
	}

}
