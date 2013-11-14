package madesy.web.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(urlPatterns = { "/*" }, description = "SessionFilter")
public class SessionFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession(false);
		System.out.println("Session Filter");
		String path = req.getServletPath();

		if (session == null && path.trim().equalsIgnoreCase("/main")) {
			resp.sendRedirect("");
			return;
		}

		if (path.trim().equalsIgnoreCase("/index.jsp")
				|| path.trim().equalsIgnoreCase("/login")) {
			chain.doFilter(request, response);
			return;
		}

		if (session != null && session.getAttribute("user") != null) {
			chain.doFilter(request, response);
			return;
		}

		resp.sendError(403, "error.jsp");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
