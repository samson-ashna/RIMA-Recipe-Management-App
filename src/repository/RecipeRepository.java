package repository;

import java.util.ArrayList;

import objects.Recipes;

public class RecipeRepository {
	ArrayList<Recipes> dbRecipes;
	public ArrayList<Recipes> getRecipes(){
		dbRecipes = new ArrayList<Recipes>();
		Recipes recipe = new Recipes("Baked Potato",9,39);
		ArrayList<String> ingredients = new ArrayList<String>();
		ingredients.add("1 large russet potato");
		ingredients.add("salt");
		ingredients.add("pepper");
		ingredients.add("1 tablespoon butter");
		ingredients.add("1 tablespoon sour cream");
		recipe.setIngredients(ingredients);
		recipe.setInstructions("Scrub potato and prick with a fork. "
				+ "Place on a microwave-safe plate."
				+ "Microwave on full power for 5 minutes. "
				+ "Turn potato over, and microwave until soft, about 5 more minutes.\r\n"
				+ "Remove potato from the microwave, and cut in half lengthwise. "
				+ "Season with salt and pepper and mash up the inside a little with a fork."
				+ "Add butter and Cheddar cheese. "
				+ "Microwave until melted, about 1 more minute."
				+ "Top with sour cream, and serve.");
		return dbRecipes;
	}
    

}
