package madesy.workers;

import madesy.model.pickings.Picking;
import madesy.model.pickings.PickingService;

/**
 * Executes the role of distributor who sends every new picking to the least
 * busy courier
 * 
 */
public class PickingDispatcherWorker extends PickingsWorker {
	private PickingService pickingService;

	public PickingDispatcherWorker(PickingService pickingService, int sleepTime) {
		super(pickingService, sleepTime);
		this.pickingService = pickingService;
	}

	@Override
	public void doWork() {
		Picking picking;
		do {
			picking = pickingService.getPickingsQueue().getPicking();
			if (picking != null)
				pickingService.dispatchPicking(picking);
		} while (picking != null);
	}

}
