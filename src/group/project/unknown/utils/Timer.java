/*
 * Copyright 2014 Joao Lourenco and Hampus Backman
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

package group.project.unknown.utils;

/**
 * Creates a Timer that uses the Game Loop.
 * 
 * @author Hampus Backman
 */
public class Timer {

	/** The last time the update was updates. */
	private long lastTime;
	/** The timer for the update. */
	private long timer;

	/** The desired update time difference. */
	private double ns;
	/** The difference in time between the last and the current update. */
	private double delta;

	/** The frames of the timer. */
	private int frames;
	/** The updates of the timer. */
	private int updates;

	/** The last frame rate. */
	private int lastFPS;
	/** The last update rate. */
	private int lastUPS;

	private long now = System.nanoTime();

	/**
	 * The constructor of the class which inits the needed variables.
	 * 
	 * @param UPS , Update rate.
	 * @author Hampus Backman
	 */
	public Timer(double UPS) {
		lastTime = System.nanoTime();
		timer = System.currentTimeMillis();

		ns = 1000000000.0 / UPS;
		delta = 0;

		frames = 0;
		updates = 0;
	}

	/**
	 * Updates the timer and returns true if delta is greater or equals to 1.
	 * 
	 * @return If it should or should not update.
	 * @author Hampus Backman
	 */
	public boolean updateTimer() {
		now = System.nanoTime();
		delta += (now - lastTime) / ns;
		lastTime = now;

		frames++;

		if (delta >= 1) {
			updates++;
			delta--;
			return true;
		}

		if (System.currentTimeMillis() - timer > 1000) {
			timer += 1000;

			lastUPS = updates;
			lastFPS = frames;

			updates = 0;
			frames = 0;
		}

		return false;
	}

	/**
	 * This sets the update rate.
	 * 
	 * @param USP , frame rate.
	 * @author Hampus Backman
	 */
	public void setUPS(float USP) {
		ns = 1000000000.0 / USP;
	}

	/**
	 * Return the fps of the timer.
	 * 
	 * @return The fps.
	 * @author Hampus Backman
	 */
	public int getFPS() {
		return lastFPS;
	}

	/**
	 * Return the update of the timer.
	 * 
	 * @return The updates.
	 * @author Hampus Backman
	 */
	public int getUPS() {
		return lastUPS;
	}

	/**
	 * Return the delta of the timer.
	 * 
	 * @return The delta.
	 * @author Hampus Backman
	 */
	public double getDelta() {
		return 1;
	}

}
