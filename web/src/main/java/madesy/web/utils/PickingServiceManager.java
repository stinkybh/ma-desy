package madesy.web.utils;

import javax.servlet.http.HttpServletRequest;

import madesy.model.User;
import madesy.model.pickings.PickingService;
import madesy.storage.EventLog;
import madesy.storage.PickingStorage;

public abstract class PickingServiceManager {
	private HttpServletRequest request;
	private PickingService pickingService;
	private User loggedUser;
	
	public PickingServiceManager(HttpServletRequest request) {
		this.request = request;
		
	}
	
	protected abstract void service();

	public void process() {
		EventLog eventLog = (EventLog) request.getServletContext().getAttribute("eventLog");
		PickingStorage pickingStorage = (PickingStorage) request.getServletContext().getAttribute("pickingStorage");
		pickingService = new PickingService(eventLog, pickingStorage);
		loggedUser = (User)request.getSession(false).getAttribute("user");
	
		service();
	}
}

