package persistence;

import java.util.ArrayList;

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
}