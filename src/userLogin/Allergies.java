package userLogin;

import java.util.Hashtable;

public class Allergies
{
    Hashtable<String, Integer> allergies;

    public Allergies(String prompt)
    {
        allergies = new Hashtable<String, Integer>();
        allergies.put("Eggs", 0); // Eggs
        allergies.put("Milk", 0); // Milk
        allergies.put("Peanuts", 0); // Peanuts
        allergies.put("Seafood", 0); // Seafood

        /*if (this.allergies.containsKey(prompt))
        {
            allergies.replace(prompt, 1);
        }*/
        if (prompt.contains("1"))
        {
            allergies.replace("Eggs", 1);
        }
        if (prompt.contains("2"))
        {
            allergies.replace("Milk", 1);
        }
        if (prompt.contains("3"))
        {
            allergies.replace("Peanuts", 1);
        }
        if (prompt.contains("4"))
        {
            allergies.replace("Seafood", 1);
        }
    }

    public String toString()
    {
        String eggs = "Eggs";
        String milk = "Milk";
        String peanuts = "Peanuts";
        String seafood = "Seafood";
        String output = "";

        if (allergies.get("Eggs") == 1)
        {
            output += eggs + " ";
        }
        if (allergies.get("Milk") == 1)
        {
            output += milk + " ";
        }
        if (allergies.get("Peanuts") == 1)
        {
            output += peanuts + " ";
        }
        if (allergies.get("Seafood") == 1)
        {
            output += seafood + " ";
        }

        return output.substring(0, output.length() - 1);
    }
}