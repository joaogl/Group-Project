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

import org.lwjgl.opengl.*;
import org.lwjgl.util.vector.*;

import group.project.unknown.*;
import group.project.unknown.level.entities.*;
import group.project.unknown.render.*;
import group.project.unknown.utils.*;

public class Level {
	
	private ShaderProgram levelShader;
	public RenderManager rm;
	public LevelLoader ll;

	public Spawner spawner;
	private Player player;

	private String levelName;

	public Level(String levelName) {
		this.levelName = levelName;
	}

	public void init() {
		levelShader = new ShaderProgram("res/shaders/level.vert", "res/shaders/level.frag");
		levelShader.attach();

		GL11.glEnable(GL11.GL_TEXTURE_2D);

		rm = new RenderManager(GL11.GL_QUADS, levelShader);
		ll = new LevelLoader(rm);
		ll.load("res/levels/" + levelName + ".map", 0, true);

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
		GL11.glTranslated(Registry.getScreenWidth() / 2 - (double) player.getX(), Registry.getScreenHeight() / 2 - (double) player.getY(), 0);

		Spritesheet.tiles.bind();
		{
			rm.renderLayer(0, false);
		}
		Spritesheet.tiles.unbind();

		spawner.render();
		{
			levelShader.useProgram(true);
			player.render();
			levelShader.useProgram(false);
		}

		GL11.glTranslated(-(Registry.getScreenWidth() / 2 - (double) player.getX()), -(Registry.getScreenHeight() / 2 - (double) player.getY()), 0);
	}

	public void cleanUp() {
		levelShader.deleteShaders();
	}

}
