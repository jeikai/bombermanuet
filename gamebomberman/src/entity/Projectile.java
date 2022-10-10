package entity;

import main.GamePanel;
import objects.OBJ_Fire;

public class Projectile extends Entity {

	protected Entity user;

	public Projectile(GamePanel gamePanel) {
		super(gamePanel);
		// TODO Auto-generated constructor stub
		solidArea.x = 0;
		solidArea.y = 0;
		solidArea.width =40;
		solidArea.height = 40;
	}

	public void set(int x, int y, String direction, boolean alive, Entity user) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.alive = alive;
		this.user = user;
		this.life = this.maxLife;
	}

	
	public void update() {
		
		if (user == gp.player) {
			gp.cChecker.checkTileProjectile(this);
			
			int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
			if (npcIndex != 999) {
				// giet monster
				gp.npc[npcIndex] = null;
				alive = false;
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
			
			if(name!= "Bomb") {
				int bTileIndex = gp.cChecker.checkEntity(this, gp.bTile);
				if (bTileIndex != 999) {
					this.alive = false;
					damageBreakableTile(bTileIndex);
				}
			}
			
			
			
		}
		if (user != gp.player) {

		}
		if(name == "Fire") {
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
