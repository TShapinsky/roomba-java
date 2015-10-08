package net.tsharp.marvin.messages;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import net.tsharp.marvin.OpenInterfaceMessage;

public enum Message{
	RESET(7),
	START(128),
	BAUD_RATE(129, MessageBaudRate.class),
	MODE_SAFE(131),
	MODE_FULL(132),
	POWER(133),
	CLEAN_SPOT(134),
	CLEAN(135),
	CLEAN_MAX(136),
	SEEK_DOCK(143),
	DIGIT_LEDS_ASCII(164, MessageDigitLedASCII.class),
	STOP(173);
	
	private static Map<Integer, Message> messageMap = new HashMap<Integer, Message>();
	static{
		for(Message message: Message.values()){
			messageMap.put(message.messageID, message);
		}
	}
	
	private int messageID;
	private Class<? extends OpenInterfaceMessage> className;
	Message(int messageID){
		this.messageID = messageID;
		className = OpenInterfaceMessage.class;
	}
	
	Message(int messageID, Class<? extends OpenInterfaceMessage> className){
		this.messageID = messageID;
		this.className = className;
	}
	
	public static Message getMessage(int messageID){
		return messageMap.get(messageID);
	}
	
	public OpenInterfaceMessage getNewInstance(){
		try {
			return className.getConstructor(int.class).newInstance(messageID);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
