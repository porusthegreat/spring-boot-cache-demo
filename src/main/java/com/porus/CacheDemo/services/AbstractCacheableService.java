package com.porus.CacheDemo.services;

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class AbstractCacheableService {

	protected final AtomicBoolean cacheMiss = new AtomicBoolean(false);

	public boolean isCacheHit() {
		return !isCacheMiss();
	}

	public boolean isCacheMiss() {
		return this.cacheMiss.compareAndSet(true,false);
	}

	protected long delayInMilliseconds() {
		return 3000L;
	}

	protected void simulateLatency() {
		try {
			Thread.sleep(delayInMilliseconds());
		}
		catch (InterruptedException ignore) {
			Thread.currentThread().interrupt();
		}
	}
}