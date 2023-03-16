package businessLogic;

import java.util.ArrayList;

import objects.Ingredient;
import objects.User;
import persistence.DatabaseAccess;
import persistence.UsersDAO;
import persistence.UsersDB;

public class IngredientActions {
	private static DatabaseAccess access = new DatabaseAccess();
	private static UsersDB userDB = (UsersDB) access.usersDB();
	
	//Returns an arraylist of the current user's ingredients.
	public static ArrayList<Ingredient> getIngredients() {
		//Save the current user.
		User user = UserActivity.getCurrentUser();
		//Ingredient Arraylist to be returned.
		ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
		//Continue if user is logged in.
		if(user != null) {
			//Save the user's ingredients stored in the database as an arraylist and return the list; 
			ingredients = userDB.getIngredients(user);
			return ingredients;
		}
		//Return null if user is null.
		return null;
	}
	
	public static boolean updateIngredients() {
		//Save the current user and return if user is null.
		User user = UserActivity.getCurrentUser();
		if(user == null) return false;
		
		//Save the current user's ingredients.
		ArrayList<Ingredient> ingredients = user.getIngredients();
		//Continue if ingredients isn't null.
		if(ingredients != null) {
			//Update user's ingredients in database.
			Boolean success = userDB.updateIngredients(user, ingredients);
			//Return whether the removal was successfully completed.
			return success;
		}
		//Return false if ingredient or user are null to inform that the removal was unsuccessful.
		return false;
	}
	
	//Adds an ingredient to the user's ingredients. Returns true upon successful completion and false otherwise.
	public static boolean addIngredient(Ingredient ingredient) {
		//Save the current user.
		User user = UserActivity.getCurrentUser();
		//Continue if user is logged in and ingredient isn't null.
		if(user != null && ingredient != null) {
			//Add ingredient to database and to the current user instance's ingredient collection if successful.
			Boolean success = userDB.addIngredient(user, ingredient);
			if(success) user.addIngredientToCollection(ingredient);
			//Return whether the addition was successfully completed.
			return success;
		}
		//Return false if ingredient or user are null to inform that the addition was unsuccessful.
		return false;
	}
	
	//Removes an ingredient from the user's ingredients. Returns true upon successful completion and false otherwise.
	public static boolean removeIngredient(Ingredient ingredient) {
		//Save the current user.
		User user = UserActivity.getCurrentUser();
		//Continue if user is logged in and ingredient isn't null.
		if(user != null && ingredient != null) {
			//Remove ingredient from database and from the current user instance's ingredient collection if successful.
			Boolean success = userDB.removeIngredient(user, ingredient);
			if(success) user.removeIngredientFromCollection(ingredient);
			//Return whether the removal was successfully completed.
			return success;
		}
		//Return false if ingredient or user are null to inform that the removal was unsuccessful.
		return false;
	}

	//Return whether an ingredient with the same name exists.
	public static boolean checkName(String name) {
		boolean ingredientExists = false;
		ArrayList<Ingredient> ingredients = getIngredients();
		
		for(Ingredient ingredient:ingredients) {
			if(ingredient.getName().equalsIgnoreCase(name)) {
				ingredientExists = true;
			}
		}
		return ingredientExists;
	}
}
