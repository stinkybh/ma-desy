package madesy.storage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import madesy.model.Report;

public class ReportStorage {
	private List<Report> reports = new CopyOnWriteArrayList<Report>();
	
	public void add(Report report) {
		this.reports.add(report);
	}
	
	public List<Report> getReports() {
		return this.reports;
	}
	
	public List<Report> getReportsForPeriod(Date from, Date to) {
		List<Report> reportsToReturn = new ArrayList<Report>();
		
		for(Report r : this.reports) {
			if(r.getFromDate().before(from) && r.getToDate().after(to))
				reportsToReturn.add(r);
		}
		
		return reportsToReturn;
	}
	
	public Report getReportById(String id) {
		for(Report r : this.reports)
			if(r.getId().equals(id))
				return r;
		
		return null;
	}
}
