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

package com.n9mtq4.ld33.yatm.hud;

import com.n9mtq4.ld33.yatm.game.mob.MonsterPlayer;
import com.n9mtq4.ld33.yatm.graphics.Screen;

/**
 * Created by will on 8/21/15 at 9:55 PM.
 */
public class Hud {
	
	public ManaBar manaBar = new ManaBar();
	
	public void tick(MonsterPlayer player) {
		if (player.notAbility) {
//			show mana recharging
			manaBar.mana = player.cooldownTime;
			manaBar.maxMana = player.ability.getCooldown();
		}else {
//			show time left on ability
			manaBar.mana = player.time;
			manaBar.maxMana = player.ability.getTime();
		}
	}
	
	public void render(Screen screen) {
		manaBar.render(screen);
	}
	
}
