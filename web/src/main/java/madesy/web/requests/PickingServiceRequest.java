package madesy.web.requests;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import madesy.model.pickings.PickingService;
import madesy.web.model.User;

public abstract class PickingServiceRequest extends Request {
	protected PickingService pickingService;
	protected User loggedUser;
	
	public PickingServiceRequest(HttpServletRequest request, HttpServletResponse response) {
		super(request, response);
		this.pickingService = (PickingService) request.getServletContext().getAttribute("pickingService");
		this.loggedUser = (User)request.getSession(false).getAttribute("user");
	}
}
