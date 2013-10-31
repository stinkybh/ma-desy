package madesy.model.workers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import madesy.model.PickingService;
import madesy.storage.EventLog;
import madesy.storage.PickingStorage;

/**
 * Worker process used to simulate a client, requesting to send a new picking.
 * 
 */
public class ClientWorker extends BaseWorker {
	private PickingService pickingService;
	
	public ClientWorker(String id, PickingStorage pickingStorage, EventLog eventLog, int sleepTime) {
		super(id, sleepTime);
		this.pickingService = new PickingService(eventLog, pickingStorage);
	}

	@Override
	public void doWork() {
		List<Integer> barcodes = new ArrayList<Integer>();
		Random random = new Random();
		Integer barcode = random.nextInt(100000000);
		barcodes.add(barcode);
		pickingService.newPicking(id, barcodes);
	}

}
