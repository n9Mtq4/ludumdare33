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

import com.n9mtq4.ld33.yatm.game.Progress;
import com.n9mtq4.ld33.yatm.game.YouAreTheMonster;
import com.n9mtq4.ld33.yatm.game.level.House;
import com.n9mtq4.ld33.yatm.game.mob.Monster;
import com.n9mtq4.ld33.yatm.game.mob.MonsterPlayer;
import com.n9mtq4.ld33.yatm.graphics.Screen;
import com.n9mtq4.ld33.yatm.hud.Hud;
import com.n9mtq4.ld33.yatm.input.KeyBoard;
import com.n9mtq4.ld33.yatm.launcher.TextAreaWindow;
import com.n9mtq4.ld33.yatm.level.Level;
import com.n9mtq4.ld33.yatm.sound.SoundManager;

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
import java.io.PrintWriter;
import java.io.StringWriter;
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
	
	private YouAreTheMonster parent;
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
	
	public Display(YouAreTheMonster parent) {
		//noinspection ConstantConditions
		this.parent = parent;
//		this.progress = DEBUG ? Progress.IN_GAME : Progress.MAIN_MENU;
		this.progress = Progress.IN_GAME;
		Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		setPreferredSize(size);
		this.screen = new Screen(WIDTH, HEIGHT);
		
		soundManager = new SoundManager();
		sounds = new HashMap<String, Integer>();
		initSound();
		initListeners();
		
		hud = new Hud();
		
		player = new MonsterPlayer(32, 2, keyBoard, monsterType);
		level = new House(levelName);
		level.display = this;
		level.add(player);
		level.load();
		
		requestFocus();
		
	}
	
	public Clip playSound(String sound) {
		try {
			return soundManager.playSound(sounds.get(sound));
		}catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void playMusic() {
		if (musicPlaying) return;
		music = playSound("music");
		music.loop(Clip.LOOP_CONTINUOUSLY);
		musicPlaying = true;
	}
	
	public void stopMusic() {
		if (!musicPlaying) return;
		musicPlaying = false;
		music.stop();
	}
	
	private void addSound(String name) {
		try {
			sounds.put(name, soundManager.addClip("/sound/" + name + ".wav"));
		}catch (IOException e) {
			e.printStackTrace();
		}catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}catch (LineUnavailableException e) {
			e.printStackTrace();
		}
	}
	
	private void initSound() {
		
		addSound("vrrm");
		addSound("whoosh");
		addSound("wish");
		addSound("error");
		addSound("roar");
		addSound("scream1");
		addSound("caught");
		addSound("music");
		
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
	
	public void gameLoose() {
		playSound("caught");
		progress = Progress.GAME_LOST;
	}
	
	public void gameWin() {
		playSound("roar");
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(700);
				}catch (InterruptedException e) {
					e.printStackTrace();
				}
				playSound("scream1");
				progress = Progress.GAME_WON;
			}
		}).start();
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
	
	public void renderGameLost() {
		
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		screen.clear();
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Verdana", Font.BOLD, 24));
		g.drawString("You Lost!", ((WIDTH * SCALE) / 2) - 40, 50);
		g.setFont(new Font("Verdana", Font.BOLD, 20));
		g.drawString("Try again or change level / monster!", ((WIDTH * SCALE) / 2) - 200, 100);
		g.drawString("Push space to restart the level, or close the window", ((WIDTH * SCALE) / 2) - 300, 140);
		g.drawString("and change settings in the launcher and start again.", ((WIDTH * SCALE) / 2) - 300, 180);
		
		g.dispose();
		bs.show();
		
	}
	
	public void renderGameWon() {
		
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		
		screen.clear();
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Verdana", Font.BOLD, 24));
		g.drawString("You Win!", ((WIDTH * SCALE) / 2) - 40, 50);
		g.setFont(new Font("Verdana", Font.BOLD, 20));
		g.drawString("Try your luck on another level with a different monster", ((WIDTH * SCALE) / 2) - 300, 100);
		g.drawString("Push space to return to the launcher.", ((WIDTH * SCALE) / 2) - 300, 140);
		
		g.dispose();
		bs.show();
		
//		stop();
		
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
			g.drawString(String.valueOf(fps + " fps | ability: " + player.ability), 0, HEIGHT * SCALE - 18);
			g.setFont(new Font("Verdana", Font.BOLD, 12));
		}
		
		g.dispose();
		bs.show();
	}
	
	public void render() {
		
		if (progress.equals(Progress.IN_GAME)) {
			renderGame();
		}else if (progress.equals(Progress.GAME_LOST)) {
			renderGameLost();
		}else if (progress.equals(Progress.GAME_WON)) {
			renderGameWon();
		}
		
	}
	
	public void tick() {
		
		if (progress == Progress.IN_GAME) {
			keyBoard.update();
			level.tick();
			hud.tick(player);
		}
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
//			player.changeMonster();
		}else if (keyEvent.getKeyCode() == KeyEvent.VK_E) {
			player.ability();
		}else if (keyEvent.getKeyCode() == KeyEvent.VK_SPACE) {
			if (progress == Progress.GAME_WON) {
				parent.dispose();
			}else if (progress == Progress.GAME_LOST) {
				parent.restart();
			}
		}
	}
}
