package net.tsharp.marvin;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.TooManyListenersException;

import sun.print.resources.serviceui;
import net.tsharp.marvin.RoombaCallback.CallbackType;
import net.tsharp.marvin.messages.Message;

public class RoombaConnection implements SerialPortEventListener {
	private static final int TIME_OUT = 2000;

	private final RoombaConnection theRoombaConnection;
	private int baudrate;
	private CommPortIdentifier portId;
	private InputStream in;
	private OutputStream out;
	private SerialPort serialPort;
	private Map<RoombaCallback.CallbackType, ArrayList<RoombaCallback>> callbacks = new HashMap<RoombaCallback.CallbackType, ArrayList<RoombaCallback>>();

	public RoombaConnection(){
		theRoombaConnection = this;
	}
	
	public boolean initialize(String port, int baudrate) {
		this.baudrate = baudrate;
		CommPortIdentifier portId = null;
		System.setProperty("gnu.io.rxtx.SerialPorts", port);
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum
					.nextElement();
			if (currPortId.getName().equals(port)) {
				System.out.println(currPortId.getName());
				portId = currPortId;
				break;
			}
		}
		if (portId != null) {
			this.portId = portId;
			return true;
		}
		return false;
	}

	public void connect() throws UnsupportedCommOperationException, PortInUseException, IOException {
		if(portId == null){
			System.err.println("No serial port found");
			return;
		}
		// open serial port, and use class name for the appName.
		serialPort = (SerialPort) portId.open(this.getClass().getName(),
				TIME_OUT);

		// set port parameters
		serialPort.setSerialPortParams(baudrate, SerialPort.DATABITS_8,
				SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
		serialPort.setInputBufferSize(8);
		out = serialPort.getOutputStream();
		in = serialPort.getInputStream();
		try {
			serialPort.addEventListener(this);
			serialPort.notifyOnDataAvailable(true);
		} catch (TooManyListenersException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void serialEvent(SerialPortEvent event) {
		switch (event.getEventType()) {
		case SerialPortEvent.DATA_AVAILABLE:
			try {
				final int id = in.read();
				new Thread(){
					public void run() {
						Message messageEnum = Message.getMessage(id);
						if(messageEnum != null){
							OpenInterfaceMessage message = messageEnum.getNewInstance();
							try {
								message.read(in, theRoombaConnection);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					};
				}.start();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
		
	}
	
	public void registerCallback(RoombaCallback callback){
		if(callbacks.get(callback.getCallbackType()) == null){
			callbacks.put(callback.getCallbackType(), new ArrayList<RoombaCallback>());
		}
		callbacks.get(callback.getCallbackType()).add(callback);
	}
	
	public ArrayList<RoombaCallback> getCallbacks(CallbackType type){
		return callbacks.get(type);
	}
	
	public void sendMessage(OpenInterfaceMessage message){
		if(portId == null){
			System.err.println("No serial port found");
			return;
		}
		try {
			out.write(message.getID());
			message.write(out);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
