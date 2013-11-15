package madesy.web.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import madesy.model.pickings.PickingService;
import madesy.web.model.User;

public abstract class BaseServlet extends HttpServlet {
	private static final long serialVersionUID = 8849959337418346446L;

	protected PickingService pickingService;
	protected User loggedUser;

	protected HttpServletRequest request;
	protected HttpServletResponse response;

	public BaseServlet() {
		
	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.pickingService = (PickingService) this.getServletContext()
				.getAttribute("pickingService");
		this.request = request;
		this.response = response;
		if(request.getSession(false) != null)
			this.loggedUser = (User) request.getSession().getAttribute("user");
		super.service(request, response);
	}

	public void forward(String url) throws ServletException, IOException {
		doForward(url, request, response);
	}

	public void redirect(String url) throws IOException {
		doRedirect(url, request, response);
	}

	private void doForward(String url, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}

	private void doRedirect(String url, HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.sendRedirect(url);
	}
}
