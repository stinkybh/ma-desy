package madesy.simulation;

import java.util.List;
import java.util.concurrent.ExecutorService;

import madesy.model.workers.BaseWorker;
import madesy.storage.EventLog;
import madesy.storage.PickingStorage;

public abstract class SimulationBase {
	protected EventLog eventLog = new EventLog();
	protected PickingStorage pickingStorage = new PickingStorage();
	protected ExecutorService pool = new DesyThreadPoolExecutor();
	protected WorkersGenerator workersGenerator = new WorkersGenerator(pickingStorage, eventLog);
	protected List<BaseWorker> workers;
	
	public abstract void start();
	
	protected void fillPool() {
		for(BaseWorker worker : workers) {
			pool.submit(worker);
		}
	}
}
