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

import com.n9mtq4.ld33.yatm.game.Sprites;
import com.n9mtq4.ld33.yatm.graphics.AnimatedSprite;

/**
 * Created by will on 8/22/15 at 1:32 PM.
 */
public enum Monster {
	
	GREEN_BLOB(Sprites.monster1f, Sprites.monster1b, Sprites.monster1l, Sprites.monster1r),
	FLYING(Sprites.monster2f, Sprites.monster2b, Sprites.monster2l, Sprites.monster2r);
	
	private AnimatedSprite forward;
	private AnimatedSprite backward;
	private AnimatedSprite left;
	private AnimatedSprite right;
	
	Monster(AnimatedSprite forward, AnimatedSprite backward, AnimatedSprite left, AnimatedSprite right) {
		this.forward = forward;
		this.backward = backward;
		this.left = left;
		this.right = right;
	}
	
	public AnimatedSprite getForward() {
		return forward;
	}
	
	public AnimatedSprite getBackward() {
		return backward;
	}
	
	public AnimatedSprite getLeft() {
		return left;
	}
	
	public AnimatedSprite getRight() {
		return right;
	}
	
}
