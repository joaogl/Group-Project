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

import group.project.unknown.level.*;
import group.project.unknown.utils.*;

import org.lwjgl.opengl.*;
import org.lwjgl.util.vector.*;

public class Particle extends Entity {

	private float speed;
	private float angle;

	private float lifecount;
	private float life;

	private Vector3f color;

	public boolean remove = false;

	public Particle(Level level, Vector2f position, Vector3f color, float speed, float life, float angle) {
		super(level, position);

		this.angle = angle;
		this.speed = speed;
		this.life = life;

		this.color = color;
		
		init();
	}
	
	public void init() {
		cwidth = 5;
		cheight = 5;

		aabb = new AABB(position.x, position.y, cwidth, cheight);
	}

	public void tick() {
		lifecount++;
		if (lifecount >= life) remove = true;
	}

	float i = 0;

	public void update(float delta) {
		{ // MOVEMENT
			Vector2f tempPos = new Vector2f(position.x, position.y);

			tempPos.x += Math.sin(Math.toRadians(this.angle)) * speed * delta;
			tempPos.y += Math.cos(Math.toRadians(this.angle)) * speed * delta;

			if (!xCollision(tempPos.x)) position.x = tempPos.x;
			else {
				if (speed > 0) speed -= speed * 1.5 * delta;
			}

			if (!yCollision(tempPos.y)) position.y = tempPos.y;
			else {
				if (speed > 0) speed -= speed * 1.5 * delta;
			}

			if (speed > 0) speed -= (speed / 1.2) * delta;
		}
	}

	public void render() {
		GL11.glBegin(GL11.GL_QUADS);
		{
			GL11.glColor3f(color.x, color.y, color.z);
			GL11.glVertex2f(position.x - cwidth / 2 + cwidth, position.y - cheight / 2);
			GL11.glVertex2f(position.x - cwidth / 2 + cwidth, position.y - cheight / 2 + cheight);
			GL11.glVertex2f(position.x - cwidth / 2, position.y - cheight / 2 + cheight);
			GL11.glVertex2f(position.x - cwidth / 2, position.y - cheight / 2);
		}
		GL11.glEnd();
	}

}
