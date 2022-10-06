package objects;

import entity.Projectile;
import main.GamePanel;

public class OBJ_Fire extends Projectile{

	GamePanel gp;

	public OBJ_Fire(GamePanel gp) {
		super(gp);
		// TODO Auto-generated constructor stub
		this.gp = gp;
		alive = false;
		name = "Fire";
		speed = 10;
		maxLife = 20;
		life = maxLife;
		up1 = setup("/objects/fireball_up_1");
		up2 = setup("/objects/fireball_up_2");
		left1 = setup("/objects/fireball_left_1");
		left2 = setup("/objects/fireball_left_2");
		right1 = setup("/objects/fireball_right_1");
		right2 = setup("/objects/fireball_right_2");
		down1 = setup("/objects/fireball_down_1");
		down2 = setup("/objects/fireball_down_2");
	}
	
}
