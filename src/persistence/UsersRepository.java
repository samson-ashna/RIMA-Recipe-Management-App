package persistence;

import java.util.ArrayList;

import objects.User;

public class UsersRepository {
	protected ArrayList<User> users = new ArrayList<User>();
	public ArrayList<User> getUsers(){
		User sara = new User("Sara","1234");
		users.add(sara);
		return users;
	}
}
