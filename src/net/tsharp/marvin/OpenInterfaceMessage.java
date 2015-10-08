package net.tsharp.marvin;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class OpenInterfaceMessage {
	
	private final int messageID;
	public OpenInterfaceMessage(int messageID){
		this.messageID = messageID;
	}
	
	public void read(InputStream in) throws IOException{
	}
	public void write(OutputStream out) throws IOException{
	};
	public int getID(){
		return messageID;
	}
}
