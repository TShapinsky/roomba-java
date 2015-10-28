package net.tsharp.marvin.apis;

import java.util.ArrayList;

import net.tsharp.marvin.RoombaConnection;
import net.tsharp.marvin.apis.sensors.Sensor;
import net.tsharp.marvin.apis.sensors.SensorCallback;
import net.tsharp.marvin.messages.Message;
import net.tsharp.marvin.messages.MessageSensor.MessageSensorStream;

public class SensorsAPI extends RoombaAPI{

	private ArrayList<Sensor> sensorStream = new ArrayList<Sensor>();
	
	public SensorsAPI(RoombaConnection connection) {
		super(connection);
	}
	
	public void addSensorToStream(Sensor sensor){
		if(!sensorStream.contains(sensor)){
			sensorStream.add(sensor);
			Sensor[] sensors = new Sensor[sensorStream.size()];
			for(int i = 0; i < sensorStream.size(); i++){
				sensors[i] = sensorStream.get(i);
			}
			MessageSensorStream message = (MessageSensorStream) Message.STREAM.getNewInstance();
			message.sensors = sensors;
			connection.sendMessage(message);
		}
	}
	
	public void registerSensorCallback(SensorCallback callback){
		connection.registerCallback(callback);
	}
	

}
