package madesy.workers;

import madesy.model.pickings.Picking;
import madesy.model.pickings.PickingService;

/**
 * Worker process used to simulate basic duties of a courier, which are
 * dispatching and taking a picking.
 */
public class CourierWorker extends PickingsWorker {

	public CourierWorker(PickingService pickingService, int sleepTime) {
		super(pickingService, sleepTime);
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
