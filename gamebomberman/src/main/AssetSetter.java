package main;

import breakableTiles.BreakableWall;
import entity.NPC_Orc;
import objects.OBJ_Speed;
import objects.OBJ_Tent;

public class AssetSetter {

	GamePanel gp;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
<<<<<<< HEAD
		if( gp.currentMap==0) {
			gp.obj[0][1] = new OBJ_Speed(gp);
			gp.obj[0][1].worldX = 9 * gp.tileSize;
			gp.obj[0][1].worldY = 9 * gp.tileSize;
			gp.obj[0][2] = new OBJ_Speed(gp);
			gp.obj[0][2].worldX = 6 * gp.tileSize;
			gp.obj[0][2].worldY = 4 * gp.tileSize;
			gp.obj[0][3] = new OBJ_Speed(gp);
			gp.obj[0][3].worldX = 3 * gp.tileSize;
			gp.obj[0][3].worldY = 5 * gp.tileSize;
			gp.obj[0][4] = new OBJ_Key(gp);
			gp.obj[0][4].worldX = 1 * gp.tileSize;
			gp.obj[0][4].worldY = 1 * gp.tileSize;
			gp.obj[0][5] = new OBJ_Key(gp);
			gp.obj[0][5].worldX = 48 * gp.tileSize;
			gp.obj[0][5].worldY = 2 * gp.tileSize;
			gp.obj[0][6] = new OBJ_Door(gp);
			gp.obj[0][6].worldX = 6 * gp.tileSize;
			gp.obj[0][6].worldY = 3 * gp.tileSize;
			gp.obj[0][7] = new OBJ_Door(gp);
			gp.obj[0][7].worldX = 9 * gp.tileSize;
			gp.obj[0][7].worldY = 27 * gp.tileSize;
			gp.obj[0][8] = new OBJ_Tent(gp);
			gp.obj[0][8].worldX = 18 * gp.tileSize;
			gp.obj[0][8].worldY = 18 * gp.tileSize;
		}
		if(gp.currentMap == 1) {
			gp.obj[1][1] = new OBJ_Speed(gp);
			gp.obj[1][1].worldX = 10 * gp.tileSize;
			gp.obj[1][1].worldY = 20 * gp.tileSize;
			gp.obj[1][2] = new OBJ_Speed(gp);
			gp.obj[1][2].worldX = 15 * gp.tileSize;
			gp.obj[1][2].worldY = 17 * gp.tileSize;
			gp.obj[1][3] = new OBJ_Speed(gp);
			gp.obj[1][3].worldX = 40 * gp.tileSize;
			gp.obj[1][3].worldY = 35 * gp.tileSize;
			gp.obj[1][4] = new OBJ_Key(gp);
			gp.obj[1][4].worldX = 25 * gp.tileSize;
			gp.obj[1][4].worldY = 10 * gp.tileSize;
			gp.obj[1][5] = new OBJ_Key(gp);
			gp.obj[1][5].worldX = 26 * gp.tileSize;
			gp.obj[1][5].worldY = 30 * gp.tileSize;
			gp.obj[1][6] = new OBJ_Door(gp);
			gp.obj[1][6].worldX = 18 * gp.tileSize;
			gp.obj[1][6].worldY = 15 * gp.tileSize;
			gp.obj[1][7] = new OBJ_Door(gp);
			gp.obj[1][7].worldX = 5 * gp.tileSize;
			gp.obj[1][7].worldY = 18 * gp.tileSize;
			gp.obj[1][8] = new OBJ_Tent(gp);
			gp.obj[1][8].worldX = 23 * gp.tileSize;
			gp.obj[1][8].worldY = 31 * gp.tileSize;
		
		}
		if(gp.currentMap == 2) {
			gp.obj[2][1] = new OBJ_Speed(gp);
			gp.obj[2][1].worldX = 18 * gp.tileSize;
			gp.obj[2][1].worldY = 24 * gp.tileSize;
			gp.obj[2][2] = new OBJ_Speed(gp);
			gp.obj[2][2].worldX = 16 * gp.tileSize;
			gp.obj[2][2].worldY = 15 * gp.tileSize;
			gp.obj[2][3] = new OBJ_Speed(gp);
			gp.obj[2][3].worldX = 42 * gp.tileSize;
			gp.obj[2][3].worldY = 36 * gp.tileSize;
			gp.obj[2][4] = new OBJ_Key(gp);
			gp.obj[2][4].worldX = 48 * gp.tileSize;
			gp.obj[2][4].worldY = 34 * gp.tileSize;
			gp.obj[2][5] = new OBJ_Key(gp);
			gp.obj[2][5].worldX = 29 * gp.tileSize;
			gp.obj[2][5].worldY = 35 * gp.tileSize;
			gp.obj[2][6] = new OBJ_Door(gp);
			gp.obj[2][6].worldX = 29 * gp.tileSize;
			gp.obj[2][6].worldY = 42 * gp.tileSize;
			gp.obj[2][7] = new OBJ_Door(gp);
			gp.obj[2][7].worldX = 5 * gp.tileSize;
			gp.obj[2][7].worldY = 18 * gp.tileSize;
			gp.obj[2][8] = new OBJ_Tent(gp);
			gp.obj[2][8].worldX = 20 * gp.tileSize;
			gp.obj[2][8].worldY = 20 * gp.tileSize;
		
		}
		
	}
	
	public void setNPC() {
		if (gp.currentMap == 0) { 
		gp.npc[0][0] = new NPC_Orc(gp);
		gp.npc[0][0].worldX = gp.tileSize*3;
		gp.npc[0][0].worldY = gp.tileSize * 7;
		gp.npc[0][1] = new NPC_Orc(gp);
		gp.npc[0][1].worldX = gp.tileSize*2;
		gp.npc[0][1].worldY = gp.tileSize * 7;
		}
		
		if (gp.currentMap == 1) { 
			gp.npc[1][0] = new NPC_Orc(gp);
			gp.npc[1][0].worldX = gp.tileSize*2;
			gp.npc[1][0].worldY = gp.tileSize * 2;
			gp.npc[1][1] = new NPC_Orc(gp);
			gp.npc[1][1].worldX = gp.tileSize*32;
			gp.npc[1][1].worldY = gp.tileSize * 64;
		}
		if (gp.currentMap == 3) { 
			gp.npc[3][0] = new NPC_Orc(gp);
			gp.npc[3][0].worldX = gp.tileSize*9;
			gp.npc[3][0].worldY = gp.tileSize * 10;
			gp.npc[3][1] = new NPC_Orc(gp);
			gp.npc[3][1].worldX = gp.tileSize*8;
			gp.npc[3][1].worldY = gp.tileSize * 8;
			}
		
=======
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
>>>>>>> d0ddf5b536046912bdfb800eaaff24582626f31f
	}
	
	public void setBreakableTile(){
		int i = 0;
		
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		
		while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
			// lay ra so luu trong mapTileNum, neu la so 2 thi tao 1 cai breakable wall
			int tileNum = gp.tileM.mapTileNum[gp.currentMap][col][row];
			
			if(tileNum == 2) {
<<<<<<< HEAD
				gp.bTile[gp.currentMap][i] = new BreakableWall(gp);
				gp.bTile[gp.currentMap][i].worldX = gp.tileSize * col;
				gp.bTile[gp.currentMap][i].worldY = gp.tileSize * row;
=======
				gp.bTile[i] = new BreakableWall(gp);
				gp.bTile[i].worldX = gp.tileSize * col;
				gp.bTile[i].worldY = gp.tileSize * row;
>>>>>>> d0ddf5b536046912bdfb800eaaff24582626f31f
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
