package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Set;

import businessLogic.UserActivity;
import objects.Recipes;
import objects.User;

public class UsersDB implements UsersDAO {
	private static ArrayList<User> dbUsers = new ArrayList<User>();;
	String url = "jdbc:mysql://localhost:3306/rimaDB";
	String user = "root";
	String password = "";//change password
	static String query;
	static Connection con;
	static Statement statement;
	static ResultSet result;
	static int id;

	@Override
	public User get(String name) {
		dbUsers =getAll();
		User user = null;
		for (User u : dbUsers) {
			if (u.getName().equals(name)) {
				user = u;
			}
		}
		return user;
	}

	@Override
	public ArrayList<User> getAll() {
		try {
			// create connection
			con = DriverManager.getConnection (url , user , password );
			// create statement
			statement = con.createStatement();
			query = "SELECT * FROM users";
			result = statement.executeQuery(query);
			while(result.next()) {
				String name = result.getString(1);
				String password=result.getString(2);
				String myRecipes=result.getString(3);
				User u = new User(name,password);
				u.setRecipeCollection(myRecipes);
				dbUsers.add(u);
				Hashtable<String, String> recipesLst = new Hashtable<String, String>();
				String[] arrOfStr = myRecipes.split(",");
				for (String s: arrOfStr) {
					s = s.replace("{","");
					s= s.replace("}","");
					String[] values = s.split(":");
					values[0] = values[0].replace("\"","");
					values[0] = values[0].replace("\\s","");
					values[0].strip();
					if(values[0].length()>0) {
					recipesLst.put(values[0],values[1]);
					}
				}
				u.setRecipeCollection(recipesLst);
			//}
			}
		}catch (SQLException e) {
			e. printStackTrace ();
		}	
		return dbUsers;
	}

	@Override
	public void add(User t) {
		try {
			// create connection
			con = DriverManager.getConnection (url , user , password );
			// create statement
			statement = con.createStatement();
				
			query = "INSERT INTO users(name, password,myRecipes) VALUES (\'"+t.getName()+"\', \'"+t.getPassword()+"\',\'"+t.getRecipeCollection1()+"\');";

			statement.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void remove(User t) {
		try {
			// create connection
			con = DriverManager.getConnection (url , user , password );
			// create statement
			statement = con.createStatement();
			query = "DELETE FROM users WHERE `name`=\'"+t.getName()+"\';";
		
			statement.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void edit(String oldName, String name, String newPass) {
		if(name!=null) {
			try {
				// create connection
				con = DriverManager.getConnection (url , user , password );
				// create statement
				statement = con.createStatement();
				query = "UPDATE users SET name=\'"+name+"\' WHERE `name`=\'"+oldName+"\';";
				statement.execute(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else if(password !=null) {
			try {
				// create connection
				con = DriverManager.getConnection (url , user , password );
				// create statement
				statement = con.createStatement();
				query = "UPDATE users SET password=\'"+newPass+"\' WHERE `name`=\'"+oldName+"\';";
			
				statement.execute(query);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public ArrayList<Recipes> getRecipes(User u) {
		// TODO Auto-generated method stub
		ArrayList<Recipes> userRecipes = new ArrayList<Recipes>();
		ArrayList<Recipes> allRecipes = new ArrayList<Recipes>();
		RecipesDB db = new RecipesDB();
		
		allRecipes = db.getAllRecipes();
		for(Recipes r: allRecipes) {
			//if(u.getRecipeCollection1().containsKey(Integer.toString(r.getRecipeID()))) {
			if(u.getName().equals(r.getUser())) {
				userRecipes.add(r);
			}
		}
		return userRecipes;
	}

	@Override
	public void addRecipes(User u, Recipes r) {
		RecipesDB db = new RecipesDB();
		r.setID(UserActivity.RecipeIDs);
		db.add(r);
		try {
			// create connection
			con = DriverManager.getConnection (url , user , password );
			// create statement
			statement = con.createStatement();
			query = "UPDATE recipes SET user=\'"+u.getName()+"\' WHERE `food_id`="+(UserActivity.RecipeIDs)+";";
		
			statement.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		u.addRecipeToCollection(Integer. toString(UserActivity.RecipeIDs),r.getName());
		query = "UPDATE users SET myRecipes= JSON_SET(myRecipes, '$.\""+(UserActivity.RecipeIDs)+"\"',\""+ r.getName()+"\") WHERE `name`='"+u.getName()+"\';";
		//query = "UPDATE users SET myRecipes=JSON_SET(myRecipes,\'{\" "+Integer.toString(UserActivity.RecipeIDs-1)+"\":"+Integer.toString(UserActivity.RecipeIDs-1)+"}\') WHERE `name`=\'"+u.getName()+"\';";
		try {
			statement.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void removeRecipes(User u, Recipes r) {
		System.out.println("id to remove is"+(r.getRecipeID()));
		try {
			// create connection
			con = DriverManager.getConnection (url , user , password );
			// create statement
			statement = con.createStatement();
			query = "UPDATE users SET myRecipes= JSON_REMOVE(myRecipes, '$.\""+(r.getRecipeID())+"\"') WHERE `name`='"+u.getName()+"\';";
			statement.execute(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		RecipesDB db = new RecipesDB();
		db.remove(r);
	}

	@Override
	public Recipes getRecipe(User u, String name) {
		ArrayList<Recipes> lst = new ArrayList<Recipes>();
		lst = getRecipes(u);
		for(Recipes r: lst) {
			if(r.getName().equals(name)) {
				return r;
			}
		}
		return null;
	}

	@Override
	public void edit(User t) {
		// TODO Auto-generated method stub
		
	}
}
