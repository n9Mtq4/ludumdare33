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

import com.n9mtq4.ld33.yatm.entity.Entity;
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
	public int[] tilesInt;
	public int score = 0;
	
	public List<Entity> entities = new ArrayList<Entity>();
	
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		this.tilesInt = new int[width * height];
		generateLevel();
	}
	
	public Level(String path) {
		loadLevel(path);
		generateLevel();
	}
	
	public void add(Entity entity) {
		entity.init(this);
		entities.add(entity);
	}
	
	public void remove(Entity entity) {
		entities.remove(entity);
	}
	
	public void generateLevel() {
	}
	
	public void loadLevel(String path) {
		
		try {
			
			BufferedImage i = ImageIO.read(Level.class.getResource(path));
			this.width = i.getWidth();
			this.height = i.getHeight();
			tiles = new int[width * height];
			i.getRGB(0, 0, width, height, tiles, 0, width);
			
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
	
	public Player getPlayer() {
		
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i) instanceof Player) return (Player) entities.get(i);
		}
//		i hope this never gets reached
		return null;
		
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
				getTile(x, y).render(x, y, screen);
			}
		}
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(screen);
		}
	}
	
	public Tile getTile(int x, int y) {
		return Tiles.voidTile; //TODO: not engine
	}
	
	public boolean checkBounds(int x, int y) {
		return x < 0 || y < 0 || x >= width || y >= height;
	}
	
}
