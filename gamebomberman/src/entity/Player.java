package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
	GamePanel gp;
	KeyHandler keyH;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		solidArea = new Rectangle(8,8,32,32);// nho hon player
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		x = 100;
		y = 300;
		speed = 4;
		direction = "down";
	}
	
	public void getPlayerImage() {
		try {
			System.out.println("Image loading started");
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/up1.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/up2.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/down1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/down2.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/left1.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/left2.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/right1.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/right2.png"));
//			System.out.println("Image loading ended");
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		
		
		if(keyH.upPressed || keyH.downPressed 
				|| keyH.leftPressed || keyH.rightPressed) {
			
			if(keyH.upPressed == true) {
				direction = "up";
			} 
			if(keyH.downPressed == true) {
				direction = "down";
			}
			if(keyH.leftPressed == true) {
				direction = "left";
			}
			if(keyH.rightPressed == true) {
				direction = "right";
			}
			
			// check va cham: truyen doi tuong
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			if(collisionOn == false) {
				switch(direction) {
				case "up":
					y -= speed;
					break;
				case "down":
					y+= speed;
					break;
				case "left":
					x-= speed;
					break;
				case "right":
					x+= speed;
					break;
				}
			}
			
			spriteCounter++;
			//player thay đổi mỗi 8 frames
			if(spriteCounter > 8) {
				if(spriteNum == 1)
					spriteNum = 2;	
				else if(spriteNum == 2)
					spriteNum = 1;
				
				spriteCounter = 0;
			}
		}
		
		
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if(spriteNum == 1)
				image = up1;
			if(spriteNum == 2)
				image = up2;
			break;
		case "down":
			if(spriteNum == 1)
				image = down1;
			if(spriteNum == 2)
				image = down2;
			break;
		case "left":
			if(spriteNum == 1)
				image = left1;
			if(spriteNum == 2)
				image = left2;
			break;
		case "right":
			if(spriteNum == 1)
				image = right1;
			if(spriteNum == 2)
				image = right2;
			break;
		}
		// in ra hình ảnh image ở toạ độ x và y
		g2.drawImage(image,x,y,gp.tileSize,gp.tileSize,null);
		
	}
}
