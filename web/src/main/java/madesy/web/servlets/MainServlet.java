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
import madesy.web.utils.RequestManager;

@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1285140711416523203L;
	private final String CLIENT_HOME_PAGE = "client/client.jsp";
	private final String COURIER_HOME_PAGE = "courier/courier.jsp";
	private final String MANAGER_HOME_PAGE = "manager/manager.jsp";
	private String forwardURL;

	public MainServlet() {
		super();
	}

	public void doGet(final HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		final HttpSession session = request.getSession(false);
		if (session != null) {
			new RequestManager(request, response) {

				@Override
				public String request() {
					User loggedUser = (User) session.getAttribute("user");
					if (loggedUser.getType() == UserTypes.CLIENT)
						forwardURL = CLIENT_HOME_PAGE;
					else if (loggedUser.getType() == UserTypes.COURIER)
						forwardURL = COURIER_HOME_PAGE;
					else
						forwardURL = MANAGER_HOME_PAGE;
					return forwardURL;
				}
			}.forward();
			return;
		}
		
		response.sendRedirect("login");
	}
}
