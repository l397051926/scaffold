
package com.lmx.scaffold.commons.thread;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 *  if the process closes, a signal is placed as true, and all threads get this flag to stop working
 */
public class Stopper {

	private static volatile AtomicBoolean signal = new AtomicBoolean(false);

	public static final boolean isStoped(){
		return signal.get();
	}

	public static final boolean isRunning(){
		return !signal.get();
	}

	public static final void stop(){
		signal.getAndSet(true);
	}
}
