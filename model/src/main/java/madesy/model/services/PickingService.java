package madesy.model.services;

import java.util.ArrayList;
import java.util.List;

import madesy.model.CourierSupervisor;
import madesy.model.Event;
import madesy.model.pickings.Picking;
import madesy.model.types.EventType;
import madesy.model.types.PickingStatus;
import madesy.storage.EventLog;
import madesy.storage.PickingStorage;
import madesy.storage.PickingsQueue;

/**
 * Provides operations on the pickings, used by the relevant worker processes
 * 
 */
public class PickingService extends BaseService {
	private PickingStorage pickingStorage;
	private CourierSupervisor courierSupervisor = new CourierSupervisor();
	private EventLog eventLog;
	private static int count = 0;

	public PickingService(EventLog eventLog, PickingStorage pickingStorage) {
		this.eventLog = eventLog;
		this.pickingStorage = pickingStorage;
	}

	/**
	 * Creates a random new picking.
	 * 
	 * @param senderIds
	 * @param barcodes
	 */
	public void newPicking(final Picking picking) {
		if (picking == null)
			throw new NullPointerException("Picking is null");
		new Synchronizator<Void>() {

			@Override
			Void execute() {
				pickingStorage.add(picking);
				System.out.println(String.valueOf(++count) + " - New picking");
				String data = picking.getId();
				eventLog.add(new Event(EventType.NEW_PICKING, data));
				PickingsQueue.add(picking);
				return null;
			}

		}.executeWithLock();
	}

	/**
	 * Manages the distribution of new pickings to all couriers.
	 * 
	 * @param picking
	 *            - Picking to be dispatched
	 */
	public void dispatchPicking(final Picking picking) {
		new Synchronizator<Void>() {

			@Override
			Void execute() {
				String courierId = courierSupervisor
						.getCourierWithLowestPickingsNumber();
				courierSupervisor.incrementCarriedPickings(courierId);

				// System.out.println("Dispatched to: " + courierId +
				// " Number of pickings:" +
				// courierSupervisor.getPickingsNumber(courierId));

				picking.setPickingStates(PickingStatus.DISPATCHED);
				picking.setCourierId(courierId);
				String metaData = picking.getId() + ", "
						+ picking.getCourierId();
				eventLog.add(new Event(EventType.DISPATCH_PICKING, metaData));
				return null;
			}

		}.executeWithLock();
	}
	
	public Picking getPickingById(final String id) {
		return new Synchronizator<Picking>() {
			@Override
			Picking execute() {
				for (Picking p : pickingStorage.getPickings())
					if(p.getId().equals(id))
						return p;
				
				return null;
			}
		}.executeWithLock();
	}
	
	/**
	 * Finds the first occurrence in the list of pickings, carried by the
	 * courier with the specified id.
	 * 
	 * @param courierId
	 * @return instance of Picking
	 */
	public Picking getPickingByCourierId(final String courierId) {
		return new Synchronizator<Picking>() {
			@Override
			Picking execute() {
				for (Picking p : pickingStorage.getPickings()) {
					String dispatchedTo = p.getCourierId();
					if (dispatchedTo != null) {
						if (dispatchedTo.equals(courierId))
								if(p.getPickingStates() == PickingStatus.DISPATCHED)
									return p;
					}
				}
				return null;
			}
		}.executeWithLock();
	}

	/**
	 * Finds all occurrences in the list of pickings, carried by the courier
	 * with the specified id.
	 * 
	 * @param courierId
	 * @return List of {@link madesy.model.pickings.Picking}
	 */
	public List<Picking> getDispatchedPickings(final String courierId) {
		return new Synchronizator<List<Picking>>() {

			@Override
			List<Picking> execute() {
				List<Picking> pickings = new ArrayList<Picking>();
				for (Picking p : pickingStorage.getPickings()) {
					String dispatchedTo = p.getCourierId();
					if (dispatchedTo != null)
						if (dispatchedTo.equals(courierId)
								&& p.getPickingStates() == PickingStatus.DISPATCHED)
							pickings.add(p);
				}
				return pickings;
			}

		}.executeWithLock();
	}

	public List<Picking> getPickingsByClientId(final String clientId) {
		return new Synchronizator<List<Picking>>() {
			@Override
			List<Picking> execute() {
				List<Picking> allPickings = pickingStorage.getPickings();
				List<Picking> clientPickings = new ArrayList<Picking>();
				
				for (Picking p : allPickings) {
					if(p.getSenderId().equals(clientId))
						clientPickings.add(p);
				}
				
				return clientPickings;
			}
		}.executeWithLock();
		
	}
	
	/**
	 * Marks the picking with the given id as taken.
	 * 
	 * @param pickingId
	 */
	public void setTaken(final String courierId, final String pickingId) {
		new Synchronizator<Void>() {

			@Override
			Void execute() {
				Picking picking = pickingStorage.getPicking(pickingId);
				picking.setPickingStates(PickingStatus.TAKEN);
				courierSupervisor.decrementCarriedPickings(picking
						.getCourierId());
				String metaData = pickingId + ", " + picking.getCourierId();
				eventLog.add(new Event(EventType.TAKE_PICKING, metaData));
				 System.out.println("Courier with id: " + courierId +
				 " deliver picking with id: " + pickingId);
				return null;
			}

		}.executeWithLock();
	}
	
	/**
	 * If found, returns the picking with the specified id, otherwise returns null
	 * @param pickingId
	 * @return
	 */
	public Picking getPicking(final String pickingId) {
		return new Synchronizator<Picking>() {

			@Override
			Picking execute() {
				return pickingStorage.getPicking(pickingId);
			}
			
		}.executeWithLock();
	}

	public EventLog getEventLog() {
		return this.eventLog;
	}
	
	public PickingStorage getPickingStorage() {
		return this.pickingStorage;
	}
}
