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

import group.project.unknown.*;

import java.awt.*;

import org.lwjgl.input.*;
import org.newdawn.slick.opengl.*;

import static org.lwjgl.opengl.GL11.*;

public class Button {

	private Rectangle mouseRect;
	private Rectangle rect;

	private Texture tex;

	private float r;
	private float g;
	private float b;

	public Button(int x, int y, int w, int h, String texUrl) {
		this.mouseRect = new Rectangle(0, 0, 1, 1);
		this.rect = new Rectangle(x, y, w, h);

		this.tex = Loader.loadTexture(texUrl);
	}

	public boolean hover() {
		mouseRect.move(Mouse.getX(), Registry.getScreenHeight() - Mouse.getY());
		if (rect.intersects(mouseRect)) return true;
		return false;
	}

	public boolean clicked() {
		if (hover() && Mouse.isButtonDown(0)) return true;
		return false;
	}

	public void render() {
		glColor3f(r, g, b);
		glBindTexture(GL_TEXTURE_2D, tex.getTextureID());
		glBegin(GL_TRIANGLES);
		{
			RenderShape.renderRect((int) rect.getX(), (int) rect.getY(), (int) rect.getWidth(), (int) rect.getHeight(), tex.getWidth(), tex.getHeight());
		}
		glEnd();

		glBindTexture(GL_TEXTURE_2D, 0);
	}

	public void setColor(float r, float g, float b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}

}
