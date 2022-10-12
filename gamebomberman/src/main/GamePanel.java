package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import ai.PathFinder;
import breakableTiles.BreakableTile;
import entity.Entity;
import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {
	public int FPS = 60;
	final int originalTileSize = 16;
	final int scale = 3;

	public final int tileSize = originalTileSize * scale;

	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;
	
	// world setting
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;

	//map setting
	public final int maxMap = 10;
	public int currentMap = 0;
	public KeyHandler keyH = new KeyHandler(this);
	public CollisionChecker cChecker = new CollisionChecker(this);
	
	Sound sound = new Sound();
	
	// ENTITY va cac OBJECTS
	public AssetSetter aSetter = new AssetSetter(this);
	Thread gameThread;
	public Player player = new Player(this, keyH);
	public Entity npc[][] = new Entity[maxMap][10];
	public TileManager tileM = new TileManager(this);
	public Entity obj[][] = new Entity[maxMap][20]; 
	public ArrayList<Entity> entityList = new ArrayList<>();
	public ArrayList<Entity> projectileList = new ArrayList<>();
	public BreakableTile bTile[][] = new BreakableTile[maxMap][400];
	public PathFinder pFinder = new PathFinder(this);
	
	// game state
	public int gameState;
	public final int playState = 1;
	public final int pauseState = 2;
	public final int titleState = 0;
	public final int gameOverState = 4;
	
	// UI
	UI ui = new UI(this);
	
	//Event
//	public EventHandler eHandler = new EventHandler(this);
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
	public void retry() {
		player.setDefaultValues();
		aSetter.setBreakableTile();
		aSetter.setObject();
		aSetter.setNPC();
		currentMap = 0;
	}
	public void restart() {
		player.setDefaultValues();
		aSetter.setBreakableTile();
		aSetter.setObject();
		aSetter.setNPC();
		
		gameState = titleState;
	}
	public void update() {
		if(gameState == playState) {
			player.update();
			
			//NPC
			for(int i = 0;i<npc[1].length; i++) {
				if(npc[currentMap][i] != null) {
					npc[currentMap][i].update();
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
		
		for(int i = 0; i<bTile[1].length; i++) {
			if(bTile[currentMap][i] != null) {
				bTile[currentMap][i].update();
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
			for(int i = 0; i<bTile[1].length; i++) {
				if(bTile[currentMap][i] != null) {
					bTile[currentMap][i].draw(g2);
				}
			}
			
			// add entity to list
			entityList.add(player);
			for(int i = 0; i<npc[1].length; i++) {
				if(npc[currentMap][i] != null) {
					entityList.add(npc[currentMap][i]);
				}
			}
			
			for(int i = 0; i<obj[1].length; i++) {
				if(obj[currentMap][i] != null) {
					entityList.add(obj[currentMap][i]);
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
					int result = Integer.compare(e1.worldY, e2.worldY);
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
