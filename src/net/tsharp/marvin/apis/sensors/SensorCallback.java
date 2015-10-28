package net.tsharp.marvin.apis.sensors;

import net.tsharp.marvin.RoombaCallback;

public abstract class SensorCallback extends RoombaCallback{
	
	@Override
	public final CallbackType getCallbackType() {
		return CallbackType.SENSORS;
	}
	public abstract void onSensorValueRecieved(Sensor sensor, byte[] value);
}
