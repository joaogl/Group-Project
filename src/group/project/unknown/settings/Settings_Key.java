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

package group.project.unknown.settings;

/**
 * Settings_Key class
 * 
 * @author João Lourenço and Hampus Backman
 *
 */
public class Settings_Key {

	/** The name and value of the setting. */
	private String name, value;

	/**
	 * The Settings_Key constructor.
	 * 
	 * @param name , The name of the setting.
	 * @param value , The value of the setting.
	 * @author João Lourenço and Hampus Backman
	 */
	public Settings_Key(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	/**
	 * Returns setting name.
	 * 
	 * @return Returns a string with the settings name.
	 * @author João Lourenço and Hampus Backman
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the setting name.
	 * 
	 * @param name , The new setting name.
	 * @author João Lourenço and Hampus Backman
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the value of the setting.
	 * 
	 * @return Returns a string with the
	 * @author João Lourenço and Hampus Backman
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the value of the setting.
	 * 
	 * @param value , Value we want to set.
	 * @author João Lourenço and Hampus Backman
	 */
	public void setValue(String value) {
		this.value = value;
	}

}