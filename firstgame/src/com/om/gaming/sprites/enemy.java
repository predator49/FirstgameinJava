package com.om.gaming.sprites;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class enemy extends sprite {

	public enemy(int x, int speed) {
		this.speed = speed;
		w = 225;
		h = 225;
		this.x = x;
		y = 30;
		image = new ImageIcon(enemy.class.getResource("enemyy.gif"));
	}

	public void move() {
		if (y > 920) {
			y = 0;
		}
		y = y + speed;
	}
}
