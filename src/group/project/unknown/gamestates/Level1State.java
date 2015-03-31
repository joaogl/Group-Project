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

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import group.project.unknown.utils.*;

import java.io.*;

public class Level1State extends GameState {

	/** ShaderProgram holder. */
	private ShaderProgram shaderprogram;

	/**
	 * The Level1State constructor.
	 * 
	 * @param gsm
	 *            , Instance of GameStateManager.
	 * @author João Lourenço and Hampus Backman
	 */
	public Level1State(GameStateManager gsm) {
		this.gsm = gsm;
	}

	/**
	 * Inits the Level1State.
	 * 
	 * @author João Lourenço and Hampus Backman
	 */
	public void init() {
		shaderprogram = new ShaderProgram("res/shaders/shader.vert", "res/shaders/shader.frag");
		shaderprogram.attach();
	}

	/**
	 * Ticks the Level1State.
	 * 
	 * @author João Lourenço and Hampus Backman
	 */
	public void tick() {
	}

	/**
	 * Updates the Level1State.
	 * 
	 * @author João Lourenço and Hampus Backman
	 */
	public void update(float delta) {
	}

	/**
	 * Renders the Level1State.
	 * 
	 * @author João Lourenço and Hampus Backman
	 */
	public void render() {
		shaderprogram.useProgram(true);

		glBegin(GL_TRIANGLES);
		{
			RenderShape.renderRect(50, 50, 100, 100, 1, 1);
		}
		glEnd();

		shaderprogram.useProgram(false);
	}

	/**
	 * Cleans up everything in Level1State.
	 * 
	 * @author João Lourenço and Hampus Backman
	 */
	public void cleanUp() {
		shaderprogram.deleteShaders();
	}

}
