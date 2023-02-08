package SaveRecipe;

public class Recipes {

    protected String name;
    protected int protein;
    protected int carbs;

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
    
}
