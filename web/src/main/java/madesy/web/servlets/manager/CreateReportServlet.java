package madesy.web.servlets.manager;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import madesy.model.events.EventLog;
import madesy.model.events.Events;
import madesy.model.reports.Report;
import madesy.model.reports.ReportGenerator;
import madesy.model.reports.ReportService;
import madesy.web.servlets.BaseServlet;

@WebServlet("/manager/create-report")
public class CreateReportServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		EventLog eventLog = pickingService.getEventLog();
		ReportService reportService = (ReportService) request
				.getServletContext().getAttribute("reportService");
		Date from = reportService.getDateOfLastReport();
		Date to = new Date();
		Report report = new ReportGenerator(eventLog, from, to)
				.generateReport();

		reportService.addReport(report);
		pickingService.getEventLog().add(
				Events.managerReport(report.getId()));
		
		redirect("report?id=" + report.getId());
		
	}
}
