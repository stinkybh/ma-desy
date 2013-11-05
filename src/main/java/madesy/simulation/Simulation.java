package madesy.simulation;

public class Simulation {

	public static void main(String[] args) {
		SimulationBase sim = SimulationFactory.createSimulation(SimulationType.PICKINGS_NUMBER);
		
		sim.start();
	}

}
