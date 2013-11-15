package madesy.web.servlets.manager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import madesy.model.reports.ReportService;
import madesy.web.servlets.BaseServlet;

@WebServlet("/manager/manager")
public class ManagerServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		ReportService reportService = (ReportService) request
				.getServletContext().getAttribute("reportService");

		request.setAttribute("reports", reportService.getReports());
		forward("manager.jsp");
	}
}
