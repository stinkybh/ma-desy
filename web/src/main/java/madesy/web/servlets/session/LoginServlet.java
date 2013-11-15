package madesy.web.servlets.session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import madesy.web.dto.LoginRequest;
import madesy.web.model.User;
import madesy.web.model.UsersDAO;
import madesy.web.servlets.BaseServlet;
import madesy.web.utils.ParametersToBeanConverter;

@WebServlet("/login")
public class LoginServlet extends BaseServlet {
	private static final long serialVersionUID = -2203014235977020400L;

	public LoginServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		forward("index.jsp");
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
				redirect("main");
				return;
			}
		}

		forward("error.jsp");
	}
}
