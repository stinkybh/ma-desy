package madesy.model.pickings;

import madesy.model.workers.BaseWorker;
import madesy.storage.EventLog;
import madesy.storage.PickingStorage;
import madesy.storage.PickingsQueue;

/**
 * Executes the role of distributor who sends every new picking to the least
 * busy courier
 * 
 */
public class PickingDispatcher extends BaseWorker {
	private PickingService pickingService;

	public PickingDispatcher(String id, int sleepTime,
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
