package madesy.model.workers;

import java.util.UUID;

public abstract class BaseWorker implements Runnable {
	protected String id = UUID.randomUUID().toString();
	protected int sleepTime;
	
	public BaseWorker(int sleepTime) {
		this.sleepTime = sleepTime;
	}
	
	public abstract void  doWork();
	
	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			doWork();
			threadToSleep();
		}
	}

	protected void threadToSleep() {
		try {
			Thread.sleep(1000 + sleepTime);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
}
