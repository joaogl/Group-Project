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

package group.project.unknown.settings;

import group.project.unknown.Registry;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Class to manage the settings.
 * 
 * @author João Lourenço and Hampus Backman
 *
 */
public class Settings {

	/** Class path for the settings folder. */
	static String classPath = Settings.class.getProtectionDomain().getCodeSource().getLocation().toString();

	/**
	 * Method to load the default settings.
	 * 
	 * @author João Lourenço and Hampus Backman
	 */
	public static void defaultSettings() {
		Registry.registerScreen(800, 600);
		Registry.registerSetting("screen_mode", "windowed"); // Windowed | Fullscreen | Windowed Fullscreen
		Registry.registerSetting("vsync", "false");
		Registry.registerSetting("fps_lock", "120");
	}

	/**
	 * Method to load the settings from the file.
	 * 
	 * @author João Lourenço and Hampus Backman
	 */
	public static void loadSettings() {
		System.out.println("Loading settings from: " + classPath);
		try {
			Scanner sc = new Scanner(new BufferedReader(new FileReader("data/settings.conf")));
			while (sc.hasNextLine()) {
				String[] data = sc.nextLine().split("=");
				Registry.registerSetting(data[0].toString(), data[1]);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("Settings not found. Using default settings.");
			defaultSettings();
		}
	}

	/**
	 * Method to save the settings to a file.
	 * 
	 * @author João Lourenço and Hampus Backman
	 */
	public static void saveSettings() {
		File f = new File("data/");
		try {
			if (!f.exists()) f.mkdir();
			f = new File("data/settings.conf");
			if (f.exists()) f.delete();

			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("data/settings.conf", true)));
			for (Settings_Key s : Registry.getSettings())
				out.println(s.getName() + "=" + s.getValue());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}