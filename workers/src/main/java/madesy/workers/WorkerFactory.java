package madesy.workers;

import java.util.UUID;

import madesy.model.courier.CourierStatus;
import madesy.model.events.EventLog;
import madesy.model.pickings.PickingStorage;
import madesy.model.reports.ReportService;

public class WorkerFactory {

	public static BaseWorker createClient(PickingStorage pickingStorage,
			EventLog eventLog) {
		
		return new ClientWorker(UUID.randomUUID().toString(), pickingStorage,
				eventLog, WorkersConfigurator.CLIENT_WORKER_SLEEP_TIME);
	}
	
	public static BaseWorker createCourier(PickingStorage pickingStorage,
			EventLog eventLog) {
		
		CourierWorker courier = new CourierWorker(UUID.randomUUID().toString(), pickingStorage,
				eventLog, WorkersConfigurator.COURIER_WORKER_SLEEP_TIME);
		CourierStatus.addCourier(courier.getId());
		return courier;
	}
	
	public static BaseWorker createManager(PickingStorage pickingStorage,
			EventLog eventLog, ReportService reportService) {
		
		return new ManagerWorker(UUID.randomUUID().toString(), eventLog, 
				reportService, WorkersConfigurator.MANAGER_WORKER_SLEEP_TIME);
	}
}
