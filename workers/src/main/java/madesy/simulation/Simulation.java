package madesy.simulation;

import madesy.model.services.ReportService;
import madesy.storage.EventLog;
import madesy.storage.PickingStorage;
import madesy.storage.ReportStorage;

public class Simulation {

	public static void main(String[] args) {
		SimulationBase sim = SimulationFactory.createSimulation(
				SimulationType.PICKINGS_NUMBER,
				new DesyThreadPoolExecutor(),
				new PickingStorage(),
				new EventLog(),
				new ReportService(new ReportStorage()));
		
		sim.run();
	}

}
