package madesy.simulation;

import java.util.concurrent.ExecutorService;

import madesy.model.workers.BaseWorker;
import madesy.storage.EventLog;

/**
 * Manages the simulation process, based on the manager reports
 * 
 * @author hristo
 * 
 */
public abstract class SimulationSupervisor extends BaseWorker {
	protected EventLog eventLog;
	private ExecutorService service;

	public SimulationSupervisor(String id, ExecutorService service,
			EventLog eventLog, int sleepTime) {
		super(id, sleepTime);
		this.eventLog = eventLog;
		this.service = service;
	}

	/**
	 * Checks whether the number of manager reports is more than the specified
	 * count in order to terminate the thread pool.
	 * 
	 * @return
	 */
	public abstract boolean checkForTermination();

	@Override
	public void doWork() {
		if (checkForTermination()) {
			service.shutdownNow();
			System.out.println("Terminated.");
		}
	}

}
