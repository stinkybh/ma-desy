package madesy.model.reports;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import madesy.model.BaseModel;

/**
 * Contains information created by the manager worker, describing the work for a
 * period of time.
 * 
 */
public class Report extends BaseModel {
	private List<ReportType> pickingsReport = new ArrayList<ReportType>();
	private Map<String, Integer> courrierPickings = new HashMap<String, Integer>();
	private Date fromDate;
	private Date toDate;

	public Report(Date fromDate, Date toDate) {
		this.fromDate = fromDate;
		this.toDate = toDate;
	}

	@Override
	public String toString() {
		return "Report: " + pickingsReport.get(0) + " " + pickingsReport.get(1)
				+ " From date: " + fromDate + "  To date: " + toDate;
	}

	public void add(ReportType element) {
		this.pickingsReport.add(element);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Report other = (Report) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public String getId() {
		return id;
	}
	
	public List<ReportType> getPickingsReport() {
		return pickingsReport;
	}

	public Map<String, Integer> getCourrierPickings() {
		return courrierPickings;
	}

	public Date getFromDate() {
		return this.fromDate;
	}

	public Date getToDate() {
		return this.toDate;
	}
}
