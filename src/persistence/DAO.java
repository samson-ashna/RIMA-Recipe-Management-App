package persistence;

import java.util.*;

public interface DAO<T> {
	T get(String name);
	ArrayList<T> getAll();
	void add(T t);
	void remove(T t);
}