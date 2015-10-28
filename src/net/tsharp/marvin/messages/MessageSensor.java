package net.tsharp.marvin.messages;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.tsharp.marvin.OpenInterfaceMessage;
import net.tsharp.marvin.RoombaCallback;
import net.tsharp.marvin.RoombaConnection;
import net.tsharp.marvin.RoombaCallback.CallbackType;
import net.tsharp.marvin.apis.sensors.Sensor;
import net.tsharp.marvin.apis.sensors.SensorCallback;

public final class MessageSensor {
	public class MessageSensorRequest extends OpenInterfaceMessage {
		public Sensor sensor;

		public MessageSensorRequest(int messageID) {
			super(messageID);
		}

		@Override
		public void write(OutputStream out) throws IOException {
			out.write(sensor.getPacketID());
		}

	}

	public class MessageSensorStream extends OpenInterfaceMessage {
		public Sensor[] sensors;

		public MessageSensorStream(int messageID) {
			super(messageID);
		}

		@Override
		public void write(OutputStream out) throws IOException {
			out.write(sensors.length);
			for (Sensor sensor : sensors) {
				out.write(sensor.getPacketID());
			}
		}
	}

	public class MessageSensorStreamRead extends OpenInterfaceMessage {
		private Map<Sensor, byte[]> values = new HashMap<Sensor, byte[]>();

		public MessageSensorStreamRead(int messageID) {
			super(messageID);
		}

		@Override
		public void read(InputStream in, RoombaConnection connection)
				throws IOException {
			ArrayList<RoombaCallback> callbacks = connection
					.getCallbacks(CallbackType.SENSORS);
			int length = in.read();
			int i = 0;
			while (i < length) {
				int packetID = in.read();
				i++;
				for (Sensor sensor : Sensor.values()) {
					if (sensor.getPacketID() == packetID) {
						byte[] value = new byte[sensor.getPacketSize()];
						in.read(value);
						if(value[0] != 0){
							System.out.println("1");
						}
						i += sensor.getPacketSize();
						if (callbacks != null) {
							for (RoombaCallback callback : callbacks) {
								((SensorCallback) callback)
										.onSensorValueRecieved(sensor, value);
							}
						}
						values.put(sensor, value);
					}
				}
			}
			in.read();
		}

	}
}
