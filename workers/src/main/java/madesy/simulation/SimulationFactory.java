package madesy.simulation;

import madesy.model.events.EventLog;
import madesy.model.pickings.PickingStorage;
import madesy.model.reports.ReportService;

public class SimulationFactory {
	public static SimulationBase createSimulation(SimulationType type,
			PickingStorage pickingStorage, EventLog eventLog, ReportService reportService) {
		if (type == SimulationType.REPORT_NUMBER)
			return new ReportsSimulation(pickingStorage, eventLog, reportService);

		return new PickingsSimulation(pickingStorage, eventLog, reportService);
	}
}
