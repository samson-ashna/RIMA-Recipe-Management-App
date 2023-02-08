package persistence;

import java.util.*;

public class SaveRecipe {
    
    protected ArrayList<Recipes> myRecipes;

    public SaveRecipe() {

        myRecipes = new ArrayList<>();

    }

    public SaveRecipe(Recipes recipe) {

        myRecipes = new ArrayList<>();

    }

    public void save(Recipes recipe) {

        myRecipes.add(recipe);

    }

    public String getRecipes() {

        return myRecipes.toString();

    }

    @Override
    public String toString() {

        String temp = "Recipe: ";

        for(Recipes e : myRecipes) {

            temp += e.getName() + " Protein: " + e.getProtein() + " Carbohydrates: " + e.getCarbs();

        }
        
        return temp;

    }

}
