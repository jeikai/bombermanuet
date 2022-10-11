package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;
import java.util.Random;
public class TileManager {
	
	GamePanel gp;
	public Tile[] tile;
	public int mapTileNum[][];
	
	public TileManager(GamePanel gp) {
		this.gp =gp;
		tile = new Tile[10];
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		
		getTileImage();
		loadMap("/maps/map01.txt");
	}
	
	public void getTileImage() {
	
		setup(0,"floor",false);
		setup(1,"wall",true);
		setup(2,"floor",false);
			

	}
	
	void setup(int index, String imageName, boolean collision) {
		UtilityTool uTool = new UtilityTool();
		try {
			tile[index] = new Tile();
			tile[index].image = ImageIO.read(getClass().getResourceAsStream("/maps/" + imageName+".png"));
			tile[index].image = uTool.scaleImage(tile[index].image,gp.tileSize,gp.tileSize);
			tile[index].collision = collision;
		}catch(IOException e) {
			
		}
	}
	
	public void loadMap(String filePath) {
		
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
				
				String line = br.readLine();
				
				while(col < gp.maxWorldCol) {
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					col++;
				}
				if(col == gp.maxWorldCol) {
					col = 0;
					row++;
				}
			}
			
//			for ( int i = 1; i < gp.maxWorldCol - 1; i++) {
//				for ( int j = 1; j < gp.maxWorldRow - 1; j++) {
//					if ( (i != 1 || j != 1) && (i != 1 || j != 2) && (i != 2 || j != 1)) {
//						Random rd = new Random();
//						int number1 = rd.nextInt(3);
//						if ( number1 == 1) {
//							number1 = 2;
//						}
//						mapTileNum[i][j] = number1;
//					}
//				}
//			}
			
			br.close();
			
		}catch (Exception e) {
			
		}
		
	}
	
	public void draw(Graphics2D g2) {
		int worldCol = 0;
		int worldRow = 0;

		
		while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
			// lay ra so luu trong mapTileNum, in ra cai tuong ung
			int tileNum = mapTileNum[worldCol][worldRow];
			
			int worldX = worldCol * gp.tileSize;
			int worldY = worldRow * gp.tileSize;
			int screenX = worldX - gp.player.worldX + gp.player.screenX;
			int screenY = worldY - gp.player.worldY + gp.player.screenY;
			
			if(worldX + gp.tileSize >gp.player.worldX - gp.player.screenX &&
					worldX-gp.tileSize < gp.player.worldX + gp.player.screenX &&
					worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
					worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
				
				g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
			}
			
			worldCol++;
			
			if(worldCol == gp.maxWorldCol) {
				worldCol = 0;
				worldRow++;

			}
		}
	}
}
