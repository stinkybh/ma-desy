package madesy.model.workers;


public abstract class BaseWorker implements Runnable {
	protected String id;
	protected int sleepTime;
	
	public BaseWorker(String id, int sleepTime) {
		this.sleepTime = sleepTime;
		this.id = id;
	}
	
	public abstract void  doWork();
	
	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			doWork();
			//threadToSleep();
		}
	}

	protected void threadToSleep() {
		try {
			Thread.sleep(50 + sleepTime);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}
	
	@Override
	public String toString() {
		return "BaseWorker [id = " + id + ", sleepTime = " + sleepTime + "]\n";
	}
}
