package madesy.simulation;

import madesy.model.courier.CourierPickingsInfo;
import madesy.model.events.EventLog;
import madesy.model.pickings.PickingService;
import madesy.model.reports.ReportService;

public class SimulationFactory {
	public static SimulationBase createSimulation(SimulationType type,
			PickingService pickingService, EventLog eventLog,
			ReportService reportService, CourierPickingsInfo courierPickings) {
		if (type == SimulationType.REPORT_NUMBER)
			return new ReportsSimulation(pickingService, reportService,
					courierPickings);

		return new PickingsSimulation(pickingService, reportService,
				courierPickings);
	}
}
