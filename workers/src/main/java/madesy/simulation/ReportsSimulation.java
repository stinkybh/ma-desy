package madesy.simulation;

import java.util.List;
import java.util.UUID;

import madesy.model.Event;
import madesy.model.types.EventType;
import madesy.storage.EventLog;
import madesy.storage.PickingStorage;
import madesy.workers.BaseWorker;

public class ReportsSimulation extends SimulationBase {

	public ReportsSimulation(PickingStorage pickingStorage, EventLog eventLog) {
		super(pickingStorage, eventLog);
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
