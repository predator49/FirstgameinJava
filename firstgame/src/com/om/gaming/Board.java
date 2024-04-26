package com.om.gaming;

import java.awt.*;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.om.gaming.sprites.enemy;
import com.om.gaming.sprites.player;

public class Board extends JPanel {
	Timer timer;
	BufferedImage backgroundImage;
	player Player;
	enemy Enemies[] = new enemy[3];

	public Board() {
		setSize(1500, 930);
		loadBackgroundImage();
		Player = new player();
		loadEnemies();
		gameloop();
		bindEvents();
		setFocusable(true);
	}

	private void gameOver(Graphics pen) {
		if (Player.outOfScreen()) {
			pen.setFont(new Font("times", Font.BOLD, 30));
			pen.setColor(Color.red);
			pen.drawString("Game Win", 1500 / 2, 930 / 2);
			timer.stop();
			return;

		}
		for (enemy Enemy : Enemies) {
			if (isCollide(Enemy)) {
				pen.setFont(new Font("times", Font.BOLD, 30));
				pen.setColor(Color.red);
				pen.drawString("Game Over", 1500 / 2, 930 / 2);
				timer.stop();
			}
		}

	}

	private boolean isCollide(enemy Enemy) {
		int xDistance = Math.abs(Player.x - Enemy.x);
		int yDistance = Math.abs(Player.y - Enemy.y);
		int maxH = Math.max(Player.h, Enemy.h);
		int maxW = Math.max(Player.w, Enemy.w);
		return xDistance <= maxW - 120 && yDistance <= maxH - 120;
	}

	private void bindEvents() {
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				Player.speed = 0;

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					Player.speed = 10;
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					Player.speed = -5;
				}

			}
		});
	}

	private void loadEnemies() {
		int x = 400;
		int gap = 340;
		int speed = 5;
		for (int i = 0; i < Enemies.length; i++) {
			Enemies[i] = new enemy(x, speed);
			x = x + gap;
			speed = speed + 5;
		}
	}

	private void gameloop() {
		timer = new Timer(50, (e) -> {
			repaint();
			gameOver(getGraphics());
		});
		timer.start();
	}

	public void loadBackgroundImage() {
		try {
			backgroundImage = ImageIO.read(Board.class.getResource("game-bg.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.exit(1);
			e.printStackTrace();
		}
	}

	private void printEnemies(Graphics pen) {
		for (enemy Enemy : Enemies) {
			Enemy.draw(pen);
			Enemy.move();
		}
	}

	public void paintComponent(Graphics pen) {
		super.paintComponent(pen);
		pen.drawImage(backgroundImage, 0, 0, 1500, 930, null);
		Player.draw(pen);
		Player.move();
		printEnemies(pen);
		gameOver(pen);
	}
}