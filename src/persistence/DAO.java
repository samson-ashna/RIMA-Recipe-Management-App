package persistence;

import java.util.ArrayList;


public interface DAO<T> {
	T get(String name);
	ArrayList<T> getAll();
	void add(T t);
	void remove(T t);
}