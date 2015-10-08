package net.tsharp.marvin.apis;

import net.tsharp.marvin.RoombaConnection;
import net.tsharp.marvin.messages.Message;
import net.tsharp.marvin.messages.MessageDigitLedASCII;

public class DisplayAPI extends RoombaAPI{
	public DisplayAPI(RoombaConnection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	public void printASCII(String text){
		MessageDigitLedASCII message = (MessageDigitLedASCII) Message.DIGIT_LEDS_ASCII.getNewInstance();
		for(int i = 0; i < message.digits.length; i++){
			message.digits[i] = (byte) text.charAt(i);
		}
		connection.sendMessage(message);
	}
}
