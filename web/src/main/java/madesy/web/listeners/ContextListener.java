package madesy.web.listeners;

import java.util.UUID;
import java.util.concurrent.ExecutorService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import madesy.model.services.PickingService;
import madesy.model.services.ReportService;
import madesy.simulation.DesyThreadPoolExecutor;
import madesy.storage.EventLog;
import madesy.storage.PickingStorage;
import madesy.storage.ReportStorage;
import madesy.workers.BaseWorker;
import madesy.workers.PickingDispatcherWorker;
import madesy.workers.WorkersConfigurator;
import madesy.workers.WorkersGenerator;

public class ContextListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		
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
		WorkersGenerator generator = new WorkersGenerator(pickingStorage, eventLog, reportService);
		ExecutorService pool = new DesyThreadPoolExecutor();
		for(BaseWorker worker : generator.generate(3, 3, 2)) {
			pool.submit(worker);
		}
		pool.submit(new PickingDispatcherWorker(UUID.randomUUID().toString(),
				WorkersConfigurator.PICKINGS_DISPATCHER_SLEEP_TIME, pickingStorage, eventLog));
	}
}
