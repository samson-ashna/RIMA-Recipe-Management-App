package repository;

import java.util.ArrayList;

import objects.User;

public class UsersRepository {
	ArrayList<User> users = new ArrayList<User>();
	public void addUser(User user) {
		users.add(user);
	}
	public ArrayList<User> getUsers(){
		return users;
	}
}
