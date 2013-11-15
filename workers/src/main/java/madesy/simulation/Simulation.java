package madesy.simulation;

import madesy.model.events.EventLog;
import madesy.model.pickings.PickingStorage;
import madesy.model.reports.ReportService;
import madesy.model.reports.ReportStorage;

public class Simulation {

	public static void main(String[] args) {
		SimulationBase sim = SimulationFactory.createSimulation(
				SimulationType.PICKINGS_NUMBER,
				new PickingStorage(),
				new EventLog(),
				new ReportService(new ReportStorage()));
		
		sim.run();
	}

}
