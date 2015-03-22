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

import static org.lwjgl.opengl.GL11.*;

public class RenderShape {
	
	public static void renderRect(int x, int y, int w, int h, float texX, float texY) {
		glTexCoord2f(0, 0);
		glVertex2f(x, 		y);

		glTexCoord2f(0, texY);
		glVertex2f(x, 		y + h);

		glTexCoord2f(texX, texY);
		glVertex2f(x + w, 	y + h);

		glTexCoord2f(texX, texY);
		glVertex2f(x + w, 	y + h);

		glTexCoord2f(texX, 0);
		glVertex2f(x + w,	y);

		glTexCoord2f(0, 0);
		glVertex2f(x, 		y);
	}
	
}
