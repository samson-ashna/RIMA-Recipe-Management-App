package test.unitTests.businessLogic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import businessLogic.SaveRecipe;
import objects.Recipes;
import objects.User;
import persistence.DatabaseAccess;

public class SaveRecipeTest {
    
    SaveRecipe recipes;
    ArrayList<String> name=new ArrayList<String>();
    @BeforeEach
    void init() {
    	DatabaseAccess.databaseType =1;
    	name.add("Boiled Egg");
    	name.add("Fish Cakes");
    	name.add("Macaroni & Cheese");
        int[] protein = {6, 4, 3};
        int[] carbs = {0, 6, 8};

        recipes = new SaveRecipe();

        for(int i = 0; i < name.size(); i++) {

            Recipes temp = new Recipes(name.get(i), protein[i], carbs[i]);
            recipes.testSave(temp);
        }

    }
    
    @Test
    void testGetRecipes() {
    	ArrayList<String> names2 = new ArrayList<String>();
    	for(Recipes r:recipes.getRecipes()) {
    		names2.add(r.getName());
    	}
    	assertIterableEquals(name,names2);

    }

    @Test
    void testGetRecipes1() {
        assertTrue(recipes.getSize()==3);
    }

    @Test
    void testGetRecipe() {
    	assertTrue(recipes.getRecipe("Boiled Egg") !=null);
    }

}
