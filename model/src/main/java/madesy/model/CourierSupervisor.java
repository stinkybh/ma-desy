package madesy.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Class used to overseer the work of the couriers
 * 
 * @author hristo
 * 
 */
public class CourierSupervisor {
	private static Map<String, Integer> couriers = new HashMap<String, Integer>();
	
	static {
		for(User u : Users.getCouriers()) {
			couriers.put(u.getId(), 0);
		}
	}

	public CourierSupervisor() {

	}

	/**
	 * Returns all couriers ID
	 * 
	 * @return
	 */
	public Set<String> getCouriers() {
		return couriers.keySet();
	}

	public static void addCourier(String courierId) {
		couriers.put(courierId, 0);
	}

	/**
	 * Increases the current number of carried pickings of the courier,
	 * specified by his id.
	 * 
	 * @param courierId
	 */
	public void incrementCarriedPickings(String courierId) {
		int dispatchedPickings = couriers.get(courierId);
		couriers.put(courierId, ++dispatchedPickings);
		System.out.println(couriers.get(courierId));
	}

	/**
	 * Decreases the current number of carried pickings of the courier,
	 * specified by his id.
	 * 
	 * @param courierId
	 */
	public void decrementCarriedPickings(String courierId) {
		int dispatchedPickings = couriers.get(courierId);
		couriers.put(courierId, --dispatchedPickings);
	}

	/**
	 * Finds the courier, currently carrying the lowest number of pickings
	 * 
	 * @return courierId
	 */
	public String getCourierWithLowestPickingsNumber() {
		int courierPickings = (int) couriers.values().toArray()[0];
		String courierId = (String) couriers.keySet().toArray()[0];
		for (String id : couriers.keySet()) {
			if (couriers.get(id) < courierPickings) {
				courierId = id;
				courierPickings = couriers.get(id);
			}
		}
		System.out.println(courierId);
		return courierId;
	}

	/**
	 * Returns the number of dispatched pickings to the specified courier
	 * 
	 * @param courierId
	 * @return
	 */
	public int getPickingsNumber(String courierId) {
		return couriers.get(courierId);
	}

	/**
	 * Adds a new courier to track
	 * 
	 * @param courierId
	 */
	public void addNewCourier(String courierId) {
		couriers.put(courierId, 0);
	}

	/**
	 * Removes a courier from the tracking list
	 * 
	 * @param courierId
	 */
	public void removeCourier(String courierId) {
		couriers.remove(courierId);
	}
}
