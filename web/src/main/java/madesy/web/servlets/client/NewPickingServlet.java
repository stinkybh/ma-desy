package madesy.web.servlets.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import madesy.web.utils.RequestManager;

public class NewPickingServlet extends HttpServlet {
	private static final long serialVersionUID = -8281953502188692277L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		new RequestManager(request, response) {
			
			@Override
			public String request() {
				return "client.jsp";
			}
		}.forward();
	}


}
