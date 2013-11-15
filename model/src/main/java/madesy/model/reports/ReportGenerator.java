package madesy.model.reports;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import madesy.model.courier.CourierStatus;
import madesy.model.events.EventLog;
import madesy.model.events.EventLogAnalyzer;

public class ReportGenerator {
	private CourierStatus courierSupervisor;
	private EventLogAnalyzer analyzer;
	private Date from;
	private Date to;
	
	public ReportGenerator(EventLog eventLog, Date from, Date to) {
		this.analyzer = new EventLogAnalyzer(eventLog, from, to);
		this.courierSupervisor = new CourierStatus();
		this.from = from;
		this.to = to;
	}
	
	public Report generateReport() {
		Report report = new Report(from, to);
		
		report.getPickingsReport().addAll(makeReportForPickings());
		report.getCourrierPickings().putAll(makeReportForCourriers());

		return report;
	}
	
	/**
	 * Generates a report based on the status of the pickings.
	 * 
	 * @return List of {@link ReportType}
	 */
	private List<ReportType> makeReportForPickings() {
		List<ReportType> reportList = new ArrayList<ReportType>();
		if(analyzer.compareNewToDispatched())
			reportList.add(ReportType.DISPATCH_DELAYED);
		else
			reportList.add(ReportType.DISPATCH_PROPERLY);
			
		if(analyzer.compareDispatchedToTaken())
			reportList.add(ReportType.TAKEN_DELAYED);
		else
			reportList.add(ReportType.TAKEN_PROPERLY);
		return reportList;		
}

	/**
	 * Generates report consisting of information based on the
	 * work done by the couriers.
	 * @return Map, where key is the id of each courier and value
	 * the number of pickings delivered.
	 */
	private Map<String, Integer> makeReportForCourriers() {
		Map<String, Integer> countCourrierPickings = new HashMap<String, Integer>();
		Set<String> courierIds = courierSupervisor.getCouriers();
		for(String id : courierIds) {
			countCourrierPickings.put(id, analyzer.getTakenPickings(id));
		}
		return countCourrierPickings;
	}
}
