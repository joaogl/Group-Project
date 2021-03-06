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

package group.project.unknown.multiplayer.utils;

import com.esotericsoftware.minlog.*;

/**
 * Class that simplifies the System.out.println();
 * 
 * @author Jo�o Louren�o and Hampus Backman
 */
public class Out {

	/**
	 * Prints out a message.
	 * 
	 * @param message
	 *            , The specified message.
	 * @author Jo�o Louren�o and Hampus Backman
	 */
	public static void print(Object message) {
		Log.info((String) message);
		// System.out.println(message);
	}

}