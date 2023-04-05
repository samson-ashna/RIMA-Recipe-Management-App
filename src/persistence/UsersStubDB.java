package persistence;

import java.util.ArrayList;

import businessLogic.UserActivity;
import objects.Ingredient;
import objects.Recipes;
import objects.User;

/**
 * 
 */
public class UsersStubDB implements UsersDAO {

	private static ArrayList<User> users = new ArrayList<User>();

	/**
	 * Returns the User with the specified name if there exists such a user in the
	 * database.
	 */
	@Override
	public User get(String name) {

		User user = null;
		for (User u : users) {
			if (u.getName().equals(name)) {
				user = u;
			}
		}
		return user;
	}

	/**
	 * Returns an array list of all users in the database
	 */
	@Override
	public ArrayList<User> getAll() {

		return users;
	}

	/**
	 * Adds User u to the database.
	 */
	@Override
	public void add(User u) {

		users.add(u);
	}

	/**
	 * Removes the User u from the database.
	 */
	@Override
	public void remove(User u) {

		users.remove(u);
	}

	/**
	 * Return an array list of Recipes objects in the user's collection
	 */
	@Override
	public ArrayList<Recipes> getRecipes(User u) {
		for (User user : users) {
			if (user.equals(u)) {
				return user.getRecipeCollection();
			}
		}
		return null;
	}

	/**
	 * Return the Recipe object with the specified name that exists in User u's
	 * personal recipe collection
	 */
	@Override
	public Recipes getRecipe(User u, String name) {
		for (Recipes r : getRecipes(u)) {
			if (r.getName().equals(name)) {
				return r;
			}
		}
		return null;
	}

	/**
	 * Adds Recipe r to User u's personal recipes collection
	 */
	@Override
	public void addRecipes(User u, Recipes r) {
		for (User user : users) {
			if (user.equals(u)) {
				user.addRecipeToCollection(r);
			}
		}

	}

	/**
	 * Removes Recipe r from User u's personal recipes collection
	 */

	@Override
	public void removeRecipes(User u, Recipes r) {
		for (User user : users) {
			if (user.equals(u)) {
				user.removeRecipeFromCollection(r);
				if(r.favourite==1) {
					u.removeFavourite(r);
				}
			}
		}

	}

	@Override
	public void edit(String oldName, String name, String Password) {}

	@Override
	public void edit(User t) {}

	@Override
	public ArrayList<Ingredient> getIngredients(User u) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Ingredient getIngredient(User u, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void editAllergy(User u, String allergyType, int change) {
		u.getUserAllergies().getAllergies().replace(allergyType, change);
	}

	@Override
	public ArrayList<Recipes> getFavoriteList(User u) {
		return u.getFavourites();
		
	}

	@Override
	public void editFavorites(Recipes r, int change) {
		if(change ==1) {
			UserActivity.getCurrentUser().setFavourite(r);
			r.isFavourite();
		}else {
			UserActivity.getCurrentUser().removeFavourite(r);
			r.notFavourite();
		}
		
	}

	@Override
	public void editPlanner(User u, String day, String time, String recipe) {}

	@Override
	public void editShoppingList(String ingredient, String name) {}
}
