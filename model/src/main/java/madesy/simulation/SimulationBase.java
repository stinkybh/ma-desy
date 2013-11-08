package madesy.simulation;

import java.util.List;
import java.util.concurrent.ExecutorService;

import madesy.model.workers.BaseWorker;
import madesy.storage.EventLog;
import madesy.storage.PickingStorage;

public abstract class SimulationBase {
	protected PickingStorage pickingStorage;
	protected ExecutorService pool = new DesyThreadPoolExecutor();
	protected EventLog eventLog;
	protected WorkersGenerator workersGenerator;
	
	public SimulationBase(PickingStorage pickingStorage, EventLog eventLog) {
		this.pickingStorage = pickingStorage;
		this.eventLog = eventLog;
		this.workersGenerator = new WorkersGenerator(pickingStorage, eventLog);
	}
	
	public abstract List<BaseWorker> process();
	
	public void run() {
		List<BaseWorker> workers = process();
		fillPool(workers);
	}
	
	public void stop() {
		pool.shutdownNow();
	}
	
	private void fillPool(List<BaseWorker> workers) {
		for(BaseWorker worker : workers) {
			pool.submit(worker);
		}
	}
}
