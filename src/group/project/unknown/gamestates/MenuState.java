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

package group.project.unknown.gamestates;

import group.project.unknown.*;
import group.project.unknown.utils.*;

import java.awt.*;

import org.lwjgl.input.*;
import org.newdawn.slick.opengl.*;

import static org.lwjgl.opengl.GL11.*;

/**
 * The MenuState, where the main menu will be.
 * 
 * @author João Lourenço and Hampus Backman
 *
 */
public class MenuState extends GameState {

	/** Texture holders. */
	private Texture menuscreen;
	private Texture playBtn;

	/** Rectangle boxes. */
	private Rectangle mouse;
	private Rectangle play;

	/** Proper Mouse Positions. */
	private int mouseX;
	private int mouseY;

	/** Color of the play button. */
	private float btnColor;

	/**
	 * MenuState constructor.
	 * 
	 * @param gsm
	 *            , Instance of GameStateManager.
	 * @author João Lourenço and Hampus Backman
	 */
	public MenuState(GameStateManager gsm) {
		this.gsm = gsm;
	}

	/**
	 * Inits the GameState.
	 * 
	 * @author João Lourenço and Hampus Backman
	 */
	public void init() {
		menuscreen = Loader.loadTexture("tesla.png");
		playBtn = Loader.loadTexture("play.png");

		mouse = new Rectangle(0, 0, 1, 1);
		play = new Rectangle(Registry.getScreenWidth() - 175, Registry.getScreenHeight() - 100, 150, 75);

		btnColor = 1f;
	}

	/**
	 * Ticks the GameState.
	 * 
	 * @author João Lourenço and Hampus Backman
	 */
	public void tick() {
	}

	/**
	 * Updates the GameState.
	 * 
	 * @author João Lourenço and Hampus Backman
	 */
	public void update() {
		mouseX = Mouse.getX();
		mouseY = Registry.getScreenHeight() - Mouse.getY();

		mouse.move(mouseX, mouseY);

		if (mouse.intersects(play)) {
			btnColor = 0.7f;

			if (Mouse.isButtonDown(0)) gsm.setState(1);
		} else {
			btnColor = 1f;
		}
	}

	/**
	 * Renders the GameState.
	 * 
	 * @author João Lourenço and Hampus Backman
	 */
	public void render() {
		glColor3f(1f, 1f, 1f);
		glBindTexture(GL_TEXTURE_2D, menuscreen.getTextureID());
		glBegin(GL_TRIANGLES);
		{
			RenderShape.renderRect(0, 0, Registry.getScreenWidth(), Registry.getScreenHeight(), menuscreen.getWidth(), menuscreen.getHeight());
		}
		glEnd();
		glBindTexture(GL_TEXTURE_2D, 0);

		glColor3f(btnColor, btnColor, btnColor);
		glBindTexture(GL_TEXTURE_2D, playBtn.getTextureID());
		glBegin(GL_TRIANGLES);
		{
			RenderShape.renderRect((int) play.getX(), (int) play.getY(), (int) play.getWidth(), (int) play.getHeight(), playBtn.getWidth(), playBtn.getHeight());
		}
		glEnd();

		glBindTexture(GL_TEXTURE_2D, 0);
	}

}
