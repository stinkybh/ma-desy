package madesy.simulation;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DesyThreadPoolExecutor extends ThreadPoolExecutor {
	public DesyThreadPoolExecutor() {
		super(20, // core threads
				40, // max threads
				1, // timeout
				TimeUnit.MINUTES, // timeout units
				new ArrayBlockingQueue<Runnable>(10) // work queue
		);
	}

	protected void afterExecute(Runnable r, Throwable t) {
		super.afterExecute(r, t);
		if (t == null && r instanceof Future<?>) {
			try {
				Future<?> future = (Future<?>) r;
				if (future.isDone())
					future.get();
			} catch (CancellationException ce) {
				t = ce;
			} catch (ExecutionException ee) {
				t = ee.getCause();
			} catch (InterruptedException ie) {
				Thread.currentThread().interrupt(); // ignore/reset
			}
		}
		if (t != null) {
			t.printStackTrace();
		}
	}
}
