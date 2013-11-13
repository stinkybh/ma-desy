package madesy.web.servlets.courier;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import madesy.web.utils.PickingServiceManager;
import madesy.web.utils.RequestManager;

@WebServlet("/deliver")
public class DeliverServlet extends HttpServlet {
	private static final long serialVersionUID = 4692953249933523263L;
	
	public DeliverServlet() {
		super();
	}

	public void doPost(final HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		new RequestManager(request, response) {

			@Override
			public String request() {
				new PickingServiceManager(request) {

					@Override
					protected void process() {
						pickingService.setTaken(loggedUser.getId(),
								request.getParameter("pickingId"));
					}
				}.process();
				return "dispatched-pickings";
			}
		}.redirect();
	}

}
