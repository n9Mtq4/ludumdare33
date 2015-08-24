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

package com.n9mtq4.ld33.yatm.game.tiles;

import com.n9mtq4.ld33.yatm.entity.Entity;
import com.n9mtq4.ld33.yatm.game.Sprites;
import com.n9mtq4.ld33.yatm.game.mob.SeekerMob;
import com.n9mtq4.ld33.yatm.level.Tile;

/**
 * Created by will on 8/23/15 at 1:25 PM.
 */
public class SeekerSpawn extends Tile {
	
	public SeekerSpawn() {
		super(Sprites.carpetTan);
	}
	
	@Override
	public Entity newSpawn(int x, int y) {
		return new SeekerMob(x, y);
	}
	
}
