package madesy.web.servlets.courier;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import madesy.web.requests.PickingServiceRequest;

@WebServlet("/deliver")
public class DeliverServlet extends HttpServlet {
	private static final long serialVersionUID = 4692953249933523263L;

	public DeliverServlet() {
		super();
	}

	public void doPost(final HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		new PickingServiceRequest(request, response) {

			@Override
			public String request() {
				pickingService.setTaken(loggedUser.getId(),
						request.getParameter("pickingId"));

				return "dispatched-pickings";
			}
		}.redirect();
	}

}
