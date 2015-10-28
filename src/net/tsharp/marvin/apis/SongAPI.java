package net.tsharp.marvin.apis;

import net.tsharp.marvin.OpenInterfaceMessage;
import net.tsharp.marvin.RoombaConnection;
import net.tsharp.marvin.apis.song.Note;
import net.tsharp.marvin.messages.Message;
import net.tsharp.marvin.messages.MessageSong;
import net.tsharp.marvin.messages.MessageSong.MessageSongPlay;
import net.tsharp.marvin.messages.MessageSong.MessageSongUpload;

public class SongAPI extends RoombaAPI{

	public SongAPI(RoombaConnection connection) {
		super(connection);
	}
	
	public void uploadSong(int id, Note[] notes){
		MessageSongUpload message = (MessageSongUpload) Message.SONG.getNewInstance();
		message.id = id;
		message.notes = notes;
		connection.sendMessage(message);
	}
	
	public void playSong(int id){
		MessageSongPlay message = (MessageSongPlay) Message.SONG_PLAY.getNewInstance();
		message.id = id;
		connection.sendMessage(message);
	}
	

}
