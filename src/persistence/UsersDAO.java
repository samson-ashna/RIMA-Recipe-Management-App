package persistence;

import java.util.List;

import objects.Recipes;
import objects.User;

public interface UsersDAO extends DAO<User> {
	List<Recipes> getRecipes(User u);
	void addRecipes(User u,Recipes r);
	void removeRecipes(User u, Recipes r);
}
