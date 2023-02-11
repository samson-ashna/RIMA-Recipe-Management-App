package persistence;

import java.util.ArrayList;

import objects.User;

public class UsersRepository {
	private ArrayList<User> users = new ArrayList<User>();
	public void addUser(User user) {
		users.add(user);
	}
	public ArrayList<User> getUsers(){
		return users;
	}
	public void removeUser(User u) {
		users.remove(u);
	}
}
