package madesy.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import madesy.web.model.UserTypes;

@WebServlet("/main")
public class MainServlet extends BaseServlet {
	private static final long serialVersionUID = 1285140711416523203L;

	public MainServlet() {
		super();
	}

	public void doGet(final HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		if (request.getSession(false) != null) {
			if (loggedUser.getType() == UserTypes.CLIENT)
				redirect("client/new-picking");
			else if (loggedUser.getType() == UserTypes.COURIER)
				redirect("courier/dispatched-pickings");
			else
				redirect("manager/manager");
			
			return;
		}
		redirect("");
	}
}
