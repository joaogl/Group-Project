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

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

import group.project.unknown.gamestates.*;

import java.io.*;

public class ShaderProgram {

	/** Holders for shaders. */
	private int shaderProgram = glCreateProgram();
	private int vertexShader = glCreateShader(GL_VERTEX_SHADER);
	private int fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);

	private String vert;
	private String frag;

	/**
	 * ShaderProgram constructor.
	 * 
	 * @param vert
	 *            , Vertex shader location.
	 * @param frag
	 *            , Fragment shader location.
	 * @author João Lourenço and Hampus Backman
	 */
	public ShaderProgram(String vert, String frag) {
		this.vert = vert;
		this.frag = frag;

		init();
	}

	/**
	 * Inits the shaders.
	 * 
	 * @author João Lourenço and Hampus Backman
	 */
	private void init() {
		StringBuilder vertexShaderSource = new StringBuilder();
		StringBuilder fragmentShaderSource = new StringBuilder();

		try {
			BufferedReader reader = new BufferedReader(new FileReader(vert));
			String line;
			while ((line = reader.readLine()) != null) {
				vertexShaderSource.append(line).append('\n');
			}
		} catch (IOException e) {
			System.err.println("Could not load Vertex Shader!");
		}

		try {
			BufferedReader reader = new BufferedReader(new FileReader(frag));
			String line;
			while ((line = reader.readLine()) != null) {
				fragmentShaderSource.append(line).append('\n');
			}
		} catch (IOException e) {
			System.err.println("Could not load Fragment Shader!");
		}

		glShaderSource(vertexShader, vertexShaderSource);
		glCompileShader(vertexShader);
		if (glGetShader(vertexShader, GL_COMPILE_STATUS) == GL_FALSE) {
			System.err.println("Vertex shader wasn't able to be compiled.");
		}

		glShaderSource(fragmentShader, fragmentShaderSource);
		glCompileShader(fragmentShader);
		if (glGetShader(fragmentShader, GL_COMPILE_STATUS) == GL_FALSE) {
			System.err.println("Fragment shader wasn't able to be compiled.");
		}
	}

	/**
	 * Attaches the shaders.
	 * 
	 * @author João Lourenço and Hampus Backman
	 */
	public void attach() {
		glAttachShader(shaderProgram, vertexShader);
		glAttachShader(shaderProgram, fragmentShader);
		glLinkProgram(shaderProgram);
		glValidateProgram(shaderProgram);
	}

	/**
	 * Uses the program.
	 * 
	 * @param use
	 *            , If we want to use it or not.
	 * @author João Lourenço and Hampus Backman
	 */
	public void useProgram(boolean use) {
		if (use) glUseProgram(shaderProgram);
		else glUseProgram(0);
	}

	/**
	 * Deletes shaders.
	 * 
	 * @author João Lourenço and Hampus Backman
	 */
	public void deleteShaders() {
		glDeleteProgram(shaderProgram);
		glDeleteShader(vertexShader);
		glDeleteShader(fragmentShader);
	}

}
