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

package group.project.unknown.level.entities;

import group.project.unknown.*;
import group.project.unknown.level.*;
import group.project.unknown.level.tiles.*;
import group.project.unknown.utils.*;

import org.lwjgl.input.*;
import org.lwjgl.util.vector.*;

import static org.lwjgl.opengl.GL11.*;

/**
 * Player class.
 * 
 * @author João Lourenço and Hampus Backman
 *
 */
public class Player extends Entity {
	
	private float speed = 100;
	private int particleRunningCounter = 0;

	/**
	 * Constructor of Player.
	 * 
	 * @param position
	 *            , Position to spawn the player.
	 * @author João Lourenço and Hampus Backman
	 */
	public Player(Level level, Vector2f position) {
		super(level, position);
	}

	public void init() {
		cwidth = 32;
		cheight = 32;
		aabb = new AABB(position.x, position.y, cwidth, cheight);

		float[] texC = Tile.stone.getTexCoords();
		level.rm.addColor3f(new Vector3f(1, 1, 1));
		level.rm.addTextureCoord(new Vector2f(texC[0], texC[1]));
		level.rm.addVertex2f(new Vector2f(0, 0));

		level.rm.addColor3f(new Vector3f(1, 1, 1));
		level.rm.addTextureCoord(new Vector2f(texC[0] + Spritesheet.tiles.uniformSize(), texC[1]));
		level.rm.addVertex2f(new Vector2f(cwidth, 0));

		level.rm.addColor3f(new Vector3f(1, 1, 1));
		level.rm.addTextureCoord(new Vector2f(texC[0] + Spritesheet.tiles.uniformSize(), texC[1] + Spritesheet.tiles.uniformSize()));
		level.rm.addVertex2f(new Vector2f(cwidth, cheight));

		level.rm.addColor3f(new Vector3f(1, 1, 1));
		level.rm.addTextureCoord(new Vector2f(texC[0], texC[1] + Spritesheet.tiles.uniformSize()));
		level.rm.addVertex2f(new Vector2f(0, cheight));

		level.rm.flush(0, false, null);
	}

	/**
	 * Ticks the player.
	 * 
	 * @author João Lourenço and Hampus Backman
	 */
	public void tick() {
	}

	/**
	 * Updates the player.
	 * 
	 * @author João Lourenço and Hampus Backman
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
		
		if (KeyboardFilter.isKeyDown(Keyboard.KEY_UP)) 		level.spawner.spawnPartcile(5, new Vector2f(position.x, position.y), new Vector3f(0, 1, 1), 350, 10, 180, 10);
		if (KeyboardFilter.isKeyDown(Keyboard.KEY_DOWN)) 	level.spawner.spawnPartcile(5, new Vector2f(position.x, position.y), new Vector3f(0, 1, 1), 350, 10, 0, 10);
		if (KeyboardFilter.isKeyDown(Keyboard.KEY_RIGHT)) 	level.spawner.spawnPartcile(5, new Vector2f(position.x, position.y), new Vector3f(0, 1, 1), 350, 10, 90, 10);
		if (KeyboardFilter.isKeyDown(Keyboard.KEY_LEFT)) 	level.spawner.spawnPartcile(5, new Vector2f(position.x, position.y), new Vector3f(0, 1, 1), 350, 10, 270, 10);
	}

	/**
	 * Renders the player. TODO
	 * 
	 * @author João Lourenço and Hampus Backman
	 */
	public void render() {
		glTranslatef(getX() - getCwidth() / 2, getY() - getCheight() / 2, 0);
		{
			level.rm.render(0);
			
			float rotation = (float) Math.toDegrees(Math.atan2((Registry.getScreenWidth() / 2) - Mouse.getX(), (Registry.getScreenHeight() / 2) - Mouse.getY()));
			glTranslatef(getCwidth() / 2, getCheight() / 2, 0);
			glRotatef(rotation, 0, 0, 1);
			glBegin(GL_QUADS); {
				glVertex2f(-2.5f, 0);
				glVertex2f(2.5f, 0);
				glVertex2f(2.5f, 100);
				glVertex2f(-2.5f, 100);
			} glEnd();
			glRotatef(-rotation, 0, 0, 1);
			glTranslatef(-(getCwidth() / 2), -(getCheight() / 2), 0);
		}
		glTranslatef(-getX() + getCwidth() / 2, -getY() + getCheight() / 2, 0);
	}

	public void moveAngle(Vector2f pos, float delta, float angle) {
		particleRunningCounter++;
		if (particleRunningCounter >= 7) {
			particleRunningCounter = 0;
			level.spawner.spawnRunningParticle(1, new Vector2f(position.x, position.y), new Vector3f(0.1f, 0.1f, 0.1f), 20, 5, angle-180, 150);
		}

		pos.x += Math.sin(Math.toRadians(angle)) * speed * delta;
		pos.y += Math.cos(Math.toRadians(angle)) * speed * delta;
	}

	/**
	 * Returns X Position.
	 * 
	 * @return
	 * @author João Lourenço and Hampus Backman
	 */
	public float getX() {
		return this.position.getX();
	}

	/**
	 * Returns Y Position.
	 * 
	 * @return
	 * @author João Lourenço and Hampus Backman
	 */
	public float getY() {
		return this.position.getY();
	}

	public float getCwidth() {
		return this.cwidth;
	}

	public float getCheight() {
		return this.cheight;
	}
}
