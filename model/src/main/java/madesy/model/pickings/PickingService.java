package madesy.model.pickings;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import madesy.model.BaseService;
import madesy.model.courier.CourierPickingsInfo;
import madesy.model.events.Event;
import madesy.model.events.EventLog;
import madesy.model.events.EventType;

/**
 * Provides operations on the pickings, used by the relevant worker processes
 * 
 */
public class PickingService extends BaseService {
	private PickingStorage pickingStorage;
	private EventLog eventLog;
	private CourierPickingsInfo courierPickings;
	private PickingsQueue pickingsQueue = new PickingsQueue();
	private int count = 0;

	public PickingService(EventLog eventLog, PickingStorage pickingStorage,
			CourierPickingsInfo courierPickings) {
		this.eventLog = eventLog;
		this.pickingStorage = pickingStorage;
		this.courierPickings = courierPickings;
	}

	/**
	 * Creates a random new picking.
	 * 
	 * @param senderIds
	 * @param barcodes
	 */
	public void newPicking(final Picking picking) {
		Objects.requireNonNull(picking, "Picking is null");

		new Synchronizator<Void>() {

			@Override
			protected Void execute() {
				pickingStorage.add(picking);
				System.out.println(String.valueOf(++count) + " - New picking");
				eventLog.add(new Event(EventType.NEW_PICKING, picking.getId()));
				pickingsQueue.add(picking);
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
			protected Void execute() {
				String courierId = courierPickings.getCourier();
				courierPickings.onNewPicking(courierId);

				System.out.println("Dispatched to: " + courierId
						+ " Number of pickings:"
						+ courierPickings.getPickingsNumber(courierId));

				picking.setPickingStatus(PickingStatus.DISPATCHED);
				picking.setCourierId(courierId);
				eventLog.add(new Event(EventType.DISPATCH_PICKING, picking
						.getId(), picking.getCourierId()));
				return null;
			}

		}.executeWithLock();
	}

	public Picking getPickingById(final String id) {
		return new Synchronizator<Picking>() {
			@Override
			protected Picking execute() {
				for (Picking p : pickingStorage.getPickings())
					if (p.getId().equals(id))
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
			protected Picking execute() {
				for (Picking p : pickingStorage.getPickings()) {
					String dispatchedTo = p.getCourierId();
					if (dispatchedTo != null) {
						if (dispatchedTo.equals(courierId))
							if (p.getPickingStatus() == PickingStatus.DISPATCHED)
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
			protected List<Picking> execute() {
				List<Picking> pickings = new ArrayList<Picking>();
				for (Picking p : pickingStorage.getPickings()) {
					String dispatchedTo = p.getCourierId();
					if (dispatchedTo != null)
						if (dispatchedTo.equals(courierId)
								&& p.getPickingStatus() == PickingStatus.DISPATCHED)
							pickings.add(p);
				}
				return pickings;
			}

		}.executeWithLock();
	}

	public List<Picking> getPickingsByClientId(final String clientId) {
		return new Synchronizator<List<Picking>>() {
			@Override
			protected List<Picking> execute() {
				List<Picking> allPickings = pickingStorage.getPickings();
				List<Picking> clientPickings = new ArrayList<Picking>();

				for (Picking p : allPickings) {
					if (p.getSenderId().equals(clientId))
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
			protected Void execute() {
				Picking picking = pickingStorage.getPicking(pickingId);
				picking.setPickingStatus(PickingStatus.TAKEN);
				courierPickings.setTaken(courierId);
				eventLog.add(new Event(EventType.TAKE_PICKING, pickingId, picking.getCourierId()));
				System.out.println("Courier with id: " + courierId
						+ " deliver picking with id: " + pickingId);

				return null;
			}

		}.executeWithLock();
	}

	/**
	 * If found, returns the picking with the specified id, otherwise returns
	 * null
	 * 
	 * @param pickingId
	 * @return
	 */
	public Picking getPicking(final String pickingId) {
		return new Synchronizator<Picking>() {

			@Override
			protected Picking execute() {
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

	public PickingsQueue getPickingsQueue() {
		return this.pickingsQueue;
	}
}
