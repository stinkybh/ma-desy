package madesy.workers;

import java.util.UUID;

import madesy.model.CourierSupervisor;
import madesy.model.services.ReportService;
import madesy.storage.EventLog;
import madesy.storage.PickingStorage;

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
		CourierSupervisor.addCourier(courier.getId());
		return courier;
	}
	
	public static BaseWorker createManager(PickingStorage pickingStorage,
			EventLog eventLog, ReportService reportService) {
		
		return new ManagerWorker(UUID.randomUUID().toString(), eventLog, 
				reportService, WorkersConfigurator.MANAGER_WORKER_SLEEP_TIME);
	}
}
