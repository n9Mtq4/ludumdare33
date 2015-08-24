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

package com.n9mtq4.ld33.yatm.level;

import com.n9mtq4.ld33.yatm.Display;
import com.n9mtq4.ld33.yatm.entity.Entity;
import com.n9mtq4.ld33.yatm.entity.Light;
import com.n9mtq4.ld33.yatm.entity.mob.Player;
import com.n9mtq4.ld33.yatm.game.Tiles;
import com.n9mtq4.ld33.yatm.graphics.Screen;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by will on 8/21/15 at 9:51 PM.
 */
public class Level {
	
	public int width;
	public int height;
	public int[] tiles;
	public double[] lightMap;
	public double darkness = 0.2d;
	public double ambientLight = 0.16d;
	private String path;
	public Display display;
	
	public List<Entity> entities = new ArrayList<Entity>();
	
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		generateLevel();
	}
	
	public Level(String path) {
//		loadLevel(path);
		this.path = path;
		generateLevel();
	}
	
	public void add(Entity entity) {
		entity.init(this, display);
		entities.add(entity);
	}
	
	public void remove(Entity entity) {
		entities.remove(entity);
	}
	
	public void generateLightMap() {
		lightMap = new double[width * height];
		updateLightMap();
	}
	
	/**
	 * This is a cpu intensive process. should only be called once when the
	 * level is being generated
	 * */
	public void updateLightMap() {
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
//				set source light
				lightMap[x + y * width] += getTile(x, y).getSourceLight();
			}
		}
		
		Light le = new Light();
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
//				TODO: this next line works when it is backwards for some reason???
				if (getTile(x, y).getSourceLight() != 0.0d) continue;
//				for every tile, that isn't a source tile
//				make the light the ambient light
				double light = ambientLight;
				
//				go through all other tiles, looking for source tiles.
				for (int y1 = 0; y1 < height; y1++) {
					for (int x1 = 0; x1 < width; x1++) {
						if (getTile(x1, y1).getSourceLight() > 0.0d) {
//							for every source tile get the distance between the two
//							calc distance between (x, y) and (x1, y1)
							int xd = Math.abs(x - x1);
							int yd = Math.abs(y - y1);
							int xd2 = (int) Math.pow(xd, 2);
							int yd2 = (int) Math.pow(yd, 2);
							double distance = Math.sqrt(xd2 + yd2);
/*//							now make sure the light can't pass through walls
							boolean blockedByWall = false;
							double dx = x - x1;
							double dy = y - y1;
							double dir = Math.atan2(dy, dx);
							double nx = Math.cos(dir);
							double ny = Math.sin(dir);
//							now increment until it hits the tile.
							double cx = x;
							double cy = y;
							while ((int) cx != x1 && (int) cy != y1) {
								cx += nx;
								cy += ny;
								if (getTile((int) cx, (int) cy).isSolid(le)) {
									blockedByWall = true;
									break;
								}
							}
							if (blockedByWall) continue;*/
//							divide the distance by the darkness
							double dTimesD = distance * darkness;
//							light += getTile(x1, y1).getSourceLight() - addLight;
							double newLight = getTile(x1, y1).getSourceLight() - dTimesD;
//							light += getTile(x1, y1).getSourceLight() - dTimesD;
							if (newLight > ambientLight) light += newLight;
							if (light > 1) light = 1;
						}
					}
				}
				
				lightMap[x + y * width] = light;
				
			}
		}
		
	}
	
	public void generateLevel() {
		generateLightMap();
	}
	
	public void load() {
		loadLevel(path);
	}
	
	public void spawnMobs() {
		
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				Entity e = getTile(x, y).newSpawn(x, y);
				if (e != null) {
					add(e);
				}
			}
		}
		
	}
	
	public void loadLevel(String path) {
		
		try {
			
			BufferedImage i = ImageIO.read(Level.class.getResource(path));
			this.width = i.getWidth();
			this.height = i.getHeight();
			tiles = new int[width * height];
			lightMap = new double[width * height];
			i.getRGB(0, 0, width, height, tiles, 0, width);
			generateLightMap();
			spawnMobs();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void tick() {
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).tick();
		}
	}
	
	private void time() {
		
	}
	
	public boolean tileCollision(double x, double y, double xd, double yd, int sizex, int sizey, int xOff, int yOff, Entity entity) {
		
		for (int c = 0; c < 4; c++) {
			int xt = (((int) x + (int) xd) + c % 2 * sizex + xOff) / Screen.ABS_TILE_SIZE;
			int yt = (((int) y + (int) yd) + c / 2 * sizey + yOff) / Screen.ABS_TILE_SIZE;
			if (this.getTile(xt, yt).isSolid(entity)) return true;
		}
		
		return false;
		
	}
	
	/**
	 * renders everything
	 * */
	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffSet(xScroll, yScroll);
		int x0 = xScroll >> Screen.TILE_SIZE;
		int x1 = (xScroll + screen.width + (1 << Screen.TILE_SIZE)) >> Screen.TILE_SIZE;
		int y0 = yScroll >> Screen.TILE_SIZE;
		int y1 = (yScroll + screen.height + (1 << Screen.TILE_SIZE)) >> Screen.TILE_SIZE;
		
		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen, this);
			}
		}
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(screen);
		}
	}
	
	public Player getPlayer() {
		
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i) instanceof Player) return (Player) entities.get(i);
		}
//		i hope this never gets reached
		return null;
		
	}
	
	public Tile getTile(int x, int y) {
//		return (x == 4 && y == 4) ? Tiles.lampTile : Tiles.voidTile; //TODO: not engine
		return Tiles.voidTile;
	}
	
	public double getLightValue(int x, int y) {
		if (checkTileBounds(x, y)) return ambientLight;
		return lightMap[x + y * width];
	}
	
	public boolean checkTileBounds(int x, int y) {
		return x < 0 || y < 0 || x >= width || y >= height;
	}
	
}
