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

public class PickingsSimulation extends SimulationBase {
	
	public PickingsSimulation(ExecutorService pool, PickingStorage pickingStorage, EventLog eventLog,
			ReportService reportService) {
		super(pool, pickingStorage, eventLog, reportService);
	}
	
	@Override
	public List<BaseWorker> process() {

		List<BaseWorker> workers = workersGenerator.generate(3, 3, 2);
		workers.add(new SimulationSupervisor(UUID.randomUUID().toString(),
				pool, eventLog, 50) {

			@Override
			public boolean checkForTermination() {
				int count = 0;
				for (Event e : eventLog.getEvents()) {
					if (e.getEventType() == EventType.NEW_PICKING) {
						count++;
					}
				}
				return count >= 10000;
			}

		});

		return workers;
	}

}
