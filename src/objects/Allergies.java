package objects;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Set;

public class Allergies
{
    Hashtable<String, Integer> allergies;

    public Allergies()
    {
        this.allergies = new Hashtable<String, Integer>();
        this.allergies.put("Eggs", 0);
        this.allergies.put("Milk", 0);
        this.allergies.put("Peanuts", 0);
        this.allergies.put("Seafood", 0);
    }

    public Allergies(String prompt)
    {
        this.allergies = new Hashtable<String, Integer>();
        this.allergies.put("Eggs", 0);
        this.allergies.put("Milk", 0);
        this.allergies.put("Peanuts", 0);
        this.allergies.put("Seafood", 0);

        /*if (this.allergies.containsKey(prompt))
        {
            allergies.replace(prompt, 1);
        }*/
        if (prompt.contains("1"))
        {
            this.allergies.replace("Eggs", 1);
        }
        if (prompt.contains("2"))
        {
            this.allergies.replace("Milk", 1);
        }
        if (prompt.contains("3"))
        {
            this.allergies.replace("Peanuts", 1);
        }
        if (prompt.contains("4"))
        {
            this.allergies.replace("Seafood", 1);
        }
    }

    public ArrayList<String> getAllergyNames()
    {
        Set<String> keys = this.allergies.keySet();
        ArrayList<String> array = new ArrayList<String>();
        for (String key : keys)
        {
            array.add(key);
        }
        return array;
    }
    
    public Hashtable<String, Integer> getAllergies(){
    	return this.allergies;
    }

    public String toString()
    {
        String eggs = "Eggs";
        String milk = "Milk";
        String peanuts = "Peanuts";
        String seafood = "Seafood";
        String output = "";

        if (this.allergies.get("Eggs") == 1)
        {
            output += eggs + " ";
        }
        if (this.allergies.get("Milk") == 1)
        {
            output += milk + " ";
        }
        if (this.allergies.get("Peanuts") == 1)
        {
            output += peanuts + " ";
        }
        if (this.allergies.get("Seafood") == 1)
        {
            output += seafood + " ";
        }

        return output.substring(0, output.length() - 1);
    }
}