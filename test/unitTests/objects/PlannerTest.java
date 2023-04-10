package objects; 
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import objects.Recipes;
import persistence.DatabaseAccess;
import static org.junit.Assert.*;

class PlannerTest {
    @Test
    public void getBreakfastTest() {
        Planner planner = new Planner();
        planner.breakfast.add("Scrambled Eggs");
        planner.breakfast.add("Sausage");
        planner.breakfast.add("French Toast");
        assertEquals(planner.getBreakfast(), "Scrambled Eggs,\nSausage,\nFrench Toast,\n");
    }

    @Test
    public void getLunchTest() {
        Planner planner = new Planner();
        planner.lunch.add("Quinoa Salad");
        planner.lunch.add("French Onion Soup");
        planner.lunch.add("Fattoush Salad");
        assertEquals(planner.getLunch(), "Quinoa Salad,\nFrench Onion Soup,\nFattoush Salad,\n");
    }

    @Test
    public void getDinnerTest() {
        Planner planner = new Planner();
        planner.dinner.add("Steak");
        planner.dinner.add("Potato Wedges");
        planner.dinner.add("Noodles");
        assertEquals(planner.getDinner(), "Steak,\nPotato Wedges,\nNoodles,\n");
    }

    @Test
    public void recipesToJSONTest() {
        Planner planner = new Planner();
        planner.lunch.add("Rice");
        planner.lunch.add("Tacos");
        planner.breakfast.add("Cereal");
        planner.breakfast.add("Bread");
        planner.dinner.add("Dinner Rolls");
        planner.dinner.add("Pad Thai");
        assertEquals(planner.recipesToJSON("Breakfast"), "Cereal,Bread,");
        assertEquals(planner.recipesToJSON("Lunch"), "Rice,Tacos,");
        assertEquals(planner.recipesToJSON("Dinner"), "Dinner Rolls,Pad Thai,");
    }

    @Test
    public void removeFromPlannerTest() {
        Planner planner = new Planner();
        planner.breakfast.add("Cereal");
        planner.breakfast.add("Bread");
        planner.lunch.add("Rice");
        planner.lunch.add("Tacos");
        planner.dinner.add("Dinner Rolls");
        planner.dinner.add("Pad Thai");
        planner.removeFromPlanner("Tuesday", "Breakfast", "Cereal");
        planner.removeFromPlanner("Friday", "Lunch", "Tacos");
        planner.removeFromPlanner("Saturday", "Dinner", "Dinner Rolls");
        assertEquals(planner.getBreakfast(), "Bread,\n");
        assertEquals(planner.getLunch(), "Rice,\n");
        assertEquals(planner.getDinner(), "Pad Thai,\n");
    }

    @Test
    public void addToPlannerTest() {
        Planner planner = new Planner();
        planner.addToPlanner("Tuesday", "Breakfast", "Orange Juice");
        planner.addToPlanner("Friday", "Lunch", "Spring Rolls");
        planner.addToPlanner("Saturday", "Dinner","Asparagus");
    }
}
