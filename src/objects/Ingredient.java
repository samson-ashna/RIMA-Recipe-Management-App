package objects;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import businessLogic.UserActivity;

public class Ingredient {
	private String name;
	private int protein = 0;
	private int carbs = 0;
	private LocalDate expiration;
	private double cost = 0;
	private String user;
	
	public Ingredient(String name, double cost, LocalDate expiration, int protein, int carbs, String user) {
		this.name = name;
		this.expiration = expiration;
		
		//temp
		setProtein(protein);
		setCarbs(carbs);
		setCost(cost);
	}
	
	//Getters and Setters
	public void setExpiration(LocalDate expiration) {
		this.expiration = expiration;
	}
	
	public LocalDate getExpiration() {
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
		if((new UserActivity()).checkUserName(user.getName())) {
			this.user = user.getName();
			return true;
		}
		return false;
	}
	
	public String getUser() {
		return this.user;
	}
	
	public String ingredientToJSON() {
		String jsonString = "";
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern("d MMMM yyyy", Locale.ENGLISH);;
		
		jsonString = jsonString + "{" + "\"name\": " + "\"" + this.name + "\", "
	    		+ "\"cost\": " + "\"" + this.cost + "\", "
	    		+ "\"expiration\": " + "\"" + format.format(this.expiration) + "\", "
	    		+ "\"protein\": " + "\"" + this.protein + "\", "
	    		+ "\"carbs\": " + "\"" + this.carbs + "\", "
	    		+ "\"user\": " + "\"" + this.user + "\"" + "}";
		
		return jsonString;
	}
	
	
}
