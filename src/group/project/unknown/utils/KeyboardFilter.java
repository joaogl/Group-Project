/*
 * Copyright 2014 Joao Lourenco and Hampus Backman
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

import group.project.unknown.timer.*;

import java.util.*;

import org.lwjgl.input.*;

/**
 * The KeyboardFilter class.
 * 
 * @author Joao Lourenco
 */
public class KeyboardFilter {

	/** Holds which keys is currently on cooldown. */
	static List<Integer> blackList = new ArrayList<Integer>();

	/**
	 * Checks if key is down and adds cooldown.
	 * 
	 * @param key , Specified key.
	 * @return Boolean containing the information on if the key is pressed or not.
	 * @author João Lourenço and Hampus Backman
	 */
	public static boolean isKeyDown(int key) {
		if (Keyboard.isKeyDown(key) && !blackList.contains(key)) {
			blackList.add(key);

			new ThreadedTimer("KeyCoolDown-" + key, 500, 1, new TimerResult(blackList) {
				@SuppressWarnings("unchecked")
				@Override
				public void timerCall(String caller) {
					int id = getID(Integer.parseInt(caller.split("-")[1]));
					((List<Integer>) object).remove(id);
				}

				@SuppressWarnings("unchecked")
				public int getID(int id) {
					for (int i = 0; i < ((List<Integer>) object).size(); i++)
						if (((List<Integer>) object).get(i) == id) return i;

					return 0;
				}
			});
			return true;
		}
		return false;
	}

}