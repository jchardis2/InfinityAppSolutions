package com.infinityappsolutions.server.lib.log;

public class Logger {
	private static Logger logger;
	public static final String PROPERTY_DEBUG = "com.infinityappsolutions.server.lib.log.Logger.SHOULDDEBUG";
	private static boolean debug = false;

	public static Logger getInstance() {
		debug = Boolean.parseBoolean(System.getProperty(PROPERTY_DEBUG));
		if (logger == null) {
			logger = new Logger();
		}
		return logger;
	}

	private Logger() {
		debug = Boolean.parseBoolean(System.getProperty(PROPERTY_DEBUG));
	}

	public void debug(Throwable e) {
		System.out.println(debug);
		if (debug) {
			e.printStackTrace();
		}
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

}
