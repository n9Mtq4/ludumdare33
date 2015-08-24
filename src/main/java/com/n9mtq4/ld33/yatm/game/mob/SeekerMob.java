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
 * Created by will on 8/22/15 at 4:12 PM.
 */
public class SeekerMob extends Mob {
	
	private static final Random RANDOM = new Random();
	private static final Sprite[] sprites = {Sprites.guard1, Sprites.guard2};
	
	private int speed = 1;
	private MonsterPlayer lockedOnto;
	
	public SeekerMob(int x, int y) {
		super(x, y);
		sprite = sprites[RANDOM.nextInt(sprites.length)];
	}
	
	@Override
	public void tick() {
		super.tick();
		if (lockedOnto == null) lockedOnto = (MonsterPlayer) level.getPlayer();
		if (canSee(lockedOnto)) {
			chaserAi();
		}else {
			randomMovementAi();
		}
		checkOnPlayer();
	}
	
	public void checkOnPlayer() {
		int distance = getDistance(lockedOnto, this);
		if (distance <= 0 && !lockedOnto.invisible) display.gameLoose();
	}
	
	public void randomMovementAi() {
		if (time % (RANDOM.nextInt(50) + 30) == 0) {
			xd = RANDOM.nextInt(3) - 1;
			yd = RANDOM.nextInt(3) - 1;
			if (RANDOM.nextInt(5) == 0) {
				xd = 0;
				yd = 0;
			}
		}
		move(xd, yd);
	}
	
	public void chaserAi() {
		int xd = 0;
		int yd = 0;
		if (x > lockedOnto.x) xd -= speed;
		if (x < lockedOnto.x) xd += speed;
		if (y > lockedOnto.y) yd -= speed;
		if (y < lockedOnto.y) yd += speed;
		move(xd, yd);
	}
	
	private boolean canSee(MonsterPlayer player) {
		if (player.invisible) return false;
		int d = getDistance(player, this);
		if (d <= 1) return true;
		double lightLevel = player.getLightLevel();
		if (d <= 2) if (lightLevel > 0.4) return true;
		if (d <= 5) if (player.getLightLevel() > 0.6d) return true;
		return false;
	}
	
}
