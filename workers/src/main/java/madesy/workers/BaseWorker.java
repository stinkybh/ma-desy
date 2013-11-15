package madesy.workers;

import java.util.Random;
import java.util.UUID;


public abstract class BaseWorker implements Runnable {
	private Random rand = new Random();
	protected String id = UUID.randomUUID().toString();
	protected int sleepTime;
	
	public BaseWorker(int sleepTime) {
		this.sleepTime = sleepTime;
	}
	
	public abstract void  doWork();
	
	@Override 
	public void run() {
		while(!Thread.currentThread().isInterrupted()) {
			doWork();
			threadToSleep();
		}
	}

	protected void threadToSleep() {
		try {
			Thread.sleep(rand.nextInt(1000) + sleepTime);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	
	@Override
	public String toString() {
		return "BaseWorker [id = " + id + ", sleepTime = " + sleepTime + "]\n";
	}
}
