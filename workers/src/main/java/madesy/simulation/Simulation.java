package madesy.simulation;

import madesy.model.courier.CourierPickingsInfo;
import madesy.model.events.EventLog;
import madesy.model.pickings.PickingService;
import madesy.model.pickings.PickingStorage;
import madesy.model.reports.ReportService;
import madesy.model.reports.ReportStorage;

public class Simulation {

	public static void main(String[] args) {
		EventLog eventLog = new EventLog();
		PickingStorage pickingStorage = new PickingStorage();
		CourierPickingsInfo courierPickings = new CourierPickingsInfo();
		SimulationBase sim = SimulationFactory.createSimulation(
				SimulationType.PICKINGS_NUMBER,
				new PickingService(eventLog, pickingStorage, courierPickings),
				eventLog,
				new ReportService(new ReportStorage()),
				courierPickings);
		
		sim.run();
	}

}
