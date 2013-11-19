package madesy.model.events;

import java.util.Date;

import madesy.model.BaseModel;

/**
 * Contains information describing each event
 * 
 */
public class Event extends BaseModel {
	private Date date = new Date();
	private String metaData = "";
	private EventType eventType;

	public Event(EventType eventType) {
		this.eventType = eventType;
	}

	public Event(EventType eventType, String... metaData) {
		this(eventType);
		for (String meta : metaData) {
			this.metaData += meta + ", ";
		}
		// Remove last ", " from metaData
		this.metaData = this.metaData.substring(0,
				this.metaData.lastIndexOf(", ")).trim();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Event))
			return false;
		Event other = (Event) obj;
		if (id.equals(other.id))
			return false;
		return true;
	}

	public String getId() {
		return this.id;
	}

	public EventType getEventType() {
		return eventType;
	}

	public Date getDate() {
		return date;
	}

	public String getMetaData() {
		return metaData;
	}

	@Override
	public String toString() {
		return "Event [eventId=" + this.id + ", eventType=" + eventType
				+ ", date=" + date + ", metaData=" + metaData + "]\n";
	}
}
