package madesy.model.pickings;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import madesy.model.CourierSupervisor;
import madesy.model.Event;
import madesy.model.types.EventType;
import madesy.model.types.PickingStatus;
import madesy.storage.EventLog;
import madesy.storage.PickingStorage;
import madesy.storage.PickingsQueue;

/**
 * Provides operations on the pickings, used by the relevant worker processes
 * 
 */
public class PickingService {
	private PickingStorage pickingStorage;
	private CourierSupervisor courierSupervisor = new CourierSupervisor();
	private EventLog eventLog;
	private static final Lock lock = new ReentrantLock();
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
					if (dispatchedTo != null)
						if (dispatchedTo.equals(courierId)
								&& p.getPickingStates() == PickingStatus.DISPATCHED)
							return p;
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
				// System.out.println("Courier with id: " + courierId +
				// " deliver picking with id: " + pickingId);
				return null;
			}

		}.executeWithLock();
	}

	private abstract class Synchronizator<T> {
		abstract T execute();

		T executeWithLock() {
			lock.lock();
			try {
				return execute();
			} finally {
				lock.unlock();
			}
		}
	}
}
