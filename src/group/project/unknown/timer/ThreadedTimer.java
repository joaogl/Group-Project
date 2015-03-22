/*
 * Copyright 2014 João Lourenço and Hampus Backman.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package group.project.unknown.timer;

import group.project.unknown.*;

/**
 * Class to create threaded timers.
 * 
 * @author João Lourenço and Hampus Backman
 *
 */
public class ThreadedTimer implements Runnable {

	/** Thread where the timer will run. */
	private Thread thread;
	/** Variable that keeps track if the timer is running or not. */
	private boolean running = false;

	/** Name for the thread. */
	private String name;
	/** Is the thread finite or not. */
	private boolean finite = false;
	/** Last time update was run. */
	private long lastTimer;
	/** Spacing being the time gap between each update, left how many updates are left. */
	private int spacing, left;
	/** The callback to be used. */
	private TimerResult result;

	/**
	 * Constructor for the ThreadedTimer using a finite timer.
	 * 
	 * @param name
	 *            , Name for the timer.
	 * @param spacing
	 *            , time gap between each callback.
	 * @param amount
	 *            , amount of callbacks desired.
	 * @param res
	 *            , The callback desired.
	 * @author João Lourenço and Hampus Backman
	 */
	public ThreadedTimer(String name, int spacing, int amount, TimerResult res) {
		this.name = name;
		this.spacing = spacing;
		this.left = amount;
		this.finite = true;
		this.result = res;
		start();
	}

	/**
	 * Constructor for the ThreadedTimer using a infinite timer.
	 * 
	 * @param name
	 *            , Name for the timer.
	 * @param spacing
	 *            , time gap between each callback.
	 * @param res
	 *            , The callback desired.
	 * @author João Lourenço and Hampus Backman
	 */
	public ThreadedTimer(String name, int spacing, TimerResult res) {
		this.name = name;
		this.spacing = spacing;
		this.finite = false;
		this.result = res;
		start();
	}

	/**
	 * Method that starts the timer.
	 * 
	 * @author Joao Lourenco
	 */
	public synchronized void start() {
		this.thread = new Thread(this, "Timer-" + this.name);
		this.running = true;
		this.thread.start();
	}

	/**
	 * Run loop.
	 * 
	 * @author João Lourenço and Hampus Backman
	 */
	public void run() {
		this.lastTimer = System.currentTimeMillis();
		Main main = Registry.getMainClass();
		while (this.running) {
			if (System.currentTimeMillis() - this.lastTimer > this.spacing) {
				this.lastTimer += this.spacing;
				call();
			}
			if (!main.isRunning()) this.running = false;
		}
	}

	/**
	 * Callback.
	 * 
	 * @author João Lourenço and Hampus Backman
	 */
	public void call() {
		if (this.finite) {
			this.left--;
			this.result.timerCall(this.name);
			if (this.left <= 0) this.running = false;
		}
	}

}