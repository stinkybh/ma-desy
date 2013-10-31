package madesy.model.workers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import madesy.EventLogAnalyzer;
import madesy.model.Events;
import madesy.model.Report;
import madesy.model.types.ReportType;
import madesy.storage.EventLog;

/**
 * Class used to generate reports, based on status of all available in
 * PickingStorage pickings
 * 
 * @author Krasimir Atanasov
 * 
 */
public class ManagerWorker extends BaseWorker {
	private EventLog eventLog;
	private Date fromDate;
	private Date toDate;
	private EventLogAnalyzer analyzer;

	public ManagerWorker(EventLog eventLog, int sleepTime) {
		super(sleepTime);
		this.eventLog = eventLog;
		fromDate = new Date();
		analyzer = new EventLogAnalyzer(eventLog, fromDate, toDate);
	}

	@Override
	public void doWork() {
		toDate = new Date();
		System.out.println();
		System.out.println(eventLog);
		Report report = new Report(fromDate,
				toDate);
		analyzer.setFromDate(fromDate);
		analyzer.setToDate(toDate);
		report.getPickingsReport().addAll(makeReportForPickings());
		//report.getCourrierPickings().putAll(makeReportForCourriers());
		
		addToEventLog(report);
		
		System.out.println(report);
		fromDate = toDate;
	}

	/**
	 * Generates a report based on the status of the pickings.
	 * 
	 * @return List of {@link ReportType}
	 */
	private List<ReportType> makeReportForPickings() {
		List<ReportType> reportList = new ArrayList<ReportType>();
		if(analyzer.compareNewToDispatched())
			reportList.add(ReportType.TOO_MANY_NEW_PICKINGS);
		else
			reportList.add(ReportType.ENOUGH_NEW_PICKINGS);
			
		if(analyzer.compareDispatchedToTaken())
			reportList.add(ReportType.DISPATCH_DELAYED);
		else
			reportList.add(ReportType.DISPATCH_PROPERLY);

		return reportList;		
}

	/**
	 * Generates report consisting of information based on the
	 * work done by the couriers.
	 * @return Map, where key is the id of each courier and value
	 * the number of pickings delivered.
	 */
	/*private Map<String, Integer> makeReportForCourriers() {
		Map<String, Integer> countCourrierPickings = new HashMap<String, Integer>();
		Set<String> courierIds = eventLog.getCouriersId();
		for(String id : courierIds) {
			countCourrierPickings.put(id, analyzer.getTakenPickings(id));
		}

		return countCourrierPickings;
	}*/
	
	/**
	 * Adds the report creation event to the event log.
	 * @param report
	 */
	private void addToEventLog(Report report) {
		eventLog.add(Events.managerReport(report.getId()));
	}

}
