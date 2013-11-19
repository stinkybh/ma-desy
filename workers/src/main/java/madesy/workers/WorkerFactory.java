package madesy.workers;

import madesy.model.courier.CourierPickingsInfo;
import madesy.model.events.EventLog;
import madesy.model.pickings.PickingService;
import madesy.model.reports.ReportService;

public class WorkerFactory {

	public static BaseWorker createClient(PickingService service) {

		return new ClientWorker(service,
				WorkersConfigurator.CLIENT_WORKER_SLEEP_TIME);
	}

	public static BaseWorker createCourier(PickingService service,
			CourierPickingsInfo courierPickingsInfo) {

		CourierWorker courier = new CourierWorker(service,
				WorkersConfigurator.COURIER_WORKER_SLEEP_TIME);
		courierPickingsInfo.addCourier(courier.getId());
		return courier;
	}

	public static BaseWorker createManager(EventLog eventLog,
			ReportService reportService) {

		return new ManagerWorker(eventLog, reportService,
				WorkersConfigurator.MANAGER_WORKER_SLEEP_TIME);
	}
}
