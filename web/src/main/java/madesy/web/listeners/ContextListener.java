package madesy.web.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import madesy.model.services.PickingService;
import madesy.model.services.ReportService;
import madesy.simulation.SimulationBase;
import madesy.simulation.SimulationFactory;
import madesy.simulation.SimulationType;
import madesy.storage.EventLog;
import madesy.storage.PickingStorage;
import madesy.storage.ReportStorage;

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
		
		ReportStorage reportStorage = new ReportStorage();
		ReportService reportService = new ReportService(reportStorage);
		
		PickingStorage pickingStorage = new PickingStorage();
		PickingService pickingService = new PickingService(eventLog, pickingStorage);
		
		event.getServletContext().setAttribute("pickingService", pickingService);
		event.getServletContext().setAttribute("reportService", reportService);
		
		simulation = SimulationFactory.createSimulation(
				SimulationType.PICKINGS_NUMBER, pickingStorage, eventLog, reportService);
		simulation.run();
	}
}
