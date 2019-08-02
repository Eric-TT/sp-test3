package com.db.config;

import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.pool.PoolStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

@Component
public class HttpClientClose extends Thread {
	@Autowired
	private PoolingHttpClientConnectionManager manage;
	private volatile boolean shutdown;

	public HttpClientClose() {
		this.start();
	}

	@SuppressWarnings("unused")
	@Override
	public void run() {
		try {
			while (!shutdown) {
				synchronized (this) {
					wait(5000);
					PoolStats stats = manage.getTotalStats();
					int av = stats.getAvailable();
					int pend = stats.getPending();
					int lea = stats.getLeased();
					int max = stats.getMax();
//					System.out.println("max/" + max + ": av/" + av + ": pend/" + pend + ": lea/" + lea);
					manage.closeExpiredConnections();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}

		super.run();
	}

	@PreDestroy
	public void shutdown() {
		shutdown = true;
		synchronized (this) {
			notifyAll();
		}
	}
}
