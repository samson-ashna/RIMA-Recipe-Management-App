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
	public ArrayList<Recipes> getFavoriteList(User u);
	public void editFavorites(Recipes r,int change);
	public void editPlanner(User u,String day,String time, String recipe);
	public void editShoppingList(String ingredient, String name);
	//public ArrayList<String> getShoppingList(User u);
	//ArrayList<Recipes> shoppingIngredients(User u);
}
