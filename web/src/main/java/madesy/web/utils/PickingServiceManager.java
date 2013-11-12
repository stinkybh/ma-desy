package madesy.web.utils;

import javax.servlet.http.HttpServletRequest;

import madesy.model.User;
import madesy.model.pickings.PickingService;
import madesy.storage.EventLog;
import madesy.storage.PickingStorage;

public abstract class PickingServiceManager {
	private HttpServletRequest request;
	protected PickingService pickingService;
	protected User loggedUser;
	
	private EventLog eventLog;
	private PickingStorage pickingStorage;
	
	public PickingServiceManager(HttpServletRequest request) {
		this.request = request;
		this.eventLog = (EventLog) request.getServletContext().getAttribute("eventLog");
		this.pickingStorage = (PickingStorage) request.getServletContext().getAttribute("pickingStorage");
		this.pickingService = new PickingService(eventLog, pickingStorage);
		this.loggedUser = (User)request.getSession(false).getAttribute("user");
	}
	
	protected abstract void service();

	public void process() {
		
	
		service();
	}
}

