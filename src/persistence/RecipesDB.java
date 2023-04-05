package persistence;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

import businessLogic.UserActivity;
import objects.Recipes;
import objects.User;

public class RecipesDB extends DBSetup implements DAO<Recipes> {
	private ArrayList<Recipes> dbRecipes = new ArrayList<Recipes>();;

	public void setRecipeIDs() {
		try {
			// create connection
			con = DriverManager.getConnection(url, user, password);
			// create statement
			statement = con.createStatement();
			query = "SELECT * FROM id";
			result = statement.executeQuery(query);
			while (result.next()) {
				int id = Integer.parseInt(result.getString(1));
				UserActivity.RecipeIDs = id;
			}
			statement.close();
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
        // ArrayList for list of recipes
	@Override
	public Recipes get(String name) {
		ArrayList<Recipes> lst = getAll();
		Recipes recipe = null;
		for (Recipes r : lst) {
			if (r.getName().equals(name)) {
				recipe = r;
			}
		}
		return recipe;
	}
	public void changeUserNames(String oldName, String newName) {
		try {
			con = DriverManager.getConnection(url, user, password);
				// create statement
			//statement = con.createStatement();
			for(Recipes r: getAllRecipes()) {
				if(r.user.equals(oldName)) {
					r.user = newName;
					//System.out.println(r.getRecipeID());
					statement = con.createStatement();
					query = "update recipes set user=\'"+newName+"\' where food_id="+r.getRecipeID()+";";
					statement.execute(query);
				}
			}
			statement.close();
			result.close();

		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Recipes> getAllRecipes() {

		try {
			// create connection
			con = DriverManager.getConnection(url, user, password);
			// create statement
			statement = con.createStatement();
			query = "SELECT * FROM recipes";
			result = statement.executeQuery(query);
			Recipes r = new Recipes();
			
			//add recipe component results to String
			while (result.next()) {
				String name = result.getString(2);
				String instructions = result.getString(4);
				String ingredients = result.getString(3);
				int id = Integer.parseInt(result.getString(1));
				int protein1 = Integer.parseInt(result.getString(6));
				int carbs1 = Integer.parseInt(result.getString(5));
				String mealTime = result.getString(7);
				r = new Recipes(name, protein1, carbs1);
				String user = result.getString(8);
				String favorite = result.getString(9);
				r.favourite = Integer.parseInt(favorite);
				r.setUser(user);
				r.setIngredients(ingredients);
				r.setInstructions(instructions);
				r.setID(id);
				r.setMealTime(mealTime);
				dbRecipes.add(r);
			}
			statement.close();
			result.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dbRecipes;
	}

	//get a list of recipes for all app users
	@Override
	public ArrayList<Recipes> getAll() {
		ArrayList<Recipes> forAllUsers = new ArrayList<Recipes>();
		try {
			// create connection
			con = DriverManager.getConnection(url, user, password);
			// create statement
			statement = con.createStatement();
			query = "SELECT * FROM recipes WHERE `user`=\'ALL\';";
			result = statement.executeQuery(query);
			while (result.next()) {
				String name = result.getString(2);
				String instructions = result.getString(4);
				String ingredients = result.getString(3);
				int id = Integer.parseInt(result.getString(1));
				int protein1 = Integer.parseInt(result.getString(6));
				int carbs1 = Integer.parseInt(result.getString(5));
				String mealTime = result.getString(7);
				Recipes r = new Recipes(name, protein1, carbs1);
				r.setIngredients(ingredients);
				r.setInstructions(instructions);
				r.setMealTime(mealTime);
				r.setID(id);
				forAllUsers.add(r);
			}
			statement.close();
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return forAllUsers;
	}

	@Override
	public void add(Recipes t) {
		try {
			UserActivity.RecipeIDs++;
			t.setID(UserActivity.RecipeIDs - 1);
			con = DriverManager.getConnection(url, user, password);
			statement = con.createStatement();
			//each recipe added should include all these components in its display
			query = "INSERT INTO recipes(food_id, name, ingredients, instruction, carbs,protein,mealTime, favorite) VALUES" + " ("
					+ UserActivity.RecipeIDs + ",\'" + t.getName() + "\'" + ",\'" + t.getIngredients() + "\'," + "\'"
					+ t.getInstructions() + "\'," + "\'" + t.getCarbs() + "\',\'" + t.getProtein() + "\',\'"
					+ t.mealTime + "\',\'"+t.favourite+"\');";
			statement.execute(query);
			query = "UPDATE id SET recipeID = recipeID + 1";
			statement.execute(query);
			statement.close();
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void remove(Recipes t) {
		try {
			con = DriverManager.getConnection(url, user, password);
			statement = con.createStatement();
			query = "DELETE FROM recipes WHERE `food_id`=\'" + t.getRecipeID() + "\';";
			statement.execute(query);
			statement.close();
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
        
	//each recipe edited should include all these components in its display
	public void edit(Recipes t) {
		RecipesDB db = new RecipesDB();
		for (Recipes r : db.getAllRecipes()) {
			if (r.getRecipeID() == t.getRecipeID()) {
				try {
					con = DriverManager.getConnection(url, user, password);
					statement = con.createStatement();
					query = "UPDATE recipes SET name=\'" + t.getName() + "\'" + "," + "ingredients=\'"
							+ t.getIngredients() + "\'," + " instruction=\'" + t.getInstructions() + "\',"
							+ "protein=\'" + t.getProtein() + "\'," + " carbs=\'" + t.getCarbs() + "\',"+"user=\'"+t.user+"\'"
							+ "WHERE food_id=" + "\'" + t.getRecipeID() + "\';";

					statement.execute(query);
					statement.close();
					result.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		UsersDB dbUser = new UsersDB();
		for (User u : dbUser.getAll()) {
			if (dbUser.getRecipe(u, t.getName()).getRecipeID() == t.getRecipeID()) {
				query = "UPDATE users SET myRecipes= JSON_SET(myRecipes, '$.\"" + (t.getRecipeID()) + "\"',\""
						+ t.getName() + "\") WHERE `name`='" + u.getName() + "\';";
				try {
					con = DriverManager.getConnection(url, user, password);
					statement = con.createStatement();
					statement.execute(query);
					statement.close();
					result.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	
}
