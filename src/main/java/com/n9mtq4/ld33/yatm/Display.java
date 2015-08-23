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

package com.n9mtq4.ld33.yatm;

import com.n9mtq4.ld33.yatm.audio.SoundManager;
import com.n9mtq4.ld33.yatm.entity.mob.Player;
import com.n9mtq4.ld33.yatm.game.Progress;
import com.n9mtq4.ld33.yatm.game.level.House;
import com.n9mtq4.ld33.yatm.game.mob.Monster;
import com.n9mtq4.ld33.yatm.game.mob.MonsterPlayer;
import com.n9mtq4.ld33.yatm.graphics.Screen;
import com.n9mtq4.ld33.yatm.hud.Hud;
import com.n9mtq4.ld33.yatm.input.KeyBoard;
import com.n9mtq4.ld33.yatm.level.Level;

import javax.imageio.ImageIO;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by will on 8/21/15 at 9:03 PM.
 */
public class Display extends Canvas implements Runnable, MouseListener, MouseMotionListener {
	
	public static int WIDTH = 360;
	public static int HEIGHT = (WIDTH / 16) * 9; // 16:9
	public static int SCALE = 2;
	public static final double GAME_SPEED = 60.0d;
	public static final boolean DEBUG = true;
	
	public static Monster monsterType = Monster.GREEN_BLOB;
	public static String levelName = "floor1";
	
	private Thread thread;
	private boolean running;
	private int fps;
	
	private Screen screen;
	private Hud hud;
	public Level level;
	private MonsterPlayer player;
	private KeyBoard keyBoard;
	public Progress progress;
	public SoundManager soundManager;
	public HashMap<String, Integer> sounds;
	public Clip music;
	public boolean musicPlaying = false;
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	
	public Display() {
		//noinspection ConstantConditions
		this.progress = DEBUG ? Progress.IN_GAME : Progress.MAIN_MENU;
		Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		setPreferredSize(size);
		this.screen = new Screen(WIDTH, HEIGHT);
		
		soundManager = new SoundManager();
		sounds = new HashMap<String, Integer>();
		initSound();
		initListeners();
		
		hud = new Hud();
		
		player = new MonsterPlayer(32, 2, this, keyBoard, monsterType);
		level = new House(levelName);
		level.add(player);
		level.load();
		
		requestFocus();
		
	}
	
	public void playSound(String sound) {
		try {
			soundManager.playSound(sounds.get(sound));
		}catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	public void playMusic() {
		if (musicPlaying) return;
		try {
			music = soundManager.playSound(sounds.get("music1"));
			music.loop(Clip.LOOP_CONTINUOUSLY);
			musicPlaying = true;
		}catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	public void stopMusic() {
		if (!musicPlaying) return;
		musicPlaying = false;
		if (music != null) music.stop();
	}
	
	private void initSound() {
		try {
			sounds.put("vrrm", soundManager.addClip("/sound/vrrm.wav"));
			sounds.put("whoosh", soundManager.addClip("/sound/whoosh.wav"));
			sounds.put("wish", soundManager.addClip("/sound/wish.wav"));
			sounds.put("music1", soundManager.addClip("/sound/music.wav"));
		}catch (IOException e) {
			e.printStackTrace();
		}catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	private void initListeners() {
		this.keyBoard = new KeyBoard(this);
		addKeyListener(keyBoard);
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	public synchronized void start() {
		
		if (thread != null) stop();
		running = true;
//		initBuffer();
		playMusic();
		thread = new Thread(this, "Game Thread");
		thread.start();
		
	}
	
	public synchronized void stop() {
		
		try {
			running = false;
			stopMusic();
			if (thread != null) thread.join();
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void customCursor(String path, String name, int x, int y) {
		try {
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			BufferedImage image = ImageIO.read(Screen.class.getResource(path));
			Cursor cursor = toolkit.createCustomCursor(image, new Point(x, y), name);
			setCursor(cursor);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void renderGameOver() {
		
	}
	
	public void renderMenu() {
		
	}
	
	public void renderGame() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		screen.clear();
		int xScroll = player.x - screen.width / 2;
		int yScroll = player.y - screen.height / 2;
		level.render(xScroll, yScroll, screen);
		hud.render(screen);
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		if (DEBUG) {
			g.setColor(new Color(255, 255, 0));
			g.setFont(new Font("Verdana", Font.BOLD, 24));
			g.drawString(String.valueOf(fps + " fps"), 0, HEIGHT - 18);
			g.setFont(new Font("Verdana", Font.BOLD, 12));
		}
		
		g.dispose();
		bs.show();
	}
	
	public void render() {
		
		if (progress.equals(Progress.IN_GAME)) {
			renderGame();
		}else if (progress.equals(Progress.GAME_OVER)) {
			renderGameOver();
		}else if (progress.equals(Progress.CUT_SCENE)) {
//			TODO: render cut scene
		}else if (progress.equals(Progress.MAIN_MENU)) {
			renderMenu();
		}
		
	}
	
	public void tick() {
		
		keyBoard.update();
		level.tick();
		hud.tick(player);
//		too slow, so light map can't be dynamic
//		level.updateLightMap();
		
	}
	
	private void initBuffer() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
		}
	}
	
	/**
	 * Game loop
	 * */
	@Override
	public void run() {
		int frames = 0;
		double unprocessedSeconds = 0;
		long previousTime = System.nanoTime();
		double clockSpeed = 1 / GAME_SPEED;
		int tickCount = 0;
		boolean ticked = false;
		
		requestFocus();
		requestFocusInWindow();
		tick();
		while (running) {
			
//			game loop
			long currentTime = System.nanoTime();
			long passedTime = currentTime - previousTime;
			previousTime = currentTime;
			unprocessedSeconds += passedTime / 1000000000.0;
			
			while (unprocessedSeconds > clockSpeed) {
				
				tick();
				unprocessedSeconds -= clockSpeed;
				ticked = true;
				tickCount++;
				if (tickCount % GAME_SPEED == 0) {
					
					System.out.println(tickCount + " ups, " + frames + " fps");
					previousTime += 1000;
					fps = frames;
					frames = 0;
					tickCount = 0;
					
				}
				
			}
			
			if (ticked) {
				
				render();
				frames++;
				ticked = false;
				
			}
			
			render();
			frames++;
			
		}
	}
	
	public static int getWindowWidth() {
		return WIDTH * SCALE;
	}
	
	public static int getWindowHeight() {
		return HEIGHT * SCALE;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		
	}
	
	public void keyPress(KeyEvent keyEvent) {
		if (keyEvent.getKeyCode() == KeyEvent.VK_M) {
			if (this.musicPlaying) {
				this.stopMusic();
			}else {
				this.playMusic();
			}
		}else if (keyEvent.getKeyCode() == KeyEvent.VK_R) {
			player.changeMonster();
		}else if (keyEvent.getKeyCode() == KeyEvent.VK_E) {
			player.ability();
		}
	}
}
