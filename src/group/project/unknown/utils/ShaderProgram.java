package group.project.unknown.utils;

import java.io.*;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;

public class ShaderProgram {

	/** Holders for shaders. */
	private int shaderProgram = glCreateProgram();
	private int vertexShader = glCreateShader(GL_VERTEX_SHADER);
	private int fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);

	private String vert;
	private String frag;

	private boolean useVert = false;
	private boolean useFrag = false;

	public static int VERT = 1;
	public static int FRAG = 2;

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

		useVert = true;
		useFrag = true;

		init();
	}

	public ShaderProgram(String VertOrFrag, int type) {
		if (type == ShaderProgram.VERT) useVert = true;
		if (type == ShaderProgram.FRAG) useFrag = true;
		
		this.vert = VertOrFrag;
		this.frag = VertOrFrag;
		
		init();
		
	}

	/**
	 * Inits the shaders.
	 * 
	 * @author João Lourenço and Hampus Backman
	 */
	@SuppressWarnings("deprecation")
	private void init() {
		StringBuilder vertexShaderSource = new StringBuilder();
		StringBuilder fragmentShaderSource = new StringBuilder();

		if (useVert) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(vert));
				String line;
				while ((line = reader.readLine()) != null) {
					vertexShaderSource.append(line).append('\n');
				}
				reader.close();
			} catch (IOException e) {
				useVert = false;
				System.err.println("Could not load Vertex Shader! " + vert);
			}

			glShaderSource(vertexShader, vertexShaderSource);
			glCompileShader(vertexShader);
			if (glGetShader(vertexShader, GL_COMPILE_STATUS) == GL_FALSE) {
				System.err.println("Vertex shader wasn't able to be compiled.");
			}
		}

		if (useFrag) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(frag));
				String line;
				while ((line = reader.readLine()) != null) {
					fragmentShaderSource.append(line).append('\n');
				}
				reader.close();
			} catch (IOException e) {
				useFrag = false;
				System.err.println("Could not load Fragment Shader! " + frag);
			}

			glShaderSource(fragmentShader, fragmentShaderSource);
			glCompileShader(fragmentShader);
			if (glGetShader(fragmentShader, GL_COMPILE_STATUS) == GL_FALSE) {
				System.err.println("Fragment shader wasn't able to be compiled.");
			}
		}
	}

	/**
	 * Attaches the shaders.
	 * 
	 * @author João Lourenço and Hampus Backman
	 */
	public void attach() {
		if (useVert) glAttachShader(shaderProgram, vertexShader);
		if (useFrag) glAttachShader(shaderProgram, fragmentShader);
		glLinkProgram(shaderProgram);
		glValidateProgram(shaderProgram);
	}

	public void setUniform3f(String name, float x, float y, float z) {
		glUniform3f(glGetUniformLocation(this.getProgram(), name), x, y, z);
	}
	
	public void setUniform2f(String name, float x, float y) {
		glUniform2f(glGetUniformLocation(this.getProgram(), name), x, y);
	}
	
	public void setUniform1f(String name, float x) {
		glUniform1f(glGetUniformLocation(this.getProgram(), name), x);
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

	/**
	 * Getter for shaderProgram.
	 * 
	 * @return
	 * @author João Lourenço and Hampus Backman
	 */
	public int getProgram() {
		return shaderProgram;
	}

}