package madesy.web.servlets.courier;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import madesy.web.servlets.BaseServlet;

@WebServlet("/courier/deliver")
public class DeliverServlet extends BaseServlet {
	private static final long serialVersionUID = 4692953249933523263L;

	public DeliverServlet() {
		super();
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		pickingService.setTaken(loggedUser.getId(),
				request.getParameter("pickingId"));

		redirect("dispatched-pickings");
	}
}
