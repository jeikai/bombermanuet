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
			tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
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
				gp.obj[gp.currentMap][i].solidArea.x = gp.obj[gp.currentMap][i].worldX + gp.obj[gp.currentMap][i].solidArea.x;//FIXED
				gp.obj[gp.currentMap][i].solidArea.y = gp.obj[gp.currentMap][i].worldY + gp.obj[gp.currentMap][i].solidArea.y;//FIXED
				
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
			tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.alive = false;
			}
			break;
		case "down":
			entityBottomRow = (entityBottomWorldY + entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
			tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.alive = false;
			}
			break;
		case "left":
			entityLeftCol = (entityLeftWorldX - entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityLeftCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.alive = false;
			}
			break;
		case "right":
			entityRightCol = (entityRightWorldX + entity.speed) / gp.tileSize;
			tileNum1 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityTopRow];
			tileNum2 = gp.tileM.mapTileNum[gp.currentMap][entityRightCol][entityBottomRow];
			if(gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.alive = false;
			}
			break;
		}
	}
		
}
