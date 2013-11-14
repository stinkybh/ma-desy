package madesy.model.services;

import java.util.Date;
import java.util.List;

import madesy.model.Report;
import madesy.storage.ReportStorage;

public class ReportService extends BaseService {
	private ReportStorage reportStorage;
	
	public ReportService(ReportStorage reportStorage) {
		this.reportStorage = reportStorage;
	}
	
	public void addReport(final Report report) {
		new Synchronizator<Void>() {

			@Override
			Void execute() {
				reportStorage.add(report);
				
				return null;
			}
		}.executeWithLock();
	}
	
	public List<Report> getReports() {
		return this.reportStorage.getReports();
	}
	
	public List<Report> getReportsForPeriod(final Date from, final Date to) {
		return new Synchronizator<List<Report>>() {

			@Override
			List<Report> execute() {
				return reportStorage.getReportsForPeriod(from, to);
			}
		}.executeWithLock();
	}
	
	public Report getReportById(final String id) {
		return new Synchronizator<Report>() {

			@Override
			Report execute() {
				return reportStorage.getReportById(id);
			}
		}.executeWithLock();
	}
	
	public ReportStorage getReportStorage() {
		return this.reportStorage;
	}
	
	
	
	public Date getDateOfLastReport() {
		return this.reportStorage.getLastReport().getToDate();
	}
}
