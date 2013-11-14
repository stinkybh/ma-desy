package madesy.web.listeners;

import java.util.UUID;
import java.util.concurrent.ExecutorService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import madesy.model.services.PickingService;
import madesy.model.services.ReportService;
import madesy.simulation.DesyThreadPoolExecutor;
import madesy.simulation.SimulationBase;
import madesy.simulation.SimulationFactory;
import madesy.simulation.SimulationType;
import madesy.storage.EventLog;
import madesy.storage.PickingStorage;
import madesy.storage.ReportStorage;
import madesy.workers.PickingDispatcherWorker;

public class ContextListener implements ServletContextListener {
	private static SimulationBase simulation;

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		if (simulation != null)
			simulation.stop();

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		EventLog eventLog = new EventLog();

		ReportStorage reportStorage = new ReportStorage();
		ReportService reportService = new ReportService(reportStorage);

		PickingStorage pickingStorage = new PickingStorage();
		PickingService pickingService = new PickingService(eventLog,
				pickingStorage);

		event.getServletContext()
				.setAttribute("pickingService", pickingService);
		event.getServletContext().setAttribute("reportService", reportService);
		ExecutorService pool = new DesyThreadPoolExecutor();
		pool.submit(new PickingDispatcherWorker(UUID.randomUUID().toString(),
				50, pickingStorage, eventLog));
		simulation = SimulationFactory.createSimulation(
				SimulationType.PICKINGS_NUMBER, pool, pickingStorage, eventLog,
				reportService);
		//simulation.run();
	}
}
