package persistence;

import java.util.List;

import objects.Recipes;
import objects.User;

public interface UserDAO extends DAO<User>{
	List<Recipes> getRecipeCollection(User u);
	void addRecipeToCollection(User u,Recipes r);
	void removeRecipeFromCollection(User u, Recipes r);
}
