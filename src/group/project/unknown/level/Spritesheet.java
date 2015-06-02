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
import org.newdawn.slick.opengl.*;

import group.project.unknown.utils.*;

public class Spritesheet {
	
	public static Spritesheet tiles = new Spritesheet("res/tiles.png", 8);
	
	private Texture texture;
	private String path;
	private float size;
	
	public Spritesheet(String path, float size) {
		this.path = path;
		this.size = 1 / size;
		load();
	}
	
	private void load() {
		texture = TexLoader.loadTexture(path);
	}
	
	public void bind() {
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTextureID());
	}
	
	public void unbind() {
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
	}
	
	public void delete() {
		texture.release();
	}
	
	public float uniformSize() {
		return size;
	}
	
}
