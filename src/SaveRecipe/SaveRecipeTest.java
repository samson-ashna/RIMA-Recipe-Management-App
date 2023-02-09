package SaveRecipe;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SaveRecipeTest {
    
    SaveRecipe recipes;

    @BeforeEach
    void init() {

        String[] name = {"Boiled Egg", "Fish Cakes", "Macaroni & Cheese"};
        int[] protein = {6, 4, 3};
        int[] carbs = {0, 6, 8};

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

    @Test
    void testGetRecipes1() {

        SaveRecipe test = new SaveRecipe(recipes);

        ArrayList<Recipes> x = test.getRecipes();
        ArrayList<Recipes> y = recipes.getRecipes();
        boolean temp = true;

        for(int i = 0; i < test.getSize(); i++) {

            if(!x.get(i).getName().equals(y.get(i).getName())) {
                temp = false;
            }

        }

        assertTrue(temp);

    }

    @Test
    void testGetRecipes2() {

        ArrayList<String> ingredients = new ArrayList<>();
        ingredients.add("Potatoes");
        ingredients.add("Salt");

        Recipes temp = new Recipes("Boiled Potato", 3, 6);
        temp.setInstructions("1. Boil water 2. Insert potatoes into boiling water");
        temp.setIngredients(ingredients);
        SaveRecipe test = new SaveRecipe();
        
        test.save(temp);

        ArrayList<Recipes> x = test.getRecipes();
        ArrayList<Recipes> y = recipes.getRecipes();
        boolean condition = true;

        for(int i = 0; i < test.getSize(); i++) {

            if(!x.get(i).getName().equals(y.get(i).getName())) {
                condition = false;
            }

        }

        System.out.println(test);
        System.out.println(temp.getInstructions());
        System.out.println(temp.getIngredients());
        System.out.println(recipes);
        assertFalse(condition);

    }

    // @Test
    // void testSave() {

    // }

    // @Test
    // void testToString() {

    // }
}

