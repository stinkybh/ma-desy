package madesy.workers;

import java.util.Random;

import madesy.model.client.Client;
import madesy.model.client.ClientType;
import madesy.model.events.EventLog;
import madesy.model.pickings.Picking;
import madesy.model.pickings.PickingService;
import madesy.model.pickings.PickingSize;
import madesy.model.pickings.PickingStorage;

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

		Client sender = new Client("SENDER_NAME" + random.nextInt(100000),
				"Sender_Address_" + random.nextInt(10000),
				ClientType.SENDER);
		
		Client receiver = new Client("RECEIVER_NAME" + random.nextInt(100000),
				"Receiver_Address_" + random.nextInt(10000),
				ClientType.RECEIVER);
		
		return new Picking(this.id, PickingSize.generateRandomPickingSize(), sender, receiver);
	}

}
