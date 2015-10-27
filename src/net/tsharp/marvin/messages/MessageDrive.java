package net.tsharp.marvin.messages;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

import net.tsharp.marvin.OpenInterfaceMessage;

public final class MessageDrive {
	public class MessageDriveARC extends OpenInterfaceMessage{
		/**
		 * velocity in mm/s
		 */
		public short velocity;
		/**
		 * radius in mm
		 */
		public short radius;
		public MessageDriveARC(int messageID) {
			super(messageID);
		}
		@Override
		public void read(InputStream in) throws IOException {
			int input = in.read();
			velocity = (short) (input >> 16);
			radius = (short) (input & 0xFFFF);
		}
		
		@Override
		public void write(OutputStream out) throws IOException {
			ByteBuffer buffer = ByteBuffer.allocate(4);
			buffer.putShort(velocity);
			buffer.putShort(radius);
			out.write(buffer.array());
		}	
	}
	
	public class MessageDriveDirect extends OpenInterfaceMessage{
		/**
		 * velocity in mm/s
		 */
		public short rightVelocity;
		/**
		 * velocity in mm/s
		 */
		public short leftVelocity;
		public MessageDriveDirect(int messageID) {
			super(messageID);
		}
		@Override
		public void read(InputStream in) throws IOException {
			int input = in.read();
			rightVelocity = (short) (input >> 16);
			leftVelocity = (short) (input & 0xFFFF);
		}
		
		@Override
		public void write(OutputStream out) throws IOException {
			ByteBuffer buffer = ByteBuffer.allocate(4);
			buffer.putShort(rightVelocity);
			buffer.putShort(leftVelocity);
			out.write(buffer.array());
		}	
	}
	
	public class MessageDrivePWM extends OpenInterfaceMessage{
		/**
		 * PWM value (-255 - 255)
		 */
		public short rightPWM;
		/**
		 * PWM value (-255 - 255)
		 */
		public short leftPWM;
		public MessageDrivePWM(int messageID) {
			super(messageID);
		}
		@Override
		public void read(InputStream in) throws IOException {
			int input = in.read();
			rightPWM = (short) (input >> 16);
			leftPWM = (short) (input & 0xFFFF);
		}
		
		@Override
		public void write(OutputStream out) throws IOException {
			ByteBuffer buffer = ByteBuffer.allocate(4);
			buffer.putShort(rightPWM);
			buffer.putShort(leftPWM);
			out.write(buffer.array());
		}	
	}
}
