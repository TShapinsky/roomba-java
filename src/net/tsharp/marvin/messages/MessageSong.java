package net.tsharp.marvin.messages;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

import net.tsharp.marvin.OpenInterfaceMessage;
import net.tsharp.marvin.apis.song.Note;

public final class MessageSong{
	public class MessageSongUpload extends OpenInterfaceMessage{
	public int id;
	public Note[] notes;
	
	public MessageSongUpload(int messageID) {
		super(messageID);
		// TODO Auto-generated constructor stub
	}
	
	public void write(OutputStream out) throws IOException{
		ByteBuffer songInfo = ByteBuffer.allocate(2);
		songInfo.put((byte) id);
		songInfo.put((byte) notes.length);
		out.write(songInfo.array());
		for(Note note : notes){
			note.write(out);
		}
	}
	}
	
	public class MessageSongPlay extends OpenInterfaceMessage{
		public int id;
		public MessageSongPlay(int messageID) {
			super(messageID);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public void write(OutputStream out) throws IOException {
			out.write(id);
		}
	}
}
