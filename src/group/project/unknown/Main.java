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

package group.project.unknown;

import group.project.unknown.settings.*;
import group.project.unknown.utils.*;

import org.lwjgl.*;
import org.lwjgl.input.*;
import org.lwjgl.opengl.*;

import static org.lwjgl.opengl.GL11.*;

/**
 * Main class for the game.
 * 
 * @author Jo�o Louren�o and Hampus Backman
 */
public class Main implements Runnable {

	/** Thread where the game is going to run. */
	Thread thread;
	/** Flag to keep track of game running status. */
	boolean running = false;

	/**
	 * The Main Constructor.
	 * 
	 * @author Jo�o Louren�o and Hampus Backman
	 */
	public Main() {
		start();
	}

	/**
	 * Starts the game.
	 * 
	 * @author Jo�o Louren�o and Hampus Backman
	 */
	public synchronized void start() {
		thread = new Thread(this, "Game");
		running = true;
		thread.start();
	}

	/**
	 * Stops the game.
	 * 
	 * @author Jo�o Louren�o and Hampus Backman
	 */
	public synchronized void stop() {
		running = false;
	}

	/**
	 * Gameloop.
	 * 
	 * @author Jo�o Louren�o and Hampus Backman
	 */
	public void run() {
		Settings.loadSettings();
		try {
			init();
		} catch (LWJGLException e) {
			Out.print("Could not initialize LWJGL");
			System.exit(-1);
		}

		Timer timerUpdate = new Timer(60F);
		Timer timerRender = new Timer(Float.parseFloat(Registry.getSetting("fps_lock")));
		Timer timerTick = new Timer(1F);

		while (running) {
			if (timerUpdate.updateTimer()) {
				update();
			}
			if (timerRender.updateTimer()) {
				render();
				Display.update();
			}
			if (timerTick.updateTimer()) {
				tick();
				Display.setTitle("FPS: " + timerRender.getUPS() + " UPS: " + timerUpdate.getUPS() + " DELTA: " + timerUpdate.getDelta());
			}

			if (Display.isCloseRequested()) this.stop();
		}

		cleanUp();
	}

	/**
	 * Update method, called 60 times per second.
	 * 
	 * @author Jo�o Louren�o and Hampus Backman
	 */
	private void update() {
		if (KeyboardFilter.isKeyDown(Keyboard.KEY_ESCAPE)) stop();
		if (KeyboardFilter.isKeyDown(Keyboard.KEY_F11)) {
			if (Registry.getSetting("screen_mode").equalsIgnoreCase("fullscreen")) Registry.registerSetting("screen_mode", "windowed");
			else Registry.registerSetting("screen_mode", "fullscreen");

			Display.destroy();
			try {
				init();
			} catch (LWJGLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Tick method, called once per second.
	 * 
	 * @author Jo�o Louren�o and Hampus Backman
	 */
	private void tick() {}

	/**
	 * Render method, called has many times has it can.
	 * 
	 * @author Jo�o Louren�o and Hampus Backman
	 */
	private void render() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glClearColor(0.0f, 0.0f, 0.0f, 0.5f);

		glEnable(GL_BLEND);

		glColor3f(0f, 0f, 1f);
		glBegin(GL_TRIANGLES);
		{
			// Each vertice of the Quad
			glTexCoord2f(0, 0);
			glVertex2f(0, 0);

			// Each vertice of the Quad
			glTexCoord2f(0, 1);
			glVertex2f(0, 100f);

			// Each vertice of the Quad
			glTexCoord2f(1, 1);
			glVertex2f(100f, 100f);

			// Each vertice of the Quad
			glTexCoord2f(1, 1);
			glVertex2f(100f, 100f);

			// Each vertice of the Quad
			glTexCoord2f(1, 0);
			glVertex2f(100f, 0);

			// Each vertice of the Quad
			glTexCoord2f(0, 0);
			glVertex2f(0, 0);
		}
		glEnd();

		glDisable(GL_BLEND);
	}

	/**
	 * Java main method called by default.
	 * 
	 * @param args , Any kind of arguments.
	 * @author Jo�o Louren�o and Hampus Backman
	 */
	public static void main(String[] args) {
		new Main();
	}

	/**
	 * Cleans up the memory to avoid memory leaks.
	 * 
	 * @author Jo�o Louren�o and Hampus Backman
	 */
	private void cleanUp() {
		Display.destroy();
		Registry.cleanUp();
	}

	/**
	 * Initializes the Game.
	 * 
	 * @throws LWJGLException
	 * @author Jo�o Louren�o and Hampus Backman
	 */
	private void init() throws LWJGLException {
		Registry.registerMainClass(this);
		Registry.registerScreen(Registry.getScreenWidth(), Registry.getScreenHeight());

		DisplayMode[] displays = Display.getAvailableDisplayModes();

		boolean full = false;
		Out.print(Registry.getSetting("screen_mode"));
		if (Registry.getSetting("screen_mode").equalsIgnoreCase("fullscreen")) full = true;

		DisplayMode mode = null;
		for (int i = 0; i < displays.length; i++) {
			if (displays[i].getWidth() == Registry.getScreenWidth() && displays[i].getHeight() == Registry.getScreenHeight() && displays[i].getBitsPerPixel() >= 32 && displays[i].getFrequency() == 60) {
				mode = displays[i];
				break;
			}
		}

		Display.setDisplayMode(mode);
		Display.setFullscreen(full);
		Display.setTitle("");
		Display.setResizable(false);

		Display.create(new PixelFormat(0, 16, 1));

		// Setting up all the Projections stuff for OpenGL
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, Registry.getScreenWidth(), Registry.getScreenHeight(), 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);

		glEnable(GL_TEXTURE_2D);
		glEnable(GL_STENCIL_TEST);
		glClearColor(0, 0, 0, 0);

		/*
		 * for (DisplayMode dm2 : displays) Out.print(dm2.getWidth() + " : " + dm2.getHeight() + " : " + dm2.isFullscreenCapable());
		 */

	}

	/**
	 * Method to check if the game is running.
	 * 
	 * @return Returns if the game is running or not.
	 * @author Jo�o Louren�o and Hampus Backman
	 */
	public boolean isRunning() {
		return this.running;
	}

}