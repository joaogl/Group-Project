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

package group.project.unknown;

import group.project.unknown.settings.Settings_Key;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds all the registries of the game.
 * 
 * @author João Lourenço and Hampus Backman
 */
public class Registry {

	/** Instance of the Main class. */
	private static Main main;
	/** List of the settings. */
	private static List<Settings_Key> settings = new ArrayList<Settings_Key>();

	/**
	 * Registrers the Main class.
	 * 
	 * @param m , Instance of the Main.
	 * @author João Lourenço and Hampus Backman
	 */
	public static void registerMainClass(Main m) {
		main = m;
	}

	/**
	 * Registers a screen dimension.
	 * 
	 * @param w , Screen Width.
	 * @param h , Screen Height.
	 * @author João Lourenço and Hampus Backman
	 */
	public static void registerScreen(int w, int h) {
		registerSetting("screen_width", String.valueOf(w));
		registerSetting("screen_height", String.valueOf(h));
	}

	/**
	 * Returns Screen Width.
	 * 
	 * @return Integer with the screen width.
	 * @author João Lourenço and Hampus Backman
	 */
	public static int getScreenWidth() {
		return Integer.parseInt(getSetting("screen_width"));
	}

	/**
	 * Returns Screen Height.
	 * 
	 * @return Integer with the screen height.
	 * @author João Lourenço and Hampus Backman
	 */
	public static int getScreenHeight() {
		return Integer.parseInt(getSetting("screen_height"));
	}

	/**
	 * Register new setting.
	 * 
	 * @param name , Name of Register.
	 * @param value , Value of Register.
	 * @author João Lourenço and Hampus Backman
	 */
	public static void registerSetting(String name, String value) {
		for (Settings_Key k : settings)
			if (k.getName().equalsIgnoreCase(name)) {
				k.setValue(value);
				return;
			}
		settings.add(new Settings_Key(name, value));
	}

	/**
	 * Returns setting by name.
	 * 
	 * @param name , Setting name
	 * @return Returns a string with the settings value.
	 * @author João Lourenço and Hampus Backman
	 */
	public static String getSetting(String name) {
		for (Settings_Key k : settings)
			if (k.getName().equalsIgnoreCase(name)) return k.getValue().toString();
		return null;
	}

	/**
	 * Returns the settings List.
	 * 
	 * @return Returns an ArrayList with all the settings.
	 * @author João Lourenço and Hampus Backman
	 */
	public static List<Settings_Key> getSettings() {
		return settings;
	}

	/**
	 * Returns the main class.
	 * 
	 * @return Returns an Instance of the Main class.
	 * @author João Lourenço and Hampus Backman
	 */
	public static Main getMainClass() {
		return main;
	}

	/**
	 * Cleans up the settings
	 * 
	 * @author João Lourenço and Hampus Backman
	 */
	public static void cleanUp() {
		settings.clear();
	}

}