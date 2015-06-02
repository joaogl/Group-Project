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

package group.project.unknown.utils;

import java.io.*;

import org.lwjgl.opengl.*;
import org.newdawn.slick.opengl.*;
import org.newdawn.slick.util.*;

public class TexLoader {
	
	public static Texture loadTexture(String url) {
		Texture texture = null;
		
		try {
			texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(url));
			texture.setTextureFilter(GL11.GL_NEAREST);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return texture;
	}
	
}
