package madesy.web.servlets.courier;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import madesy.model.User;
import madesy.model.pickings.PickingService;
import madesy.storage.EventLog;
import madesy.storage.PickingStorage;
import madesy.web.utils.PickingServiceManager;

@WebServlet("/deliver")
public class DeliverServlet extends HttpServlet {
	private static final long serialVersionUID = 4692953249933523263L;
	private PickingService pickingService;
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		new PickingServiceManager(request) {
			
			@Override
			protected void service() {
				//pickingService.setTaken(loggedUser.getId(), pickingId);
			}
		}.process();
		
	}

}
