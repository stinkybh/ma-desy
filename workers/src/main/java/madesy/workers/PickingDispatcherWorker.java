package madesy.workers;

import madesy.model.pickings.Picking;
import madesy.model.pickings.PickingService;
import madesy.storage.EventLog;
import madesy.storage.PickingStorage;
import madesy.storage.PickingsQueue;
import madesy.workers.BaseWorker;

/**
 * Executes the role of distributor who sends every new picking to the least
 * busy courier
 * 
 */
public class PickingDispatcherWorker extends BaseWorker {
	private PickingService pickingService;

	public PickingDispatcherWorker(String id, int sleepTime,
			PickingStorage pickingStorage, EventLog eventLog) {
		super(id, sleepTime);
		this.pickingService = new PickingService(eventLog, pickingStorage);
	}

	@Override
	public void doWork() {
		Picking picking;
		do {
			picking = PickingsQueue.getPicking();
			if (picking != null)
				pickingService.dispatchPicking(picking);
		} while (picking != null);
	}

}
