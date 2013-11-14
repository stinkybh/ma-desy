package madesy.web.servlets.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import madesy.web.requests.PickingServiceRequest;

@WebServlet("/track-picking")
public class TrackPickingServlet extends HttpServlet {
	private static final long serialVersionUID = 9201624660696571253L;

	public void doGet(final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {
		
		new PickingServiceRequest(request, response) {
			@Override
			public String request() {
				String pickingId = request.getParameter("id");
				request.setAttribute("picking",
						pickingService.getPickingById(pickingId));

				return "client/track-picking.jsp";
			}
		}.forward();
	}
}
