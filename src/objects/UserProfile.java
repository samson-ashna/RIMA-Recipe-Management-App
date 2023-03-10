package objects;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 */
public class UserProfile
{
    // User names
    Username username;
    ArrayList<Username> previousUserNames = new ArrayList<Username>();
    // Passwords
    Password password;
    ArrayList<Password> previousPasswords = new ArrayList<Password>();
    // Recipes
    ArrayList<Recipes> recipes = new ArrayList<Recipes>();
    // Allergies
    Allergies allergens;

    /**
     * Created a User Profile Class
     */
    public UserProfile() {}

    /**
     * This method adds the user's username and password into the system
     * @param username
     * @param password
     */
    public UserProfile(Username username, Password password)
    {
        this.username = username;
        previousUserNames.add(username);
        this.password = password;
        previousPasswords.add(password);
    }

    /**
     * This method adds recipes
     * @param recipe
     */
    public void addRecipe(Recipes recipe)
    {
        this.recipes.add(recipe);
    }

    /**
     * This method removes recipes
     * @param recipe
     */
    public void removeRecipe(Recipes recipe)
    {
        this.recipes.remove(recipe);
    }

    /**
     * This method allows the User to change their username
     * @param username
     */
    public void changeUsername(Username username)
    {
        this.username = username;
        previousUserNames.add(username);
    }

    /**
     * This method allows the User to change their password
     * @param password
     */
    public void changePassword(Password password)
    {
        this.password = password;
        previousPasswords.add(password);
    }

    /**
     * This methods writes the username as a String
     * @return username, as a String conversion
     */
    public String username()
    {
        return this.username.toString();
    }

    /**
     * This methods writes the password as a String
     * @return password, as a String conversion
     */
    public String password()
    {
        return this.password.toString();
    }

    /**
     * This method is the User's Signup page
     */
    public void SignUp()
    {
        // Asks user to input username
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter a username:");
        String usernameInput = scanner.nextLine();
        Username username = new Username(usernameInput);
        this.username = username;

        // Asks user to input password
        System.out.println("Please enter a password:");
        String passwordInput = scanner.nextLine();
        Password password = new Password(passwordInput);
        this.password = password;
    }

    /**
     * This method allows the user to add any allergies they have to their account
     */
    public void addAllergen()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select your food allergies:");
        System.out.println("1. Eggs");
        System.out.println("2. Milk");
        System.out.println("3. Peanuts");
        System.out.println("4. Seafood");
        String allergenInput = scanner.nextLine();
        this.allergens = new Allergies(allergenInput);

    }

    /**
     * This methods writes the allergies selected as a String
     * @return allergies, as a String conversion
     */
    public String allergies()
    {

        return this.allergens.toString();
    }
}

/**
 * These methods return the username and password
 */
class Username
{
    String username;

    public Username(String username)
    {
        this.username = username;
    }

    public String toString()
    {
        return username;
    }
}

class Password
{
    String password;

    public Password(String password)
    {
        this.password = password;
    }

    public String toString()
    {
        return this.password;
    }
}
