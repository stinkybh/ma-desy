package madesy;

import java.util.concurrent.ExecutorService;

import madesy.model.Event;
import madesy.model.types.EventType;
import madesy.model.workers.BaseWorker;
import madesy.storage.EventLog;

/**
 * Manages the simulation process, based on the manager reports
 * 
 * @author hristo
 * 
 */
public class SimulationSupervisor extends BaseWorker {
	private EventLog eventLog;
	private int terminationCount;
	private ExecutorService service;

	public SimulationSupervisor(ExecutorService service, EventLog eventLog,
			int terminationCount, int sleepTime) {
		super(sleepTime);
		this.eventLog = eventLog;
		this.terminationCount = terminationCount;
		this.service = service;
	}

	/**
	 * Checks whether the number of manager reports is more than the specified
	 * count in order to terminate the thread pool.
	 * 
	 * @return
	 */
	private boolean checkForTermination() {
		int count = 0;
		for (Event e : eventLog.getEvents()) {
			if (e.getEventType() == EventType.MANAGER_REPORT) {
				count++;
			}
		}

		return count >= terminationCount;

	}

	@Override
	public void doWork() {
		if (checkForTermination()) {
			service.shutdownNow();
			System.out.println("Terminated.");
		}
	}

}
