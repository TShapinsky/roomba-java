package net.tsharp.marvin.messages.enums;

public enum BaudRate {
	BAUD_300(0),
	BAUD_600(1),
	BAUD_1200(2),
	BAUD_2400(3),
	BAUD_4800(4),
	BAUD_9600(5),
	BAUD_14400(6),
	BAUD_19200(7),
	BAUD_28800(8),
	BAUD_38400(9),
	BAUD_57600(10),
	BAUD_115200(11);
	public final int baudCode;
	BaudRate(int baudCode){
		this.baudCode = baudCode;
	}
	
	public static BaudRate getBaud(int baudCode){
		for(BaudRate rate : BaudRate.values()){
			if(rate.baudCode == baudCode){
				return rate;
			}
		}
		return null;
	}
}
