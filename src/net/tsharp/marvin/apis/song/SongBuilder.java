package net.tsharp.marvin.apis.song;

import java.util.ArrayList;

import net.tsharp.marvin.apis.song.Note.Pitch;

public class SongBuilder {
	private ArrayList<Note> notes = new ArrayList<Note>();
	
	private int tempo = 120;
	private long songDuration = 0;
	public SongBuilder(int tempo){
		this.tempo = tempo;
	}
	
	public SongBuilder(){
		
	}
	
	public SongBuilder append(Pitch pitch, int octave, int duration){
		duration = (int)(duration * (120/(double)tempo));
		notes.add(new Note(pitch, octave, duration));
		songDuration += (duration * 1000 / 64);
		return this;
	}
	
	public SongBuilder append(Pitch pitch, int duration){
		return append(pitch, 4, duration);
	}
	
	public Note[] build(){
		Note[] notes = new Note[this.notes.size()];
		for(int i = 0; i < this.notes.size(); i++){
			notes[i] = this.notes.get(i);
		}
		return notes;
	}
	
	public long getSongDurationMillis(){
		return songDuration;
	}
}
