package madesy.web.servlets.manager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import madesy.model.services.ReportService;
import madesy.web.utils.RequestManager;

@WebServlet("/report")
public class ViewReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(final HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		new RequestManager(request, response) {

			@Override
			public String request() {
				ReportService reportService = (ReportService) request
						.getServletContext().getAttribute("reportService");
				String id = request.getParameter("id");
				request.setAttribute("report", reportService.getReportById(id));
				
				return "manager/view-report.jsp";
			}
		}.forward();
	}
}
