package persistence;

import java.util.List;

import objects.Recipes;
import objects.User;

public interface UserDAO extends DAO<User>{
	List<Recipes> getRecipeCollection(User u);
}
