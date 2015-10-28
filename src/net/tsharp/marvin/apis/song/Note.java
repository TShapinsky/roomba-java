package net.tsharp.marvin.apis.song;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

public class Note {
	public static int WHOLE = 128;
	public static int HALF = 64;
	public static int QUARTER = 32;
	public static int EIGTH = 16;
	public static int SIXTEENTH = 8;
	
	public enum Pitch {
		C(0), Cs(1), D(2), Ds(3), E(4), F(5), Fs(6), G(7), Gs(8), A(9), As(10), B(11);

		private int step;
		Pitch(int step) {
			this.step = step;
		}
		
		public byte getIdentifier(int octave) {
			return (byte) (12 + 12 * octave + step);
		}
	}
	
	private Pitch pitch;
	private int duration;
	private int octave;
	public Note(Pitch pitch, int octave, int duration){
		this.pitch = pitch;
		this.octave = octave;
		this.duration = duration;
	}
	
	public void write(OutputStream out) throws IOException{
		out.write(pitch.getIdentifier(octave));
		out.write(duration);
	}
}