package madesy.simulation;

import java.util.concurrent.ExecutorService;

import madesy.model.services.ReportService;
import madesy.storage.EventLog;
import madesy.storage.PickingStorage;

public class SimulationFactory {
	public static SimulationBase createSimulation(SimulationType type, ExecutorService pool,
			PickingStorage pickingStorage, EventLog eventLog, ReportService reportService) {
		if (type == SimulationType.REPORT_NUMBER)
			return new ReportsSimulation(pool, pickingStorage, eventLog, reportService);

		return new PickingsSimulation(pool, pickingStorage, eventLog, reportService);
	}
}
