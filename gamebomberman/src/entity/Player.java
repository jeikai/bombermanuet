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

	public final int screenX;
	public final int screenY;
	public int dialogueMap = 0;
	// Key
	int hasKey = 0;

	// dan bay theo 4 huong
	public Player(GamePanel gp, KeyHandler keyH) {
		super(gp);
		this.keyH = keyH;

		screenX = gp.screenWidth / 2 - (gp.tileSize / 2);
		screenY = gp.screenHeight / 2 - (gp.tileSize / 2);

		solidArea = new Rectangle(15,20,20,20);// nho hon player
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

		worldX = gp.tileSize * 8;
		worldY = gp.tileSize * 8;
		speed = 7;
		direction = "down";

		// player status
		maxLife = 6;
		life = maxLife - 4;
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

		if (gp.keyH.spacePressed == true && projectileDown.alive == false && projectileUp.alive == false
				&& projectileLeft.alive == false && projectileRight.alive == false) {

			// dat vi tri cho projectile
			bombXpos = (worldX + gp.tileSize / 2) - ((worldX + gp.tileSize / 2) % gp.tileSize);
			bombYpos = (worldY + gp.tileSize / 2) - ((worldY + gp.tileSize / 2) % gp.tileSize);
			bomb.set(bombXpos, bombYpos, "down", true, this);
			gp.projectileList.add(bomb);
			projectileUp.set(bombXpos, bombYpos, "up", true, this);
			projectileDown.set(bombXpos, bombYpos, "down", true, this);
			projectileLeft.set(bombXpos, bombYpos, "left", true, this);
			projectileRight.set(bombXpos, bombYpos, "right", true, this);
			// delay thi bom no
			new java.util.Timer().schedule(new java.util.TimerTask() {
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
					// 2000
					(bomb.maxLife / gp.FPS) * 1000);
		}

		// xu ly khi bi va cham voi quai
		if (invincible == true) {
			invincibleCounter++;
			if (invincibleCounter > 120) {
				invincible = false;
				invincibleCounter = 0;
			}
		}
		if (life <= 0) {
			gp.gameState = gp.gameOverState;
		}
		if (gp.currentMap > 2) {
			gp.gameState = gp.gameWinState;
		}
	}

	public void pickUpObject(int i) {

		if (i != 999) {
			String name = gp.obj[gp.currentMap][i].name;// FIXED

			switch (name) {
			case "Speed":
				speed += 1;
				gp.obj[gp.currentMap][i] = null;// FIXED
				break;
			case "Key":
				hasKey++;
				gp.obj[gp.currentMap][i] = null;// FIXED
				break;
			case "Door":
				if(hasKey > 0) {
					gp.obj[gp.currentMap][i] = null;//FIXED
					
					hasKey--;
				} else {
					gp.gameState = gp.dialogueState;
					gp.obj[gp.currentMap][i].speak();
				}
				break;
			case "Tent":
				gp.currentMap++;
				setDefaultValues();
				gp.aSetter.setBreakableTile();
				gp.aSetter.setObject();
				gp.aSetter.setNPC();
<<<<<<< HEAD
				dialogueMap++;
=======
				break;
			case "Heal":
				life+=1;
				gp.obj[gp.currentMap][i] = null;
>>>>>>> 47002431aff3a310ca901b7a24a2c9b9b9b9c893
			}
		}
	}

	public void interactNPC(int i) {
		if (i != 999) {
			if (invincible == false) {
				speed = 4;
				life--;
				invincible = true;
				gp.gameState = gp.dialogueState;
				gp.npc[dialogueMap][i].speak();
			}

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
