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

import com.n9mtq4.ld33.yatm.game.Sprites;
import com.n9mtq4.ld33.yatm.graphics.Screen;

/**
 * Created by will on 8/23/15 at 2:21 AM.
 */
public class ManaBar {
	
	public int mana;
	public int maxMana;
	private int y = 8;
	
	public void render(Screen screen) {
		
//		math
		int maxBars = (screen.width - (16 * 2)) / 8;
		int fullBars = (maxBars * mana) / maxMana;
//		System.out.println(fullBars + " / " + maxBars);
//		start
		if (fullBars > 0) {
			screen.renderSpriteAbs(12, y, 12, y, Sprites.manabarStartFull, null); // 16 - (8 / 2) = 12
		}else {
			screen.renderSpriteAbs(12, y, 12, y, Sprites.manabarStartEmpty, null); // 16 - (8 / 2) = 12
		}
//		segments in middle
		for (int b = 1; b < maxBars; b++) {
			int x = (b << 3) + 8; // multiply by 8 and add the length of the sprite = 8
			if (b < fullBars) {
				screen.renderSpriteAbs(x, y, 0, 0, Sprites.manabarMidFull, null);
			}else {
				screen.renderSpriteAbs(x, y, 0, 0, Sprites.manabarMidEmpty, null);
			}
		}
//		end
		if (fullBars == maxBars) {
			screen.renderSpriteAbs(screen.width - 24, y, 0, 0, Sprites.manabarEndFull, null); // 16 + 8 = 24
		}else {
			screen.renderSpriteAbs(screen.width - 24, y, 0, 0, Sprites.manabarEndEmpty, null); // 16 + 8 = 24
		}
		
	}
	
}
