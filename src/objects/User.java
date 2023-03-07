package objects;

import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

/**
 * This class represents a User object. 
 */
public class User {
	
	protected String name;
	private String password;
	
	//personal collection of the user's recipes,unlike database recipes, these recipes can be modified.
	private ArrayList<Recipes> myRecipes=new ArrayList<Recipes>();
	private Hashtable<String, String> recipelst=new Hashtable<String, String>();
	private ArrayList<Recipes> myFavourites = new ArrayList<Recipes>();
	//private ArrayList<String> recipelst = new ArrayList<String>();
	
	private ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
	
	String lst;
	public boolean loggedIn = false;
	public Allergies allergens;
	public static int id=1;
	private int userID;
	
	/**
	 *Default constructor 
	 */
	//public User(){}
	
	/**
	 * Overloaded constructor
	 * @param name represents a user name
	 * @param password represents the user's password
	 */
	public User(String name,String password) {
		this.name=name;
		this.password=password;
		this.allergens = new objects.Allergies();
		this.userID = id;
		id++;
	}
	public static  int getID() {
		return id;
	}
	public int getUserID() {
		return this.userID;
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
	public String getPassword() {
		return this.password;
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
	public void setRecipeCollection(Hashtable<String, String> recipes) {
		this.recipelst = recipes;
	}
	public void setRecipeCollection(String s) {
		this.lst =s;
	}

	/**
	 * Adds Recipe recipe to the user's recipe collection
	 * @param recipe represents a recipe object.
	 */
	public void addRecipeToCollection(Recipes recipe) {
		this.myRecipes.add(recipe);
	}
	public void addRecipeToCollection(String id, String recipe) {
		this.recipelst.put(id,recipe);
	}

	/**
	 * Returns a list of all recipes in the user's collection.
	 * @return an array list
	 */
	public ArrayList<Recipes> getRecipeCollection(){
		return this.myRecipes;
	}
	public Hashtable<String, String> getRecipeCollection1(){
		return this.recipelst;
	}

	/**
	 * Removes Recipes recipe from the user's recipe collection
	 * @param recipe
	 */
	public void removeRecipeFromCollection(Recipes recipe) {
		this.myRecipes.remove(recipe);
	}
	public void removeRecipeFromCollection(String recipe) {
		this.recipelst.remove(recipe);
	}
	
	public void setIngredients(ArrayList<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}
	
	public ArrayList<Ingredient> getIngredients() {
		return this.ingredients;
	}
	
	//Adds Ingredient ingredient to the user's ingredient collection
	public void addIngredientToCollection(Ingredient ingredient) {
		this.ingredients.add(ingredient);
	}
	
	//Removes Ingredient ingredient from the user's ingredient collection
	public void removeIngredientFromCollection(Ingredient ingredient) {
		this.ingredients.remove(ingredient);
	}
	
	public String ingredientsToJSON() {
		//For testing purposes.
		ingredients.add(new Ingredient("x", 1.00, new Date(), 0, 0, "User"));
		
		//String for converted ingredients arraylist.
		String jsonString = "";
		
		jsonString = jsonString + "[";
		for (int i = 0; i < this.ingredients.size(); i++) {
		    Ingredient ingredient = this.ingredients.get(i);
		    jsonString = jsonString + ingredient.ingredientToJSON();
		    if (i < this.ingredients.size() - 1) {
		    	jsonString = jsonString + ",";
		    }
		}
		jsonString = jsonString + "]";
		
		return jsonString;
	}
	
	
	/**
	 * Returns an instance of the class Allergies which contains information on the user's food allergies. 
	 * @return Allergies object
	 */
	public Allergies getUserAllergies()
	{
		return this.allergens;
	}

	public void setFavourite(Recipes recipes)
	{
		this.myFavourites.add(recipes);
	}

	public ArrayList<Recipes> getFavourites()
	{
		return this.myFavourites;
	}

}
