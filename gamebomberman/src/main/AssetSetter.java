package main;

import breakableTiles.BreakableWall;
import entity.NPC_Orc;
import objects.OBJ_Door;
import objects.OBJ_Heal;
import objects.OBJ_Key;
import objects.OBJ_Speed;
import objects.OBJ_Tent;

public class AssetSetter {

	GamePanel gp;

	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}

	public void setObject() {
		if (gp.currentMap == 0) {
			gp.obj[0][1] = new OBJ_Speed(gp);
			gp.obj[0][1].worldX = 7 * gp.tileSize;
			gp.obj[0][1].worldY = 9 * gp.tileSize;
			gp.obj[0][2] = new OBJ_Speed(gp);
			gp.obj[0][2].worldX = 6 * gp.tileSize;
			gp.obj[0][2].worldY = 4 * gp.tileSize;
			gp.obj[0][3] = new OBJ_Speed(gp);
			gp.obj[0][3].worldX = 15 * gp.tileSize;
			gp.obj[0][3].worldY = 42 * gp.tileSize;
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
			gp.obj[0][8].worldX = 25 * gp.tileSize;
			gp.obj[0][8].worldY = 25 * gp.tileSize;
			gp.obj[0][9] = new OBJ_Heal(gp);
			gp.obj[0][9].worldX = 1 * gp.tileSize;
			gp.obj[0][9].worldY = 4 * gp.tileSize;
		}
		if (gp.currentMap == 1) {
			gp.obj[1][1] = new OBJ_Speed(gp);
			gp.obj[1][1].worldX = 4 * gp.tileSize;
			gp.obj[1][1].worldY = 4 * gp.tileSize;
			gp.obj[1][2] = new OBJ_Speed(gp);
			gp.obj[1][2].worldX = 15 * gp.tileSize;
			gp.obj[1][2].worldY = 40 * gp.tileSize;
			gp.obj[1][3] = new OBJ_Speed(gp);
			gp.obj[1][3].worldX = 33 * gp.tileSize;
			gp.obj[1][3].worldY = 47 * gp.tileSize;
			gp.obj[1][4] = new OBJ_Key(gp);
			gp.obj[1][4].worldX = 45 * gp.tileSize;
			gp.obj[1][4].worldY = 3 * gp.tileSize;
			gp.obj[1][5] = new OBJ_Key(gp);
			gp.obj[1][5].worldX = 42 * gp.tileSize;
			gp.obj[1][5].worldY = 7 * gp.tileSize;
			gp.obj[1][11] = new OBJ_Key(gp);
			gp.obj[1][11].worldX = 3 * gp.tileSize;
			gp.obj[1][11].worldY = 34 * gp.tileSize;
			gp.obj[1][9] = new OBJ_Key(gp);
			gp.obj[1][9].worldX = 4 * gp.tileSize;
			gp.obj[1][9].worldY = 44 * gp.tileSize;
			gp.obj[1][12] = new OBJ_Key(gp);
			gp.obj[1][12].worldX = 2 * gp.tileSize;
			gp.obj[1][12].worldY = 36 * gp.tileSize;
			gp.obj[1][10] = new OBJ_Door(gp);
			gp.obj[1][10].worldX = 4 * gp.tileSize;
			gp.obj[1][10].worldY = 39 * gp.tileSize;

			gp.obj[1][6] = new OBJ_Door(gp);
			gp.obj[1][6].worldX = 47 * gp.tileSize;
			gp.obj[1][6].worldY = 6 * gp.tileSize;
			gp.obj[1][7] = new OBJ_Door(gp);
			gp.obj[1][7].worldX = 37 * gp.tileSize;
			gp.obj[1][7].worldY = 7 * gp.tileSize;
			gp.obj[1][8] = new OBJ_Tent(gp);
			gp.obj[1][8].worldX = 26 * gp.tileSize;
			gp.obj[1][8].worldY = 44 * gp.tileSize;
			gp.obj[1][11] = new OBJ_Door(gp);
			gp.obj[1][11].worldY = 20 * gp.tileSize;
			gp.obj[1][11].worldX = 45 * gp.tileSize;

		}
		if (gp.currentMap == 2) {
			gp.obj[2][0] = new OBJ_Key(gp);
			gp.obj[2][0].worldX = 23 * gp.tileSize;
			gp.obj[2][0].worldY  = 23 * gp.tileSize;
			
			gp.obj[2][1] = new OBJ_Key(gp);
			gp.obj[2][1].worldX = 36 * gp.tileSize;
			gp.obj[2][1].worldY  = 12 * gp.tileSize;
			
			gp.obj[2][2] = new OBJ_Key(gp);
			gp.obj[2][2].worldX = 47 * gp.tileSize;
			gp.obj[2][2].worldY  = 47 * gp.tileSize;
			
			gp.obj[2][3] = new OBJ_Door(gp);
			gp.obj[2][3].worldX = 24 * gp.tileSize;
			gp.obj[2][3].worldY = 12 * gp.tileSize;
			
			gp.obj[2][4] = new OBJ_Door(gp);
			gp.obj[2][4].worldX = 40 * gp.tileSize;
			gp.obj[2][4].worldY = 24 * gp.tileSize;
			
			gp.obj[2][5] = new OBJ_Door(gp);
			gp.obj[2][5].worldX = 24 * gp.tileSize;
			gp.obj[2][5].worldY = 46 * gp.tileSize;
			
			gp.obj[2][6] = new OBJ_Tent(gp);
			gp.obj[2][6].worldX = 3 * gp.tileSize;
			gp.obj[2][6].worldY = 27 * gp.tileSize;
		}

	}

	public void setNPC() {
		if (gp.currentMap == 0) {
			gp.npc[0][0] = new NPC_Orc(gp);
			gp.npc[0][0].worldX = gp.tileSize * 4;
			gp.npc[0][0].worldY = gp.tileSize * 7;
			gp.npc[0][2] = new NPC_Orc(gp);
			gp.npc[0][2].worldX = gp.tileSize * 5;
			gp.npc[0][2].worldY = gp.tileSize * 43;
			gp.npc[0][3] = new NPC_Orc(gp);
			gp.npc[0][3].worldX = gp.tileSize * 3;
			gp.npc[0][3].worldY = gp.tileSize * 7;

			gp.npc[0][4] = new NPC_Orc(gp);
			gp.npc[0][4].worldX = gp.tileSize * 9;
			gp.npc[0][4].worldY = gp.tileSize * 43;
			gp.npc[0][5] = new NPC_Orc(gp);
			gp.npc[0][5].worldX = gp.tileSize * 38;
			gp.npc[0][5].worldY = gp.tileSize * 45;
			gp.npc[0][6] = new NPC_Orc(gp);
			gp.npc[0][6].worldX = gp.tileSize * 38;
			gp.npc[0][6].worldY = gp.tileSize * 26;
		}

		if (gp.currentMap == 1) {
			gp.npc[1][0] = new NPC_Orc(gp);
			gp.npc[1][0].worldX = gp.tileSize * 7;
			gp.npc[1][0].worldY = gp.tileSize * 10;
			gp.npc[1][1] = new NPC_Orc(gp);
			gp.npc[1][1].worldX = gp.tileSize * 5;
			gp.npc[1][1].worldY = gp.tileSize * 8;
			gp.npc[1][2] = new NPC_Orc(gp);
			gp.npc[1][2].worldX = gp.tileSize * 5;
			gp.npc[1][2].worldY = gp.tileSize * 34;
			gp.npc[1][3] = new NPC_Orc(gp);
			gp.npc[1][3].worldX = gp.tileSize * 3;
			gp.npc[1][3].worldY = gp.tileSize * 32;

			gp.npc[1][4] = new NPC_Orc(gp);
			gp.npc[1][4].worldX = gp.tileSize * 33;
			gp.npc[1][4].worldY = gp.tileSize * 44;
			gp.npc[1][5] = new NPC_Orc(gp);
			gp.npc[1][5].worldX = gp.tileSize * 35;
			gp.npc[1][5].worldY = gp.tileSize * 44;
			gp.npc[1][6] = new NPC_Orc(gp);
			gp.npc[1][6].worldX = gp.tileSize * 27;
			gp.npc[1][6].worldY = gp.tileSize * 33;
		}
		if (gp.currentMap == 2) {
			gp.npc[2][0] = new NPC_Orc(gp);
			gp.npc[2][0].worldX = gp.tileSize * 20;
			gp.npc[2][0].worldY = gp.tileSize * 2;
			gp.npc[2][1] = new NPC_Orc(gp);
			gp.npc[2][1].worldX = gp.tileSize * 20;
			gp.npc[2][1].worldY = gp.tileSize * 3;
			gp.npc[2][2] = new NPC_Orc(gp);
			gp.npc[2][2].worldX = gp.tileSize * 48;
			gp.npc[2][2].worldY = gp.tileSize * 2;
			gp.npc[2][3] = new NPC_Orc(gp);
			gp.npc[2][3].worldX = gp.tileSize * 48;
			gp.npc[2][3].worldY = gp.tileSize * 3;
			gp.npc[2][4] = new NPC_Orc(gp);
			gp.npc[2][4].worldX = gp.tileSize * 48;
			gp.npc[2][4].worldY = gp.tileSize * 4;
			gp.npc[2][5] = new NPC_Orc(gp);
			gp.npc[2][5].worldX = gp.tileSize * 27;
			gp.npc[2][5].worldY = gp.tileSize * 27;
			gp.npc[2][6] = new NPC_Orc(gp);
			gp.npc[2][6].worldX = gp.tileSize * 27;
			gp.npc[2][6].worldY = gp.tileSize * 28;
			gp.npc[2][7] = new NPC_Orc(gp);
			gp.npc[2][7].worldX = gp.tileSize * 27;
			gp.npc[2][7].worldY = gp.tileSize * 29;
			gp.npc[2][8] = new NPC_Orc(gp);
			gp.npc[2][8].worldX = gp.tileSize * 27;
			gp.npc[2][8].worldY = gp.tileSize * 30;
			gp.npc[2][9] = new NPC_Orc(gp);
			gp.npc[2][9].worldX = gp.tileSize * 17;
			gp.npc[2][9].worldY = gp.tileSize * 40;
			gp.npc[2][10] = new NPC_Orc(gp);
			gp.npc[2][10].worldX = gp.tileSize * 17;
			gp.npc[2][10].worldY = gp.tileSize * 42;
			gp.npc[2][11] = new NPC_Orc(gp);
			gp.npc[2][11].worldX = gp.tileSize * 17;
			gp.npc[2][11].worldY = gp.tileSize * 43;
			gp.npc[2][12] = new NPC_Orc(gp);
			gp.npc[2][12].worldX = gp.tileSize * 17;
			gp.npc[2][12].worldY = gp.tileSize * 45;
			
		}
//		if (gp.currentMap == 3) {
//			gp.npc[3][0] = new NPC_Orc(gp);
//			gp.npc[3][0].worldX = gp.tileSize * 9;
//			gp.npc[3][0].worldY = gp.tileSize * 10;
//			gp.npc[3][1] = new NPC_Orc(gp);
//			gp.npc[3][1].worldX = gp.tileSize * 8;
//			gp.npc[3][1].worldY = gp.tileSize * 8;
//		}

	}

	public void setBreakableTile() {
		int i = 0;

		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;

		while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
			// lay ra so luu trong mapTileNum, neu la so 2 thi tao 1 cai breakable wall
			int tileNum = gp.tileM.mapTileNum[gp.currentMap][col][row];

			if (tileNum == 2) {
				gp.bTile[gp.currentMap][i] = new BreakableWall(gp);
				gp.bTile[gp.currentMap][i].worldX = gp.tileSize * col;
				gp.bTile[gp.currentMap][i].worldY = gp.tileSize * row;
				i++;
			}
			col++;

			if (col == gp.maxWorldCol) {
				col = 0;
				row++;
			}
		}
	}

}
