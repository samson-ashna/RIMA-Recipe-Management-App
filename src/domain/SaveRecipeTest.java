package domain;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


class SaveRecipeTest {
	SaveRecipe saveRecipe;

	@Test
	void checksaveTest() {
	    SaveRecipe recipe;
		Object s;
		assertThrows(Exception.class,() -> saveRecipe.save(s));
	}
	
	@Test
	  public void getSizeTest() {
	    int size = 4; //assume 4 in this case
	    SaveRecipe saveRecipe = new SaveRecipe();
	    int actual = saveRecipe.getSize();
	    double expected = 4;
	    assertEquals(expected, actual);
	}
	
	@Test
	  public void toStringTest() {
		String result = "myResultThatIMustObtain";
	    assertEquals(saveRecipe.toString(), result);
	}

}
