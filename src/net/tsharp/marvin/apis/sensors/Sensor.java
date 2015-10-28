package net.tsharp.marvin.apis.sensors;

public enum Sensor {
	BUMPS_WHEELDROPS(7,1),
	BUTTONS(18,1);
	
	private int packetID;
	private int packetSize;
	Sensor(int packetID, int packetSize){
		this.packetID = packetID;
		this.packetSize = packetSize;
	}
	
	public int getPacketID(){
		return packetID;
	}
	
	public int getPacketSize(){
		return packetSize;
	}
}
