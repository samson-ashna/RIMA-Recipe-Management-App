package objects;

import java.util.Hashtable;

public class FoodPreferences
{
    Hashtable<String, Integer> foodPreferences;

    public FoodPreferences(String prompt)
    {
        this.foodPreferences = new Hashtable<String, Integer>();
        this.foodPreferences.put("Gluten Free", 0);
        this.foodPreferences.put("Vegatarian", 0);
        this.foodPreferences.put("Pollotarian", 0);
        this.foodPreferences.put("Ketogenic", 0);
        this.foodPreferences.put("Spicy", 0);
    }


}