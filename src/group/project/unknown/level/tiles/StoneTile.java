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

package group.project.unknown.level.tiles;

import group.project.unknown.level.*;
import group.project.unknown.utils.*;

public class StoneTile extends Tile {
	
	public byte getId() {
		return 1;
	}

	public float[] getTexCoords() {
		return new float[]{0 * Spritesheet.tiles.uniformSize(), 0 * Spritesheet.tiles.uniformSize()};
	}

	public boolean isSolid() {
		return true;
	}

	public AABB getCollision(int nx, int ny) {
		return new AABB(nx, ny, LevelLoader.tilesize, LevelLoader.tilesize);
	}
}
