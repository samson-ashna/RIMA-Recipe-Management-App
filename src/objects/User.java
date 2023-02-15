package objects;

import java.util.*;

public class User {
	protected String name;
	private String password;
	protected static ArrayList<Recipes> myRecipes=new ArrayList<Recipes>();
	public boolean loggedIn;
	public User(String name) {
		this.name = name;
		this.loggedIn = false;
	}
	public User(String name,String password) {
		this.name=name;
		this.password=password;
	}
	public String getName() {
		return this.name;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean checkPassword(String password) {
		return this.password.equals(password);
	}
	public void setName(String name) {
		this.name = name;
	}
	public void addRecipeToCollection(Recipes recipe) {
		myRecipes.add(recipe);
	}
	public ArrayList<Recipes> getRecipeCollection(){
		return myRecipes;
	}
	public void removeRecipeFromCollection(Recipes recipe) {
		myRecipes.remove(recipe);
	}
}
