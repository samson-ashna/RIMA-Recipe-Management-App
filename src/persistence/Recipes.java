package persistence;

public class Recipes {

    protected String name;
    protected int protein;
    protected int carbs;

    public Recipes(String name, int protein, int carbs) {

        this.name = name;
        this.protein = protein;
        this.carbs = carbs;

    }

    protected String getName() {
        return this.name;
    }

    protected int getProtein() {
        return this.protein;
    }

    protected int getCarbs() {
        return this.carbs;
    }
    
}
