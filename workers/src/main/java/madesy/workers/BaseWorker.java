package madesy.workers;

import java.util.Random;


public abstract class BaseWorker implements Runnable {
	private Random rand = new Random();
	protected String id;
	protected int sleepTime;
	
	public BaseWorker(String id, int sleepTime) {
		this.sleepTime = sleepTime;
		this.id = id;
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
