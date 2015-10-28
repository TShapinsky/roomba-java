import gnu.io.PortInUseException;
import gnu.io.UnsupportedCommOperationException;

import java.io.IOException;

import net.tsharp.marvin.RoombaConnection;
import net.tsharp.marvin.apis.DisplayAPI;
import net.tsharp.marvin.apis.DriveAPI;
import net.tsharp.marvin.apis.ModeAPI;
import net.tsharp.marvin.apis.ModeAPI.Mode;
import net.tsharp.marvin.apis.SongAPI;
import net.tsharp.marvin.apis.song.Note;
import net.tsharp.marvin.apis.song.SongBuilder;
import static net.tsharp.marvin.apis.song.Note.*;
import static net.tsharp.marvin.apis.song.Note.Pitch.*;
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
			Thread.sleep(200);
			drive.driveARC(100, 300);
			final SongAPI song = new SongAPI(connection);
			SongBuilder builder = new SongBuilder(120);
			Note[] notes = builder.append(G, HALF).append(F, HALF).append(Ds, HALF).append(D,HALF).append(G, HALF).append(F, HALF).append(Ds, HALF).append(D, HALF).build();
			song.uploadSong(0, notes);
			builder = new SongBuilder(120);
			notes = builder.append(D, 3,EIGTH).append(G, EIGTH).append(As, SIXTEENTH).append(F, (int)(EIGTH*1.5)).append(G, QUARTER)
					.append(As, 3, SIXTEENTH).append(D, EIGTH).append(C, SIXTEENTH)
					.append(D, 3,EIGTH).append(C,5, EIGTH).append(As, SIXTEENTH).append(F, (int)(EIGTH*1.5)).append(G, QUARTER)
					.append(As, 3, SIXTEENTH).append(D, EIGTH).append(C, SIXTEENTH).build();
			final long songDuration = builder.getSongDurationMillis();
			song.uploadSong(1, notes);
			song.playSong(0);
			class SongThread extends Thread{
				private boolean stopped;
				@Override
				public void run() {
					while(!stopped){
						song.playSong(1);
						try {
							Thread.sleep(songDuration);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				public void setStopped(){
					stopped = true;
				}
			};
			new SongThread().start();
			Thread.sleep(20000);
			drive.stop();
			mode.setMode(Mode.PASSIVE);
			
		} catch (UnsupportedCommOperationException | PortInUseException
				| IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
