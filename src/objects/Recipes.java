package objects;

import java.util.ArrayList;

/**
 * This class defines a Recipes object
 */
public class Recipes {

	public String name;
	public int protein;
	public int carbs;
	String instructions;
	public ArrayList<String> ingredients;

	/**
	 * Constructor that initialize the Recipes object with name, protein and carbs. 
	 * @param name is a string that represents the recipe's name;
	 * @param protein is an integer representing the protein content in grams.
	 * @param carbs is an integer representing the carbs content in grams.
	 */
	public Recipes(String name, int protein, int carbs) {

		this.name = name;
		this.protein = protein;
		this.carbs = carbs;

	}

	/**
	 * This method returns the name of the recipe
	 * @return name of recipe
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * This method sets the recipes's name to parameter name.
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * This method returns the protein content of the meal prepared by this recipe in grams
	 * @return integer representing protein content
	 */
	public int getProtein() {
		return this.protein;
	}

	/**
	 * This method returns the carbs content of the meal prepared by this recipe in grams
	 * @return integer representing carbs content
	 */
	public int getCarbs() {
		return this.carbs;
	}

	/**
	 * This method sets the protein content of this recipe to newProtein.
	 * @param newProtein is integer representing protein content
	 */
	public void setProtein(int newProtein) {
		this.protein = newProtein;
	}

	/**
	 * This method sets the carbs content of this recipe to newCarbs.
	 * @param newProtein is integer representing carbs content
	 */
	public void setCarbs(int newCarbs) {
		this.carbs = newCarbs;
	}

	/**
	 * This method sets the recipe's instruction to the string s
	 * @param s is a string
	 */
	public void setInstructions(String s) {
		instructions = s;
	}

	/**
	 * This method adds ingredients to the ingredient list of the recipe
	 * @param s is a list that contains ingredient items
	 */
	public void setIngredients(ArrayList<String> s) {

		ingredients = new ArrayList<>();

		for (int i = 0; i < s.size(); i++) {
			ingredients.add(s.get(i));
		}

	}

	/**
	 * This method returns the instruction of this recipe
	 * @return a string representing instruction
	 */
	public String getInstructions() {
		return instructions;
	}

	/**
	 * This methods return the ingredient items of this recipe separated by a new line
	 * @return ingredientsText, a string representing list of ingredients
	 */
	public String getIngredients() {
		
		String ingredientsText = "";
		
		for (String item : ingredients) {
			ingredientsText += item + "\n";
		}

		return ingredientsText;

	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		return "Name=" + name + "\n\n" + "Protein(g) =" + protein + "\n\n" + "Carbs(g)=" + carbs + "\n\n" + "Ingredients: "+"\n"
				+ getIngredients() + "\n" + "Instruction: " + instructions;
	}
}
