package madesy.web.utils;

import javax.servlet.http.HttpServletRequest;

import madesy.model.User;
import madesy.model.services.PickingService;

public abstract class PickingServiceManager {
	protected PickingService pickingService;
	protected User loggedUser;
	
	public PickingServiceManager(HttpServletRequest request) {
		this.pickingService = (PickingService) request.getServletContext().getAttribute("pickingService");
		this.loggedUser = (User)request.getSession(false).getAttribute("user");
	}

	protected abstract void process();
}
