package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;
import objects.OBJ_Bomb;

public class Entity {
	GamePanel gp;
	public int x,y;
	public int speed;
	
	public BufferedImage up1,up2,down1,down2, left1, left2, right1, right2;
	public String direction = "down";
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	public Rectangle solidArea = new Rectangle(0,0,15,15);
	
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
	public int actionLockCounter = 0;
	
	public BufferedImage image, image2, image3;
	public String name;
	public boolean collision = false;
	public int type ; // 1 = npc , 0 = player; 2 = fire
	
	// objects 
	public Projectile projectileUp,projectileDown,projectileLeft,projectileRight,bomb;

	//Character status
	public int maxLife;
	public int life;
	public boolean invincible = false;
	public int invincibleCounter;
	boolean spaceNotPressed = true;
	// ENTITY STATUS
    public boolean alive = true;
    public int bombCount;
    public boolean fired = false;
	
	public Entity(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setAction() {
	}
	
	public void update() {
		setAction();
		
		
		collisionOn = false;
		gp.cChecker.checkTile(this);
		//gp.cChecker.checkObject(this, false);
		boolean contactPlayer = gp.cChecker.checkPlayer(this);
		int contactBreakableTile = gp.cChecker.checkEntity(this, gp.bTile);
		// if this is monster and contact a player and breakable tile
		if(this.type == 1) {
			if(contactPlayer == true) {
				if(gp.player.invincible == false) {
					// give damage 
					gp.player.life--;
					gp.player.invincible = true;
				}
			}
			if(contactBreakableTile != 999) {
				collisionOn = true;
			}
		}
		
		
		if (collisionOn == false) {
			switch (direction) {
			case "up":
				y -= speed;
				break;
			case "down":
				y += speed;
				break;
			case "left":
				x -= speed;
				break;
			case "right":
				x += speed;
				break;
			}
		}

		spriteCounter++;
		if (spriteCounter > 8) {
			if (spriteNum == 1)
				spriteNum = 2;
			else if (spriteNum == 2)
				spriteNum = 1;

			spriteCounter = 0;
		}
	}
	
	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		switch (direction) {
		case "up":
			if (spriteNum == 1)
				image = up1;
			if (spriteNum == 2)
				image = up2;
			break;
		case "down":
			if (spriteNum == 1)
				image = down1;
			if (spriteNum == 2)
				image = down2;
			break;
		case "left":
			if (spriteNum == 1)
				image = left1;
			if (spriteNum == 2)
				image = left2;
			break;
		case "right":
			if (spriteNum == 1)
				image = right1;
			if (spriteNum == 2)
				image = right2;
			break;
		}
		
		g2.drawImage(image, x, y, gp.tileSize, gp.tileSize,null);
	}
	
	public BufferedImage setup(String imagePath) {
		UtilityTool uTool = new UtilityTool();
		BufferedImage image = null;
		
		
		try {
			image = ImageIO.read(getClass().getResourceAsStream(  imagePath +".png"));
			image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return image;
	}

	public void updateBomb() {
		// TODO Auto-generated method stub
		
	}
}
