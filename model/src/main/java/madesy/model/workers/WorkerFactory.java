package madesy.model.workers;

import java.util.Random;
import java.util.UUID;

import madesy.model.CourierSupervisor;
import madesy.model.types.UserTypes;
import madesy.storage.EventLog;
import madesy.storage.PickingStorage;

public class WorkerFactory {

	public static BaseWorker createWorker(UserTypes type,
			PickingStorage pickingStorage, EventLog eventLog) {
		Random rand = new Random();
		if (type == UserTypes.CLIENT)
			return new ClientWorker(UUID.randomUUID().toString(),
					pickingStorage, eventLog, rand.nextInt(60));
		else if (type == UserTypes.COURIER) {
			String courierId = UUID.randomUUID().toString();
			CourierSupervisor.addCourier(courierId);
			return new CourrierWorker(courierId,
					pickingStorage, eventLog, rand.nextInt(30));
		}

		return new ManagerWorker(UUID.randomUUID().toString(), eventLog,
				10);

	}
}
