package madesy.workers;

import java.util.Random;
import java.util.UUID;

import madesy.model.CourierSupervisor;
import madesy.model.services.ReportService;
import madesy.storage.EventLog;
import madesy.storage.PickingStorage;

public class WorkerFactory {
	private static Random rand = new Random();

	public static BaseWorker createClient(PickingStorage pickingStorage,
			EventLog eventLog) {
		
		return new ClientWorker(UUID.randomUUID().toString(), pickingStorage,
				eventLog, rand.nextInt(60));
	}
	
	public static BaseWorker createCourier(PickingStorage pickingStorage,
			EventLog eventLog) {
		
		CourierWorker courier = new CourierWorker(UUID.randomUUID().toString(), pickingStorage,
				eventLog, rand.nextInt(60));
		CourierSupervisor.addCourier(courier.getId());
		return courier;
	}
	
	public static BaseWorker createManager(PickingStorage pickingStorage,
			EventLog eventLog, ReportService reportService) {
		
		return new ManagerWorker(UUID.randomUUID().toString(), eventLog, 
				reportService, rand.nextInt(500));
	}
}
