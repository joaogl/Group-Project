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
import group.project.unknown.utils.Button;

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

	/** Background texture. */
	private Texture background;

	/** Button instances. */
	private Button play;

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
		background = Loader.loadTexture("tesla.png");
		play = new Button(Registry.getScreenWidth() - 175, Registry.getScreenHeight() - 100, 150, 75, "play.png");
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
	public void update(float delta) {
		if (play.hover()) play.setColor(0.7f, 0.7f, 0.7f);
		else play.setColor(1f, 1f, 1f);
		
		if (play.clicked()) gsm.setState(1);
	}

	/**
	 * Renders the GameState.
	 * 
	 * @author João Lourenço and Hampus Backman
	 */
	public void render() {
		glColor3f(1f, 1f, 1f);
		glBindTexture(GL_TEXTURE_2D, background.getTextureID());
		glBegin(GL_TRIANGLES);
		{
			RenderShape.renderRect(0, 0, Registry.getScreenWidth(), Registry.getScreenHeight(), background.getWidth(), background.getHeight());
		}
		glEnd();
		glBindTexture(GL_TEXTURE_2D, 0);

		play.render();
	}

}
