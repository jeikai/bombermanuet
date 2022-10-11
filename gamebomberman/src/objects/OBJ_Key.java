package objects;

import entity.Entity;
import main.GamePanel;

public class OBJ_Key extends Entity{
	
	public OBJ_Key(GamePanel gp) {
		super(gp);
		name = "Key";
		down1 = setup("/objects/key"); // down la huong mac dinh nen dat down de no tu tao khi chay game
		collision = true;
	}
}
