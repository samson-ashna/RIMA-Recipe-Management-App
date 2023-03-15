package objects;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Ingredient {
	private String name;
	private int protein = 0;
	private int carbs = 0;
	private Date expiration;
	private double cost = 0;
	private String user;
	
	public Ingredient(String name, double cost, Date expiration, int protein, int carbs, String user) {
		this.name = name;
		this.expiration = expiration;
		
		//temp
		setProtein(protein);
		setCarbs(protein);
		setCost(cost);
	}
	
	//Getters and Setters
	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}
	
	public Date getExpiration() {
		return this.expiration;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	//Sets the protein if the given value is nonnegative and returns whether the value was changed.
	public boolean setProtein(int protein) {
		if(protein >= 0) {
			this.protein = protein;
			return true;
		} else {
			return false;
		}
	}
	
	public int getProtein() {
		return this.protein;
	}
	
	//Sets the carbs if the given value is nonnegative and returns whether the value was changed.
	public boolean setCarbs(int carbs) {
		if(carbs >= 0) {
			this.carbs = carbs;
			return true;
		} else {
			return false;
		}
	}
	
	public int getCarbs() {
		return this.carbs;
	}
	
	//Sets the cost if the given value is nonnegative and returns whether the value was changed.
	public boolean setCost(double cost) {
		if(carbs >= 0) {
			this.cost = cost;
			return true;
		} else {
			return false;
		}
	}
	
	public double getCost() {
		return this.cost;
	}
	
	public boolean setUser(User user) {
		//check if user exists, if so, set the user and return true.
		
		return false;
	}
	
	public String getUser() {
		return this.user;
	}
	
	public String ingredientToJSON() {
		String jsonString = "";
		
		SimpleDateFormat format = new SimpleDateFormat("dd MMMM yyyy");
		
		jsonString = jsonString + "{" + "\"name\": " + "\"" + this.name + "\", "
	    		+ "\"cost\": " + "\"" + this.cost + "\", "
	    		+ "\"expiration\": " + "\"" + format.format(this.expiration) + "\", "
	    		+ "\"protein\": " + "\"" + this.protein + "\", "
	    		+ "\"carbs\": " + "\"" + this.carbs + "\", "
	    		+ "\"user\": " + "\"" + this.user + "\"" + "}";
		
		return jsonString;
	}
	
	
}
