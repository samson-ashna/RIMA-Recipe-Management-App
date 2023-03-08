package persistence;

import java.util.ArrayList;

import businessLogic.UserActivity;
import objects.Recipes;
import objects.User;

public class DatabaseAccess {
	int databaseType = 1;//0 for access to real database, 1 for access to stub database
	public DAO<Recipes> recipesDB() {
		if(databaseType==0) {
			RecipesDB db = new RecipesDB();
			db.setRecipeIDs(); 
			return db;
		}
		RecipesStubDB db = new RecipesStubDB();
		return db;
	}
	public UsersDAO usersDB(){
		if(databaseType==0) {
			RecipesDB dbRecipe = new RecipesDB();
			dbRecipe.setRecipeIDs();
			UsersDB db = new UsersDB();
			return db;
		}
		UsersStubDB db = new UsersStubDB();
		return db;
	}
}
