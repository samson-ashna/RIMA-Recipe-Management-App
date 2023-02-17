package persistence;

import java.util.ArrayList;

import objects.Recipes;

/**
 * 
 */
public class RecipesDAOImpl implements DAO<Recipes> {
	
	private static ArrayList<Recipes> dbRecipes;
	
	/**
	 * 
	 */
	public RecipesDAOImpl() {
		//stub implementation of the recipes database
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
		ingredients2.add("1/2 cup brown sugar(110 g)");
		recipe2.setIngredients(ingredients2);
		recipe2.setInstructions("Sear the chicken thighs evenly in a pan, then flip.\r\n"
				+ "Add the soy sauce and brown sugar, stirring and bringing to a boil.\r\n"
				+ "Stir until the sauce has reduced and evenly glazes the chicken.");
		dbRecipes.add(recipe2);
	}

	/**
	 * Returns the Recipe object in the database with the name n.
	 */
	@Override
	public Recipes get(String n) {
		Recipes recipe = null;
		for (Recipes r: dbRecipes) {
			if(r.getName().equals(n)) {
				recipe = r;
			}
		}
		return recipe;
	}

	/**
	 * Return an array list containing all Recipe objects in the database.
	 */
	@Override
	public ArrayList<Recipes> getAll() {
		return dbRecipes;
	}

	/**
	 * Adds Recipes r to the database
	 */
	@Override
	public void add(Recipes r) {
		dbRecipes.add(r);
		
	}

	/**
	 * Removes Recipes r from the database
	 */
	@Override
	public void remove(Recipes r) {
		dbRecipes.remove(r);
	}
	
}
