package madesy.storage;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import madesy.model.Picking;

/**
 * Holds newly created pickings, waiting to be dispatched to the couriers
 * @author hristo
 *
 */
public class PickingsQueue {
	private static BlockingQueue<Picking> newPickings = new ArrayBlockingQueue<Picking>(30);
	
	/**
	 * Adds a new picking to the queue
	 * @param picking
	 */
	public static void add(Picking picking) {
		newPickings.offer(picking);
	}
	
	/**
	 * Gets the first available picking.
	 * @return {@link madesy.model.Picking} if the container is not empty, otherwise returns NULL.
	 */
	public static Picking getPicking() {
		return newPickings.poll();
	}

}
