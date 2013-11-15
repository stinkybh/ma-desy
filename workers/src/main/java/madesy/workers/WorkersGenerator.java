package madesy.workers;

import java.util.ArrayList;
import java.util.List;

import madesy.model.events.EventLog;
import madesy.model.pickings.PickingStorage;
import madesy.model.reports.ReportService;

public class WorkersGenerator {
	private PickingStorage pickingStorage;
	private EventLog eventLog;
	private ReportService reportService;
	
	public WorkersGenerator(PickingStorage pickingStorage, EventLog eventLog, ReportService reportService) {
		this.pickingStorage = pickingStorage;
		this.eventLog = eventLog;
		this.reportService = reportService;
	}

	// TODO: Fix method body
	public List<BaseWorker> generate(int clientsCount, int couriersCount,
			int managersCount) {
		List<BaseWorker> workers = new ArrayList<BaseWorker>();

		for (int i = 0; i < clientsCount; i++) {
			workers.add(WorkerFactory.createClient(pickingStorage, eventLog));
		}

		for (int i = 0; i < couriersCount; i++) {
			workers.add(WorkerFactory.createCourier(pickingStorage, eventLog));
		}

		for (int i = 0; i < managersCount; i++) {
			workers.add(WorkerFactory.createManager(pickingStorage, eventLog, reportService));
		}

		return workers;
	}
}
