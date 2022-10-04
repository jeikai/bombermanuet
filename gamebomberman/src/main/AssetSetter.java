package main;

import objects.OBJ_Bomb;
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
		gp.obj[3].y = 7 * gp.tileSize;
	}
}
