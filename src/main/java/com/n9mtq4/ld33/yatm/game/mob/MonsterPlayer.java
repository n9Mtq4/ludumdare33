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

import com.n9mtq4.ld33.yatm.Display;
import com.n9mtq4.ld33.yatm.entity.mob.Player;
import com.n9mtq4.ld33.yatm.input.KeyBoard;

/**
 * Created by will on 8/22/15 at 1:31 PM.
 */
public class MonsterPlayer extends Player {
	
	private Monster type;
	
	public MonsterPlayer(int x, int y, Display display, KeyBoard keyBoard, Monster type) {
		super(x, y, display, keyBoard);
		this.type = type;
		initSprites();
	}
	
	private void initSprites() {
		
		forward = type.getForward();
		backwards = type.getBackward();
		left = type.getLeft();
		right = type.getRight();
		
	}
	
	public Monster getType() {
		return type;
	}
	
	public void setType(Monster type) {
		this.type = type;
	}
	
}
