/*
 * Copyright 2014 Jo�o Louren�o and Hampus Backman.
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

public class AABB {
	
	public float x0, x1;
	public float y0, y1;
	
	public float width, height;
	
	public AABB(float x0, float y0, float width, float height) {
		this.x0 = x0;
		this.x1 = x0 + width;
		this.y0 = y0;
		this.y1 = y0 + height;
		
		this.width = width;
		this.height = height;
	}
	
    public boolean intersects(AABB b) {
    	if (x0 + width/2 	> b.x0 &&
    		x0 - width/2 	< b.x1 &&
    		y0 + height/2	> b.y0 &&
    		y0 - height/2	< b.y1) return true;
    	
    	return false;
    }
    
    public void move(float x, float y) {
    	x0 = x;
    	y0 = y;
    	x1 = x + width;
    	y1 = y + height;
    }
	
}
