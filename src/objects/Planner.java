package objects;

import java.util.HashMap;
import java.util.List;

public class Planner {
	public String breakfast;
	public String lunch;
	public String dinner;
	String[] days= {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
	Planner(){
		this.breakfast = "";
		this.lunch="";
		this.dinner="";
	}
	public Planner(String breakfast,String lunch,String dinner){
		this.breakfast = breakfast;
		this.lunch = lunch;
		this.dinner = dinner;
	}

}

