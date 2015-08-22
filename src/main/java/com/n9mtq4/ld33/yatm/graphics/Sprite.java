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

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by will on 8/21/15 at 9:19 PM.
 */
public class Sprite {
	
	public final int SIZE;
	public final int SIZE_MASK;
	public int x;
	public int y;
	public int width;
	public int height;
	public int[] pixels;
	private SpriteSheet sheet;
	public boolean effectedByLight = true;
	
	public Sprite(int size, Color color) {
		this.SIZE = size;
		this.SIZE_MASK = SIZE - 1;
		this.width = SIZE;
		this.height = SIZE;
		pixels = new int[width * height];
		setColor(color.getRGB());
	}
	
	public Sprite(int width, int height) {
		this.SIZE = width;
		this.SIZE_MASK = SIZE - 1;
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
	}
	
	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		this.SIZE = size;
		this.SIZE_MASK = SIZE - 1;
		this.width = SIZE;
		this.height = SIZE;
		this.x = x * SIZE;
		this.y = y * SIZE;
		this.sheet = sheet;
		pixels = new int[width * height];
		load();
	}
	
	private void load() {
		
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.width];
			}
		}
		
	}
	
	private void setColor(int color) {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = color;
		}
	}
	
	public BufferedImage toBufferedImage() {
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_4BYTE_ABGR);
		image.setRGB(0, 0, width, height, pixels, 0, width);
		return image;
	}
	
	public void tick() {
		
	}
	
	public Sprite getSprite() {
		return this;
	}
	
}
