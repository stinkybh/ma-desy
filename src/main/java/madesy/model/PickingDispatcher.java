package madesy.model;

import madesy.model.types.PickingStatus;

/**
 * Executes the role of distributor who sends every new picking to the least
 * busy courier
 * 
 */
public class PickingDispatcher {
	private CourierSupervisor courierSupervisor = new CourierSupervisor();

	/**
	 * Manages the distribution of new pickings to all couriers.
	 * 
	 * @param picking
	 *            - Picking to be dispatched
	 */
	public void dispatchNewPicking(Picking picking) {
		String courierId = courierSupervisor
				.getCourierWithLowestPickingsNumber();
		courierSupervisor.incrementCarriedPickings(courierId);
		/*
		 * System.out.println("Dispatched to: " + courierId +
		 * " Number of pickings:" +
		 * courierSupervisor.getPickingsNumber(courierId));
		 */
		picking.setPickingStates(PickingStatus.DISPATCHED);
		picking.setCourierId(courierId);
	}

}
