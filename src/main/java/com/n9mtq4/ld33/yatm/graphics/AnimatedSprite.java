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

package com.n9mtq4.ld33.yatm.graphics;

/**
 * Created by will on 8/21/15 at 10:22 PM.
 */
public class AnimatedSprite extends Sprite {
	
	private int frameTick;
	private int frame;
	private int frameRate;
	private Sprite[] frames;
	
	public AnimatedSprite(Sprite[] frames, int width, int height, int frameRate) {
		super(width, height);
		this.frames = frames;
		this.frame = 0;
		this.frameRate = frameRate;
		this.frameTick = 0;
	}
	
	@Override
	public void tick() {
		if (frameTick - frameRate == 0) {
			frameTick = 0;
			frame = frame < frames.length - 1 ? frame + 1 : 0;
		}
		frameTick++;
	}
	
	@Override
	public Sprite getSprite() {
		return frames[frame];
	}
	
}
