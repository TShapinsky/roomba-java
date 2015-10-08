package net.tsharp.marvin.apis;

import net.tsharp.marvin.RoombaConnection;
import net.tsharp.marvin.messages.Message;

public class ModeAPI extends RoombaAPI {

	public ModeAPI(RoombaConnection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}
	
	public void setMode(Mode mode){
		connection.sendMessage(mode.message.getNewInstance());
	}
	
	public enum Mode{
		PASSIVE(Message.START),
		SAFE(Message.MODE_SAFE),
		FULL(Message.MODE_FULL);
		
		public final Message message;
		Mode(Message message){
			this.message = message;
		}
		
		
	}
	
	

}
