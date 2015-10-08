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
import java.util.Enumeration;
import java.util.TooManyListenersException;

import sun.print.resources.serviceui;
import net.tsharp.marvin.messages.Message;

public class RoombaConnection implements SerialPortEventListener {
	private static final int TIME_OUT = 2000;

	private int baudrate;
	private CommPortIdentifier portId;
	private InputStream in;
	private OutputStream out;
	private SerialPort serialPort;

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
				Message messageEnum = Message.getMessage(in.read());
				if(messageEnum != null){
					OpenInterfaceMessage message = messageEnum.getNewInstance();
					message.read(in);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
		
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
