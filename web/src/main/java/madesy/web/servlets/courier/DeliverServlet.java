package madesy.web.servlets.courier;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import madesy.web.utils.PickingServiceManager;

@WebServlet("/deliver")
public class DeliverServlet extends HttpServlet {
	private static final long serialVersionUID = 4692953249933523263L;

	public void doPost(final HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		new PickingServiceManager(request) {

			@Override
			protected void process() {
				// request.getAttribute ???
				System.out.println(request.getParameterMap().toString());
				//pickingService.setTaken(loggedUser.getId(),
				//		request.getParameter("pickingId"));
			}
		}.process();

		
	}

}
