package repository;

import java.util.ArrayList;

import objects.User;

public class UsersRepository {
	static ArrayList<User> users = new ArrayList<User>();
	public void addUser(User user) {
		users.add(user);
	}
	public static ArrayList<User> getUsers(){
		return users;
	}
}
