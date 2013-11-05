package madesy.model.types;

public enum ReportType {
	ENOUGH_NEW_PICKINGS("The new pickings are dispatched properly."),
	TOO_MANY_NEW_PICKINGS("There are too many new pickings that the courriers have not dispatched yet."),
	DISPATCH_PROPERLY("The dispatched pickings are taken properly."),
	DISPATCH_DELAYED("Couriers are delayed in passing the pickings.");
	
	private String value;
	
	private ReportType(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
}
