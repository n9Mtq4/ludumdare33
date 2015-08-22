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

import com.n9mtq4.ld33.yatm.graphics.Screen;
import com.n9mtq4.ld33.yatm.graphics.Sprite;
import com.n9mtq4.ld33.yatm.graphics.SpriteSheet;

import java.awt.Color;

/**
 * Created by will on 8/21/15 at 10:52 PM.
 */
public class Sprites {
	
	public static final SpriteSheet debugPlayer = new SpriteSheet("/textures/playerSheet.png", 256);
	public static final SpriteSheet tiles = new SpriteSheet("/textures/tiles.png", 265);
	
	public static final Sprite voidTile = new Sprite(Screen.ABS_TILE_SIZE, new Color(255, 255, 255));
	public static final Sprite lampTile = new Sprite(Screen.ABS_TILE_SIZE, 0, 0, tiles);
	public static final Sprite carpetGrey = new Sprite(Screen.ABS_TILE_SIZE, 1, 0, tiles);
	public static final Sprite carpetTan = new Sprite(Screen.ABS_TILE_SIZE, 4, 0, tiles);
	public static final Sprite stainedWood = new Sprite(Screen.ABS_TILE_SIZE, 2, 0, tiles);
	public static final Sprite table = new Sprite(Screen.ABS_TILE_SIZE, 3, 0, tiles);
	
}
