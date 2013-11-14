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

import madesy.model.User;
import madesy.model.types.UserTypes;

/**
 * Servlet Filter implementation class AccessFilter
 */
@WebFilter(urlPatterns = { "/client/*", "/courier/*", "/manager/*" }, description = "Gives access to pages only for the logged user")
public class AccessFilter implements Filter {

	/**
	 * Default constructor.
	 */
	public AccessFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession(false);

		String path = req.getServletPath();

		if (session == null) {
			chain.doFilter(request, response);
			return;
		}

		User loggedUser = (User) session.getAttribute("user");
		if (loggedUser != null && !path.trim().equals("/main")) {
			if (loggedUser.getType() == UserTypes.CLIENT
					&& !path.trim().startsWith("/client")) {
				resp.sendError(403, "Unauthorized access");
				return;
			} else if (loggedUser.getType() == UserTypes.COURIER
					&& !path.trim().startsWith("/courier")) {
				resp.sendError(403, "Unauthorized access");
				return;
			} else if (loggedUser.getType() == UserTypes.MANAGER
					&& !path.trim().startsWith("/manager")) {
				resp.sendError(403, "Unauthorized access");
				return;
			}
		}

		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
