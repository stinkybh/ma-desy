package madesy.model.pickings;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import madesy.model.pickings.Picking;

/**
 * Holds newly created pickings, waiting to be dispatched to the couriers
 * @author hristo
 *
 */
public class PickingsQueue {
	private BlockingQueue<Picking> newPickings = new ArrayBlockingQueue<Picking>(30);
	
	/**
	 * Adds a new picking to the queue
	 * @param picking
	 */
	public void add(Picking picking) {
		newPickings.offer(picking);
	}
	
	/**
	 * Gets the first available picking.
	 * @return {@link madesy.model.pickings.Picking} if the container is not empty, otherwise returns NULL.
	 */
	public Picking getPicking() {
		return newPickings.poll();
	}

}
