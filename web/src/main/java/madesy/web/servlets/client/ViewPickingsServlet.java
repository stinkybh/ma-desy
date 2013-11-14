package madesy.web.servlets.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import madesy.web.requests.PickingServiceRequest;

@WebServlet("/view-pickings")
public class ViewPickingsServlet extends HttpServlet {
	private static final long serialVersionUID = -7682281396626429728L;

	public void doGet(final HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		new PickingServiceRequest(request, response) {

			@Override
			public String request() {
				request.setAttribute("pickings", pickingService
						.getPickingsByClientId(loggedUser.getId()));

				return "client/view_pickings.jsp";
			}
		}.forward();
	}

}
