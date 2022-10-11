package objects;

import entity.Entity;
import main.GamePanel;

public class OBJ_Tent extends Entity{
	public OBJ_Tent(GamePanel gp){
		super(gp);
		name = "Tent";
		down1 = setup("/objects/tent"); // down la huong mac dinh nen dat down de no tu tao khi chay game
		collision = true;
	}
}
