package madesy.workers;

import java.util.ArrayList;
import java.util.List;

import madesy.model.courier.CourierPickingsInfo;
import madesy.model.pickings.PickingService;
import madesy.model.reports.ReportService;

public class WorkersGenerator {
	private PickingService pickingService;
	private ReportService reportService;
	private CourierPickingsInfo courierPickings;

	public WorkersGenerator(PickingService pickingService,
			ReportService reportService, CourierPickingsInfo courierPickings) {
		this.reportService = reportService;
		this.pickingService = pickingService;
		this.courierPickings = courierPickings;
	}

	public List<BaseWorker> generate(int clientsCount, int couriersCount,
			int managersCount) {
		List<BaseWorker> workers = new ArrayList<BaseWorker>();

		for (int i = 0; i < clientsCount; i++) {
			workers.add(WorkerFactory.createClient(pickingService));
		}

		for (int i = 0; i < couriersCount; i++) {
			workers.add(WorkerFactory.createCourier(pickingService,
					courierPickings));
		}

		for (int i = 0; i < managersCount; i++) {
			workers.add(WorkerFactory.createManager(pickingService.getEventLog(), reportService));
		}

		return workers;
	}
}
