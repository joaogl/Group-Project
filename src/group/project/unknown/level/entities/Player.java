/*
 * Copyright 2014 Jo�o Louren�o and Hampus Backman.
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

package group.project.unknown.level.entities;

import group.project.unknown.gamestates.*;
import group.project.unknown.level.*;
import group.project.unknown.level.tiles.*;
import group.project.unknown.utils.*;

import org.lwjgl.input.*;
import org.lwjgl.opengl.*;
import org.lwjgl.util.vector.*;
import org.newdawn.slick.opengl.*;

/**
 * Player class.
 * 
 * @author Jo�o Louren�o and Hampus Backman
 *
 */
public class Player extends Entity {

	private Texture tex;

	private float speed = 100;

	/**
	 * Constructor of Player.
	 * 
	 * @param position
	 *            , Position to spawn the player.
	 * @author Jo�o Louren�o and Hampus Backman
	 */
	public Player(Level level, Vector2f position) {
		super(level, position);
	}

	public void init() {
		cwidth = 32;
		cheight = 32;
		aabb = new AABB(position.x, position.y, cwidth, cheight);

		speed += 0.10f;

		tex = TexLoader.loadTexture("res/player.png");

		float[] texC = Tile.stone.getTexCoords();
		level.rm.addColor3f(new Vector3f(1, 1, 1));
		level.rm.addTextureCoord(new Vector2f(texC[0], texC[1]));
		level.rm.addVertex2f(new Vector2f(0, 0));

		level.rm.addTextureCoord(new Vector2f(texC[0] + Spritesheet.tiles.uniformSize(), texC[1]));
		level.rm.addVertex2f(new Vector2f(cwidth, 0));

		level.rm.addTextureCoord(new Vector2f(texC[0] + Spritesheet.tiles.uniformSize(), texC[1] + Spritesheet.tiles.uniformSize()));
		level.rm.addVertex2f(new Vector2f(cwidth, cheight));

		level.rm.addTextureCoord(new Vector2f(texC[0], texC[1] + Spritesheet.tiles.uniformSize()));
		level.rm.addVertex2f(new Vector2f(0, cheight));

		level.rm.flush(1);
	}

	/**
	 * Ticks the player.
	 * 
	 * @author Jo�o Louren�o and Hampus Backman
	 */
	public void tick() {
		// System.out.println(speed);
	}

	/**
	 * Updates the player.
	 * 
	 * @author Jo�o Louren�o and Hampus Backman
	 */
	public void update(float delta) {
		{ // MOVEMENT
			Vector2f tempPos = new Vector2f(position.x, position.y);

			if (Keyboard.isKeyDown(Keyboard.KEY_W)) moveAngle(tempPos, delta, 180);
			if (Keyboard.isKeyDown(Keyboard.KEY_S)) moveAngle(tempPos, delta, 0);
			if (Keyboard.isKeyDown(Keyboard.KEY_D)) moveAngle(tempPos, delta, 90);
			if (Keyboard.isKeyDown(Keyboard.KEY_A)) moveAngle(tempPos, delta, 270);

			if (Keyboard.isKeyDown(Keyboard.KEY_Z)) speed -= 1;
			if (Keyboard.isKeyDown(Keyboard.KEY_X)) speed += 1;

			if (!xCollision(tempPos.x)) position.x = tempPos.x;
			if (!yCollision(tempPos.y)) position.y = tempPos.y;
		}

		if (KeyboardFilter.isKeyDown(Keyboard.KEY_UP)) level.spawner.spawnPartciles(5, new Vector2f(position.x, position.y), new Vector3f(0, 1, 1), 350, 10, 180, 10);
		if (KeyboardFilter.isKeyDown(Keyboard.KEY_DOWN)) level.spawner.spawnPartciles(5, new Vector2f(position.x, position.y), new Vector3f(0, 1, 1), 350, 10, 0, 10);
		if (KeyboardFilter.isKeyDown(Keyboard.KEY_RIGHT)) level.spawner.spawnPartciles(5, new Vector2f(position.x, position.y), new Vector3f(0, 1, 1), 350, 10, 90, 10);
		if (KeyboardFilter.isKeyDown(Keyboard.KEY_LEFT)) level.spawner.spawnPartciles(5, new Vector2f(position.x, position.y), new Vector3f(0, 1, 1), 350, 10, 270, 10);
	}

	/**
	 * Renders the player. TODO
	 * 
	 * @author Jo�o Louren�o and Hampus Backman
	 */
	public void render() {
		GL11.glTranslatef(position.x-cwidth/2, position.y-cheight/2, 0);
		{
			level.rm.renderLayer(1, false);
		}
		GL11.glTranslatef(-position.x+cwidth/2, -position.y+cheight/2, 0);
	}
	
	public void moveAngle(Vector2f pos, float delta, float angle) {
		pos.x += Math.sin(Math.toRadians(angle)) * speed * delta;
		pos.y += Math.cos(Math.toRadians(angle)) * speed * delta;
	}

	/**
	 * Returns X Position.
	 * 
	 * @return
	 * @author Jo�o Louren�o and Hampus Backman
	 */
	public float getX() {
		return this.position.getX();
	}

	/**
	 * Returns Y Position.
	 * 
	 * @return
	 * @author Jo�o Louren�o and Hampus Backman
	 */
	public float getY() {
		return this.position.getY();
	}
}