package persistence;

import static org.junit.Assert.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SaveRecipeTest {
    
    SaveRecipe recipes;

    @BeforeEach
    void init() {

        String[] name = {"Boiled Egg"};
        int[] protein = {6};
        int[] carbs = {0};

        recipes = new SaveRecipe();

        for(int i = 0; i < name.length; i++) {

            Recipes temp = new Recipes(name[i], protein[i], carbs[i]);
            recipes.save(temp);

        }

    }
    
    @Test
    void testGetRecipes() {

        Recipes temp = new Recipes("Boiled Egg", 6, 0);
        SaveRecipe test = new SaveRecipe();
        test.save(temp);

        assertEquals(test, recipes);

    }

    // @Test
    // void testSave() {

    // }

    // @Test
    // void testToString() {

    // }
}
