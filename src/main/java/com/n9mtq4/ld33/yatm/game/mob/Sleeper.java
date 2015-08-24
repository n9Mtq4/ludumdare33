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

package com.n9mtq4.ld33.yatm.game.mob;

import com.n9mtq4.ld33.yatm.entity.mob.Mob;
import com.n9mtq4.ld33.yatm.game.Sprites;
import com.n9mtq4.ld33.yatm.graphics.Sprite;

import java.util.Random;

/**
 * Created by will on 8/23/15 at 1:14 PM.
 */
public class Sleeper extends Mob {
	
	private static final Random RANDOM = new Random();
	private static final Sprite[] sprites = {Sprites.sleeper1};
	
	public Sleeper(int x, int y) {
		super(x, y);
		sprite = sprites[RANDOM.nextInt(sprites.length)];
	}
	
	@Override
	public void tick() {
		sprite.tick();
	}
	
}
