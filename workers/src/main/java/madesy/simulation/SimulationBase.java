package madesy.simulation;

import java.util.List;
import java.util.concurrent.ExecutorService;

import madesy.model.events.EventLog;
import madesy.model.pickings.PickingStorage;
import madesy.model.reports.ReportService;
import madesy.workers.BaseWorker;
import madesy.workers.WorkersGenerator;

public abstract class SimulationBase {
	protected ReportService reportService;
	protected PickingStorage pickingStorage;
	protected ExecutorService pool = new DesyThreadPoolExecutor();
	protected EventLog eventLog;
	protected WorkersGenerator workersGenerator;
	
	public SimulationBase(PickingStorage pickingStorage, EventLog eventLog, 
			ReportService reportService) {
		
		this.pickingStorage = pickingStorage;
		this.eventLog = eventLog;
		this.reportService = reportService;
		this.workersGenerator = new WorkersGenerator(pickingStorage, eventLog,
				reportService);
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
	
	public ExecutorService getThreadPool() {
		return this.pool;
	}
}
