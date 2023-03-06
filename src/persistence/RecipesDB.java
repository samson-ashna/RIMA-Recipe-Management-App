package persistence;

import java.util.ArrayList;

import businessLogic.UserActivity;
import objects.Recipes;
import java.sql.*;

public class RecipesDB extends DBSetup implements DAO<Recipes> {
	private ArrayList<Recipes> dbRecipes= new ArrayList<Recipes>();;
	
	public void setRecipeIDs() {
		try {
			// create connection
			con = DriverManager.getConnection (url , user , password );
			// create statement
			statement = con.createStatement();
			query = "SELECT * FROM id";
			result = statement.executeQuery(query);
			while(result.next()) {
				int id=Integer.parseInt(result.getString(1));
				UserActivity.RecipeIDs = id;
			}
		} catch (SQLException e) {
			e. printStackTrace ();
		}
	}
	@Override
	public Recipes get(String name){
		ArrayList<Recipes> lst = getAll();
		Recipes recipe = null;
		for (Recipes r: lst) {
			if(r.getName().equals(name)) {
				recipe = r;
			}
		}
		return recipe;
	}
	
	
	public ArrayList<Recipes> getAllRecipes() {
	
		try {
			// create connection
			con = DriverManager.getConnection (url , user , password );
			// create statement
			statement = con.createStatement();
			query = "SELECT * FROM recipes";
			result = statement.executeQuery(query);
			Recipes r=new Recipes();
			
			while(result.next()) {
				String name = result.getString(2);
				String instructions=result.getString(4);
				String ingredients=result.getString(3);
				int id = Integer.parseInt(result.getString(1));
				int protein1=Integer.parseInt(result.getString(8));
				int carbs1= Integer.parseInt(result.getString(7));
				r = new Recipes(name,protein1,carbs1);
				String user = result.getString(11);
				r.setUser(user);
				r.setIngredients(ingredients);
				r.setInstructions(instructions);
				r.setID(id);
				dbRecipes.add(r);
				
			}
			
		} catch (SQLException e) {
			e. printStackTrace ();
		}
		//UserActivity.RecipeIDs=dbRecipes.size();
		
		return dbRecipes;
	}
	@Override
	public ArrayList<Recipes> getAll() {
		ArrayList<Recipes> forAllUsers = new ArrayList<Recipes>();
		try {
			// create connection
			con = DriverManager.getConnection (url , user , password );
			// create statement
			statement = con.createStatement();
			query = "SELECT * FROM recipes WHERE `user`=\'ALL\';";
			result = statement.executeQuery(query);
			while(result.next()) {
				String name = result.getString(2);
				String instructions=result.getString(4);
				String ingredients=result.getString(3);
				int id = Integer.parseInt(result.getString(1));
				int protein1=Integer.parseInt(result.getString(8));
				int carbs1= Integer.parseInt(result.getString(7));
				Recipes r = new Recipes(name,protein1,carbs1);
				r.setIngredients(ingredients);
				r.setInstructions(instructions);
				r.setID(id);
				forAllUsers.add(r);
				
			}
		} catch (SQLException e) {
			e. printStackTrace ();
		}
		return forAllUsers;
	}
	@Override
	public void add(Recipes t) {
		try {
			UserActivity.RecipeIDs++;
			t.setID(UserActivity.RecipeIDs-1);
			con = DriverManager.getConnection (url , user , password );
			statement = con.createStatement();
			query = "INSERT INTO recipes(food_id, name, ingredients, instruction, carbs,protein) VALUES"
					+ " ("+UserActivity.RecipeIDs+",\'"+t.getName()+"\'"+",\'"+t.getIngredients()+"\',"+"\'"+t.getInstructions()+"\',"+"\'"+t.getCarbs()+"\',\'"+t.getProtein()+"\');";
			statement.execute(query);
			query = "UPDATE id SET recipeID = recipeID + 1";
			statement.execute(query);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void remove(Recipes t) {
		try {
			con = DriverManager.getConnection (url , user , password );
			statement = con.createStatement();
			query = "DELETE FROM recipes WHERE `name`=\'"+t.getName()+"\';";
			statement.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

}
