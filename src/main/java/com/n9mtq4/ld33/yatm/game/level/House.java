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

package com.n9mtq4.ld33.yatm.game.level;

import com.n9mtq4.ld33.yatm.game.Tiles;
import com.n9mtq4.ld33.yatm.graphics.Screen;
import com.n9mtq4.ld33.yatm.graphics.Sprite;
import com.n9mtq4.ld33.yatm.level.Level;
import com.n9mtq4.ld33.yatm.level.Tile;

import java.awt.*;

/**
 * Created by will on 8/22/15 at 6:31 PM.
 */
public class House extends Level {
	
	public House(String name) {
		super("/world/" + name + ".png");
	}
	
	@Override
	public Tile getTile(int x, int y) {
		
		if (checkTileBounds(x, y)) return Tiles.voidTile;
		
		if (tiles[x + y * width] == 0xff646464) return Tiles.wallTile;
		if (tiles[x + y * width] == 0xffc8c8c8) return Tiles.houseWall;
		if (tiles[x + y * width] == 0xff00ff00) return Tiles.seekerBarrier;
		if (tiles[x + y * width] == 0xff0000ff) return Tiles.lampTile;
		if (tiles[x + y * width] == 0xffffffff) return Tiles.tanCarpet;
		if (tiles[x + y * width] == 0xff050000) return Tiles.toiletTile;
		if (tiles[x + y * width] == 0xff060100) return Tiles.sinkTop;
		if (tiles[x + y * width] == 0xff060200) return Tiles.sinkLeft;
		if (tiles[x + y * width] == 0xff060300) return Tiles.sinkBottom;
		if (tiles[x + y * width] == 0xff060400) return Tiles.sinkRight;
		if (tiles[x + y * width] == 0xff070000) return Tiles.stainedWood;
		if (tiles[x + y * width] == 0xff080000) return Tiles.stainedWood;
		if (tiles[x + y * width] == 0xff090000) return Tiles.stainedWood;
		if (tiles[x + y * width] == 0xff0a0000) return Tiles.stove;
		if (tiles[x + y * width] == 0xfffe0000) return Tiles.redBedTile;
		if (tiles[x + y * width] == 0xfffefffe) return Tiles.whiteBedTile;
		if (tiles[x + y * width] == 0xff0b0000) return Tiles.water;
		if (tiles[x + y * width] == 0xff0c0000) return Tiles.tile;
		if (tiles[x + y * width] == 0xff640064) spawnMob(x, y);
		
//		return Tiles.voidTile;
		return new Tile(new Sprite(Screen.ABS_TILE_SIZE, new Color(tiles[x + y * width])));
		
	}
	
	private void spawnMob(int xt, int yt) {
		
	}
	
}
