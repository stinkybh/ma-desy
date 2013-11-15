package madesy.web.servlets.courier;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import madesy.model.pickings.Picking;
import madesy.web.servlets.BaseServlet;

@WebServlet("/courier/details")
public class PickingDetailsServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException,
			IOException {

		Picking picking = pickingService.getPicking(request
				.getParameter("pickingId"));
		if (picking != null) {
			request.setAttribute("dispatchedPicking", picking);
			forward("picking-details.jsp");
			return;
		}

		forward("error.jsp");
	}
}
