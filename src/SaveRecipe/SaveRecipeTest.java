package SaveRecipe;

import static org.junit.Assert.assertEquals;

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
    void testGetRecipe() {

        Recipes temp = new Recipes("Boiled Egg", 6, 0);
        SaveRecipe test = new SaveRecipe();
        test.save(temp);

        assertEquals(test.getRecipe("Boiled Egg").getName(), recipes.getRecipe("Boiled Egg").getName());

    }

    // @Test
    // void testSave() {

    // }

    // @Test
    // void testToString() {

    // }
}
