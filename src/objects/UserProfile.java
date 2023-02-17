package objects;

import java.util.ArrayList;
import java.util.Scanner;

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
    //ArrayList<Allergies> allergens = new ArrayList<Allergies>();
    //Hashtable<Integer, Integer> allergens = new Hashtable<Integer, Integer>();
    Allergies allergens;

    public UserProfile()
    {

    }

    public UserProfile(Username username, Password password)
    {
        this.username = username;
        previousUserNames.add(username);
        this.password = password;
        previousPasswords.add(password);
    }

    public void addRecipe(Recipes recipe)
    {
        this.recipes.add(recipe);
    }

    public void removeRecipe(Recipes recipe)
    {
        this.recipes.remove(recipe);
    }

    /*public String recipes()
    {
        return this.recipes;
    }*/

    public void changeUsername(Username username)
    {
        this.username = username;
        previousUserNames.add(username);
    }

    public void changePassword(Password password)
    {
        this.password = password;
        previousPasswords.add(password);
    }

    public String username()
    {
        return this.username.toString();
    }

    public String password()
    {
        return this.password.toString();
    }

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
        /*if (!this.allergens.contains(allergen))
        {
            this.allergens.add(allergen);
        }*/

    }

    public String allergies()
    {
        //System.out.println("1.Eggs 2.Milk 3.Peanuts, 4.Seafood");
        return this.allergens.toString();
    }
}

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


/*class Allergies
{
    //ArrayList<String> allergies = new ArrayList<String>();
    String allergen;

    public Allergies()
    {

    }

    public Allergies(String allergen)
    {
        this.allergen = allergen;
    }

    public String toString()
    {
        return this.allergen;
    }
}*/