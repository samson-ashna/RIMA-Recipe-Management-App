package persistence;

import java.util.ArrayList;

import objects.Ingredient;
import objects.Recipes;
import objects.User;

/**
 * 
 */
public interface UsersDAO extends DAO<User> {
	ArrayList<Recipes> getRecipes(User u);
	void addRecipes(User u,Recipes r);
	void removeRecipes(User u, Recipes r);
	Recipes getRecipe(User u, String name);
	ArrayList<Ingredient> getIngredients(User u);
	boolean addIngredient(User u,Ingredient i);
	boolean removeIngredient(User u, Ingredient i);
	Ingredient getIngredient(User u, String name);
	void edit(String oldName, String name, String Password); 
	void editAllergy(User u, String allergyType, int change);
}
