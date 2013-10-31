package madesy.model.workers;

import madesy.model.Picking;
import madesy.model.PickingService;
import madesy.storage.EventLog;
import madesy.storage.PickingStorage;

/**
 * Worker process used to simulate basic duties of a courier, which are
 * dispatching and taking a picking.
 */
public class CourrierWorker extends BaseWorker {
	private PickingService pickingService;
	private String id;

	public CourrierWorker(String id, PickingStorage pickingStorage, EventLog eventLog, int sleepTime) {
		super(sleepTime);
		this.id = id;
		this.pickingService = new PickingService(eventLog, pickingStorage);
	}

	@Override
	public void doWork() {
		System.out.println("Couurrier with id " + this.id + " is now working!");
		Picking p = pickingService.getPickingByCourierId(id);
		
		if(p != null) {
			pickingService.setTaken(p.getId());
		}
		System.out.println("I'm finished " + id);
	}
	
	public String getId() {
		return this.id;
	}

}
