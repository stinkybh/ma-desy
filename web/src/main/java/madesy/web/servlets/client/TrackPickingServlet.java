package madesy.web.servlets.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import madesy.web.servlets.BaseServlet;

@WebServlet("/client/track-picking")
public class TrackPickingServlet extends BaseServlet {
	private static final long serialVersionUID = 9201624660696571253L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String pickingId = request.getParameter("id");
		request.setAttribute("picking",
				pickingService.getPickingById(pickingId));

		forward("track-picking.jsp");
	}
}
