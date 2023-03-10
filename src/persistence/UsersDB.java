package persistence;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;

import businessLogic.UserActivity;
import objects.Ingredient;
import objects.Recipes;
import objects.User;

/**
 * 
 */
public class UsersDB extends DBSetup implements UsersDAO  {
	private static ArrayList<User> dbUsers = new ArrayList<User>();;

	/**
	 * 
	 */
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

	/**
	 * 
	 */
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
				String allergies = result.getString(5);
				//String mealTime = result.getString(5);
				
				User u = new User(name,password);
				u.setRecipeCollection(myRecipes);
				u.setAllergies(allergies);
				dbUsers.add(u);

				Hashtable<String, String> recipesLst = new Hashtable<String, String>();
				if(myRecipes !=null) {
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
				}
				Hashtable<String,Integer> allergyLst = new Hashtable<String, Integer>();
				if(allergies !=null) {
				String[] arrOfStr = allergies.split(",");
				for (String s: arrOfStr) {
					s = s.replace("{","");
					s= s.replace("}","");
					String[] values = s.split(":");
					values[0] = values[0].replace("\"","");
					values[0] = values[0].replace("\\s","");
					values[0].strip();
					values[1] = values[1].replace("\"","");
					values[1] = values[1].replace("\\s","");
					values[1].strip();
					//System.out.println(values[1]);
					if(values[0].length()>0) {
					allergyLst.put(values[0],Integer.parseInt(values[1].strip()));
					}
				}
				}
				u.setAllergyInformation(allergyLst);
				u.setRecipeCollection(recipesLst);
			//}
			}
			statement.close();
			result.close();
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
			
			query = "INSERT INTO users(name, password, myRecipes, myIngredients,allergies) VALUES (\'"+t.getName()+"\', \'"+t.getPassword()+"\',\'"+t.getRecipeCollection1()+"\', \'"+t.ingredientsToJSON()+"\', \'"+t.getAllergies()+"\');";
			statement.execute(query);
			query = "UPDATE users SET allergies= JSON_SET(allergies, '$.\""+"Eggs"+"\"',\'"+ 0+"\','$.\""+"Milk"+"\"',\'"+0+"\','$.\""+"Peanuts"+"\"',\'"+0+"\','$.\""+"Seafood"+"\"',\'"+0+"\') WHERE `name`=\'"+t.getName()+"\';";
			statement.execute(query);
			//query = "UPDATE users SET allergies= JSON_SET(allergies, '$.\"Egg\"','0') WHERE `name`=\'"+t.getName()+"\';";
			//statement.execute(query);
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
			con = DriverManager.getConnection (url , user , password );
			// create statement
			statement = con.createStatement();
			query = "DELETE FROM users WHERE `name`=\'"+t.getName()+"\';";
		
			statement.execute(query);
			statement.close();
			result.close();
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
				statement.close();
				result.close();
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
				statement.close();
				result.close();
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
			statement.close();
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		u.addRecipeToCollection(Integer. toString(UserActivity.RecipeIDs),r.getName());
		query = "UPDATE users SET myRecipes= JSON_SET(myRecipes, '$.\""+(UserActivity.RecipeIDs)+"\"',\""+ r.getName()+"\") WHERE `name`='"+u.getName()+"\';";
		//query = "UPDATE users SET myRecipes=JSON_SET(myRecipes,\'{\" "+Integer.toString(UserActivity.RecipeIDs-1)+"\":"+Integer.toString(UserActivity.RecipeIDs-1)+"}\') WHERE `name`=\'"+u.getName()+"\';";
		try {
			con = DriverManager.getConnection (url , user , password );
			// create statement
			statement = con.createStatement();
			statement.execute(query);
			query = "UPDATE id SET recipeID="+UserActivity.RecipeIDs+";";
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
			con = DriverManager.getConnection (url , user , password );
			// create statement
			statement = con.createStatement();
			query = "UPDATE users SET myRecipes= JSON_REMOVE(myRecipes, '$.\""+(r.getRecipeID())+"\"') WHERE `name`='"+u.getName()+"\';";
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
		for(Recipes r: lst) {
			if(r.getName().equals(name)) {
				return r;
			}
		}
		return null;
	}

	@Override
	public ArrayList<Ingredient> getIngredients(User u) {
		ArrayList<User> users = getAll();
		ArrayList<Ingredient> ingredients = new ArrayList<Ingredient>();
		String ingredientsString;
		
		for(User usr : users) {
			//Return user ingredients if user exists in the database.
			if(usr.getName().equals(u.getName())) {
								
				try {
					// create connection
					con = DriverManager.getConnection (url , user , password );
					// create statement
					statement = con.createStatement();
					query = "SELECT myIngredients FROM users WHERE `name`='"+u.getName()+"\';";
					result = statement.executeQuery(query);
					while(result.next()) {
						
						//Parse result set into ingredient's attributes.
						String name = result.getString(1);
						double cost = Double.parseDouble(result.getString(2));
						Date expiration = null;
						try {
							expiration = DateFormat.getDateInstance().parse(result.getString(3));
						} catch (Exception e) {
							
						}
						int protein = Integer.parseInt(result.getString(4));
						int carbs = Integer.parseInt(result.getString(5));
						String userName = result.getString(6);
						
						//Create a new Ingredient object from the attributes and add it to the ingredients arraylist.
						ingredients.add(new Ingredient(name, cost, expiration, protein, carbs, userName));
						
					}
					statement.close();
					result.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				return ingredients;
			}
		}
		//If user doesn't exist in the database, return null.
		return null;
	}

	@Override
	public boolean addIngredient(User u, Ingredient i) {
		ArrayList<User> users = getAll();
		
		for(User usr : users) {
			//Return user ingredients if user exists in the database.
			if(usr.getName().equals(u.getName())) {
				query = "SELECT JSON_SEARCH('myIngredients', 'one', '" + i.getName() + "') FROM users;";
				try {
					// create connection
					con = DriverManager.getConnection (url , user , password );
					// create statement
					statement = con.createStatement();
					query = "SELECT JSON_SEARCH('myIngredients', 'one', '" + i.getName() + "') FROM users;";
					result = statement.executeQuery(query);
					if(!result.getString(1).equals("null")) {
						String path = result.getString(1);
						path = path.substring(0,path.indexOf("."));	
						statement.close();
						result.close();						
						query = "UPDATE users SET myIngredients = JSON_SET(myIngredients, '"+path+"', \""+ i.ingredientToJSON()+"\") WHERE `name`='"+u.getName()+"';";
						statement = con.createStatement();
						statement.execute(query);
						u.addIngredientToCollection(i);
						return true;
					}else {
						query = "SELECT JSON_LENGTH('myIngredients') FROM users;";
						statement.close();
						result.close();
						statement = con.createStatement();
						result = statement.executeQuery(query);
						query = "UPDATE users SET myIngredients = JSON_INSERT(myIngredients, '"+Integer.parseInt(result.getString(1))+1+"', \""+ i.ingredientToJSON()+"\") WHERE `name`='"+u.getName()+"';";
						statement.close();
						result.close();
						statement = con.createStatement();
						statement.execute(query);
						u.addIngredientToCollection(i);
						return true;
					}
				} catch (SQLException e) {
					
				}				
			}
		}
		return false;
	}

	@Override
	public boolean removeIngredient(User u, Ingredient i) {
		ArrayList<User> users = getAll();
		
		for(User usr : users) {
			//Return user ingredients if user exists in the database.
			if(usr.getName().equals(u.getName())) {
				
				try {
					// create connection
					con = DriverManager.getConnection (url , user , password );
					// create statement
					statement = con.createStatement();
					query = "SELECT JSON_SEARCH('myIngredients', 'one', '" + i.getName() + "') FROM users;";
					result = statement.executeQuery(query);
					if(!result.getString(1).equals("null")) {
						String path = result.getString(1);
						path = path.substring(0,path.indexOf("."));
						query = "UPDATE users SET myIngredients = JSON_REMOVE(myIngredients, '"+path+"') WHERE `name`='"+u.getName()+"';";
						statement.close();
						result.close();
						statement = con.createStatement();
						statement.execute(query);
						u.removeIngredientFromCollection(i);
						return true;
					}
				} catch (SQLException e) {
					
				}	
				
				
			}
		}
		return false;
		
	}

	@Override
	public Ingredient getIngredient(User u, String name) {
		ArrayList<User> users = getAll();
		Ingredient ingredient = null;
		
		for(User usr : users) {
			//Return user ingredients if user exists in the database.
			if(usr.getName().equals(u.getName())) {
				
				try {
					// create connection
					con = DriverManager.getConnection (url , user , password );
					// create statement
					statement = con.createStatement();
					query = "SELECT JSON_SEARCH('myIngredients', 'one', '" + name + "') FROM users;";
					result = statement.executeQuery(query);
					if(!result.getString(1).equals("null")) {
						String path = result.getString(1);
						path = path.substring(0,path.indexOf("."));
						query = "SELECT JSON_EXTRACT('myIngredients', '" + path + "') FROM users;";
						statement.close();
						result.close();
						statement = con.createStatement();
						result = statement.executeQuery(query);
						
						//Parse result set into ingredient's attributes.
						String iName = result.getString(1);
						double cost = Double.parseDouble(result.getString(2));
						Date expiration = null;
						try {
							expiration = DateFormat.getDateInstance().parse(result.getString(3));
						} catch (Exception e) {
							
						}
						int protein = Integer.parseInt(result.getString(4));
						int carbs = Integer.parseInt(result.getString(5));
						String userName = result.getString(6);
						
						//Create a new Ingredient object from the attributes and add it to the ingredients arraylist.
						ingredient = new Ingredient(name, cost, expiration, protein, carbs, userName);
					}
				} catch (SQLException e) {
					
				}
			}
		}
		return ingredient;
	}
	
	@Override
	public void edit(User t) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void editAllergy(User u, String allergyType, int changeNum) {
		u.getUserAllergies().getAllergies().replace(allergyType, changeNum);
		try {
			// create connection
			con = DriverManager.getConnection (url , user , password );
			// create statement
			statement = con.createStatement();
			query = "UPDATE users SET allergies= JSON_REPLACE(allergies, '$.\""+allergyType+"\"',\'"+ changeNum+"\') WHERE `name`=\'"+u.getName()+"\';";
			statement.execute(query);
			statement.close();
			result.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
