package madesy.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import madesy.model.types.ReportType;

/**
 * Contains information created by the manager worker,
 * describing the work for a period of time.
 * 
 */
public class Report {
	private String id;
	private List<ReportType> pickingsReport = new ArrayList<ReportType>();
	private Map<String, Integer> courrierPickings = new HashMap<String, Integer>();
	private Date fromDate;
	private Date toDate;

	public Report(Date fromDate, Date toDate) {
		System.out.println();
		this.id = UUID.randomUUID().toString();
		this.fromDate = fromDate;
		this.toDate = toDate;
	}

	@Override
	public String toString() {
		return "Report [id=" + id + ", pickingsReport=" + pickingsReport
				+ ", courrierPickings=" + courrierPickings + ", fromDate="
				+ fromDate + ", toDate=" + toDate + "]";
	}

	public void add(ReportType element) {
		this.pickingsReport.add(element);
	}

	@Override
	public int hashCode() {
		return id.hashCode();
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

}
