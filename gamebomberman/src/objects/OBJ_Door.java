package objects;

import entity.Entity;
import main.GamePanel;

public class OBJ_Door extends Entity{
	
	public OBJ_Door(GamePanel gp) {
		super(gp);
		name = "Door";
		down1 = setup("/objects/door"); // down la huong mac dinh nen dat down de no tu tao khi chay game
		collision = true;
	}
}