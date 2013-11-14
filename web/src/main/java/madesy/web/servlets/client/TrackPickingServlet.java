package madesy.web.servlets.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import madesy.web.utils.PickingServiceManager;
import madesy.web.utils.RequestManager;

@WebServlet("/client/track-picking")
public class TrackPickingServlet extends HttpServlet {
	private static final long serialVersionUID = 9201624660696571253L;

	public void doGet(final HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		new RequestManager(request, response) {
			
			@Override
			public String request() {
				new PickingServiceManager(request) {
					
					@Override
					protected void process() {
						String pickingId = request.getParameter("id");
						
						request.setAttribute("picking", pickingService.getPickingById(pickingId));
					}
				}.process();
				return "track-picking.jsp";
			}
		}.forward();
	}
}
