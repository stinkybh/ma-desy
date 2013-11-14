package madesy.model.services;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class BaseService {
	private static final Lock lock = new ReentrantLock();
	
	protected abstract class Synchronizator<T> {
		abstract T execute();

		T executeWithLock() {
			lock.lock();
			try {
				return execute();
			} finally {
				lock.unlock();
			}
		}
	}
}
