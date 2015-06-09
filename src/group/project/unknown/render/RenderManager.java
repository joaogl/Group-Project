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

package group.project.unknown.render;

import group.project.unknown.level.*;
import group.project.unknown.utils.*;

import java.util.*;

import org.lwjgl.util.vector.*;

import static org.lwjgl.opengl.GL11.*;

public class RenderManager {

	/**
	 * ArrayLists to hold stuff.
	 */
	public TreeMap<Integer, Integer> displayLists = new TreeMap<Integer, Integer>();
	public ArrayList<Vector2f> textureCoords = new ArrayList<Vector2f>();
	public ArrayList<Vector2f> vertices = new ArrayList<Vector2f>();
	public ArrayList<Vector3f> colors = new ArrayList<Vector3f>();

	public int layers;

	@SuppressWarnings("rawtypes")
	private Set set;
	@SuppressWarnings("rawtypes")
	private Map.Entry mentry;
	@SuppressWarnings("rawtypes")
	private Iterator iterator;

	private ShaderProgram sh;
	private final int MODE;

	/**
	 * Constructor for RenderManager.
	 * 
	 * @param MODE
	 *            , What render mode which should be used.
	 * @author João Lourenço and Hampus Backman
	 */
	public RenderManager(int MODE, ShaderProgram sh) {
		this.MODE = MODE;
		this.sh = sh;
	}

	/**
	 * Adds a vertex in form of a Vector2f.
	 * 
	 * @param vertex
	 * @author João Lourenço and Hampus Backman
	 */
	public void addVertex2f(Vector2f vertex) {
		vertices.add(vertex);
	}

	/**
	 * Adds texture choords.
	 * 
	 * @param texCh
	 * @author João Lourenço and Hampus Backman
	 */
	public void addTextureCoord(Vector2f texCh) {
		textureCoords.add(texCh);
	}

	/**
	 * Adds a color in form of a Vector3f.
	 * 
	 * @param color
	 * @author João Lourenço and Hampus Backman
	 */
	public void addColor3f(Vector3f color) {
		colors.add(color);
	}

	/**
	 * Flushed the vertices and adds them into a displayList
	 * 
	 * @param layer
	 * @author João Lourenço and Hampus Backman
	 */
	public void flush(int layer, boolean useTextures, Spritesheet spritesheet) {
		int displayL = layer;
		displayL = glGenLists(1);
		glNewList(displayL, GL_COMPILE);
		if (useTextures) spritesheet.bind();
		glBegin(MODE);
		for (int j = 0; j < vertices.size(); j++) {
			try {
				glColor3f(colors.get(j).getX(), colors.get(j).getY(), colors.get(j).getZ());
				glTexCoord2f(textureCoords.get(j).getX(), textureCoords.get(j).getY());
			} catch (IndexOutOfBoundsException e) {
			}
			glVertex2f(vertices.get(j).getX(), vertices.get(j).getY());
		}
		glEnd();
		if (useTextures) spritesheet.unbind();
		glEndList();

		displayLists.put(layer, displayL);

		textureCoords.clear();
		vertices.clear();
		colors.clear();
	}

	/**
	 * Render the layers.
	 * 
	 * @author João Lourenço and Hampus Backman
	 */
	@SuppressWarnings("rawtypes")
	public void render() {
		set = displayLists.entrySet();
		iterator = set.iterator();

		while (iterator.hasNext()) {
			mentry = (Map.Entry) iterator.next();
			glCallList((int) mentry.getValue());
		}
	}

	public void render(boolean useShaders) {
		sh.useProgram(useShaders);
		render();
		sh.useProgram(false);
	}

	public void render(int layer, boolean useShaders) {
		sh.useProgram(useShaders);
		glCallList(displayLists.get(layer));
		sh.useProgram(false);
	}

	public void render(int layer) {
		glCallList(displayLists.get(layer));
	}

}
