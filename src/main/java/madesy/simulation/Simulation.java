package madesy.simulation;

import madesy.storage.EventLog;
import madesy.storage.PickingStorage;

public class Simulation {

	public static void main(String[] args) {
		SimulationBase sim = SimulationFactory.createSimulation(
				SimulationType.PICKINGS_NUMBER,
				new PickingStorage(),
				new EventLog());
		
		sim.run();
	}

}
