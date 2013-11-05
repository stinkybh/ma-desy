package madesy.simulation;

public class SimulationFactory {
	public static SimulationBase createSimulation(SimulationType type) {
		System.out.println();
		if(type == SimulationType.REPORT_NUMBER)
			return new ReportsSimulation();
		
		return new PickingsSimulation();
	}
}
