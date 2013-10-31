package madesy;

import java.util.Date;
import java.util.List;

import madesy.model.Event;
import madesy.model.types.EventType;
import madesy.storage.EventLog;

public class EventLogAnalyzer {
	private EventLog eventLog;
	private Date fromDate;
	private Date toDate;

	public EventLogAnalyzer(EventLog eventLog, Date fromDate, Date toDate) {
		this.eventLog = eventLog;
		this.fromDate = fromDate;
		this.toDate = toDate;
	}

	public boolean compareNewToDispatched() {
		return true;//checkPickingsRatio(EventType.NEW_PICKING, EventType.DISPATCH_PICKING);
	}
	
	public boolean compareDispatchedToTaken() {
		return true;//checkPickingsRatio(EventType.DISPATCH_PICKING, EventType.TAKE_PICKING);
	}
	
/*	public int getTakenPickings(String courierId) {
		List<Event> takenPickings = eventLog.getEvents(EventType.TAKE_PICKING, fromDate, toDate);
		int count = 0;
		for(Event e : takenPickings) {
			String[] metaData = e.getMetaData().split(",");
			if(metaData[1] == courierId)
				count++;
		}
		return count;
	}*/

	/*private boolean checkPickingsRatio(EventType type1, EventType type2) {
		return eventLog.getEvents(type1, fromDate, toDate).size() > 2 *
					eventLog.getEvents(type2, fromDate, toDate).size();
	}*/
	
	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}
}
