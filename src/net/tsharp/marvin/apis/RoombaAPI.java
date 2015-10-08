package net.tsharp.marvin.apis;

import net.tsharp.marvin.RoombaConnection;

class RoombaAPI {
	protected RoombaConnection connection;
	protected RoombaAPI(RoombaConnection connection){
		this.connection = connection;
	}
}
