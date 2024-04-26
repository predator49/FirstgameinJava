package com.om.gaming.sprites;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class player extends sprite {
	public player() {
		w = 225;
		h = 225;
		x = 50;
		y = 550;
		image = new ImageIcon(player.class.getResource("player1.gif"));
	}

	public void move() {
		x = x + speed;
	}

	public boolean outOfScreen() {
		return x > 1500;
	}
}
