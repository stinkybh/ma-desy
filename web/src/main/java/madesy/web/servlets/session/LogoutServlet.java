package madesy.web.servlets.session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import madesy.web.servlets.BaseServlet;

@WebServlet("/logout")
public class LogoutServlet extends BaseServlet {
	private static final long serialVersionUID = 8273458547981675642L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session != null) {
			session.invalidate();
		}
		redirect("main");
	}

}
