
package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_Orc extends Entity {
	public NPC_Orc(GamePanel gp) {
		super(gp);
		type = 1;
		direction = "down";
		speed = 2;
		solidArea.width = 42;
		solidArea.height = 40;
		solidArea.x = 3;
		solidArea.y = 3;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		onPath = false;
		getImage();
	}

	public void getImage() {

		up1 = setup("/npc/orc_up_1");
		up2 = setup("/npc/orc_up_2");
		down1 = setup("/npc/orc_down_1");
		down2 = setup("/npc/orc_down_2");
		left1 = setup("/npc/orc_left_1");
		left2 = setup("/npc/orc_left_2");
		right1 = setup("/npc/orc_right_1");
		right2 = setup("/npc/orc_right_2");
	}
	
	// override update 
	public void update() {
		
		super.update();
		
		int xDistance = Math.abs(worldX - gp.player.worldX);
		int yDistance = Math.abs(worldY - gp.player.worldY);
		int tileDistance = (xDistance + yDistance) / gp.tileSize;
		
		if(onPath == false && tileDistance < 10) {
			onPath = true;
		}
		if(onPath == true && tileDistance > 20) {
			onPath = false;
		}
	}
	
	public void setAction() {

		if (onPath == true) {
			
			int goalCol = (gp.player.worldX + gp.player.solidArea.x) / gp.tileSize;
			int goalRow = (gp.player.worldY + gp.player.solidArea.y) / gp.tileSize;
			
			searchPath(goalCol,goalRow);
		} else {
			actionLockCounter++;
			if (actionLockCounter == 60) {

				Random random = new Random();
				int i = random.nextInt(100) + 1; // chon 1 so ngau nhien

				if (i <= 25) {
					direction = "up";
				}
				if (i > 25 && i <= 50) {
					direction = "down";
				}
				if (i > 50 && i <= 75) {
					direction = "left";
				}
				if (i > 75 && i <= 100) {
					direction = "right";
				}

				actionLockCounter = 0;
			}

		}

	}
}
