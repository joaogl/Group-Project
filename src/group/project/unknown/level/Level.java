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

package group.project.unknown.level;

import group.project.unknown.*;
import group.project.unknown.level.entities.*;
import group.project.unknown.render.*;
import group.project.unknown.utils.*;

import java.util.*;

import org.lwjgl.opengl.*;
import org.lwjgl.util.vector.*;

import static org.lwjgl.opengl.GL11.*;

public class Level {

	private ShaderProgram levelShader;
	private ShaderProgram lightShader;
	public RenderManager rm;
	public LevelLoader ll;

	public Spawner spawner;
	private Player player;

	private String levelName;

	private ArrayList<Light> lights = new ArrayList<Light>();

	public Level(String levelName) {
		this.levelName = levelName;
	}

	public void init() {
		levelShader = new ShaderProgram("res/shaders/level.vert", "res/shaders/level.frag");
		levelShader.attach();

		lightShader = new ShaderProgram("res/shaders/light.frag", ShaderProgram.FRAG);
		lightShader.attach();

		glEnable(GL_TEXTURE_2D);
		
		rm = new RenderManager(GL11.GL_QUADS, levelShader);
		ll = new LevelLoader(rm);
		ll.load("res/levels/" + levelName + ".map", -1, true);
		ll.load("res/levels/" + levelName + "_roof.map", 1, false);

		lights.add(new Light(500, 750, 20, 1.0f, 0.5f, 0.5f));
		lights.add(new Light(850, 850, 20, 0.0f, 0.0f, 1f));

		spawner = new Spawner(this);

		player = new Player(this, new Vector2f(0, 0));
		player.init();
	}

	public void tick() {
		spawner.tick();
		player.tick();
	}

	public void update(float delta) {
		spawner.update(delta);
		player.update(delta);
	}

	public void render() {
		glTranslatef(Registry.getScreenWidth() / 2 - (float) player.getX(), Registry.getScreenHeight() / 2 - (float) player.getY(), 0);
		{
			glEnable(GL_BLEND);
			glBlendFunc(GL_ONE, GL_ONE);

			levelShader.setUniform1f("elevation", 0f);
			rm.render(-1, true);
			
			spawner.render();
			player.render();
			
			lightShader.useProgram(true);
			for (Light light : lights) {
				glColorMask(true, true, true, true);

				lightShader.setUniform2f("lightPosition", light.position.x - player.getX(), Registry.getScreenHeight() - (light.position.y - player.getY()));
				lightShader.setUniform3f("lightColor", light.red, light.green, light.blue);
				lightShader.setUniform1f("size", light.size);

				glBegin(GL_QUADS);
				{
					glColor3f(0, 0, 0);
					glVertex2f(0, 0);
					glVertex2f(ll.width*ll.tilesize, 0);
					glVertex2f(ll.width*ll.tilesize, ll.height*ll.tilesize);
					glVertex2f(0, ll.height*ll.tilesize);
				}
				glEnd();
			}
			glDisable(GL_BLEND);
			lightShader.useProgram(false);

			rm.render(1, true);
		}
		glTranslatef(-(Registry.getScreenWidth() / 2 - (float) player.getX()), -(Registry.getScreenHeight() / 2 - (float) player.getY()), 0);
	}

	public void cleanUp() {
		levelShader.deleteShaders();
	}

}
