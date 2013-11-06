package madesy.web.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import madesy.simulation.SimulationBase;
import madesy.simulation.SimulationFactory;
import madesy.simulation.SimulationType;
import madesy.storage.EventLog;
import madesy.storage.PickingStorage;

public class ContextListener implements ServletContextListener {
	private static SimulationBase simulation;
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		if(simulation != null)
			simulation.stop();

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		EventLog eventLog = new EventLog();
		PickingStorage pickingStorage = new PickingStorage();
		event.getServletContext().setAttribute("eventLog", eventLog);
		event.getServletContext()
				.setAttribute("pickingStorage", pickingStorage);

		// Run simulation
		simulation = SimulationFactory.createSimulation(
				SimulationType.PICKINGS_NUMBER, pickingStorage, eventLog);
		simulation.run();
	}

}
