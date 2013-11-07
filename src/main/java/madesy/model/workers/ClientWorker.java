package madesy.model.workers;

import java.util.Random;

import madesy.model.Person;
import madesy.model.PersonType;
import madesy.model.Picking;
import madesy.model.PickingService;
import madesy.model.PickingSize;
import madesy.storage.EventLog;
import madesy.storage.PickingStorage;

/**
 * Worker process used to simulate a client, requesting to send a new picking.
 * 
 */
public class ClientWorker extends BaseWorker {
	private PickingService pickingService;

	public ClientWorker(String id, PickingStorage pickingStorage,
			EventLog eventLog, int sleepTime) {
		super(id, sleepTime);
		this.pickingService = new PickingService(eventLog, pickingStorage);
	}

	@Override
	public void doWork() {
		pickingService.newPicking(generatePicking());
		//System.out.println("Client with id: " + this.id + " create new picking");
	}

	private Picking generatePicking() {
		Random random = new Random();

		Person sender = new Person("SENDER_NAME" + random.nextInt(100000),
				"Sender_Address_" + random.nextInt(10000),
				PersonType.SENDER);
		
		Person reveiver = new Person("RECEIVER_NAME" + random.nextInt(100000),
				"Receiver_Address_" + random.nextInt(10000),
				PersonType.RECEIVER);
		
		return new Picking(this.id, PickingSize.generateRandomPickingSize(), sender, reveiver);
	}

}
