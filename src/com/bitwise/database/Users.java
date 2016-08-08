package com.bitwise.database;

import java.util.ArrayList;
import java.util.List;
import com.bitwise.models.User;

public class Users {
	
	private List<User> users = new ArrayList<User>();
	
	public Users () {
		this.initUsers();
	}
	
	public void initUsers () {
		users.add(new User("pikachu", "electricPokemon@12"));
		users.add(new User("balbasaur", "forestPokemon@12"));
		users.add(new User("charizard", "firePokemon@12"));
		users.add(new User("meow", "catPokemon@12"));
		users.add(new User("richu", "electricPokemon@12"));
	}
	
	public List<User> getUsers () {
		return this.users;
	}
}
