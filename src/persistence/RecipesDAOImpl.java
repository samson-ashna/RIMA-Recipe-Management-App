package persistence;

import java.util.ArrayList;

import objects.Recipes;


public class RecipesDAOImpl implements DAO<Recipes> {
	RecipeRepository db = new RecipeRepository();
	ArrayList<Recipes> recipesDB=db.getRecipes();
	@Override
	public Recipes get(String name) {
		Recipes recipe = null;
		for (Recipes r: recipesDB) {
			if(r.getName().equals(name)) {
				recipe = r;
			}
		}
		return recipe;
	}
	
	@Override
	public ArrayList<Recipes> getAll() {
		return recipesDB;
	}
	
	@Override
	public void add(Recipes r) {
		recipesDB.add(r);
		
	}
	
	@Override
	public void remove(Recipes r) {
		recipesDB.remove(r);
	}
}
