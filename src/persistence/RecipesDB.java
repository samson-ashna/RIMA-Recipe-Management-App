package persistence;

import java.util.ArrayList;

import businessLogic.UserActivity;
import objects.Recipes;
import objects.User;

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
				int protein1=Integer.parseInt(result.getString(6));
				int carbs1= Integer.parseInt(result.getString(5));
				String mealTime = result.getString(7);
				r = new Recipes(name,protein1,carbs1);
				String user = result.getString(8);
				r.setUser(user);
				r.setIngredients(ingredients);
				r.setInstructions(instructions);
				r.setID(id);
				r.setMealTime(mealTime);
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
				int protein1=Integer.parseInt(result.getString(6));
				int carbs1= Integer.parseInt(result.getString(5));
				String mealTime = result.getString(7);
				
				Recipes r = new Recipes(name,protein1,carbs1);
				r.setIngredients(ingredients);
				r.setInstructions(instructions);
				r.setMealTime(mealTime);
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
			query = "INSERT INTO recipes(food_id, name, ingredients, instruction, carbs,protein,mealTime) VALUES"
					+ " ("+UserActivity.RecipeIDs+",\'"+t.getName()+"\'"+",\'"+t.getIngredients()+"\',"+"\'"+t.getInstructions()+"\',"+"\'"+t.getCarbs()+"\',\'"+t.getProtein()+"\',\'"+t.mealTime+"\');";
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
			query = "DELETE FROM recipes WHERE `food_id`=\'"+t.getRecipeID()+"\';";
			statement.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void edit(Recipes t){
		RecipesDB db = new RecipesDB();
		//DAO<Recipes> db = new RecipesStubDB();
		for (Recipes r: db.getAllRecipes()) {
			if(r.getRecipeID()==t.getRecipeID()) {			
				try {
					con = DriverManager.getConnection (url , user , password );
					statement = con.createStatement();
					query = "UPDATE recipes SET name=\'"+t.getName()+"\'"+","
							+ "ingredients=\'"+t.getIngredients()+"\',"
									+ " instruction=\'"+t.getInstructions()+"\',"
											+ "protein=\'"+t.getProtein()+"\',"
													+ " carbs=\'"+t.getCarbs()+"\' "
															+ "WHERE food_id="+"\'"+t.getRecipeID()+"\';";
							
					statement.execute(query);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		UsersDB dbUser = new UsersDB();
		for(User u: dbUser.getAll()) {
			if(dbUser.getRecipe(u, t.getName()).getRecipeID()==t.getRecipeID()){
				query = "UPDATE users SET myRecipes= JSON_SET(myRecipes, '$.\""+(t.getRecipeID())+"\"',\""+ t.getName()+"\") WHERE `name`='"+u.getName()+"\';";
				try {
					statement.execute(query);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		
		
	}
}
