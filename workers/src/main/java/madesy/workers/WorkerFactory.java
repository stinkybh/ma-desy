package madesy.workers;

import madesy.model.courier.CourierPickingsInfo;
import madesy.model.events.EventLog;
import madesy.model.pickings.PickingStorage;
import madesy.model.reports.ReportService;

public class WorkerFactory {

	public static BaseWorker createClient(PickingStorage pickingStorage,
			EventLog eventLog) {

		return new ClientWorker(pickingStorage, eventLog,
				WorkersConfigurator.CLIENT_WORKER_SLEEP_TIME);
	}

	public static BaseWorker createCourier(PickingStorage pickingStorage,
			EventLog eventLog) {

		CourierWorker courier = new CourierWorker(pickingStorage, eventLog,
				WorkersConfigurator.COURIER_WORKER_SLEEP_TIME);
		CourierPickingsInfo.addCourier(courier.getId());
		return courier;
	}

	public static BaseWorker createManager(PickingStorage pickingStorage,
			EventLog eventLog, ReportService reportService) {

		return new ManagerWorker(eventLog, reportService,
				WorkersConfigurator.MANAGER_WORKER_SLEEP_TIME);
	}
}
