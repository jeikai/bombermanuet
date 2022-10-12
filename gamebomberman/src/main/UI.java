package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import entity.Entity;
import objects.OBJ_Heart;

public class UI {
	GamePanel gp;
	Graphics2D g2;
	Font maruMonica;
	BufferedImage heart_full,heart_half,heart_blank;
	public String message = "";
	public boolean gameFinished = false;
	public int commandNum = 0;
	public String currentDialogue = "";
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
		
		// tao cac object cho man hinh choi
		Entity heart = new OBJ_Heart(gp);
		heart_full = heart.image;
		heart_half = heart.image2;
		heart_blank = heart.image3;
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
			drawPlayerLife();
		}
		// Pause state
		if(gp.gameState == gp.pauseState) {
			drawPlayerLife();
			drawPauseScreen();
		}
		//GameOver state
		if(gp.gameState == gp.gameOverState) {
			drawGameOverScreen();
		}
		if ( gp.gameState == gp.dialogueState) {
			drawDialogueScreen();
		}
		//GameWin state
		if(gp.gameState == gp.gameWinState) {
			drawGameWinScreen();
		}
	}

	public void drawPlayerLife() {
		int x = gp.tileSize/2;
		int y = gp.tileSize/2;
		int i = 0;
		
		// draw blank heart
		while(i<gp.player.maxLife/2) {
			g2.drawImage(heart_blank,x,y,null);
			i++;
			x+= gp.tileSize;
		}
		// reset
		x = gp.tileSize/2;
		y = gp.tileSize/2;
		i = 0;
		//  draw current life
		while(i<gp.player.life) {
			g2.drawImage(heart_half,x,y,null);
			i++;
			if(i< gp.player.life) {
				g2.drawImage(heart_full,x,y,null);
			}
			i++;
			x+= gp.tileSize;
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
	public void drawDialogueScreen() {
		// Window
		int x = gp.tileSize * 2;
		int y = gp.tileSize/2;
		int width = gp.screenWidth - gp.tileSize*4;
		int height = gp.tileSize*4;
		drawSubWindow( x, y, width, height);
		
		g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
		 x+= gp.tileSize;
		 y+= gp.tileSize;
		 g2.drawString(currentDialogue, x, y);
	}
	public void drawSubWindow( int x, int y, int width, int height) {
		Color draw = new Color(0, 0, 0, 100);
		g2.setColor( draw);
		g2.fillRoundRect(x, y, width, height, 35, 35 );
		draw = new Color(255, 255, 255);
		g2.setColor(draw);
		//Ve ra do rong cua stroke
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
		
	}
	public int getXforCenteredText(String text) {
		int length = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth / 2 - length / 2;
	}
	
	public void drawGameOverScreen() {
		
		g2.setColor(new Color(0,0,0,150));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		int x;
		int y;
		String text;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));
		text = "Game Over";
		//shadow
		g2.setColor(Color.black);
		x = getXforCenteredText(text);		
		y = gp.tileSize*4;
		g2.drawString(text, x, y);
		//Main
		g2.setColor(Color.white);
		g2.drawString(text, x-4, y-4);
		
		//Retry
		g2.setFont(g2.getFont().deriveFont(50f));
		text = "Retry";
		x = getXforCenteredText(text);
		y += gp.tileSize*4;
		g2.drawString(text, x, y);
		if(commandNum == 0) {
			g2.drawString(">", x-40,y);
		}
		
		
		//Back to the title screen
		text = "Quit";
		x = getXforCenteredText(text);
		y += 55;
		g2.drawString(text, x, y);
		if(commandNum == 1 ) {
			g2.drawString(">", x-40,y);
		}
	}
	
	public void drawGameWinScreen() {
		g2.setColor(new Color(0,0,0,150));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		
		int x;
		int y;
		String text;
		g2.setFont(g2.getFont().deriveFont(Font.BOLD, 110f));
		text = "You Win!!!";
		//shadow
		g2.setColor(Color.white);
		x = getXforCenteredText(text);		
		y = gp.tileSize*4;
		g2.drawString(text, x, y);
		//Main
		g2.setColor(Color.yellow);
		g2.drawString(text, x-4, y-4);
		
		//Retry
		g2.setFont(g2.getFont().deriveFont(50f));
		text = "Play again";
		x = getXforCenteredText(text);
		y += gp.tileSize*4;
		g2.drawString(text, x, y);
		if(commandNum == 0) {
			g2.drawString(">", x-40,y);
		}
		
		
		//Back to the title screen
		text = "Quit";
		x = getXforCenteredText(text);
		y += 55;
		g2.drawString(text, x, y);
		if(commandNum == 1 ) {
			g2.drawString(">", x-40,y);
		}
	}
	
}
