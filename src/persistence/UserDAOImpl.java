package persistence;

import java.util.ArrayList;

import objects.Recipes;
import objects.User;

public class UserDAOImpl implements UsersDAO {

	private static ArrayList<User> users = new ArrayList<User>();
	
	@Override
	public User get(String name){
		
		User user = null;
		for (User u: users) {
			if(u.getName().equals(name)) {
				user =u;
			}
		}
		return user;
	}

	@Override
	public ArrayList<User> getAll() {
		
		return users;
	}

	@Override
	public void add(User u) {
		
		users.add(u);
	}

	@Override
	public void remove(User u) {
	
		users.remove(u);
	}

	@Override
	public ArrayList<Recipes> getRecipes(User u) {
		for(User user: users) {
			if (user.equals(u)) {
				return user.getRecipeCollection();
			}
		}
		return null;
	}
	@Override
	public Recipes getRecipe(User u,String name) {
		for (Recipes r: getRecipes(u)) {
			if(r.getName().equals(name)) {
				return r;
			}
		}
		return null;
	}

	@Override
	public void addRecipes(User u, Recipes r) {
		for(User user:users) {
			if (user.equals(u)) {
				user.addRecipeToCollection(r);
			}
		}
		
	}

	@Override
	public void removeRecipes(User u, Recipes r) {
		for(User user:users) {
			if (user.equals(u)) {
				user.removeRecipeFromCollection(r);
			}
		}
		
	}
}



