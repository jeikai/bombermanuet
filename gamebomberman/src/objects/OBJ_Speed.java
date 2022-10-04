package objects;

import entity.Entity;
import main.GamePanel;


public class OBJ_Speed extends Entity{
	
	public OBJ_Speed(GamePanel gp){
		super(gp);
		name = "Speed";
		down1 = setup("/objects/speed"); // down is default direction
		collision = true;
	}
}
