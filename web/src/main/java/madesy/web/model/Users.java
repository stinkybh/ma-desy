package madesy.web.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Contains a list of users, able to log in
 * 
 */
public class Users {
	private static List<User> users = new ArrayList<User>();

	static {
		for (int i = 0; i < 8; i++) {
			users.add(new User(UUID.randomUUID().toString(), "client" + i,
					"client" + i, UserTypes.CLIENT));
			if (i < 5)
				users.add(new User(UUID.randomUUID().toString(), "courier" + i,
						"courier" + i, UserTypes.COURIER));
		}
		users.add(new User(UUID.randomUUID().toString(), "manager", "manager",
				UserTypes.MANAGER));
	}

	public Users() {

	}

	public List<User> getUsers() {
		return Users.users;
	}
	
	public static List<User> getCouriers() {
		List<User> couriers = new ArrayList<User>();
		for(User u : users) {
			if(u.getType() == UserTypes.COURIER)
				couriers.add(u);
		}
		
		return couriers;
	}
}
