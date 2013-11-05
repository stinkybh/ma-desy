package madesy.storage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import madesy.model.Event;
import madesy.model.types.EventType;

/**
 * Contains a list of events for all worker threads activities on the picking
 * storage.
 */
public class EventLog {
	private List<Event> eventLog = new CopyOnWriteArrayList<Event>();

	public EventLog() {
		System.out.println();
	}

	public void add(Event event) {
		this.eventLog.add(event);
	}

	/**
	 * Returns a list of events, specified by the EventType parameter and based
	 * on the arguments for from and to dates.
	 * 
	 * @param type
	 *            - The type of the event {@link madesy.model.types.EventType}
	 * @param fromDate
	 * @param toDate
	 * @return List of {@link madesy.model.Event}
	 */
	public List<Event> getEvents(EventType type, Date fromDate, Date toDate) {
		List<Event> eventsForPeriod = new ArrayList<Event>();

		for (Event event : eventLog) {
			if (event.getEventType() == type
					&& ((event.getDate().after(fromDate) && event.getDate()
							.before(toDate))
							|| event.getDate().equals(fromDate) || event
							.getDate().equals(toDate))) {
				eventsForPeriod.add(event);
			}
		}

		return eventsForPeriod;
	}

	/**
	 * 
	 * @param picikingId
	 * @return
	 */
	public List<Event> getPickingInfo(String picikingId) {
		List<Event> events = new ArrayList<Event>();

		for (Event e : eventLog) {
			EventType type = e.getEventType();
			String picId = splitMetaData(e.getMetaData())[0];

			if (picId.equals(picikingId)) {
				if (type == EventType.NEW_PICKING
						|| type == EventType.DISPATCH_PICKING
						|| type == EventType.TAKE_PICKING) {
					events.add(e);
				}
			}
		}

		return events;
	}

	@Override
	public String toString() {
		return "EventLog [eventLog=" + eventLog + "]";
	}

	public List<Event> getEvents() {
		return eventLog;
	}

	private String[] splitMetaData(String metaData) {
		String[] data = metaData.split(", ");

		return data;
	}

}
