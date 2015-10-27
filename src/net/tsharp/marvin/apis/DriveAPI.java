package net.tsharp.marvin.apis;

import net.tsharp.marvin.RoombaConnection;
import net.tsharp.marvin.messages.Message;
import net.tsharp.marvin.messages.MessageDrive.MessageDriveARC;
import net.tsharp.marvin.messages.MessageDrive.MessageDriveDirect;
import net.tsharp.marvin.messages.MessageDrive.MessageDrivePWM;

public class DriveAPI extends RoombaAPI{

	public DriveAPI(RoombaConnection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @param velocity in mm/s
	 * @param radius in mm
	 */
	public void driveARC(int velocity, int radius){
		MessageDriveARC message = (MessageDriveARC) Message.DRIVE_ARC.getNewInstance();
		message.velocity = (short) velocity;
		message.radius = (short) radius;
		connection.sendMessage(message);
	}
	/**
	 * 
	 * @param rightVelocity in mm/s
	 * @param leftVelocity in mm/s
	 */
	public void driveDirect(int rightVelocity, int leftVelocity){
		MessageDriveDirect message = (MessageDriveDirect) Message.DRIVE_DIRECT.getNewInstance();
		message.rightVelocity = (short) rightVelocity;
		message.leftVelocity = (short) leftVelocity;
		connection.sendMessage(message);
	}
	
	/**
	 * 
	 * @param rightPWN PWM value (-255 - 255)
	 * @param leftPWM PWM value (-255 - 255)
	 */
	public void drivePWM(int rightPWN, int leftPWM){
		MessageDrivePWM message = (MessageDrivePWM) Message.DRIVE_PWM.getNewInstance();
		message.rightPWM = (short) rightPWN;
		message.leftPWM = (short) leftPWM;
		connection.sendMessage(message);
	}
	
	public void stop(){
		MessageDriveDirect message = (MessageDriveDirect) Message.DRIVE_DIRECT.getNewInstance();
		connection.sendMessage(message);
	}

}
