package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;
import objects.OBJ_Bomb;
import objects.OBJ_Fire;

public class Player extends Entity {
	KeyHandler keyH;
	int hasKey=0;
	
	public final int screenX;
	public final int screenY;
	
	// dan bay theo 4 huong
	public Player(GamePanel gp, KeyHandler keyH) {
		super(gp);
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - (gp.tileSize/2);
		screenY = gp.screenHeight/2 - (gp.tileSize/2);
		
		solidArea = new Rectangle(5, 5, 24, 24);// nho hon player
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		setDefaultValues();
		getPlayerImage();
		bomb = new OBJ_Bomb(gp);
		projectileUp = new OBJ_Fire(gp);
		projectileDown = new OBJ_Fire(gp);
		projectileLeft = new OBJ_Fire(gp);
		projectileRight = new OBJ_Fire(gp);
	}

	public void setDefaultValues() {
		x = gp.tileSize * 23;
		y = gp.tileSize * 21;
		speed = 4;
		direction = "down";

		// player status
		maxLife = 6;
		life = maxLife - 4;
		bombCount = 1;
	}

	public void getPlayerImage() {

		up1 = setup("/player/up1");
		up2 = setup("/player/up2");
		down1 = setup("/player/down1");
		down2 = setup("/player/down2");
		left1 = setup("/player/left1");
		left2 = setup("/player/left2");
		right1 = setup("/player/right1");
		right2 = setup("/player/right2");
	}

	public void update() {
		if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {

			if (keyH.upPressed == true) {
				direction = "up";
			}
			if (keyH.downPressed == true) {
				direction = "down";
			}
			if (keyH.leftPressed == true) {
				direction = "left";
			}
			if (keyH.rightPressed == true) {
				direction = "right";
			}

			// check va cham voi tuong
			collisionOn = false;
			gp.cChecker.checkTile(this);

			// check va cham voi breakable tile
			gp.cChecker.checkEntity(this, gp.bTile);

			// check object collision
			int objIndex = gp.cChecker.checkObject(this, true);
			pickUpObject(objIndex);

			// check va cham monster va Fire
			int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
			interactNPC(npcIndex);
			
			
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
		
		
		if (gp.keyH.spacePressed == true && projectileDown.alive == false
				&& projectileUp.alive == false && projectileLeft.alive == false
				&& projectileRight.alive == false) {
			//bomb.set(x, y, direction, true, this);
			//gp.bombList.add(bomb);
			// dat vi tri cho projectile
			bomb.set(x,y,"down",true,this);
			gp.projectileList.add(bomb);
			projectileUp.set(x, y, "up", true, this);
			projectileDown.set(x, y, "down", true, this);
			projectileLeft.set(x, y, "left", true, this);
			projectileRight.set(x, y, "right", true, this);
			// delay thi bom no
			new java.util.Timer().schedule( 
			        new java.util.TimerTask() {
			            @Override
			            public void run() {
			                // your code here
			            	// them vao danh sach cac projectile
			    			gp.projectileList.add(projectileUp);
			    			gp.projectileList.add(projectileDown);
			    			gp.projectileList.add(projectileLeft);
			    			gp.projectileList.add(projectileRight);
			            	
			            }
			        }, 
			        //2000 
			        (bomb.maxLife/60)*1000
			);
//			// dat vi tri cho projectile
//			projectileUp.set(x, y, "up", true, this);
//			projectileDown.set(x, y, "down", true, this);
//			projectileLeft.set(x, y, "left", true, this);
//			projectileRight.set(x, y, "right", true, this);
//			// them vao danh sach cac projectile
//			gp.projectileList.add(projectileUp);
//			gp.projectileList.add(projectileDown);
//			gp.projectileList.add(projectileLeft);
//			gp.projectileList.add(projectileRight);
		}

		// xu ly khi bi va cham voi quai
		if (invincible == true) {
			//gp.playSE(5);
			invincibleCounter++;
			if (invincibleCounter > 120) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
	}


	public void pickUpObject(int i) {

		if (i != 999) {
			String name = gp.obj[i].name;

			switch (name) {
			case "Speed":
				gp.playSE(2);
				speed += 2;
				gp.obj[i] = null;
				break;
			case "Key":
				gp.playSE(1);
				hasKey++;
				gp.obj[i] = null;
				System.out.println("Key:"+hasKey);
				break;
			case "Door":
				if(hasKey > 0) {
					gp.playSE(3);
					gp.obj[i] = null;
					hasKey--;
				}
				System.out.println("Key: "+hasKey);
				break;
			}
			
		}
	}

	public void interactNPC(int i) {
		if (i != 999) {
		if (invincible == false) {
			speed = 4;
			life--;
			invincible = true;
		}

		if (life <= 0)
			setDefaultValues();
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

		// lam mo nhan vat khi bi giet
		if (invincible == true) {
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
		}

		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

		// reset alpha
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
	}
}
