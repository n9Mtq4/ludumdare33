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

package com.n9mtq4.ld33.yatm.game;

import com.n9mtq4.ld33.yatm.graphics.*;

import java.awt.*;

/**
 * Created by will on 8/21/15 at 10:52 PM.
 */
public class Sprites {
	
	public static final SpriteSheet monsters = new SpriteSheet("/textures/monsters.png", 256);
	public static final SpriteSheet tiles = new SpriteSheet("/textures/tiles.png", 256);
	public static final SpriteSheet hud = new SpriteSheet("/textures/hud.png", 32);
	public static final SpriteSheet mobs = new SpriteSheet("/textures/mobs.png", 256);
	
	public static final Sprite voidTile = new Sprite(Screen.ABS_TILE_SIZE, new Color(0, 0, 0));
	public static final Sprite wallTile = new Sprite(Screen.ABS_TILE_SIZE, new Color(100, 100, 100));
	public static final Sprite houseWall = wallTile;
	public static final Sprite redBed = new Sprite(Screen.ABS_TILE_SIZE, new Color(255, 0, 0));
	public static final Sprite whiteBed = new Sprite(Screen.ABS_TILE_SIZE, new Color(255, 255, 255));
	public static final Sprite lampTile = new Sprite(Screen.ABS_TILE_SIZE, 0, 0, tiles);
	public static final Sprite carpetGray = new Sprite(Screen.ABS_TILE_SIZE, 1, 0, tiles);
	public static final Sprite carpetTan = new Sprite(Screen.ABS_TILE_SIZE, 1, 1, tiles);
	public static final Sprite stainedWood = new Sprite(Screen.ABS_TILE_SIZE, 2, 0, tiles);
	public static final Sprite table = new Sprite(Screen.ABS_TILE_SIZE, 3, 0, tiles);
	public static final Sprite toiletTile = new Sprite(Screen.ABS_TILE_SIZE, 0, 1, tiles);
	public static final Sprite sinkTop = new Sprite(Screen.ABS_TILE_SIZE, 2, 1, tiles);
	public static final Sprite sinkRight = new Sprite(Screen.ABS_TILE_SIZE, 3, 1, tiles);
	public static final Sprite sinkBottom = new Sprite(Screen.ABS_TILE_SIZE, 4, 1, tiles);
	public static final Sprite sinkLeft = new Sprite(Screen.ABS_TILE_SIZE, 5, 1, tiles);
	public static final Sprite stove = new Sprite(Screen.ABS_TILE_SIZE, 6, 1, tiles);
	public static final Sprite water = new Sprite(Screen.ABS_TILE_SIZE, 7, 1, tiles);
	public static final Sprite tile = new Sprite(Screen.ABS_TILE_SIZE, 5, 0, tiles);
	
	public static final AnimatedSprite sleeper1 = getAnimatedSprite(7, 0, 3, Screen.ABS_TILE_SIZE, mobs, 60);
	public static final Sprite guard1 = new Sprite(Screen.ABS_TILE_SIZE, 0, 0, mobs);
	public static final Sprite guard2 = new Sprite(Screen.ABS_TILE_SIZE, 1, 0, mobs);
	
	public static final Sprite manabarStartFull = new NoLightSprite(8, 0, 0, hud);
	public static final Sprite manabarMidFull = new NoLightSprite(8, 1, 0, hud);
	public static final Sprite manabarEndFull = new NoLightSprite(8, 2, 0, hud);
	public static final Sprite manabarStartEmpty = new NoLightSprite(8, 0, 1, hud);
	public static final Sprite manabarMidEmpty = new NoLightSprite(8, 1, 1, hud);
	public static final Sprite manabarEndEmpty = new NoLightSprite(8, 2, 1, hud);
	
	public static final AnimatedSprite monster1b = getAnimatedSprite(0, 0, 2, Screen.ABS_TILE_SIZE, monsters, 60);
	public static final AnimatedSprite monster1f = getAnimatedSprite(1, 0, 2, Screen.ABS_TILE_SIZE, monsters, 60);
	public static final AnimatedSprite monster1l = getAnimatedSprite(2, 0, 2, Screen.ABS_TILE_SIZE, monsters, 60);
	public static final AnimatedSprite monster1r = getAnimatedSprite(3, 0, 2, Screen.ABS_TILE_SIZE, monsters, 60);
	
	public static final AnimatedSprite monster1ib = getAnimatedSprite(0, 2, 4, Screen.ABS_TILE_SIZE, monsters, 60);
	public static final AnimatedSprite monster1if = getAnimatedSprite(1, 2, 3, Screen.ABS_TILE_SIZE, monsters, 60);
	public static final AnimatedSprite monster1il = getAnimatedSprite(2, 2, 3, Screen.ABS_TILE_SIZE, monsters, 60);
	public static final AnimatedSprite monster1ir = getAnimatedSprite(3, 2, 3, Screen.ABS_TILE_SIZE, monsters, 60);
	
	public static final AnimatedSprite monster2b = getAnimatedSprite(4, 0, 2, Screen.ABS_TILE_SIZE, monsters, 60);
	public static final AnimatedSprite monster2f = getAnimatedSprite(5, 0, 2, Screen.ABS_TILE_SIZE, monsters, 60);
	public static final AnimatedSprite monster2l = getAnimatedSprite(6, 0, 2, Screen.ABS_TILE_SIZE, monsters, 60);
	public static final AnimatedSprite monster2r = getAnimatedSprite(7, 0, 2, Screen.ABS_TILE_SIZE, monsters, 60);
	
	
	/**
	 * generates an animated sprite
	 * */
	private static AnimatedSprite getAnimatedSprite(int y, int x1, int x2, int size, SpriteSheet sheet, int framerate) {
		
		Sprite[] frames = new Sprite[Math.abs(x1 - x2)];
		
		for (int x = 0; x + x1 < x2; x++) {
			frames[x] = new Sprite(size, x + x1, y, sheet);
		}
		
		return new AnimatedSprite(frames, size, size, framerate);
		
	}
	
}
