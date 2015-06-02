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

import org.lwjgl.util.vector.*;
import group.project.unknown.level.*;

import group.project.unknown.render.*;

public abstract class Tile {

	public static Tile stone = new StoneTile();
	public static Tile grass = new GrassTile();
	
	public boolean solid;
	
	public abstract float[] getTexCoords();
	public abstract byte getId();
	public abstract boolean isSolid();

	public static Tile getTile(byte id) {
		switch (id) {
		case 1:
			return Tile.stone;
		case 2:
			return Tile.grass;
		}

		return Tile.stone;
	}

	public static void createTile(RenderManager rm, Tile tile, int x, int y, int size) {
		rm.addColor3f(new Vector3f(1f, 1f, 1f));

		rm.addTextureCoord(new Vector2f(tile.getTexCoords()[0], tile.getTexCoords()[1]));
		rm.addVertex2f(new Vector2f(x, y));

		rm.addTextureCoord(new Vector2f(tile.getTexCoords()[0] + Spritesheet.tiles.uniformSize(), tile.getTexCoords()[1]));
		rm.addVertex2f(new Vector2f(x + size, y));

		rm.addTextureCoord(new Vector2f(tile.getTexCoords()[0] + Spritesheet.tiles.uniformSize(), tile.getTexCoords()[1] + Spritesheet.tiles.uniformSize()));
		rm.addVertex2f(new Vector2f(x + size, y + size));

		rm.addTextureCoord(new Vector2f(tile.getTexCoords()[0], tile.getTexCoords()[1] + Spritesheet.tiles.uniformSize()));
		rm.addVertex2f(new Vector2f(x, y + size));
	}

}
