package objects;

import java.util.ArrayList;

public class Planner {
	public ArrayList<String> breakfast = new ArrayList<String>();
	public ArrayList<String> lunch = new ArrayList<String>();
	public ArrayList<String> dinner = new ArrayList<String>();
	String[] days= {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
	Planner(){
	}
	public Planner(String breakfast,String lunch,String dinner){
		this.breakfast.add(breakfast);
		this.lunch.add(lunch);
		this.dinner.add(dinner);
	}
	public String getBreakfast() {
		String breakfastString="";
		for(String r: this.breakfast) {
			breakfastString +=r+",\n";
		}
		return breakfastString;
	}
	public String getLunch() {
		String lunchString="";
		for(String r: this.lunch) {
			lunchString +=r+",\n";
		}
		return lunchString;
	}
	public String getDinner() {
		String dinnerString="";
		for(String r: this.dinner) {
			dinnerString +=r+",\n";
		}
		return dinnerString;
	}
	public String recipesToJSON(String time) {
		String jsonString = "";
		ArrayList<String> recipes = new ArrayList<String>();
		if(time=="Breakfast") {
			recipes = this.breakfast;
		}else if(time=="Lunch") {
			recipes = this.lunch;
		}else{
			recipes= this.dinner;
		}
		for(String s:recipes) {
			jsonString = jsonString +s+","; 
		}
		return jsonString;
	}
	public void setListFormat(String time, String recipes) {
		String[] arr = recipes.split(",");
		for(String s:arr) {
			if(s.length()>0) {
				if(time=="breakfast") {
					this.breakfast.add(s);
				}else if (time=="lunch") {
					this.lunch.add(s);
				}else {
					this.dinner.add(s);
				}
				
			}
		}
	}
	public void removeFromPlanner(String day,String time,String recipe) {
		
		if(time=="Breakfast") {
			breakfast.remove(recipe);
		}else if(time == "Lunch") {
			lunch.remove(recipe);			
		}else {
			dinner.remove(recipe);
		}	
	}
	public void addToPlanner(String day,String time, String recipe) {
		
		if(time=="Breakfast") {
			breakfast.add(recipe);
		}else if(time == "Lunch") {
			lunch.add(recipe);			
		}else {
			dinner.add(recipe);
		}
	}


}

