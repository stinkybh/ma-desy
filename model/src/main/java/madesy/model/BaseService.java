package madesy.model;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class BaseService {
	private final Lock lock = new ReentrantLock();
	
	protected abstract class Synchronizator<T> {
		protected abstract T execute();

		public T executeWithLock() {
			lock.lock();
			try {
				return execute();
			} finally {
				lock.unlock();
			}
		}
	}
}
