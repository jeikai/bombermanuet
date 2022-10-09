package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import breakableTiles.BreakableTile;
import entity.Entity;
import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
	int FPS = 30;
	final int originalTileSize = 16;
	final int scale = 3;

	public final int tileSize = originalTileSize * scale;

	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;

	public KeyHandler keyH = new KeyHandler(this);
	public CollisionChecker cChecker = new CollisionChecker(this);
	
	Sound sound = new Sound();
	
	// ENTITY va cac OBJECTS
	public AssetSetter aSetter = new AssetSetter(this);
	Thread gameThread;
	public Player player = new Player(this, keyH);
	public Entity npc[] = new Entity[10];
	public TileManager tileM = new TileManager(this);
	public Entity obj[] = new Entity[10]; 
	public ArrayList<Entity> entityList = new ArrayList<>();
	public ArrayList<Entity> projectileList = new ArrayList<>();
	public BreakableTile bTile[] = new BreakableTile[100];
	
	// game state
	public int gameState;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int titleState = 0;
	
	// UI
	UI ui = new UI(this);

	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		this.requestFocusInWindow();
	}
	
	public void setupGame() {
		aSetter.setBreakableTile();
		aSetter.setObject();
		aSetter.setNPC();
		
		gameState = titleState;
		playMusic();
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
			
			//NPC
			for(int i = 0;i<npc.length; i++) {
				if(npc[i] != null) {
					npc[i].update();
				}
			}
		}
		
		for(int i = 0; i<projectileList.size(); i++) {
			if(projectileList.get(i) != null) {
				if(projectileList.get(i).alive == true) {
					projectileList.get(i).update();
				}
				if(projectileList.get(i).alive == false) {
					projectileList.remove(i);
				}
			}	
		}
		
		for(int i = 0; i<bTile.length; i++) {
			if(bTile[i] != null) {
				bTile[i].update();
			}
		}
		
		if(gameState == pauseState) {
		}
	}

	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		
		//Title screen
		if(gameState == titleState) {
			ui.draw(g2);
		}
		// other screen
		else {
			// Tile
			tileM.draw(g2);
			for(int i = 0; i<bTile.length; i++) {
				if(bTile[i] != null) {
					bTile[i].draw(g2);
				}
			}
			
			// add entity to list
			entityList.add(player);
			for(int i = 0; i<npc.length; i++) {
				if(npc[i] != null) {
					entityList.add(npc[i]);
				}
			}
			
			for(int i = 0; i<obj.length; i++) {
				if(obj[i] != null) {
					entityList.add(obj[i]);
				}
			}
			for(int i = 0; i<projectileList.size();i++) {
				if(projectileList.get(i) != null) {
					entityList.add(projectileList.get(i));
				}
			}
			
			
			// sort entity theo thu tu cai nao co y thap hon thi render sau
			Collections.sort(entityList,new Comparator<Entity>() {

				@Override
				public int compare(Entity e1, Entity e2) {
					int result = Integer.compare(e1.y, e2.y);
					return result;
				}
				
			});
			// draw entities
			for(int i = 0; i<entityList.size(); i++) {
				entityList.get(i).draw(g2);
			}
			// empty the list
				entityList.clear();
			
			// UI
			ui.draw(g2);
		}
		
		
		g2.dispose();
	}
	public void playMusic() {
		sound.setFile();
		sound.play();
		sound.loop();
	}
	public void stopMusic() {
		sound.stop();
	}
}
