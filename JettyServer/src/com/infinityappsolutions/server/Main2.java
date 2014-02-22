package com.infinityappsolutions.server;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.util.thread.ScheduledExecutorScheduler;

public class Main2 {

	public static void main(String[] args) {
		String jetty_home = System.getProperty("jetty.home",
				"/home/jchardis/ds/WebAppWorkSpace");
		String jetty_base = System.getProperty("jetty.home",
				"/home/jchardis/ds/WebAppWorkSpace");
		System.setProperty("jetty.home", jetty_home);
		System.setProperty("jetty.base", jetty_base);
		QueuedThreadPool threadPool = new QueuedThreadPool();
		threadPool.setMaxThreads(500);
		Server server = new Server(threadPool);
		server.addBean(new ScheduledExecutorScheduler());
		try {
			server.start();
			server.join();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
