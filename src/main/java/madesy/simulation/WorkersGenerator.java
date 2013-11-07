package madesy.simulation;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import madesy.model.PickingDispatcher;
import madesy.model.types.UserTypes;
import madesy.model.workers.BaseWorker;
import madesy.model.workers.WorkerFactory;
import madesy.storage.EventLog;
import madesy.storage.PickingStorage;

public class WorkersGenerator {
	private PickingStorage pickingStorage;
	private EventLog eventLog;

	public WorkersGenerator(PickingStorage pickingStorage, EventLog eventLog) {
		this.pickingStorage = pickingStorage;
		this.eventLog = eventLog;
	}

	// TODO: Fix method body
	public List<BaseWorker> generate(int clientsCount, int couriersCount,
			int managersCount) {
		List<BaseWorker> workers = new ArrayList<BaseWorker>();

		for (int i = 0; i < clientsCount; i++) {
			workers.add(WorkerFactory.createWorker(UserTypes.CLIENT,
					pickingStorage, eventLog));
		}

		for (int i = 0; i < couriersCount; i++) {
			workers.add(WorkerFactory.createWorker(UserTypes.COURIER,
					pickingStorage, eventLog));
		}

		for (int i = 0; i < managersCount; i++) {
			workers.add(WorkerFactory.createWorker(UserTypes.MANAGER,
					pickingStorage, eventLog));
		}
		
		workers.add(new PickingDispatcher(UUID.randomUUID().toString(), 50, pickingStorage, eventLog));

		return workers;
	}
}
