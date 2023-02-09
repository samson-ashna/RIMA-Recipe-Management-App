package SaveRecipe;

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

    public int getProtein() {
        return this.protein;
    }

    public int getCarbs() {
        return this.carbs;
    }

    public void setInstructions(String s) {

        instructions = "Instructions: " + s;

    }

    public void setIngredients(ArrayList<String> s) {

        ingredients = new ArrayList<>();

        for(int i = 0; i < s.size(); i++) {

            ingredients.add(s.get(i));

        }

    }

    public String getInstructions() {

        return instructions;

    }

    public String getIngredients() {

        
        return "Ingredients: " + ingredients;

    }
    
}
