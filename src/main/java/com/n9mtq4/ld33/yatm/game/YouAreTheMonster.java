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

package com.n9mtq4.ld33.yatm.game;

import com.n9mtq4.ld33.yatm.Display;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * Created by will on 8/21/15 at 9:35 PM.
 */
public class YouAreTheMonster {
	
	public static void main(String[] args) {
		
		JFrame frame = new JFrame("Game");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		Display game = new Display();
		
		frame.add(game);
		
		frame.setResizable(false);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		frame.addWindowListener(new WinListener(game));
		game.start();
		
	}
	
	public static class WinListener implements WindowListener {
		
		private Display game;
		
		public WinListener(Display game) {
			this.game = game;
		}
		
		@Override
		public void windowOpened(WindowEvent e) {
			
		}
		
		@Override
		public void windowClosing(WindowEvent e) {
			
		}
		
		@Override
		public void windowClosed(WindowEvent e) {
			game.stop();
		}
		
		@Override
		public void windowIconified(WindowEvent e) {
			
		}
		
		@Override
		public void windowDeiconified(WindowEvent e) {
			
		}
		
		@Override
		public void windowActivated(WindowEvent e) {
			
		}
		
		@Override
		public void windowDeactivated(WindowEvent e) {
			
		}
		
	}
	
}
