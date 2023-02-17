package objects;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RecipesTest {
	Recipes r;
	ArrayList<String> ingredients;
	@BeforeEach
	void init() {
		r= new Recipes("pasta",1,3);
		r.setInstructions("b");
		ingredients= new ArrayList<String>();
		ingredients.add("water");
		r.setIngredients(ingredients);
		
	}
	@Test
	void getName() {
		assertEquals("pasta",r.getName());
	}
	@Test
	void setName() {
		r.setName("p");
		assertNotEquals("pasta",r.getName());
	}
	@Test
	void getProtein() {
		assertEquals(1,r.getProtein());
	}
	@Test
	void getCarbs() {
		assertEquals(3,r.getCarbs());
	}
	@Test
	void setProtein() {
		r.setProtein(6);
		assertNotEquals(1,r.getProtein());
	}
	@Test
	void setCarbs() {
		r.setCarbs(6);
		assertNotEquals(3,r.getCarbs());
	}
	@Test
	void testGetInstruction() {
		assertEquals("b",r.getInstructions());
	}
	@Test
	void testGetIngredient() {
		assertEquals("water\n", r.getIngredients());
	}

}
