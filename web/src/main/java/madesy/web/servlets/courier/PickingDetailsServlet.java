package madesy.web.servlets.courier;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import madesy.model.pickings.Picking;
import madesy.web.utils.PickingServiceManager;
import madesy.web.utils.RequestManager;

@WebServlet("/courier/details")
public class PickingDetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(final HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		new RequestManager(request, response) {

			@Override
			public String request() {
				new PickingServiceManager(request) {

					@Override
					protected void process() {
						Picking picking = pickingService.getPicking(request.getParameter("pickingId"));
						if(picking != null) {
							request.setAttribute("dispatchedPicking", picking);
						}
					}
					
				}.process();
				if(request.getAttribute("dispatchedPicking") != null)
					return "picking-details.jsp";
				
				return "error.jsp";
			}
		}.forward();
	}
}
