package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import objects.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
	int FPS = 60;
	final int originalTileSize = 16;
	final int scale = 3;

	public final int tileSize = originalTileSize * scale;

	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;

	KeyHandler keyH = new KeyHandler(this);
	public CollisionChecker cChecker = new CollisionChecker(this);
	
	// dat bomb va cac objects
	public AssetSetter aSetter = new AssetSetter(this);
	Thread gameThread;
	Player player = new Player(this, keyH);
	TileManager tileM = new TileManager(this);
	public SuperObject obj[] = new SuperObject[10]; 
	
	// game state
	public int gameState;
	public final int playState = 1;
	public final int pauseState = 2;
	

	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		this.requestFocusInWindow();
	}
	
	public void setupGame() {
		aSetter.setObject();
		gameState = playState;
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		double drawInterval = 1000000000 / FPS;
		double nextDrawTime = System.nanoTime() + drawInterval;
		while (gameThread != null) {

			update();
			repaint();

			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime / 1000000;

				if (remainingTime < 0) {
					remainingTime = 0;
				}

				Thread.sleep((long) remainingTime);

				nextDrawTime += drawInterval;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public void update() {
		if(gameState == playState) {
			player.update();
		}
		if(gameState == pauseState) {
			
		}
	}

	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		tileM.draw(g2);
		
	
		//drawing objects on screen except bomb
		for(int i = 1; i<obj.length; i++) {
			if(obj[i] != null) {
				obj[i].draw(g2, this);
			}
		}
		// player
		player.draw(g2);
		//ui.draw(g2);
		g2.dispose();
	}
}
