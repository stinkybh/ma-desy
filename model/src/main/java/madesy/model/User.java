package madesy.model;

import madesy.model.types.UserTypes;

/**
 * Represents a user, able to log with a username and password
 * 
 */
public class User {
	private String id;
	private String username;
	private String password;
	private UserTypes type;

	public User() {
	}

	public User(String id, String username, String password, UserTypes type) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.type = type;
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", type=" + type + "]";
	}

	public String getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public UserTypes getType() {
		return type;
	}

}
