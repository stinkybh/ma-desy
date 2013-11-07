package madesy.model.types;

public enum ReportType {
	DISPATCH_PROPERLY("The new pickings are dispatched properly."),
	DISPATCH_DELAYED("There are too many new pickings that the courriers have not dispatched yet."),
	TAKEN_PROPERLY("The dispatched pickings are taken properly."),
	TAKEN_DELAYED("Couriers are delayed in passing the pickings.");
	
	private String value;
	
	private ReportType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
