package objects;

import entity.Entity;
import main.GamePanel;

public class OBJ_Door extends Entity{
	
	public OBJ_Door(GamePanel gp) {
		super(gp);
		name = "Door";
		down1 = setup("/objects/door"); // down la huong mac dinh nen dat down de no tu tao khi chay game
		collision = true;
		setDialogue();
	}
	public void setDialogue() {
		dialogue[0] = "You need a Key to open.";
	}
	public void speak() {
		gp.ui.currentDialogue = dialogue[dialogueIndex];
	}
}