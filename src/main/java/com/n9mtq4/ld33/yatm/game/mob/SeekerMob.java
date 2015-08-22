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

import com.n9mtq4.ld33.yatm.entity.Entity;
import com.n9mtq4.ld33.yatm.entity.mob.AnimatedMob;

import java.util.Random;

/**
 * Created by will on 8/22/15 at 4:12 PM.
 */
public class SeekerMob extends AnimatedMob {
	
	private static final Random RANDOM = new Random();
	
	private Entity lockedOnto;
	
	public SeekerMob(int x, int y) {
		super(x, y);
	}
	
	@Override
	public void tick() {
		super.tick();
		randomMovementAi();
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
	
	public void chaiserAi() {
		if (lockedOnto == null) lockedOnto = level.getPlayer();
		if (canSee(lockedOnto)) {
			if (x > lockedOnto.x) xd--;
			if (x < lockedOnto.x) xd++;
			if (y > lockedOnto.y) yd--;
			if (y < lockedOnto.y) yd++;
			move(xd, yd);
		}
	}
	
	private boolean canSee(Entity entity) {
		return true;
	}
	
}
