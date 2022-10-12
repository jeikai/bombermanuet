package objects;

import entity.Entity;
import main.GamePanel;

public class OBJ_Heal extends Entity{
	public OBJ_Heal(GamePanel gp) {
		super(gp);
		name="Heal";
		down1 = setup("/objects/heal"); // down la huong mac dinh nen dat down de no tu tao khi chay game
		collision = true;
	}

}
