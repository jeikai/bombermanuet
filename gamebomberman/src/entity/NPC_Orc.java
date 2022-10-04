package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_Orc extends Entity {
	public NPC_Orc(GamePanel gp) {
		super(gp);

		direction = "down";
		speed = 2;
		
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
	
	public void setAction() {
		
		actionLockCounter ++;
		if(actionLockCounter == 60) {

			Random random = new Random();
			int i = random.nextInt(100)+1; // chon 1 so ngau nhien
			
			if(i<= 25) {
				direction = "up";
			}
			if(i>25 && i<= 50) {
				direction = "down";
			}
			if(i>50 && i<= 75) {
				direction = "left";
			}
			if(i>75 && i<= 100) {
				direction = "right";
			}
			
			actionLockCounter = 0;
		}
		
	}
}
