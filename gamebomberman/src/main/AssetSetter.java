package main;

import breakableTiles.BreakableWall;
import entity.NPC_Orc;
import objects.OBJ_Door;
import objects.OBJ_Key;
import objects.OBJ_Speed;

public class AssetSetter {

	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
//		gp.obj[0] = new OBJ_Speed(gp);
//		gp.obj[0].x = 37 * gp.tileSize;
//		gp.obj[0].y = 42 * gp.tileSize;
//		gp.obj[1] = new OBJ_Speed(gp);
//		gp.obj[1].x = 10 * gp.tileSize;
//		gp.obj[1].y = 7 * gp.tileSize;
//		gp.obj[2] = new OBJ_Key(gp);
//		gp.obj[2].x = 23 * gp.tileSize;
//		gp.obj[2].y = 40 * gp.tileSize;
//		gp.obj[3] = new OBJ_Door(gp);
//		gp.obj[3].x = 10 * gp.tileSize;
//		gp.obj[3].y = 11 * gp.tileSize;
//		gp.obj[4] = new OBJ_Key(gp);
//		gp.obj[4].x = 37 * gp.tileSize;
//		gp.obj[4].y = 7 * gp.tileSize;
//		gp.obj[5] = new OBJ_Door(gp);
//		gp.obj[5].x = 12 * gp.tileSize;
//		gp.obj[5].y = 22 * gp.tileSize;
	}
	
	public void setNPC() {
//		gp.npc[0] = new NPC_Orc(gp);
//		gp.npc[0].x = gp.tileSize * 3;
//		gp.npc[0].y = gp.tileSize * 7;
//		gp.npc[1] = new NPC_Orc(gp);
//		gp.npc[1].x = gp.tileSize*2;
//		gp.npc[1].y = gp.tileSize * 7;
	}
	
	public void setBreakableTile(){
		int i = 0;
		
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		
		while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
			// lay ra so luu trong mapTileNum, neu la so 2 thi tao 1 cai breakable wall
			int tileNum = gp.tileM.mapTileNum[col][row];
			
			if(tileNum == 2) {
				gp.bTile[i] = new BreakableWall(gp);
				gp.bTile[i].x = gp.tileSize*col;
				gp.bTile[i].y = gp.tileSize * row;
				i++;
			}
			col++;
			
			if(col == gp.maxScreenCol) {
				col = 0;
				row++;
			}
		}
	}
		
}
