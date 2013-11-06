package madesy.simulation;

import madesy.storage.EventLog;
import madesy.storage.PickingStorage;

public class SimulationFactory {
	public static SimulationBase createSimulation(SimulationType type, PickingStorage pickingStorage, EventLog eventLog) {
		if (type == SimulationType.REPORT_NUMBER)
			return new ReportsSimulation(pickingStorage, eventLog);

		return new PickingsSimulation(pickingStorage, eventLog);
	}
}
