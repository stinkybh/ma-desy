package madesy.storage;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import madesy.model.Picking;

public class PickingsQueue {
	private static BlockingQueue<Picking> newPickings = new ArrayBlockingQueue<Picking>(30);
	
	public static void add(Picking picking) {
		newPickings.offer(picking);
	}
	
	public static Picking getPicking() {
		return newPickings.poll();
	}

}
