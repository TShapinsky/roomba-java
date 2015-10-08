package net.tsharp.marvin.messages;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import net.tsharp.marvin.OpenInterfaceMessage;
import net.tsharp.marvin.messages.enums.BaudRate;

public class MessageBaudRate extends OpenInterfaceMessage{
	public MessageBaudRate(int messageID) {
		super(messageID);
	}

	public BaudRate rate;
	@Override
	public void read(InputStream in) throws IOException {
		rate = BaudRate.getBaud(in.read());
		
	}

	@Override
	public void write(OutputStream out) throws IOException {
		out.write(rate.baudCode);
	}

}
