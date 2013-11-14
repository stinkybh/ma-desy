package madesy.simulation;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;

import madesy.model.Event;
import madesy.model.services.ReportService;
import madesy.model.types.EventType;
import madesy.storage.EventLog;
import madesy.storage.PickingStorage;
import madesy.workers.BaseWorker;

public class ReportsSimulation extends SimulationBase {

	public ReportsSimulation(ExecutorService pool, PickingStorage pickingStorage, EventLog eventLog, 
			ReportService reportService) {
		super(pool, pickingStorage, eventLog, reportService);
	}
	
	@Override
	public List<BaseWorker> process() {

		List<BaseWorker> workers = workersGenerator.generate(8, 5, 1);
		workers.add(new SimulationSupervisor(UUID.randomUUID().toString(),
				pool, eventLog, 50) {

			@Override
			public boolean checkForTermination() {
				int count = 0;
				for (Event e : eventLog.getEvents()) {
					if (e.getEventType() == EventType.MANAGER_REPORT) {
						count++;
					}
				}

				return count >= 10;
			}

		});

		return workers;

	}

}
