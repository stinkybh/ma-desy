package madesy.web.servlets.courier;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import madesy.model.pickings.Picking;
import madesy.web.servlets.BaseServlet;

@WebServlet("/courier/dispatched-pickings")
public class DispatchedPickingsServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Picking> pickings = pickingService
				.getDispatchedPickings(loggedUser.getId());
		request.setAttribute("dispatchedPickings", pickings);
		forward("dispatched-pickings.jsp");
	}
}
