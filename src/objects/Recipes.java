package objects;

import java.util.ArrayList;

public class Recipes {

	protected String name;
	protected int protein;
	protected int carbs;
	String instructions;

	ArrayList<String> ingredients;

	public Recipes(String name, int protein, int carbs) {

		this.name = name;
		this.protein = protein;
		this.carbs = carbs;

	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getProtein() {
		return this.protein;
	}

	public int getCarbs() {
		return this.carbs;
	}

	public void setProtein(int protein) {
		this.protein = protein;
	}

	public void setCarbs(int carbs) {
		this.carbs = carbs;
	}

	public void setInstructions(String s) {

		instructions = s;

	}

	public void setIngredients(ArrayList<String> s) {

		ingredients = new ArrayList<>();

		for (int i = 0; i < s.size(); i++) {

			ingredients.add(s.get(i));

		}

	}

	public String getInstructions() {

		return instructions;

	}

	public String getIngredients() {
		String ingredientsText = "";
		for (String item : ingredients) {
			ingredientsText += item + "\n";
		}

		return ingredientsText;

	}

	@Override
	public String toString() {
		return "Name=" + name + "\n\n" + "Protein(g) =" + protein + "\n\n" + "Carbs(g)=" + carbs + "\n\n" + "Ingredients: "+"\n"
				+ getIngredients() + "\n" + "Instruction: " + instructions;
	}
}
