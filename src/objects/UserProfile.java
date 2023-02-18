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
     * 
     */
    public UserProfile() {}

    /**
     * 
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
     * 
     * @param recipe
     */
    public void addRecipe(Recipes recipe)
    {
        this.recipes.add(recipe);
    }

    /**
     * 
     * @param recipe
     */
    public void removeRecipe(Recipes recipe)
    {
        this.recipes.remove(recipe);
    }

    /**
     * 
     * @param username
     */
    public void changeUsername(Username username)
    {
        this.username = username;
        previousUserNames.add(username);
    }

    /**
     * 
     * @param password
     */
    public void changePassword(Password password)
    {
        this.password = password;
        previousPasswords.add(password);
    }

    /**
     * 
     * @return
     */
    public String username()
    {
        return this.username.toString();
    }

    /**
     * 
     * @return
     */
    public String password()
    {
        return this.password.toString();
    }

    /**
     * 
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
     * 
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
     * 
     * @return
     */
    public String allergies()
    {

        return this.allergens.toString();
    }
}

/**
 * 
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