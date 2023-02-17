package objects;

import java.util.ArrayList;
/**
 * This class represents a User object. 
 *
 */

public class User {
	protected String name;
	private String password;
	//personal collection of the user's recipes,unlike database recipes, these recipes can be modified.
	private ArrayList<Recipes> myRecipes=new ArrayList<Recipes>();
	public boolean loggedIn = false;
	public Allergies allergens;
	/**
	 *Default constructor 
	 */
	public User(){}
	/**
	 * Overloaded constructor
	 * @param name represents a user name
	 */
	public User(String name) {
		this.name = name;
	}
	/**
	 * Overloaded constructor
	 * @param name represents a user name
	 * @param password represents the user's password
	 */
	public User(String name,String password) {
		this.name=name;
		this.password=password;
		this.allergens = new objects.Allergies();
	}
	/**
	 * Returns user name.
	 * @return a string representing user name.
	 */
	public String getName() {
		return this.name;
	}
	/**
	 * This methods sets the user's password to newPassword.
	 * @param newPassword
	 */
	public void setPassword(String newPassword) {
		this.password = newPassword;
	}
	/**
	 * This method return true if the password entered by the user is correct.
	 * @param password a string representing the password entered by user
	 * @return true if the two passwords match else returns false
	 */
	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}
	/**
	 * Sets the user name to newName
	 * @param newName
	 */
	public void setName(String newName) {
		this.name = newName;
	}
	/**
	 * Adds Recipe recipe to the user's recipe collection
	 * @param recipe represents a recipe object.
	 */
	public void addRecipeToCollection(Recipes recipe) {
		this.myRecipes.add(recipe);
	}
	/**
	 * Returns a list of all recipes in the user's collection.
	 * @return an array list
	 */
	public ArrayList<Recipes> getRecipeCollection(){
		return this.myRecipes;
	}
	/**
	 * Removes Recipes recipe from the user's recipe collection
	 * @param recipe
	 */
	public void removeRecipeFromCollection(Recipes recipe) {
		this.myRecipes.remove(recipe);
	}
	/**
	 * Returns an instance of the class Allergies which contains information on the user's food allergies. 
	 * @return Allergies object
	 */
	public Allergies getUserAllergies()
	{
		return this.allergens;
	}

}
