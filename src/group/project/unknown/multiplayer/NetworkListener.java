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

import group.project.unknown.multiplayer.utils.Packets.*;

import com.esotericsoftware.kryonet.*;

/**
 * @author João Lourenço and Hampus Backman
 *
 */
public class NetworkListener extends Listener {

	private ClientConnectionManager clt;
	private Client client;

	public NetworkListener(ClientConnectionManager clt) {
		this.clt = clt;
		this.client = clt.client;
	}

	public void connected(Connection connection) {
	}

	public void received(Connection connection, Object object) {
		if (object instanceof RegistrationRequired) {
			Register register = new Register();
			register.name = clt.name;
			register.otherStuff = clt.ui.inputOtherStuff();
			client.sendTCP(register);
		}

		if (object instanceof AddCharacter) {
			AddCharacter msg = (AddCharacter) object;
			clt.ui.addCharacter(msg.character);
			return;
		}

		if (object instanceof UpdateCharacter) {
			clt.ui.updateCharacter((UpdateCharacter) object);
			return;
		}

		if (object instanceof RemoveCharacter) {
			RemoveCharacter msg = (RemoveCharacter) object;
			clt.ui.removeCharacter(msg.id);
			return;
		}
	}

	public void disconnected(Connection connection) {
		System.exit(0);
	}

}