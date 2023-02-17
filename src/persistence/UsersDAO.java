package persistence;

import java.util.ArrayList;

import objects.Recipes;
import objects.User;

public interface UsersDAO extends DAO<User> {
	ArrayList<Recipes> getRecipes(User u);
	void addRecipes(User u,Recipes r);
	void removeRecipes(User u, Recipes r);
	Recipes getRecipe(User u, String name);
}
