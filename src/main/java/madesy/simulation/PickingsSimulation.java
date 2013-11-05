package madesy.simulation;

import java.util.UUID;

import madesy.model.Event;
import madesy.model.types.EventType;

public class PickingsSimulation extends SimulationBase {

	@Override
	public void start() {
		
		workers = workersGenerator.generate(3, 3, 2);
		
		fillPool();
		
		pool.submit(new SimulationSupervisor(UUID.randomUUID().toString(),
				pool, eventLog, 50) {

			@Override
			public boolean checkForTermination() {
				int count = 0;
				for (Event e : eventLog.getEvents()) {
					if (e.getEventType() == EventType.NEW_PICKING) {
						count++;
					}
				}

				return count >= 1000000;
			}

		});

	}

}
