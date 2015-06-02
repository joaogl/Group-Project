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

import group.project.unknown.level.tiles.*;
import group.project.unknown.render.*;
import group.project.unknown.utils.*;

import java.io.*;
import java.util.*;

import org.lwjgl.util.vector.*;

public class LevelLoader {

	private RenderManager rm;
	private int width;
	private int height;

	/** Array of tiles. */
	private byte[][] tiles;
	private int tilesize;
	
	public List<AABB> collision = new ArrayList<AABB>();

	/**
	 * Constructor for LevelLoader.
	 * 
	 * @param rm
	 *            , Instance of RenderManager.
	 * @author João Lourenço and Hampus Backman
	 */
	public LevelLoader(RenderManager rm) {
		this.rm = rm;
	}

	/**
	 * Loads the level. !WIP!
	 * 
	 * @author João Lourenço and Hampus Backman
	 */
	public void load(String url, int layer, boolean solid) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(url));
				
			width = Integer.parseInt(br.readLine());
			height = Integer.parseInt(br.readLine());
			tilesize = Integer.parseInt(br.readLine());
			
			tiles = new byte[width][height];
			
			String divider = "\\s+";
			for (int x = 0; x < width; x++) {
				String line = br.readLine();
				String[] tokens = line.split(divider);
				
				for (int y = 0; y < height; y++) {
					tiles[y][x] = Byte.parseByte(tokens[y]);
				}
			}
			
			br.close();
			
			int nx, ny;
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					nx = x * tilesize;
					ny = y * tilesize;
					
					float[] texC = Tile.getTile(tiles[x][y]).getTexCoords();
					if (Tile.getTile(tiles[x][y]).isSolid() && solid) {
						collision.add(new AABB(nx, ny, tilesize, tilesize));
					}
					
					if (tiles[x][y] == 0) continue;
					
					rm.addColor3f(new Vector3f(1f, 1f, 1f));
					rm.addTextureCoord(new Vector2f(texC[0], texC[1]));
					rm.addVertex2f(new Vector2f(nx, ny));
					
					rm.addColor3f(new Vector3f(1f, 1f, 1f));
					rm.addTextureCoord(new Vector2f(texC[0] + Spritesheet.tiles.uniformSize(), texC[1]));
					rm.addVertex2f(new Vector2f(nx + tilesize, ny));

					rm.addColor3f(new Vector3f(1f, 1f, 1f));
					rm.addTextureCoord(new Vector2f(texC[0] + Spritesheet.tiles.uniformSize(), texC[1] + Spritesheet.tiles.uniformSize()));
					rm.addVertex2f(new Vector2f(nx + tilesize, ny + tilesize));

					rm.addColor3f(new Vector3f(1f, 1f, 1f));
					rm.addTextureCoord(new Vector2f(texC[0], texC[1] + Spritesheet.tiles.uniformSize()));
					rm.addVertex2f(new Vector2f(nx, ny + tilesize));
				}
			}

			rm.flush(layer);
		} catch (Exception e) {
			Out.print("Could not find map file, generating new!");
			generate();
		}
	}
	
	private void generate() {
		Out.print("TODO");
	}
}
