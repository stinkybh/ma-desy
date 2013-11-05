package madesy.simulation;

import java.util.UUID;

import madesy.model.Event;
import madesy.model.types.EventType;

public class ReportsSimulation extends SimulationBase {

	@Override
	public void start() {
		workers = workersGenerator.generate(8, 5, 1);
		fillPool();
		
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
