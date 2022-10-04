package main;

import entity.NPC_Orc;
import objects.OBJ_Speed;

public class AssetSetter {

	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		
		gp.obj[1] = new OBJ_Speed(gp);
		gp.obj[1].x = 9 * gp.tileSize;
		gp.obj[1].y = 9 * gp.tileSize;
		gp.obj[2] = new OBJ_Speed(gp);
		gp.obj[2].x = 6 * gp.tileSize;
		gp.obj[2].y = 4 * gp.tileSize;
		gp.obj[3] = new OBJ_Speed(gp);
		gp.obj[3].x = 3 * gp.tileSize;
		gp.obj[3].y = 5 * gp.tileSize;
	}
	
	public void setNPC() {
		gp.npc[0] = new NPC_Orc(gp);
		gp.npc[0].x = gp.tileSize*3;
		gp.npc[0].y = gp.tileSize * 7;
	}
}
