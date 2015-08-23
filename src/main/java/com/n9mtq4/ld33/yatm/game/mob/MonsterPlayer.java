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

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Created by will on 8/22/15 at 1:31 PM.
 */
public class MonsterPlayer extends Player {
	
	private static final int SPEED_MULTIPLE = 2;
	
	private Monster type;
	public MonsterAbility ability;
	public boolean notAbility = true;
	public int cooldownTime = 0;
	public int time = 0;
	public boolean invisible = false;
	public boolean disabled = true;
	
	public MonsterPlayer(int x, int y, Display display, KeyBoard keyBoard, Monster type) {
		super(x, y, display, keyBoard);
		this.type = type;
		initMonster();
	}
	
	private void setSprites(Monster monster) {
		
		forward = monster.getForward();
		backwards = monster.getBackward();
		left = monster.getLeft();
		right = monster.getRight();
		
	}
	
	private void initMonster() {
		
		setSprites(type);
		if (type == Monster.GREEN_BLOB) ability = MonsterAbility.INVIBILITY;
		if (type == Monster.FLYING) ability = MonsterAbility.SPEED;
		cooldownTime = ability.getCooldown() / 2;
		
	}
	
	public void ability() {
		if (cooldownTime < ability.getCooldown()) return;
		notAbility = false;
		disabled = false;
		cooldownTime = 0;
		time = ability.getTime();
		if (ability == MonsterAbility.SPEED) {
			speed *= SPEED_MULTIPLE;
			display.playSound("vrrm");
		}else if (ability == MonsterAbility.INVIBILITY) {
			setSprites(Monster.INVISIBLE_BLOB);
			invisible = true;
			display.playSound("wish");
		}
	}
	
	public void disableAbility() {
		if (disabled) return;
		disabled = true;
		notAbility = true;
		if (ability == MonsterAbility.SPEED) {
			speed /= SPEED_MULTIPLE;
		}else if (ability == MonsterAbility.INVIBILITY) {
			setSprites(Monster.GREEN_BLOB);
			invisible = false;
			display.playSound("whoosh");
		}
	}
	
	public void inBed() {
//		TODO: win condition
		System.out.println("IN BED");
	}
	
	@Deprecated
	public void changeMonster() {
		disableAbility();
		if (type.equals(Monster.GREEN_BLOB)) {
			this.type = Monster.FLYING;
		}else if (type.equals(Monster.FLYING)) {
			this.type = Monster.GREEN_BLOB;
		}
		try {
			display.soundManager.playSound(display.sounds.get("wish"));
		}catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
		}catch (LineUnavailableException e) {
			e.printStackTrace();
		}
		initMonster();
	}
	
	public void changeMonster(Monster type) {
		this.type = type;
		initMonster();
	}
	
	@Override
	public void tick() {
		super.tick();
		if (cooldownTime < ability.getCooldown() && notAbility) cooldownTime++;
		if (time == 0 && !disabled) disableAbility();
		if (!notAbility) time--;
	}
	
	public Monster getType() {
		return type;
	}
	
	public void setType(Monster type) {
		this.type = type;
	}
	
}
