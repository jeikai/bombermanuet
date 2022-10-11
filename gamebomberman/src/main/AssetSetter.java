package main;

import breakableTiles.BreakableWall;
import entity.NPC_Orc;
import objects.OBJ_Speed;

public class AssetSetter {

	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		gp.obj[1] = new OBJ_Speed(gp);
		gp.obj[1].worldX = 9 * gp.tileSize;
		gp.obj[1].worldY = 9 * gp.tileSize;
		gp.obj[2] = new OBJ_Speed(gp);
		gp.obj[2].worldX = 6 * gp.tileSize;
		gp.obj[2].worldY = 4 * gp.tileSize;
		gp.obj[3] = new OBJ_Speed(gp);
		gp.obj[3].worldX = 3 * gp.tileSize;
		gp.obj[3].worldY = 5 * gp.tileSize;
	}
	
	public void setNPC() {
		gp.npc[0] = new NPC_Orc(gp);
		gp.npc[0].worldX = gp.tileSize*3;
		gp.npc[0].worldY = gp.tileSize * 7;
		gp.npc[1] = new NPC_Orc(gp);
		gp.npc[1].worldX = gp.tileSize*2;
		gp.npc[1].worldY = gp.tileSize * 7;
	}
	
	public void setBreakableTile(){
		int i = 0;
		
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		
		while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
			// lay ra so luu trong mapTileNum, neu la so 2 thi tao 1 cai breakable wall
			int tileNum = gp.tileM.mapTileNum[col][row];
			
			if(tileNum == 2) {
				gp.bTile[i] = new BreakableWall(gp);
				gp.bTile[i].worldX = gp.tileSize * col;
				gp.bTile[i].worldY = gp.tileSize * row;
				i++;
			}
			col++;
			
			if(col == gp.maxWorldCol) {
				col = 0;
				row++;
			}
		}
	}
		
}
