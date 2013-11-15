package madesy.workers;

import java.util.Date;

import madesy.model.events.EventLog;
import madesy.model.events.Events;
import madesy.model.reports.Report;
import madesy.model.reports.ReportGenerator;
import madesy.model.reports.ReportService;

/**
 * Class used to generate reports, based on status of all available in
 * PickingStorage pickings
 * 
 * @author Trimata glupaci
 * 
 */
public class ManagerWorker extends BaseWorker {
	private EventLog eventLog;
	private Date fromDate;
	private Date toDate;
	private ReportService reportService;

	public ManagerWorker(EventLog eventLog, ReportService reportService,
			int sleepTime) {
		super(sleepTime);
		this.eventLog = eventLog;
		this.reportService = reportService;
		fromDate = new Date();
	}

	@Override
	public void doWork() {
		toDate = new Date();
		ReportGenerator generator = new ReportGenerator(eventLog, fromDate,
				toDate);
		Report report = generator.generateReport();

		addToEventLog(report);
		this.reportService.addReport(report);

		// System.out.println(report);
		fromDate = toDate;
	}

	/**
	 * Adds the report creation event to the event log.
	 * 
	 * @param report
	 */
	private void addToEventLog(Report report) {
		eventLog.add(Events.managerReport(report.getId()));
	}

}
