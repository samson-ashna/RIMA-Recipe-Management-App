package objects;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Set;

/**
 * 
 */
public class Allergies
{
    Hashtable<String, Integer> allergies=new Hashtable<String, Integer>();

    /**
     * Lists the options of allergies into a hashtable
     */
    public Allergies()
    {
        //this.allergies = new Hashtable<String, Integer>();
        this.allergies.put("Eggs", 0);
        this.allergies.put("Milk", 0);
        this.allergies.put("Peanuts", 0);
        this.allergies.put("Seafood", 0);
    }
    
    /**
     * Places and updates the hashtable keys where the allergies are contained
     * @param prompt
     */
    public Allergies(String prompt)
    {
        this.allergies = new Hashtable<String, Integer>();
        this.allergies.put("Eggs", 0);
        this.allergies.put("Milk", 0);
        this.allergies.put("Peanuts", 0);
        this.allergies.put("Seafood", 0);

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

    /**
     * 
     * @return
     */
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

    /**
     * 
     * @return
     */
    public Hashtable<String, Integer> getAllergies(){
    	
    	return this.allergies;
    }
    public void setAllergies(Hashtable<String,Integer> lst) {
    	this.allergies = lst;
    }

    /**
     * The specific allergy is displayed as an output to the user
     */
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
