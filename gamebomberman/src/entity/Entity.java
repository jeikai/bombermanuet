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
	// State
	public GamePanel gp;
	public int worldX,worldY;
	public int speed;
	public BufferedImage up1,up2,down1,down2, left1, left2, right1, right2;
	public String direction = "down";
	public boolean onPath = true;
	
	public int spriteCounter = 0;
	public int spriteNum = 1;
	public Rectangle solidArea;
	
	public int solidAreaDefaultX, solidAreaDefaultY;
	public boolean collisionOn = false;
	public int actionLockCounter = 0;
	
	//Dialogue in game
	public String dialogue[] = new String[20];
	public int dialogueIndex = 0;
		
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
	// ENTITY STATUS
    public boolean alive = true;
    public int bombCount;
    public int bombXpos, bombYpos;
	
	public Entity(GamePanel gp) {
		this.gp = gp;
		solidArea =  new Rectangle(0,0,gp.tileSize,gp.tileSize);
	}
	
	public void setAction() {
	}
	
	public void checkCollision() {

		collisionOn = false;
		gp.cChecker.checkTile(this);
		gp.cChecker.checkObject(this, false);
		gp.cChecker.checkEntity(this, gp.npc);
		boolean contactPlayer = gp.cChecker.checkPlayer(this);
		gp.cChecker.checkEntity(this, gp.bTile);
		// if this is monster and contact a player and breakable tile
		if(this.type == 1) {
			if(contactPlayer == true) {
				if(gp.player.invincible == false) {
					// give damage 
					gp.player.life--;
					gp.player.invincible = true;
				}
			}
		}
	}
	
	
	public void update() {
		setAction();
		
		checkCollision();
		
		
		if (collisionOn == false) {
			switch (direction) {
			case "up":
				worldY -= speed;
				break;
			case "down":
				worldY += speed;
				break;
			case "left":
				worldX -= speed;
				break;
			case "right":
				worldX += speed;
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
		
		int screenX = worldX - gp.player.worldX + gp.player.screenX;
		int screenY = worldY - gp.player.worldY + gp.player.screenY;
		
		if(worldX + gp.tileSize >gp.player.worldX - gp.player.screenX &&
				worldX-gp.tileSize < gp.player.worldX + gp.player.screenX &&
				worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
				worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
			
			g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize,null);
		}
		
	}
	public void speak() {}
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
	
	public void searchPath(int goalCol, int goalRow) {
		
		int startCol = (worldX + solidArea.x)/gp.tileSize;
		int startRow = (worldY + solidArea.y)/ gp.tileSize;
		
		gp.pFinder.setNodes(startCol, startRow, goalCol, goalRow);
		
		if(gp.pFinder.search() == true) {
			
			//next worldX and worldY
			int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
			int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;
			
			//entity solid area pos
			int enLeftX = worldX + solidArea.x;
			int enRightX = worldX + solidArea.x + solidArea.width;
			int enTopY = worldY + solidArea.y;
			int enBottomY = worldY + solidArea.y + solidArea.height;
			
			// now compare entity current pos to next move's pos
			
			// many ifs here to ensure npc wont get stuck ^^
			if(enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
				direction = "up";
			}
			else if(enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
				direction = "down";
			}else if(enTopY >= nextY && enBottomY < nextY + gp.tileSize) {
				// left or right
				if(enLeftX > nextX) {
					direction = "left";
					
				}
				if(enLeftX < nextX) {
					direction  = "right";
				}
			}
			else if(enTopY > nextY && enLeftX > nextX) {
				// up or left
				direction = "up";
				checkCollision();
				if(collisionOn == true) {
					direction = "left";
				}
			}
			else if(enTopY > nextY && enLeftX < nextX) {
				// up or right
				direction = "up";
				checkCollision();
				if(collisionOn == true) {
					direction = "right";
				}
			}
			else if(enTopY <nextY && enLeftX > nextX) {
				// down or left
				direction = "down";
				checkCollision();
				if(collisionOn == true) {
					direction = "left";
				}
			}
			else if(enTopY <nextY && enLeftX < nextX) {
				// down or right
				direction = "down";
				checkCollision();
				if(collisionOn == true) {
					direction = "right";
				}
			}
			
//			// dung khi da den noi
//			int nextCol = gp.pFinder.pathList.get(0).col;
//			int nextRow = gp.pFinder.pathList.get(0).row;
//			if(nextCol == goalCol && nextRow == goalRow) {
//				onPath = false;
//			}
		}
	}
}
