package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.InputStream;

public class UI {
	GamePanel gp;
	Graphics2D g2;
	Font maruMonica;
	public String message = "";
	public boolean gameFinished = false;
	public int commandNum = 0;

	public UI(GamePanel gp) {
		this.gp = gp;
		
		try {
			InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
			maruMonica = Font.createFont(Font.TRUETYPE_FONT,is);
		}catch(FontFormatException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
		this.g2 = g2;
		g2.setFont(maruMonica);
		g2.setColor(Color.white);
		
		
		// Title state
		if(gp.gameState == gp.titleState) {
			drawTitleScreen();
		}
		// Play State
		
		if(gp.gameState == gp.playState) {
			
		}
		// Pause state
		if(gp.gameState == gp.pauseState) {
			drawPauseScreen();
		}
	}

	public void drawTitleScreen() {
		
		g2.setColor(new Color(30,60,10));
		g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);
		
		// title name
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
		String text = "Bomberman";
		int x = getXforCenteredText(text);
		int y = gp.tileSize*3;
		
		// Shadow effect 
		g2.setColor(Color.black);
		g2.drawString(text, x+5, y+5);
		g2.setColor(Color.white);
		g2.drawString(text, x, y);
		
		// image
		x = gp.screenWidth /2 - gp.tileSize;
		y += gp.tileSize*2;
		g2.drawImage(gp.player.down1,x,y,gp.tileSize*2,gp.tileSize*2,null);
		
		// menu
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));
		text = "NEW GAME";
		x = getXforCenteredText(text);
		y += gp.tileSize*3;
		g2.drawString(text, x, y);
		
		if(commandNum == 0) {
			g2.drawString(">>",x-gp.tileSize,y);
		}
		
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));
		text = "EXIT";
		x = getXforCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		
		if(commandNum == 1) {
			g2.drawString(">>",x-gp.tileSize,y);
		}
		
		// instruction
		g2.setFont(g2.getFont().deriveFont(Font.ITALIC,20F));
		text = "How to play: Use AWSD to move, Spacebar to drop bomb";
		x = getXforCenteredText(text);
		y += gp.tileSize;
		g2.drawString(text, x, y);
		
	}

	public void drawPauseScreen() {
		// TODO Auto-generated method stub
		
		
		g2.setFont(g2.getFont().deriveFont(Font.BOLD,96F));
		String text = "PAUSED";
		int x = getXforCenteredText(text);
		int y = gp.screenHeight/2;
		
		
		g2.setColor(Color.MAGENTA);
		g2.drawString(text, x, y);
	}

	public int getXforCenteredText(String text) {
		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth / 2 - length / 2;
	}
}
