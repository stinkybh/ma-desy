package madesy.model.dao;

import java.util.List;

import madesy.model.User;
import madesy.model.Users;

public class UsersDAO {
	private Users users;
	
	public UsersDAO() {
		this.users = new Users();
	}
	
	public List<User> getUsersList() {
		return this.users.getUsers();
	}
	
	public User getUser(String id) {
		for( User u : users.getUsers() ) {
			if(u.getId().equals(id)) {
				return u;
			}
		}
		return null;
	}
}
