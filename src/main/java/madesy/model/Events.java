package madesy.model;

import madesy.model.types.EventType;

/**
 * Factory class used to create Event objects
 * @author Krasimir Atanasov
 *
 */
public class Events {
	
	private Events() {
		System.out.println();
		//	This class cannot be initialized
	}
	
	/**
	 * Creates new picking event
	 * @param pickingId
	 * @return 
	 */
	public static Event newPicking(String pickingId) {
		return new Event(EventType.NEW_PICKING, pickingId);
	}
	
	/**
	 * Creates new dispatched picking event
	 * @param pickingId
	 * @param courrierId
	 * @return 
	 */
	public static Event dispachedPicking(String pickingId, String courrierId) {
		String metaData = buildMetadata(pickingId, courrierId);
		return new Event(EventType.DISPATCH_PICKING, metaData);
	}
	
	/**
	 * Creates taken picking event
	 * @param pickingId
	 * @param courrierId
	 * @return
	 */
	public static Event takenPicking(String pickingId, String courrierId) {
		String metaData = buildMetadata(pickingId, courrierId);
		return new Event(EventType.TAKE_PICKING, metaData);
	}
	
	/**
	 * Creates manager report event 
	 * @param reportId
	 * @return
	 */
	public static Event managerReport(String reportId) {
		return new Event(EventType.MANAGER_REPORT, reportId);
	}
	
	private static String buildMetadata(String pickingId, String courrierId) {
		return (pickingId.concat(",")).concat(courrierId);
	}
}
