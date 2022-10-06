package objects;

import entity.Projectile;
import main.GamePanel;

public class OBJ_Bomb extends Projectile{

	public OBJ_Bomb(GamePanel gp) {
		super(gp);
		name = "Bomb";
		down1 = setup("/objects/bomb1");
		down2 = setup("/objects/bomb2");
//		up1 = left1 = right1 = down1;
//		up2 = left2 = right2 = down2;
		maxLife = 120;
		life = maxLife;

	}
}