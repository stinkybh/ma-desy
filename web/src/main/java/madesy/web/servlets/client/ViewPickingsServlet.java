package madesy.web.servlets.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import madesy.model.User;
import madesy.model.pickings.PickingService;
import madesy.web.utils.RequestManager;

@WebServlet("/view-pickings")
public class ViewPickingsServlet extends HttpServlet {
	private static final long serialVersionUID = -7682281396626429728L;

	public void doGet(final HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		new RequestManager(request, response) {

			@Override
			public String request() {
				User loggedUser = (User) request.getSession().getAttribute("user");
				PickingService pickingService = (PickingService) request
						.getServletContext().getAttribute("pickingService");

				request.setAttribute("pickings", pickingService
						.getPickingsByClientId(loggedUser.getId()));
				
				return "client/view_pickings.jsp";
			}
		}.forward();
	}

}
