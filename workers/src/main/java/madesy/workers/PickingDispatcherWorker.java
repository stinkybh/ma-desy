package madesy.workers;

import madesy.model.events.EventLog;
import madesy.model.pickings.Picking;
import madesy.model.pickings.PickingService;
import madesy.model.pickings.PickingStorage;
import madesy.model.pickings.PickingsQueue;

/**
 * Executes the role of distributor who sends every new picking to the least
 * busy courier
 * 
 */
public class PickingDispatcherWorker extends BaseWorker {
	private PickingService pickingService;

	public PickingDispatcherWorker(int sleepTime,
			PickingStorage pickingStorage, EventLog eventLog) {
		super(sleepTime);
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
