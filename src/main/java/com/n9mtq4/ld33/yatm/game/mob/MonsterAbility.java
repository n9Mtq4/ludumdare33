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

/**
 * Created by will on 8/23/15 at 2:16 AM.
 */
public enum MonsterAbility {
	
	INVISIBILITY(60 * 10, 60 * 60), 
	SPEED(60 * 10, 60 * 40);
	
	private int time;
	private int cooldown;
	
	MonsterAbility(int time, int cooldown) {
		this.time = time;
		this.cooldown = cooldown;
	}
	
	public int getTime() {
		return time;
	}
	
	public int getCooldown() {
		return cooldown;
	}
	
	
	@Override
	public String toString() {
		if (this == INVISIBILITY) return "Invisibility";
		if (this == SPEED) return "Speed";
		return "Not defined";
	}
	
}
