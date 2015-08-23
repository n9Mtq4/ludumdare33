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

package com.n9mtq4.ld33.yatm.launcher;

import com.n9mtq4.ld33.yatm.Display;
import com.n9mtq4.ld33.yatm.game.YouAreTheMonster;
import com.n9mtq4.ld33.yatm.game.mob.Monster;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * Created by will on 8/23/15 at 12:49 AM.
 */
public class Launcher {
	
	private static String[] args;
	public static void main(String[] args) {
		Launcher.args = args;
		new Launcher();
	}
	
	private static void openSource() {
		try {
			Desktop.getDesktop().browse(new URL("https://github.com/n9Mtq4/ludumdare33").toURI());
		}catch (IOException e1) {
			e1.printStackTrace();
		}catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
	}
	
	private JFrame frame;
	private JButton start;
	private JButton settings;
	private JButton source;
	private JButton exit;
	private JComboBox monster;
	private JComboBox levels;
	private JPanel bottomPanel;
	private JPanel gameSettingsPanel;
	private JPanel settingsPanel;
	private JPanel buttonPanel;
	private JPanel logoPanel;
	private JTextField windowWidth;
	private JTextField windowScale;
	
	public Launcher() {
		gui();
	}
	
	private void gui() {
		
		frame = new JFrame("Launcher | n9Mtq4 | LD33");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		start = new JButton("Launch");
		settings = new JButton("Settings");
		source = new JButton("View Source");
		exit = new JButton("Exit");
		
		start.addActionListener(new ButtonListener());
		settings.addActionListener(new ButtonListener());
		source.addActionListener(new ButtonListener());
		exit.addActionListener(new ButtonListener());
		
		try {
			final BufferedImage i = ImageIO.read(Launcher.class.getResource("/logo/banner.png"));
			logoPanel = new JPanel() {
				protected void paintComponent(Graphics graphics) {
					super.paintComponent(graphics);
					graphics.drawImage(i, 0, 0, null);
				}
			};
			logoPanel.setPreferredSize(new Dimension(i.getWidth(), i.getHeight()));
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		monster = new JComboBox(new Object[]{"Hareeny", "Chair"});
		levels = new JComboBox(new Object[]{"floor1"});
		gameSettingsPanel = new JPanel(new GridLayout(2, 2));
		gameSettingsPanel.add(new JLabel("You Monster"));
		gameSettingsPanel.add(new JLabel("The Level"));
		gameSettingsPanel.add(monster);
		gameSettingsPanel.add(levels);
		
		buttonPanel = new JPanel(new GridLayout(2, 2));
		buttonPanel.add(start);
		buttonPanel.add(settings);
		buttonPanel.add(source);
		buttonPanel.add(exit);
		
		windowWidth = new JTextField("" + Display.WIDTH);
		windowScale = new JTextField("" + Display.SCALE);
		settingsPanel = new JPanel(new GridLayout(2, 2));
		settingsPanel.add(new JLabel("Window Width"));
		settingsPanel.add(new JLabel("Window Scale"));
		settingsPanel.add(windowWidth);
		settingsPanel.add(windowScale);
		
		bottomPanel = new JPanel(new GridLayout(3, 1));
		bottomPanel.add(gameSettingsPanel);
		bottomPanel.add(settingsPanel);
		bottomPanel.add(buttonPanel);
		
		frame.add(logoPanel, BorderLayout.CENTER);
		frame.add(bottomPanel, BorderLayout.SOUTH);
		
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.getRootPane().setDefaultButton(start);
		
	}
	
	private Monster getMonsterType() {
		String monsterName = (String) monster.getSelectedItem();
		if (monsterName.equalsIgnoreCase("hareeny")) {
			return Monster.FLYING;
		}else if (monsterName.equalsIgnoreCase("chair")) {
			return Monster.GREEN_BLOB;
		}
		return Monster.FLYING;
	}
	
	private void updateSettings() {
		try {
			int width = Integer.parseInt(windowWidth.getText().trim());
			int scale = Integer.parseInt(windowScale.getText().trim());
			int height = (width / 16) * 9; // 16:9
			Display.WIDTH = width;
			Display.HEIGHT = height;
			Display.SCALE = scale;
			Display.monsterType = getMonsterType();
			Display.levelName = ((String) levels.getSelectedItem()).trim();
		}catch (NumberFormatException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(frame, "Your settings are invalid!");
		}
	}
	
	private class ButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton) e.getSource();
			if (source.getText().equalsIgnoreCase("exit")) {
				System.exit(0);
			}else if (source.getText().equalsIgnoreCase("launch")) {
//				JOptionPane.showMessageDialog(frame, "To change your monster, level, or settings - quit the game/launcher and run the game again.");
				updateSettings();
				YouAreTheMonster.main(Launcher.args);
//				frame.dispose();
			}else if (source.getText().equalsIgnoreCase("view source")) {
				openSource();
			}else if (source.getText().equalsIgnoreCase("settings")) {
//				TODO: screen settings
				
			}
		}
	}
	
}
