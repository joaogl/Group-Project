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

import group.project.unknown.level.entities.*;
import group.project.unknown.utils.*;

import java.util.*;

import org.lwjgl.util.vector.*;

public class Spawner {

	private Level level;

	private ArrayList<Particle> particles = new ArrayList<Particle>();

	public Spawner(Level level) {
		this.level = level;
	}

	public void tick() {
		for (Particle particle : particles) {
			particle.tick();
		}

		for (int i = 0; i < particles.size(); i++) {
			particles.get(i).tick();
			if (particles.get(i).remove) particles.remove(i);
		}
	}

	public void update(float delta) {
		for (Particle particle : particles) {
			particle.update(delta);
		}
	}

	public void render() {
		for (Particle particle : particles) {
			particle.render();
		}
	}

	/**
	 * Spawns a particle.
	 * 
	 * @param amt
	 *            , Number of particles.
	 * @param position
	 *            , Position of particle.
	 * @param color
	 *            , Particle color.
	 * @param speed
	 *            , Particle speed.
	 * @param life
	 *            , Lifespan.
	 * @param angle
	 *            , Angle defined in gradients.
	 * @param spread
	 *            , Particle spread.
	 * @author João Lourenço and Hampus Backman
	 */
	public void spawnPartciles(float amt, Vector2f position, Vector3f color, float speed, float life, float angle, float spread) {
		for (int i = 0; i < amt; i++) {
			float newAngle = (float) randInt((int) (angle - spread), (int) (angle + spread));
			particles.add(new Particle(this.level, new Vector2f(position), new Vector3f(color.x, color.y, color.z), speed, life, newAngle));
		}
	}

	public static int randInt(int min, int max) {
		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;
	}
}
