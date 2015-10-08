package net.tsharp.marvin.messages;

import java.io.IOException;
import java.io.OutputStream;

import net.tsharp.marvin.OpenInterfaceMessage;

public class MessageDigitLedASCII extends OpenInterfaceMessage {
	public byte[] digits = new byte[4];
	
	public MessageDigitLedASCII(int messageID) {
		super(messageID);
		// TODO Auto-generated constructor stub
	}
	@Override
	public void write(OutputStream out) throws IOException {
		String message = (new String(digits)+"    ").substring(0, 4);
		out.write(message.getBytes());
	}


}
