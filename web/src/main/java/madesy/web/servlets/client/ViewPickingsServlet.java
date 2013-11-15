package madesy.web.servlets.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import madesy.web.servlets.BaseServlet;

@WebServlet("/client/view-pickings")
public class ViewPickingsServlet extends BaseServlet {
	private static final long serialVersionUID = -7682281396626429728L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("pickings",
				pickingService.getPickingsByClientId(loggedUser.getId()));
		forward("view_pickings.jsp");
	}

}
