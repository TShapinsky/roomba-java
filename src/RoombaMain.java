import gnu.io.PortInUseException;
import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;

import net.tsharp.marvin.RoombaConnection;
import net.tsharp.marvin.apis.DisplayAPI;
import net.tsharp.marvin.apis.DriveAPI;
import net.tsharp.marvin.apis.ModeAPI;
import net.tsharp.marvin.apis.ModeAPI.Mode;


public class RoombaMain {

	public static void main(String[] args) {
		RoombaConnection connection = new RoombaConnection();
		connection.initialize("/dev/ttyAMA0", 115200);
		try {
			connection.connect();
			DisplayAPI display = new DisplayAPI(connection);
			ModeAPI mode = new ModeAPI(connection);
			mode.setMode(Mode.SAFE);
//			display.printASCII("Kill");
//			Thread.sleep(5000);
			DriveAPI drive = new DriveAPI(connection);
			drive.driveDirect(100, 100);
			Thread.sleep(2000);
			drive.stop();
			mode.setMode(Mode.PASSIVE);
			
		} catch (UnsupportedCommOperationException | PortInUseException
				| IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
