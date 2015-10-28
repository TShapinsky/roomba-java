package net.tsharp.marvin;

public abstract class RoombaCallback {
	public enum CallbackType{
		SENSORS;
	}
	
	public abstract CallbackType getCallbackType();
}
