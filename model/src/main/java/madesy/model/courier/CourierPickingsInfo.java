package madesy.model.courier;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Class used to track the current amount of carried pickings by every courier
 * 
 * @author hristo
 * 
 */
public class CourierPickingsInfo {
	private Map<String, Integer> couriers = new HashMap<String, Integer>();

	public CourierPickingsInfo() {

	}

	/**
	 * Returns all couriers ID
	 * 
	 * @return
	 */
	public Set<String> getCouriers() {
		return couriers.keySet();
	}

	/**
	 * Adds new courier to track
	 * 
	 * @param courierId
	 */
	public void addCourier(String courierId) {
		couriers.put(courierId, 0);
	}

	/**
	 * Increases the current number of carried pickings of the courier,
	 * specified by his id.
	 * 
	 * @param courierId
	 */
	public void onNewPicking(String courierId) {
		int dispatchedPickings = couriers.get(courierId);
		couriers.put(courierId, ++dispatchedPickings);
	}

	/**
	 * Decreases the current number of carried pickings of the courier,
	 * specified by his id.
	 * 
	 * @param courierId
	 */
	public void setTaken(String courierId) {
		int dispatchedPickings = couriers.get(courierId);
		couriers.put(courierId, --dispatchedPickings);
	}

	/**
	 * Finds the courier, currently carrying the lowest number of pickings
	 * 
	 * @return courierId
	 */
	public String getCourier() {
		int courierPickings = (int) couriers.values().toArray()[0];
		String courierId = (String) couriers.keySet().toArray()[0];
		for (String id : couriers.keySet()) {
			if (couriers.get(id) < courierPickings) {
				courierId = id;
				courierPickings = couriers.get(id);
			}
		}
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
	 * Removes a courier from the tracking list
	 * 
	 * @param courierId
	 */
	public void removeCourier(String courierId) {
		couriers.remove(courierId);
	}
}
