package userLogin;

import java.util.ArrayList;
import java.util.Scanner;

public class UserProfile
{
    // Usernames
    Username username;
    ArrayList<Username> previousUserNames = new ArrayList<Username>();
    // Passwords
    Password password;
    ArrayList<Password> previousPasswords = new ArrayList<Password>();
    // Recipes
    ArrayList<Recipes> recipes = new ArrayList<Recipes>();
    // Allergies
    ArrayList<Allergies> allergens = new ArrayList<Allergies>();

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
        System.out.println("Please enter your food allergen:");
        String allergenInput = scanner.nextLine();
        Allergies allergen = new Allergies(allergenInput);
        if (!this.allergens.contains(allergen))
        {
            this.allergens.add(allergen);
        }
    }

    public ArrayList<Allergies> allergies()
    {
        return this.allergens;
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

class Recipes
{

}

class Allergies
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
}