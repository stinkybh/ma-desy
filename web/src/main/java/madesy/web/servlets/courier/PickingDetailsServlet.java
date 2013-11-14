package madesy.web.servlets.courier;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import madesy.model.pickings.Picking;
import madesy.web.requests.PickingServiceRequest;

@WebServlet("/courier/details")
public class PickingDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {

		new PickingServiceRequest(request, response) {

			@Override
			public String request() {
				List<Picking> pickings = pickingService
						.getDispatchedPickings(loggedUser.getId());
				request.setAttribute("dispatchedPickings", pickings);

				return "dispatched-pickings.jsp";
			}
		}.forward();
	}
}
