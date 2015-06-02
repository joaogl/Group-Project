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

package group.project.unknown.multiplayer;

import group.project.unknown.multiplayer.utils.*;
import group.project.unknown.multiplayer.utils.Packets.*;

import java.io.*;

import com.esotericsoftware.kryonet.*;
import com.esotericsoftware.minlog.*;

/**
 * @author João Lourenço and Hampus Backman
 *
 */
public class ClientConnectionManager {

	UI ui;
	Client client;
	String name;

	public ClientConnectionManager() {
		Log.set(Log.LEVEL_DEBUG);

		client = new Client();
		client.start();

		// For consistency, the classes to be sent over the network are
		// registered by the same method for both the client and server.
		Packets.register(client);

		// ThreadedListener runs the listener methods on a different thread.
		client.addListener(new NetworkListener(this));

		ui = new UI();

		String host = ui.inputHost();
		try {
			client.connect(5000, host, Packets.port);
			// Server communication after connection can go here, or in Listener#connected().
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		name = ui.inputName();
		Login login = new Login();
		login.name = name;
		client.sendTCP(login);

		while (true) {
			move('w');
		}
	}

	public void move(char ch) {
		MoveCharacter msg = new MoveCharacter();
		switch (ch) {
		case 'w':
			msg.y = -1;
			break;
		case 's':
			msg.y = 1;
			break;
		case 'a':
			msg.x = -1;
			break;
		case 'd':
			msg.x = 1;
			break;
		default:
			msg = null;
		}
		if (msg != null) client.sendTCP(msg);
	}

}