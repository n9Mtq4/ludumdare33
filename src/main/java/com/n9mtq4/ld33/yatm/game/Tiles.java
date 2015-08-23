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

import com.n9mtq4.ld33.yatm.game.tiles.*;
import com.n9mtq4.ld33.yatm.level.Tile;
import com.n9mtq4.ld33.yatm.level.tile.VoidTile;

/**
 * Created by will on 8/21/15 at 10:53 PM.
 */
public class Tiles {
	
	public static final VoidTile voidTile = new VoidTile();
	public static final WallTile wallTile = new WallTile();
	public static final HouseWall houseWall = new HouseWall();
	public static final LampTile lampTile = new LampTile();
	public static final TanCarpet tanCarpet = new TanCarpet();
	public static final RedBedTile redBedTile = new RedBedTile();
	public static final WhiteBedTile whiteBedTile = new WhiteBedTile();
	public static final Tile tableSide = new Tile(Sprites.table);
	public static final Tile stainedWood = new Tile(Sprites.stainedWood);
	public static final Tile toiletTile = new Tile(Sprites.toiletTile);
	public static final Tile sinkTop = new Tile(Sprites.sinkTop);
	public static final Tile sinkRight = new Tile(Sprites.sinkRight);
	public static final Tile sinkBottom = new Tile(Sprites.sinkBottom);
	public static final Tile sinkLeft = new Tile(Sprites.sinkLeft);
	public static final Tile stove = new SolidTile(Sprites.stove);
	public static final Tile water = new SolidTile(Sprites.water);
	public static final Tile tile = new Tile(Sprites.tile);
	public static final SeekerBarrierTile seekerBarrier = new SeekerBarrierTile();
	
}
