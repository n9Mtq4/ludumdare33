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

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by will on 8/23/15 at 3:43 PM.
 */
public class TextAreaWindow {
	
	private String title;
	private String text;
	private Dimension size;
	private Component parent;
	
	private JFrame frame;
	private JTextArea textArea;
	private JButton ok;
	
	public TextAreaWindow(String title, String text, Dimension size, Component parent) {
		
		this.title = title;
		this.text = text;
		this.size = size;
		this.parent = parent;
		gui();
		
	}
	
	private void gui() {
		
		frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		textArea = new JTextArea();
		textArea.setText(text);
		textArea.setEditable(false);
		ok = new JButton("Close");
		
		frame.add(new JScrollPane(textArea), BorderLayout.CENTER);
		frame.add(ok, BorderLayout.SOUTH);
		
		frame.pack();
		frame.setVisible(true);
		frame.setSize(size);
		frame.setLocationRelativeTo(parent);
		frame.getRootPane().setDefaultButton(ok);
		
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
	}
	
	public void dispose() {
		frame.dispose();
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getText() {
		return text;
	}
	
	public Dimension getSize() {
		return size;
	}
	
	public Component getParent() {
		return parent;
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public JTextArea getTextArea() {
		return textArea;
	}
	
	public JButton getOk() {
		return ok;
	}
	
}
