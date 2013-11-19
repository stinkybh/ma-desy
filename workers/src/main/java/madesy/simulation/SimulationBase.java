package madesy.simulation;

import java.util.List;
import java.util.concurrent.ExecutorService;

import madesy.model.courier.CourierPickingsInfo;
import madesy.model.pickings.PickingService;
import madesy.model.reports.ReportService;
import madesy.workers.BaseWorker;
import madesy.workers.WorkersGenerator;

public abstract class SimulationBase {
	protected ReportService reportService;
	protected PickingService pickingService;
	protected ExecutorService pool = new DesyThreadPoolExecutor();
	protected WorkersGenerator workersGenerator;
	protected CourierPickingsInfo courierPickings;

	public SimulationBase(PickingService pickingService,
			ReportService reportService, CourierPickingsInfo courierPickings) {

		this.pickingService = pickingService;
		this.courierPickings = courierPickings;
		this.reportService = reportService;
		this.workersGenerator = new WorkersGenerator(pickingService,
				reportService, courierPickings);
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
		for (BaseWorker worker : workers) {
			pool.submit(worker);
		}
	}

	public ExecutorService getThreadPool() {
		return this.pool;
	}
}
