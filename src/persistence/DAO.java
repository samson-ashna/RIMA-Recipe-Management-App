package persistence;

import java.util.ArrayList;

import objects.Recipes;
import objects.User;

/**
 * Interface to the Databases containing user and recipe information.
 * @param <T> is a User or Recipes object
 */
public interface DAO<T> {
	T get(String name);
	ArrayList<T> getAll();
	void add(T t);
	void remove(T t);
	void edit(T t);
	//void changeUserNames(User u, String newName);
}