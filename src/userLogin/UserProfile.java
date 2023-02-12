package eecs2311;

import java.util.ArrayList;

public class UserProfile
{
    Username username;
    ArrayList<Username> previousUserNames = new ArrayList<Username>();
    Password password;
    ArrayList<Password> previousPasswords = new ArrayList<Password>();
    ArrayList<Recipes> recipes = new ArrayList<Recipes>();

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

    public Username viewUsername()
    {
        return this.username;
    }
}

class Username
{
    String username;

    public Username(String username)
    {
        this.username = username;
    }
}

class Password
{
    String password;

    public Password(String password)
    {
        this.password = password;
    }
}

class Recipes
{

}