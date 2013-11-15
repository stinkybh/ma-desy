package madesy.web.listeners;

import java.util.concurrent.ExecutorService;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import madesy.model.events.EventLog;
import madesy.model.pickings.PickingService;
import madesy.model.pickings.PickingStorage;
import madesy.model.reports.ReportService;
import madesy.model.reports.ReportStorage;
import madesy.simulation.DesyThreadPoolExecutor;
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
		WorkersGenerator generator = new WorkersGenerator(pickingStorage,
				eventLog, reportService);
		ExecutorService pool = new DesyThreadPoolExecutor();
		for (BaseWorker worker : generator.generate(3, 3, 1)) {
			pool.submit(worker);
		}
		pool.submit(new PickingDispatcherWorker(
				WorkersConfigurator.PICKINGS_DISPATCHER_SLEEP_TIME,
				pickingStorage, eventLog));
	}
}
