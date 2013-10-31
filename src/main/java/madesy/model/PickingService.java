package madesy.model;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import madesy.model.types.EventType;
import madesy.model.types.PickingStatus;
import madesy.storage.EventLog;
import madesy.storage.PickingStorage;

public class PickingService {
	private PickingStorage pickingStorage;
	private PickingDispatcher pickingDispatcher = new PickingDispatcher();
	private CourierSupervisor courierSupervisor = new CourierSupervisor();
	private EventLog eventLog;
	private static final Lock lock = new ReentrantLock();
	
	public PickingService(EventLog eventLog, PickingStorage pickingStorage) {
		this.eventLog = eventLog;
		this.pickingStorage = pickingStorage;
	}
	
	/**
	 * 
	 * @param senderId
	 * @param barcodes
	 */
	public void newPicking(final String senderId, final List<Integer> barcodes) {
		new Synchronizator<Void>() {

			@Override
			Void execute() {
				Picking picking = new Picking(senderId);
				picking.setBarcodes(barcodes);
				pickingStorage.add(picking);
				String data = picking.getId();
				eventLog.add(new Event(EventType.NEW_PICKING, data));
				pickingDispatcher.dispatchNewPicking(picking);
				String metaData = picking.getId() + ", " + picking.getCourierId();
				eventLog.add(new Event(EventType.DISPATCH_PICKING, metaData));
				//System.out.println(eventLog);
				return null;
			}
			
		}.executeWithLock();
	}
	
	/**
	 * Finds the first occurrence in the list of pickings, carried by
	 * the courier with the specified id.
	 * @param courierId
	 * @return instance of Picking
	 */
	public Picking getPickingByCourierId(final String courierId) {
		return new Synchronizator<Picking>() {
			@Override
			Picking execute() {
				List<Picking> pickings = pickingStorage.getPickings();
				for(Picking p : pickings) {
					if(p.getCourierId().equals(courierId) &&
						p.getPickingStates() == PickingStatus.DISPATCHED)
						return p;
				}
				return null;
			}
		}.executeWithLock();
	}
	
	/**
	 * Marks the picking with the given id as taken.
	 * @param pickingId
	 */
	public void setTaken(final String pickingId) {
		new Synchronizator<Void>() {

			@Override
			Void execute() {
				Picking picking = pickingStorage.getPicking(pickingId);
				picking.setPickingStates(PickingStatus.TAKEN);
				courierSupervisor.decrementCarriedPickings(picking.getCourierId());
				String metaData = pickingId + ", " + picking.getCourierId();
				eventLog.add(new Event(EventType.TAKE_PICKING, metaData));
				System.out.println(eventLog);
				System.out.println("Track Picking");
				System.out.println(eventLog.getPickingInfo(pickingId));
				return null;
			}
			
		}.executeWithLock();
	}
	
	private abstract class Synchronizator<T> {
		abstract T execute();
		
		T executeWithLock() {
			lock.lock();
			System.out.println("Lock taken");
			try {
				return execute();
			} finally {
				System.out.println("Lock released");
				lock.unlock();
			}
		}
	}
}
