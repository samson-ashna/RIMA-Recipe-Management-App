package objects;

import java.util.ArrayList;

public class User {
	protected String name;
	private String password;
	private ArrayList<Recipes> myRecipes=new ArrayList<Recipes>();
	public boolean loggedIn = false;
	public Allergies allergens;

	public User()
	{

	}
	public User(String name) {
		this.name = name;
	}
	public User(String name,String password) {
		this.name=name;
		this.password=password;
		this.allergens = new objects.Allergies();
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
		this.myRecipes.add(recipe);
	}
	public ArrayList<Recipes> getRecipeCollection(){
		return this.myRecipes;
	}
	public void removeRecipeFromCollection(Recipes recipe) {
		this.myRecipes.remove(recipe);
	}
	public Allergies getUserAllergies()
	{
		return this.allergens;
	}

}
