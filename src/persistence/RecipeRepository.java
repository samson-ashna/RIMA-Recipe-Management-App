package persistence;

import java.util.ArrayList;

import objects.Recipes;

public class RecipeRepository {
	protected ArrayList<Recipes> dbRecipes;
	public ArrayList<Recipes> getRecipes(){
		dbRecipes=new ArrayList<Recipes>();
		//From https://www.allrecipes.com/recipe/85337/microwave-baked-potato/
		Recipes recipe1 = new Recipes("Baked Potato",9,39);
		ArrayList<String> ingredients1 = new ArrayList<String>();
		ingredients1.add("1 large russet potato");
		ingredients1.add("salt");
		ingredients1.add("pepper");
		ingredients1.add("1 tablespoon butter");
		ingredients1.add("1 tablespoon sour cream");
		recipe1.setIngredients(ingredients1);
		recipe1.setInstructions("Scrub potato and prick with a fork. "
				+ "Place on a microwave-safe plate."
				+ "Microwave on full power for 5 minutes. "
				+ "Turn potato over, and microwave until soft, about 5 more minutes.\r\n"
				+ "Remove potato from the microwave, and cut in half lengthwise. "
				+ "Season with salt and pepper and mash up the inside a little with a fork."
				+ "Add butter and Cheddar cheese. "
				+ "Microwave until melted, about 1 more minute."
				+ "Top with sour cream, and serve.");
		dbRecipes.add(recipe1);
		//From https://tasty.co/recipe/3-ingredient-teriyaki-chicken
		Recipes recipe2 = new Recipes("Teriyaki Chicken",48,20);
		ArrayList<String> ingredients2 = new ArrayList<String>();
		ingredients2.add("2 lb chicken thighs sliced into chunks");
		ingredients2.add("1 cup soy sauce(240 ml)");
		ingredients2.add("1/2 cup brown sugar(110 g");
		recipe2.setIngredients(ingredients2);
		recipe2.setInstructions("Sear the chicken thighs evenly in a pan, then flip.\r\n"
				+ "Add the soy sauce and brown sugar, stirring and bringing to a boil.\r\n"
				+ "Stir until the sauce has reduced and evenly glazes the chicken.");
		dbRecipes.add(recipe2);
		return dbRecipes;
	}
	
}
