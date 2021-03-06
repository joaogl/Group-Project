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

package group.project.unknown.level;

import group.project.unknown.gamestates.*;

public class Level1 extends GameState {

	public Level level;

	public Level1(GameStateManager gsm) {
		this.gsm = gsm;
	}

	public void init() {
		level = new Level("level1");
		level.init();
	}

	/**
	 * Ticks the Level1State.
	 * 
	 * @author Jo�o Louren�o and Hampus Backman
	 */
	public void tick() {
		level.tick();
	}

	/**
	 * Updates the Level1State.
	 * 
	 * @author Jo�o Louren�o and Hampus Backman
	 */
	public void update(float delta) {
		level.update(delta);
	}

	/**
	 * Renders the Level1State.
	 * 
	 * @author Jo�o Louren�o and Hampus Backman
	 */
	public void render() {
		level.render();
	}

	/**
	 * Cleans up everything in Level1State.
	 * 
	 * @author Jo�o Louren�o and Hampus Backman
	 */
	public void cleanUp() {
		level.cleanUp();
	}

}
