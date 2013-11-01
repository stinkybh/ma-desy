package madesy;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import madesy.model.Event;
import madesy.model.User;
import madesy.model.Users;
import madesy.model.types.EventType;
import madesy.model.types.UserTypes;
import madesy.model.workers.ClientWorker;
import madesy.model.workers.CourrierWorker;
import madesy.model.workers.ManagerWorker;
import madesy.storage.EventLog;
import madesy.storage.PickingStorage;

public class Simulation {
	public static void main(String[] args) {
		EventLog eventLog = new EventLog();
		PickingStorage pickingStorage = new PickingStorage();
		ExecutorService pool = Executors.newFixedThreadPool(20);
		Users users = new Users();
		int sleepTime = 1000, i = 1;
		// Gets all users
		for (User u : users.getUsers()) {
			Double sleep = sleepTime + i * (sleepTime * 0.25);
			sleep.intValue();
			if (u.getType() == UserTypes.CLIENT)
				pool.submit(new ClientWorker(u.getId(), pickingStorage,
						eventLog, sleep.intValue()));
			else if (u.getType() == UserTypes.COURIER)
				pool.submit(new CourrierWorker(u.getId(), pickingStorage,
						eventLog, sleep.intValue()));
			else
				pool.submit(new ManagerWorker(u.getId(), eventLog, 5000));
			i++;
		}
		pool.submit(new SimulationSupervisor(UUID.randomUUID().toString(),
				pool, eventLog, 5000) {

					@Override
					public boolean checkForTermination() {
						int count = 0;
						for (Event e : eventLog.getEvents()) {
							if (e.getEventType() == EventType.MANAGER_REPORT) {
								count++;
							}
						}

						return count >= 5;
					}
			
		});
	}
}
