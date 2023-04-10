package persistence;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Locale;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import businessLogic.UserActivity;
import objects.Ingredient;
import objects.Recipes;
import objects.User;

/**
 * this method implements a new arraylist consisting of users in the database
 */
public class UsersDB extends DBSetup implements UsersDAO {
	private ArrayList<User> dbUsers = new ArrayList<User>();;

	// Returns the user with specified name.
	@Override
	public User get(String name) {
		dbUsers = getAll();
		User user = null;
		for (User u : dbUsers) {
			if (u.getName().equals(name)) {
				user = u;
			}
		}
		return user;
	}

	// Returns a list of all users in the database.
	@Override
	public ArrayList<User> getAll() {
		try {
			// create connection
			con = DriverManager.getConnection(url, user, password);
			// create statement
			statement = con.createStatement();
			query = "SELECT * FROM users";
			result = statement.executeQuery(query);
			while (result.next()) {
				String name = result.getString(1);
				String password = result.getString(2);
				String myRecipes = result.getString(3);
				ArrayList<Ingredient> ingredients = parseIngredients(result.getString(4));
				String allergies = result.getString(5);
				String plan = result.getString(6);
				String shoppingList = result.getString(7);
				User u = new User(name, password);
				u.setRecipeCollection(myRecipes);
				u.setAllergies(allergies);
				u.setIngredients(ingredients);
				dbUsers.add(u);
				u.setAllergyInformation(getAllergyList(allergies));
				u.setRecipeCollection(getRecipeLst(myRecipes));
				ArrayList<String> shoppingLst = new ArrayList<String>();
				if(shoppingList !=null) {
					String[] arrOfStr = shoppingList.split("-");
					for(String s: arrOfStr) {
						if(s.length()>0) {
							shoppingLst.add(s.strip());
						}
					}
				}
				u.setShoppingList(shoppingLst);
				setUpPlanner(plan,u);
			}			
			statement.close();
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dbUsers;
	}
	private Hashtable<String, String> getRecipeLst(String myRecipes){
		Hashtable<String, String> recipesLst = new Hashtable<String, String>();
		if (myRecipes != null) {
			String[] arrOfStr = myRecipes.split(",");
			for (String s : arrOfStr) {
				s = s.replace("{", "");
				s = s.replace("}", "");
				String[] values = s.split(":");
				values[0] = values[0].replace("\"", "");
				values[0] = values[0].replace("\\s", "");
				values[0].strip();
				if (values[0].length() > 0) {
					recipesLst.put(values[0], values[1]);
				}
			}
		}
		return recipesLst;
	}
	private Hashtable<String, Integer> getAllergyList(String allergies){
		Hashtable<String, Integer> allergyLst = new Hashtable<String, Integer>();
		if (allergies != null) {
			String[] arrOfStr = allergies.split(",");
			for (String s : arrOfStr) {
				s = s.replace("{", "");
				s = s.replace("}", "");
				String[] values = s.split(":");
				values[0] = values[0].replace("\"", "");
				values[0] = values[0].replace("\\s", "");
				values[0].strip();
				values[1] = values[1].replace("\"", "");
				values[1] = values[1].replace("\\s", "");
				values[1].strip();
				if (values[0].length() > 0) {
					allergyLst.put(values[0].strip(), Integer.parseInt(values[1].strip()));
				}
			}
		}	
		return allergyLst;
	}
	private void setUpPlanner(String plan, User u) {
		String day = null;
		String time = null;
		ArrayList<String> days= new ArrayList<String>();
		String[] dayOfWeek = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
		for(String d: dayOfWeek) {days.add(d);}
		ArrayList<String> times = new ArrayList<String>();
		String[] meal = {"Breakfast","Lunch","Dinner"};
		for(String m:meal) {times.add(m);}
		if(plan != null) {
			String[] arrOfStr = plan.split(",");
			for (String s : arrOfStr) {
				s = s.replace("\"", "");
				s = s.replace("\\s", "");
				s = s.replace("{", "");
				s = s.replace("}", "");
				String[] arr = s.split(":");
				for(String s2:arr) {
					s2 = s2.replace("\"", "");
					s2 = s2.replace("\\s", "");
					s2 = s2.replace("{", "");
					s2 = s2.replace("}", "");
					if(days.contains(s2.strip())) {
						day= s2.strip();
					}else if(times.contains(s2.strip())) {
						time = s2.strip();
					}else {
						if(s2.length()>1) {
							u.editPlan(day.strip(),time.strip(),s2.strip());
						}
					}
				}
			}
		}
}

	@Override
	public void add(User t) {
		try {
			// create connection
			con = DriverManager.getConnection(url, user, password);
			// create statement
			statement = con.createStatement();
			String meal = "{\"Monday\":{\"Breakfast\":\"\",\"Lunch\":\"\",\"Dinner\":\"\"},\"Tuesday\":{\"Breakfast\":\"\",\"Lunch\":\"\",\"Dinner\":\"\"},\"Wednesday\":\r\n"
					+ "{\"Breakfast\":\"\",\"Lunch\":\"\",\"Dinner\":\"\"},\"Thursday\":{\"Breakfast\":\"\",\"Lunch\":\"\",\"Dinner\":\"\"},\"Friday\":{\"Breakfast\":\"\",\"Lunch\":\"\",\"Dinner\":\"\"},\"Saturday\":{\"Breakfast\":\"\",\r\n"
					+ "\"Lunch\":\"\",\"Dinner\":\"\"},\"Sunday\":{\"Breakfast\":\"\",\"Lunch\":\"\",\"Dinner\":\"\"}}";
			query = "INSERT INTO users(name, password, myRecipes, myIngredients,allergies,plan) VALUES (\'" + t.getName()
					+ "\', \'" + t.getPassword() + "\',\'" + t.getRecipeCollection1() + "\', \'" + t.ingredientsToJSON()
					+ "\', \'" + t.getAllergies() + "\',\'"+meal+"\');";
			statement.execute(query);
			query = "UPDATE users SET allergies= JSON_SET(allergies, '$.\"" + "Eggs" + "\"',\'" + 0 + "\','$.\""
					+ "Milk" + "\"',\'" + 0 + "\','$.\"" + "Peanuts" + "\"',\'" + 0 + "\','$.\"" + "Seafood" + "\"',\'"
					+ 0 + "\') WHERE `name`=\'" + t.getName() + "\';";
			statement.execute(query);
			statement.close();
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void remove(User t) {
		try {
			// create connection
			con = DriverManager.getConnection(url, user, password);
			// create statement
			statement = con.createStatement();
			query = "DELETE FROM users WHERE `name`=\'" + t.getName() + "\';";
			statement.execute(query);
			statement.close();
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void edit(String oldName, String name, String newPass) {
		if (name != null) {
			try {
				RecipesDB dbRecipes = new RecipesDB();
				dbRecipes.changeUserNames(oldName,name);
				con = DriverManager.getConnection(url, user, password);
				statement = con.createStatement();
				query = "UPDATE users SET name=\'" + name + "\' WHERE `name`=\'" + oldName + "\';";
				statement.execute(query);
				statement.close();
				result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (password != null) {
			try {
				con = DriverManager.getConnection(url, user, password);
				statement = con.createStatement();
				query = "UPDATE users SET password=\'" + newPass + "\' WHERE `name`=\'" + oldName + "\';";
				statement.execute(query);
				statement.close();
				result.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public ArrayList<Recipes> getRecipes(User u) {
		ArrayList<Recipes> userRecipes = new ArrayList<Recipes>();
		ArrayList<Recipes> allRecipes = new ArrayList<Recipes>();
		RecipesDB db = new RecipesDB();
		allRecipes = db.getAllRecipes();
		for (Recipes r : allRecipes) {
			if (u.getName().equals(r.getUser())) {
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
			con = DriverManager.getConnection(url, user, password);
			// create statement
			statement = con.createStatement();
			query = "UPDATE recipes SET user=\'" + u.getName() + "\' WHERE `food_id`=" + (UserActivity.RecipeIDs) + ";";

			statement.execute(query);
			statement.close();
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		u.addRecipeToCollection(Integer.toString(UserActivity.RecipeIDs), r.getName());
		query = "UPDATE users SET myRecipes= JSON_SET(myRecipes, '$.\"" + (UserActivity.RecipeIDs) + "\"',\""
				+ r.getName() + "\") WHERE `name`='" + u.getName() + "\';";
		try {
			con = DriverManager.getConnection(url, user, password);
			// create statement
			statement = con.createStatement();
			statement.execute(query);
			query = "UPDATE id SET recipeID=" + UserActivity.RecipeIDs + ";";
			statement.execute(query);
			statement.close();
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void removeRecipes(User u, Recipes r) {
		try {
			// create connection
			con = DriverManager.getConnection(url, user, password);
			// create statement
			statement = con.createStatement();
			query = "UPDATE users SET myRecipes= JSON_REMOVE(myRecipes, '$.\"" + (r.getRecipeID())
					+ "\"') WHERE `name`='" + u.getName() + "\';";
			statement.execute(query);
			query = "DELETE FROM recipes WHERE `food_id`=\'" + r.getRecipeID() + "\';";
			statement.execute(query);
			statement.close();
			result.close();
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
		for (Recipes r : lst) {
			if (r.getName().equals(name)) {
				return r;
			}
		}
		return null;
	}
	public boolean getByID(User u, int id) {
		ArrayList<Recipes> lst = new ArrayList<Recipes>();
		lst = getRecipes(u);
		for (Recipes r : lst) {
			if (r.getRecipeID()==id) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<Ingredient> getIngredients(User u) {
		ArrayList<User> users = getAll();
		ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
		String jsonString;

		for (User usr : users) {
			// Return user ingredients if user exists in the database.
			if (usr.getName().equals(u.getName())) {

				try {
					// Create connection
					con = DriverManager.getConnection(url, user, password);
					// Create statement
					statement = con.createStatement();
					// Set query to retrieve user's ingredients from database.
					query = "SELECT myIngredients FROM users WHERE `name`='" + u.getName() + "\';";
					// Execute query and save result.
					result = statement.executeQuery(query);

					// Move result set cursor to the first (and only) row.
					result.next();
					// Retrieve the user's ingredients as a json string.
					jsonString = result.getString(1);
					// Parse json string.
					ingredients = parseIngredients(jsonString);
				} catch (SQLException e) {
					e.printStackTrace();
				}

				return ingredients;
			}
		}
		// If user doesn't exist in the database, return null.
		return null;
	}
	
	private ArrayList<Ingredient> parseIngredients(String jsonString) {
		ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
		
		// Remove array-indicating square brackets from the string.
		jsonString = jsonString.substring(1, jsonString.length() - 1);
		// Split string up into an array of strings for each ingredient by splitting
		// where the string has "}, {"
		String[] ingredientStrings = jsonString.split("\\}, \\{");
		for (String ingredientString : ingredientStrings) {
			// Further split each ingredient string up into another array for each field by
			// spitting at each ",".
			String[] fieldStrings = ingredientString.split(", ");
			// Create an array to save the retrieved field values in.
			String[] valueStrings = new String[fieldStrings.length];
			for (int i = 0; i < fieldStrings.length; i++) {
				// Further split each field into its key and value
				String[] kvField = fieldStrings[i].split(": ");
				if(kvField.length <= 1) return ingredients;
				// Remove extra characters from value leaving behind only letters and numbers
				// and save it.
				valueStrings[i] = kvField[1].replaceAll("[{}\"]", "");
			}
			LocalDate date = null;
			try {
				DateTimeFormatter format = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH);
				date = LocalDate.parse(valueStrings[5], format);
			} catch (Exception e) {
				e.printStackTrace();
			}
			ingredients.add(new Ingredient(valueStrings[1], Double.parseDouble(valueStrings[0]), date,
					Integer.parseInt(valueStrings[4]), Integer.parseInt(valueStrings[3]), valueStrings[2]));
		}
		// Return user ingredients if it exists in the database.
		return ingredients;
	}
	
	public boolean updateIngredients(User u, ArrayList<Ingredient> ingredients) {
		ArrayList<User> users = getAll();
		for (User usr : users) {
			// Return user ingredients if user exists in the database.
			if (usr.getName().equals(u.getName())) {

				try {
					// Create connection
					con = DriverManager.getConnection(url, user, password);
					// Create statement
					statement = con.createStatement();
					// Set query to retrieve user's ingredients from database.
					query = "UPDATE users SET myIngredients = \'" + u.ingredientsToJSON() + "\' WHERE `name`=\'" + u.getName() + "\';";
					// Execute query and save result.
					statement.execute(query);

					return true;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		// If user doesn't exist in the database, return false.
		return false;
	}

	@Override
	public Ingredient getIngredient(User u, String name) {
		ArrayList<User> users = getAll();
		Ingredient ingredient = null;

		for (User usr : users) {
			// Return user ingredients if user exists in the database.
			if (usr.getName().equals(u.getName())) {

				try {
					// create connection
					con = DriverManager.getConnection(url, user, password);
					// create statement
					statement = con.createStatement();
					query = "SELECT JSON_SEARCH('myIngredients', 'one', '" + name + "') FROM users;";
					result = statement.executeQuery(query);
					if (!result.getString(1).equals("null")) {
						String path = result.getString(1);
						path = path.substring(0, path.indexOf("."));
						query = "SELECT JSON_EXTRACT('myIngredients', '" + path + "') FROM users;";
						statement.close();
						result.close();
						statement = con.createStatement();
						result = statement.executeQuery(query);

						// Parse result set into ingredient's attributes.
						double cost = Double.parseDouble(result.getString(2));
						LocalDate expiration = null;
						DateTimeFormatter format = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH);;						
						try {
							expiration = LocalDate.parse(result.getString(3), format);
						} catch (Exception e) {

						}
						int protein = Integer.parseInt(result.getString(4));
						int carbs = Integer.parseInt(result.getString(5));
						String userName = result.getString(6);

						// Create a new Ingredient object from the attributes and add it to the
						// ingredients arraylist.
						ingredient = new Ingredient(name, cost, expiration, protein, carbs, userName);
					}
				} catch (SQLException e) {

				}
			}
		}
		return ingredient;
	}

	@Override
	public void edit(User t) {}
        
	// this method edits the user's allergies
	@Override
	public void editAllergy(User u, String allergyType, int changeNum) {
		u.getUserAllergies().getAllergies().replace(allergyType, changeNum);
		try {
			// create connection
			con = DriverManager.getConnection(url, user, password);
			// create statement
			statement = con.createStatement();
			query = "UPDATE users SET allergies= JSON_REPLACE(allergies, '$.\"" + allergyType + "\"',\'" + changeNum
					+ "\') WHERE `name`=\'" + u.getName() + "\';";
			statement.execute(query);
			statement.close();
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	@Override
	public ArrayList<Recipes> getFavoriteList(User u) {
		ArrayList<Recipes> recipeList = new ArrayList<>();
		for(Recipes r: getRecipes(u)) {
			if(r.favourite==1) {
				recipeList.add(r);
			}
		}
		return recipeList;
	}

	// this method edits the user's favourite recipes
	@Override
	public void editFavorites(Recipes r, int change) {
		
		try {
			// create connection
			con = DriverManager.getConnection(url, user, password);
			// create statement
			statement = con.createStatement();
			query = "UPDATE recipes SET favorite=\'" + change + "\' WHERE `food_id`=\'" + r.getRecipeID() + "\';";
			statement.execute(query);
			statement.close();
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void editShoppingList(String ingredient, String name)
	{
		try {
			// create connection
			con = DriverManager.getConnection(url, user, password);
			// create statement
			statement = con.createStatement();
			query = "UPDATE users SET shoppingList=\'" + ingredient + "\' WHERE `name`=\'" + name + "\';";
			statement.execute(query);
			statement.close();
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void editPlanner(User u,String day,String time, String recipe) {
		try {
			// create connection
			con = DriverManager.getConnection(url, user, password);
			// create statement
			statement = con.createStatement();
			query = "UPDATE users SET `plan`=json_set(plan,'$."+day+"."+time+"\', \'"+u.getWeekPlanner().get(day).recipesToJSON(time)+"\') where `name`=\'"+u.getName()+"\';";
			statement.execute(query);
			statement.close();
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
