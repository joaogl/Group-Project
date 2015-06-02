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

import group.project.unknown.gamestates.*;

import group.project.unknown.level.*;
import group.project.unknown.utils.*;

import org.lwjgl.util.vector.*;

public abstract class Entity {

	protected Vector2f position;
	protected Level level;
	
	protected float width;
	protected float height;
	protected float cwidth;
	protected float cheight;
	
	protected AABB aabb;
	
	public Entity(Level level, Vector2f position) {
		this.position = position;
		this.level = level;
	}
	
	public abstract void init();
	
	public abstract void tick();
	public abstract void update(float delta);
	public abstract void render();
	
	public boolean xCollision(float tempX) {
		boolean[] xCollision = new boolean[level.ll.collision.size()];
		aabb.move(tempX, position.y);
		
		for (int x = 0; x < level.ll.collision.size(); x++) {
			xCollision[x] = aabb.intersects(level.ll.collision.get(x));
		}
		
		return !Utils.areAllFalse(xCollision);
	}
	
	public boolean yCollision(float tempY) {
		boolean[] yCollision = new boolean[level.ll.collision.size()];
		aabb.move(position.x, tempY);
		
		for (int y = 0; y < level.ll.collision.size(); y++) {
			yCollision[y] = aabb.intersects(level.ll.collision.get(y));
		}
		
		return !Utils.areAllFalse(yCollision);
	}
	
}
