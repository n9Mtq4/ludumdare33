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

package com.n9mtq4.ld33.yatm.entity;

import com.n9mtq4.ld33.yatm.graphics.Screen;
import com.n9mtq4.ld33.yatm.graphics.Sprite;
import com.n9mtq4.ld33.yatm.level.Level;

/**
 * Created by will on 4/18/15 at 2:58 PM.
 */
public abstract class Entity {
	
	public int x;
	public int y;
	public boolean removed = false;
	public Level level;
	protected Sprite sprite;
	
	public void init(Level level) {
		this.level = level;
	}
	
	public void render(Screen screen) {
		
	}
	
	public void renderSpriteRel(Screen screen, Sprite sprite) {
		screen.renderSpriteRel(x, y, x / Screen.ABS_TILE_SIZE, y / Screen.ABS_TILE_SIZE, sprite, level);
	}
	
	public void renderSpriteAbs(Screen screen, Sprite sprite) {
		screen.renderSpriteAbs(x, y, x / Screen.ABS_TILE_SIZE, y / Screen.ABS_TILE_SIZE, sprite, level);
	}
	
	public void tick() {
		
	}
	
	public void remove() {
		removed = true;
	}
	
	public boolean isRemoved() {
		return removed;
	}
	
	
}
