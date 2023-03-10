package businessLogic;

import java.util.ArrayList;

import objects.Recipes;
import objects.User;
import persistence.UsersStubDB;
import persistence.DatabaseAccess;
import persistence.UsersDAO;

/**
 * 
 */
public class SaveRecipe {
    
    public ArrayList<Recipes> myRecipes;
    /* The database containing user information is accessed through the data access
	object, UsersDAO*/
    DatabaseAccess access = new DatabaseAccess();
    UsersDAO usersinfo = access.usersDB();
    /**
     * Default constructor
     */
    public SaveRecipe() {
    	myRecipes = new ArrayList<>();
    }

    /**
     * 
     * @param currentUser
     */
    public SaveRecipe(User currentUser) {
		
		myRecipes = usersinfo.getRecipes(currentUser);

	}

    /**
     * Copy constructor
     * @param s is a SaveRecipe object
     */
    public SaveRecipe(SaveRecipe myRecipes) {
        this.myRecipes = myRecipes.getRecipes();
    }

    /**
     * Saves user's recipe into myRecipes
     * @param recipe is the user's recipe they want to add
     */
    public void save(Recipes recipe) {
        myRecipes.add(recipe);
        usersinfo.addRecipes(UserActivity.getCurrentUser(),recipe);
        
    }

    public void testSave(Recipes recipe) {
        myRecipes.add(recipe);
        usersinfo.addRecipes(new User("test", "test"),recipe);
         
     }

    /**
     * Getter method for myRecipes
     * @return it returns ArrayList myRecipes
     */
    public ArrayList<Recipes> getRecipes() {
        return myRecipes;
    }

    /**
     * 
     * @param name
     * @return
     */
    public Recipes getRecipe(String name) {

        for(Recipes e : myRecipes) {

            if(e.getName().equals(name)) {
                return e;
            }

        }

        return null;

    }

    /**
     * 
     * @return
     */
    public int getSize() {
        return myRecipes.size();
    }

    /**
     * 
     */
    @Override
    public String toString() {

        String temp = "Recipes: | ";

        for(Recipes e : myRecipes) {
            temp += e.getName() + " Protein: " + e.getProtein() + " Carbohydrates: " + e.getCarbs() + " | ";
        }
        
        return temp;

    }

}
