package madesy.web.servlets.courier;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import madesy.model.pickings.Picking;
import madesy.web.requests.PickingServiceRequest;

@WebServlet("/details")
public class PickingDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {

		new PickingServiceRequest(request, response) {

			@Override
			public String request() {
				Picking picking = pickingService.getPicking(request
						.getParameter("pickingId"));
				if (picking != null) {
					request.setAttribute("dispatchedPicking", picking);
					return "courier/picking-details.jsp";
				}

				return "error.jsp";
			}
		}.forward();
	}
}
