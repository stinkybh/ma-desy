package madesy.web.servlets.session;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import madesy.model.User;
import madesy.model.dao.UsersDAO;
import madesy.model.dto.LoginRequest;
import madesy.web.utils.ParametersToBeanConverter;
import madesy.web.utils.RequestManager;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = -2203014235977020400L;

	public LoginServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		new RequestManager(request, response) {

			@Override
			public String request() {
				return "index.jsp";
			}
		}.forward();
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		LoginRequest loginRequest = ParametersToBeanConverter.populate(
				LoginRequest.class, request);
		UsersDAO usersDAO = new UsersDAO();
		for (User u : usersDAO.getUsersList()) {
			if (u.getUsername().equals(loginRequest.getUsername())
					&& u.getPassword().equals(loginRequest.getPassword())) {
				request.getSession().setAttribute("user", u);
				response.sendRedirect("main");
				return;
			}
		}

		RequestDispatcher rd = request.getRequestDispatcher("error.jsp");
		rd.forward(request, response);
	}
}
