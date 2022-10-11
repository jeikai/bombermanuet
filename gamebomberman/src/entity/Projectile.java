package entity;

import main.GamePanel;
import objects.OBJ_Fire;

public class Projectile extends Entity {

	protected Entity user;

	public Projectile(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
		solidArea.x = gp.tileSize/8;
		solidArea.y = gp.tileSize/8;
		solidArea.width = gp.tileSize -8 ;
		solidArea.height = gp.tileSize -8;
	}

	public void set(int worldX, int worldY, String direction, boolean alive, Entity user) {
		this.worldX = worldX;
		this.worldY = worldY;
		this.direction = direction;
		this.alive = alive;
		this.user = user;
		this.life = this.maxLife;
	}

	
	public void update() {
		
		if (user == gp.player) {
			gp.cChecker.checkTileProjectile(this);
			
			// xu ly khi lua cham npc
			if(name == "Fire") {
				int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
				if (npcIndex != 999) {
					// giet monster
					gp.npc[npcIndex] = null;
					alive = false;
				}
			}
			// xu ly khi lua cham tuong
			if(name=="Fire") {
				int playerIndex = gp.cChecker.checkCollidePlayer(this,gp.player);
				if(playerIndex != 999) {
					
					if(gp.player.invincible == false) {
						// give damage 
						gp.player.life--;
						gp.player.invincible = true;
					}
					if(gp.player.life<= 0) {
						gp.player.setDefaultValues();
					}
				}
			}
			
			int bTileIndex = gp.cChecker.checkEntity(this, gp.bTile);
			if (bTileIndex != 999) {
				this.alive = false;
				damageBreakableTile(bTileIndex);
			}
			
		}
		if (user != gp.player) {

		}
		if(name == "Fire") {
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
		

		life--;
		if (life <= 0) {
			alive = false;
		}

		spriteCounter++;
		if (spriteCounter > 24) {
			if (spriteNum == 1) {
				spriteNum = 2;
			} else if (spriteNum == 2) {
				spriteNum = 1;
			}
			spriteCounter = 0;
		}
	}

	public void damageBreakableTile(int i) {

		if (i != 999 && gp.bTile[i].destructible == true) {

			gp.bTile[i] = null;
		}
	}

}
