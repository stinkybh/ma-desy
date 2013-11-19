package madesy.workers;

import madesy.model.pickings.PickingService;

public abstract class PickingsWorker extends BaseWorker {
	protected PickingService pickingService;
	
	public PickingsWorker(PickingService pickingService, int sleepTime) {
		super(sleepTime);
		this.pickingService = pickingService;
	}


}
