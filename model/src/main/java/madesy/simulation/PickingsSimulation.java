package madesy.simulation;

import java.util.List;
import java.util.UUID;

import madesy.model.Event;
import madesy.model.types.EventType;
import madesy.model.workers.BaseWorker;
import madesy.storage.EventLog;
import madesy.storage.PickingStorage;

public class PickingsSimulation extends SimulationBase {
	
	public PickingsSimulation(PickingStorage pickingStorage, EventLog eventLog) {
		super(pickingStorage, eventLog);
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
