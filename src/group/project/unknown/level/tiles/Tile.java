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
import group.project.unknown.utils.*;

public abstract class Tile {

	public static Tile stone = new StoneTile();
	public static Tile floor = new FloorTile(2, 0, 3);

	public static Tile wall0_0 = new WallTile(3, 0, 2); // LOWER WALL
	public static Tile wall0_1 = new WallTile(4, 0, 1); // HIGHER WALL
	
	public static Tile wall1_0 = new WallTile(5, 2, 2); // LOWER ROOF LEFT WALL
	public static Tile wall1_1 = new WallTile(6, 3, 2); // LOWER ROOF MIDDLE WALL
	public static Tile wall1_2 = new WallTile(7, 4, 2); // LOWER ROOF RIGHT WALL
	
	public static Tile wall2_0 = new WallTile(8, 2, 1); //  ROOF LEFT EDGE
	public static Tile wall2_1 = new WallTile(9, 3, 1); //  ROOF MIDDLE
	public static Tile wall2_2 = new WallTile(10, 4, 1); // ROOF RIGHT EDGE
	
	public static Tile wall3_0 = new WallTile(11, 2, 0); // TOP ROOF LEFT EDGE
	public static Tile wall3_1 = new WallTile(12, 3, 0); // TOP ROOF MIDDLE
	public static Tile wall3_2 = new WallTile(13, 4, 0); // TOP ROOF RIGHT EDGE

	public boolean solid;

	public abstract float[] getTexCoords();

	public abstract byte getId();

	public abstract boolean isSolid();
	
	public abstract AABB getCollision(int nx, int ny);

	public static Tile getTile(byte id) {
		switch (id) {
		case 1:
			return stone;
		case 2:
			return floor;
		case 3:
			return wall0_0;
		case 4:
			return wall0_1;
		case 5:
			return wall1_0;
		case 6:
			return wall1_1;
		case 7:
			return wall1_2;
		case 8:
			return wall2_0;
		case 9:
			return wall2_1;
		case 10:
			return wall2_2;
		case 11:
			return wall3_0;
		case 12:
			return wall3_1;
		case 13:
			return wall3_2;
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
