package madesy.web.servlets.courier;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import madesy.model.pickings.Picking;
import madesy.web.utils.PickingServiceManager;
import madesy.web.utils.RequestManager;

@WebServlet("/dispatched-pickings")
public class DispatchedPickingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(final HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		new RequestManager(request, response) {

			@Override
			public String request() {
				new PickingServiceManager(request) {

					@Override
					protected void process() {
						List<Picking> pickings = pickingService
								.getDispatchedPickings(loggedUser.getId());
						request.setAttribute("dispatchedPickings", pickings);
					}
				}.process();
				return "courier/dispatched-pickings.jsp";
			}
		}.forward();
	}
}
