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

package group.project.unknown.multiplayer.utils;

import group.project.unknown.multiplayer.utils.Character;
import group.project.unknown.multiplayer.utils.Packets.*;

import java.util.*;

import javax.swing.*;

/**
 * @author João Lourenço and Hampus Backman
 *
 */
public class UI {

	HashMap<Integer, Character> characters = new HashMap<Integer, Character>();

	public String inputHost() {
		String input = (String) JOptionPane.showInputDialog(null, "Host:", "Connect to server", JOptionPane.QUESTION_MESSAGE, null, null, "localhost");
		if (input == null || input.trim().length() == 0) System.exit(1);
		return input.trim();
	}

	public String inputName() {
		String input = (String) JOptionPane.showInputDialog(null, "Name:", "Connect to server", JOptionPane.QUESTION_MESSAGE, null, null, "Test");
		if (input == null || input.trim().length() == 0) System.exit(1);
		return input.trim();
	}

	public String inputOtherStuff() {
		String input = (String) JOptionPane.showInputDialog(null, "Other Stuff:", "Create account", JOptionPane.QUESTION_MESSAGE, null, null, "other stuff");
		if (input == null || input.trim().length() == 0) System.exit(1);
		return input.trim();
	}

	public void addCharacter(Character character) {
		characters.put(character.id, character);
		System.out.println(character.name + " added at " + character.x + ", " + character.y);
	}

	public void updateCharacter(UpdateCharacter msg) {
		Character character = characters.get(msg.id);
		if (character == null) return;
		character.x = msg.x;
		character.y = msg.y;
		System.out.println(character.name + " moved to " + character.x + ", " + character.y);
	}

	public void removeCharacter(int id) {
		Character character = characters.remove(id);
		if (character != null) System.out.println(character.name + " removed");
	}

}