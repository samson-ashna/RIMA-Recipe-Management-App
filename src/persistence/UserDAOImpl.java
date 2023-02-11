package persistence;

import java.util.ArrayList;
import java.util.List;

import objects.Recipes;
import objects.User;

public class UserDAOImpl implements UserDAO {
	UsersRepository db = new UsersRepository();
	@Override
	public User get(String name) {
		User user = null;
		for (User u: db.getUsers()) {
			if(u.getName().equals(name)) {
				user = u;
			}
		}
		return user;
	}

	@Override
	public ArrayList<User> getAll() {
		
		return db.getUsers();
	}

	@Override
	public void add(User u) {
		
		db.addUser(u);
	}

	@Override
	public void remove(User u) {
	
		db.removeUser(u);
	}

	@Override
	public List<Recipes> getRecipeCollection(User u){
		for (User user: db.getUsers()) {
			if(user.equals(u)) {
				return u.getRecipeCollection();
			}
		}
		return null;
	}

}
