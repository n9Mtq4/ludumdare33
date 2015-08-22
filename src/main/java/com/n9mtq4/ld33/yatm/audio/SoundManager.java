/*
 * NOTE: This is added by intellij IDE. Disregard this copyright if there is another copyright later in the file.
 * Copyright (C) 2015  Will (n9Mtq4) Bresnahan
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.n9mtq4.ld33.yatm.audio;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Vector;

/*
 * IGNORE THE COPYRIGHT AT THE TOP OF THE FILE.<BR>
 * TAKEN FROM oNyx at https://web.archive.org/web/20150822035212/http://www.java-gaming.org/index.php?topic=1948.0
 * */
@SuppressWarnings("unchecked")
public class SoundManager {
	private javax.sound.sampled.Line.Info lineInfo;
	
	private Vector afs;
	private Vector sizes;
	private Vector infos;
	private Vector audios;
	private int num = 0;
	
	public SoundManager() {
		afs = new Vector();
		sizes = new Vector();
		infos = new Vector();
		audios = new Vector();
	}
	
	public int addClip(String s)
			throws IOException, UnsupportedAudioFileException, LineUnavailableException {
		URL url = getClass().getResource(s);
		//InputStream inputstream = url.openStream();
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(loadStream(url.openStream()));
		AudioFormat af = audioInputStream.getFormat();
		int size = (int) (af.getFrameSize() * audioInputStream.getFrameLength());
		byte[] audio = new byte[size];
		DataLine.Info info = new DataLine.Info(Clip.class, af, size);
		audioInputStream.read(audio, 0, size);
		
		afs.add(af);
		sizes.add(new Integer(size));
		infos.add(info);
		audios.add(audio);
		
		num++;
		return num - 1;
		
	}
	
	private ByteArrayInputStream loadStream(InputStream inputstream)
			throws IOException {
		ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
		byte data[] = new byte[1024];
		for (int i = inputstream.read(data); i != -1; i = inputstream.read(data))
			bytearrayoutputstream.write(data, 0, i);
		
		inputstream.close();
		bytearrayoutputstream.close();
		data = bytearrayoutputstream.toByteArray();
		return new ByteArrayInputStream(data);
	}
	
	public void playSound(int x)
			throws UnsupportedAudioFileException, LineUnavailableException {
		if (x > num) {
			System.out.println("playSound: sample nr[" + x + "] is not available");
		}else {
			Clip clip = (Clip) AudioSystem.getLine((DataLine.Info) infos.elementAt(x));
			clip.open((AudioFormat) afs.elementAt(x), (byte[]) audios.elementAt(x), 0, ((Integer) sizes.elementAt(x)).intValue());
			clip.start();
		}
	}
	
}