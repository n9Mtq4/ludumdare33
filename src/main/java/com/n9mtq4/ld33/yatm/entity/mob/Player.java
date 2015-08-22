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

package com.n9mtq4.ld33.yatm.entity.mob;

import com.n9mtq4.ld33.yatm.Display;
import com.n9mtq4.ld33.yatm.game.Sprites;
import com.n9mtq4.ld33.yatm.graphics.AnimatedSprite;
import com.n9mtq4.ld33.yatm.graphics.Screen;
import com.n9mtq4.ld33.yatm.graphics.Sprite;

/**
 * Created by will on 8/21/15 at 9:55 PM.
 */
public class Player extends Mob {
	
	public Display display;
	
	public AnimatedSprite forward;
	public AnimatedSprite backwards;
	public AnimatedSprite left;
	public AnimatedSprite right;
	
	public Player(int x, int y, Sprite sprite, Display display) {
		super(x, y);
		this.x = x;
		this.y = y;
		this.sprite = sprite;
		this.display = display;
		this.forward = Sprites.forwardAnimation;
		this.backwards = Sprites.forwardAnimation;
		this.left = Sprites.forwardAnimation;
		this.right = Sprites.forwardAnimation;
	}
	
	@Override
	public void render(Screen screen) {
		if (Direction.FORWARD.equals(dir)) {
			screen.renderSpriteRel(x, y, forward);
		}else if (Direction.RIGHT.equals(dir)) {
			screen.renderSpriteRel(x, y, right);
		}else if (Direction.BACKWARDS.equals(dir)) {
			screen.renderSpriteRel(x, y, backwards);
		}else if (Direction.LEFT.equals(dir)) {
			screen.renderSpriteRel(x, y, left);
		}
	}
	
	@Override
	public void tick() {
		super.tick();
		forward.tick();
		backwards.tick();
		left.tick();
		right.tick();
	}
	
}
