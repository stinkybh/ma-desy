package madesy.workers;

import madesy.model.events.EventLog;
import madesy.model.pickings.Picking;
import madesy.model.pickings.PickingService;
import madesy.model.pickings.PickingStorage;

/**
 * Worker process used to simulate basic duties of a courier, which are
 * dispatching and taking a picking.
 */
public class CourierWorker extends BaseWorker {
	private PickingService pickingService;

	public CourierWorker(PickingStorage pickingStorage, EventLog eventLog,
			int sleepTime) {
		super(sleepTime);
		this.pickingService = new PickingService(eventLog, pickingStorage);
	}

	@Override
	public void doWork() {
		Picking picking = pickingService.getPickingByCourierId(id);
		if (picking != null) {
			String pickingId = picking.getId();
			pickingService.setTaken(id, pickingId);
		}
	}

	public String getId() {
		return this.id;
	}

}
