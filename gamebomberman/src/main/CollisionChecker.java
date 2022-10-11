package main;

import entity.Entity;

public class CollisionChecker {
	GamePanel gp;
	
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkTile(Entity entity) {
		int entityLeftWorldX = entity.worldX + entity.solidArea.x;
		int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		int entityTopWorldY = entity.worldY + entity.solidArea.y;
		int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
		
		int entityLeftCol = entityLeftWorldX / gp.tileSize;
		int entityRightCol = entityRightWorldX / gp.tileSize;
		int entityTopRow = entityTopWorldY / gp.tileSize;
		int entityBottomRow = entityBottomWorldY / gp.tileSize;
		
		int tileNum1, tileNum2;
		
		switch(entity.direction) {
		case "up":
			entityTopRow = (entityTopWorldY - entity.speed)/ gp.tileSize;
<<<<<<< HEAD
			tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
=======
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
>>>>>>> d0ddf5b536046912bdfb800eaaff24582626f31f
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
<<<<<<< HEAD
			tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
=======
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
>>>>>>> d0ddf5b536046912bdfb800eaaff24582626f31f
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
<<<<<<< HEAD
			tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
=======
			tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
>>>>>>> d0ddf5b536046912bdfb800eaaff24582626f31f
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
<<<<<<< HEAD
			tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
=======
			tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
>>>>>>> d0ddf5b536046912bdfb800eaaff24582626f31f
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		}
	}
	
	
	public int checkObject(Entity entity, boolean isPlayer) {
		
		int index = 999;
		
		for(int i  = 0; i< gp.obj[1].length; i++) {//FIXED
			
			if(gp.obj[gp.currentMap][i] != null) {
				// lay vi tri phan va cham cua entity duoc pass vao fuction nay
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				// lay vi tri phan va cham cua cac objects 
<<<<<<< HEAD
				gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].worldX + gp.obj[gp.currentMap][i].solidArea.x;//FIXED
				gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].worldY + gp.obj[gp.currentMap][i].solidArea.y;//FIXED
=======
				gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
				gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;
				
				switch(entity.direction) {
				case "up":
					entity.solidArea.y -= entity.speed;break;
				case "down":
					entity.solidArea.y += entity.speed;break;
				case "left":
					entity.solidArea.x -= entity.speed;break;
				case "right":
					entity.solidArea.x += entity.speed;break;
				}
				
				if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
					if(gp.obj[i].collision == true) {
						entity.collisionOn = true;
					}
					if(isPlayer == true) {
						index = i;
					}
				}
				
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
				gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
			}
		}
		
		return index;
	}
	//check va cham voi quai vat, breakable tile
	public int checkEntity(Entity entity, Entity[] target) {

		int index = 999;
		
		for(int i  = 0; i< target.length; i++) {
			
			if(target[i] != null) {
				// lay vi tri phan va cham cua entity duoc pass vao fuction nay
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				// lay vi tri phan va cham cua cac objects 
				target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
				target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;
>>>>>>> d0ddf5b536046912bdfb800eaaff24582626f31f
				
				switch(entity.direction) {
				case "up":
					entity.solidArea.y -= entity.speed;break;
				case "down":
					entity.solidArea.y += entity.speed;break;
				case "left":
					entity.solidArea.x -= entity.speed;break;
				case "right":
					entity.solidArea.x += entity.speed;break;
				}
				
				if(entity.solidArea.intersects(gp.obj[gp.currentMap][i].solidArea)) {//FIXED
					if(gp.obj[gp.currentMap][i].collision == true) {//FIXED
						entity.collisionOn = true;
					}
					if(isPlayer == true) {
						index = i;
					}
				}
				
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].solidAreaDefaultX; //FIXED
				gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].solidAreaDefaultY; //FIXED
			}
		}
		
		return index;
	}
	//check va cham voi quai vat, breakable tile
	public int checkEntity(Entity entity, Entity[][] target) {

		int index = 999;
		
		for(int i  = 0; i< target[1].length; i++) {//FIXED
			
			if(target[gp.currentMap][i] != null) {//FIXED
				// lay vi tri phan va cham cua entity duoc pass vao fuction nay
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				// lay vi tri phan va cham cua cac objects 
				target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].worldX + target[gp.currentMap][i].solidArea.x;//FIXED
				target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].worldY + target[gp.currentMap][i].solidArea.y;//FIXED
				
				switch(entity.direction) {
				case "up":
					entity.solidArea.y -= entity.speed;break;
				case "down":
					entity.solidArea.y += entity.speed;break;
				case "left":
					entity.solidArea.x -= entity.speed;break;
				case "right":
					entity.solidArea.x += entity.speed;break;
				}
				
				if(entity.solidArea.intersects(target[gp.currentMap][i].solidArea)) {//FIXED
					if(target[gp.currentMap][i] != entity) {//FIXED
						entity.collisionOn = true;
						index = i;
					}
				}
				// neu ko va cham thi return 999
				
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				target[gp.currentMap][i].solidArea.x = target[gp.currentMap][i].solidAreaDefaultX;//FIXED
				target[gp.currentMap][i].solidArea.y = target[gp.currentMap][i].solidAreaDefaultY;//FIXED
			}
		}
		
		return index;
	}
	
	public boolean checkPlayer(Entity entity) {
		
		boolean contactPlayer = false;
		// lay vi tri phan va cham cua entity duoc pass vao fuction nay
		entity.solidArea.x = entity.worldX + entity.solidArea.x;
		entity.solidArea.y = entity.worldY + entity.solidArea.y;
		// lay vi tri phan va cham cua cac objects 
		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
		
		switch(entity.direction) {
		case "up":
			entity.solidArea.y -= entity.speed;break;
		case "down":
			entity.solidArea.y += entity.speed;break;
		case "left":
			entity.solidArea.x -= entity.speed;break;
		case "right":
			entity.solidArea.x += entity.speed;break;
		}
		if(entity.solidArea.intersects(gp.player.solidArea)) {
			entity.collisionOn = true;
			contactPlayer = true;
		}
		
		// neu ko va cham thi return 999
		
		entity.solidArea.x = entity.solidAreaDefaultX;
		entity.solidArea.y = entity.solidAreaDefaultY;
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
		
		return contactPlayer;
	}
	
	// dan va  cham nguoi
	public int checkCollidePlayer(Entity entity, Entity target) {

		int index = 999;
		
			
			if(target != null) {
				// lay vi tri phan va cham cua entity duoc pass vao fuction nay
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				// lay vi tri phan va cham cua cac objects 
				target.solidArea.x = target.worldX + target.solidArea.x;
				target.solidArea.y = target.worldY + target.solidArea.y;
				
				switch(entity.direction) {
				case "up":
					entity.solidArea.y -= entity.speed;break;
				case "down":
					entity.solidArea.y += entity.speed;break;
				case "left":
					entity.solidArea.x -= entity.speed;break;
				case "right":
					entity.solidArea.x += entity.speed;break;
				}
				
				if(entity.solidArea.intersects(target.solidArea)) {
					if(target != entity) {
						entity.alive = false;
						index = 1;
					}
				}
				// neu ko va cham thi return 999
				
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				target.solidArea.x = target.solidAreaDefaultX;
				target.solidArea.y = target.solidAreaDefaultY;
			}
		
		return index;
	}
	
	public void checkTileProjectile(Entity entity) {
		
		int entityLeftX = entity.worldX + entity.solidArea.x;
		int entityRightX = entity.worldY + entity.solidArea.x + entity.solidArea.width;
		int entityTopY = entity.worldX + entity.solidArea.y;
		int entityBottomY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
		
		int entityLeftCol = entityLeftX / gp.tileSize;
		int entityRightCol = entityRightX / gp.tileSize;
		int entityTopRow = entityTopY / gp.tileSize;
		int entityBottomRow = entityBottomY / gp.tileSize;
		
		int tileNum1, tileNum2;
		
		switch(entity.direction) {
		case "up":
			entityTopRow = (entityTopY - entity.speed)/ gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.alive = false;
			}
			break;
		case "down":
			entityBottomRow = (entityBottomY + entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.alive = false;
			}
			break;
		case "left":
			entityLeftCol = (entityLeftX - entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.alive = false;
			}
			break;
		case "right":
			entityRightCol = (entityRightX + entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.alive = false;
			}
			break;
		}
		
	}
		
}
