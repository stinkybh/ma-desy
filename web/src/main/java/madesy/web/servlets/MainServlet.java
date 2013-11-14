package madesy.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import madesy.model.User;
import madesy.model.types.UserTypes;
import madesy.web.requests.Request;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1285140711416523203L;
	private String forwardURL;

	public MainServlet() {
		super();
	}

	public void doGet(final HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		final HttpSession session = request.getSession(false);
		if (session != null) {
			new Request(request, response) {

				@Override
				public String request() {
					User loggedUser = (User) session.getAttribute("user");
					if (loggedUser.getType() == UserTypes.CLIENT)
						forwardURL = "new-picking";
					else if (loggedUser.getType() == UserTypes.COURIER)
						forwardURL = "dispatched-pickings";
					else
						forwardURL = "manager";
					return forwardURL;
				}
			}.forward();
			return;
		}
		
		response.sendRedirect("login");
	}
}
