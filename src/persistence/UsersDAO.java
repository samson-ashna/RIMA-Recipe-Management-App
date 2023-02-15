package persistence;

import java.util.*;
import objects.*;

public interface UsersDAO extends DAO<User> {
	ArrayList<Recipes> getRecipes(User u);
	void addRecipes(User u,Recipes r);
	void removeRecipes(User u, Recipes r);
	User getCurrentUser();
}
