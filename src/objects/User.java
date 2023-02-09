package objects;

import java.util.ArrayList;

public class User {
	protected String name;
	private String password;
	protected ArrayList<Recipes> myRecipes;
	public User(String name) {
		this.name = name;
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
	public void setName(String name) {
		this.name = name;
	}
	public void addRecipeToCollection(Recipes recipe) {
		myRecipes.add(recipe);
	}
	public ArrayList<Recipes> getRecipeCollection(){
		return myRecipes;
	}
	
}
