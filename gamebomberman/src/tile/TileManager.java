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
		mapTileNum = new int[gp.maxScreenCol][gp.maxScreenRow];
		
		getTileImage();
		loadMap();
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
	
	public void loadMap() {
		
		for ( int i = 0; i< gp.maxScreenCol; i++) {
			for ( int j = 0; j< gp.maxScreenRow; j++) {
				mapTileNum[i][j] = 0;
			}
		}
		for ( int i = 0; i< gp.maxScreenRow; i++) {
			mapTileNum[0][i] = 1;
			mapTileNum[gp.maxScreenCol-1][i] = 1;
		}
		for ( int i = 0; i< gp.maxScreenCol; i++) {
			mapTileNum[i][0] = 1;
			mapTileNum[i][gp.maxScreenRow-1] = 1;
		}
		for ( int i = 1; i < gp.maxScreenCol - 1; i++) {
			for ( int j = 1; j < gp.maxScreenRow - 1; j++) {
				if ( (i != 1 || j != 1) && (i != 1 || j != 2) && (i != 2 || j != 1)) {
					Random rd = new Random();
					int number1 = rd.nextInt(3);
					if ( number1 == 1) {
						number1 = 2;
					}
					mapTileNum[i][j] = number1;
				}
			}
		}
	}
	
	public void draw(Graphics2D g2) {
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		
		while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
			// lay ra so luu trong mapTileNum, in ra cai tuong ung
			int tileNum = mapTileNum[col][row];
			
			g2.drawImage(tile[tileNum].image,x,y,null);
			col++;
			x+= gp.tileSize;
			
			if(col == gp.maxScreenCol) {
				col = 0;
				x = 0;
				row++;
				y+= gp.tileSize;
			}
		}
	}
}
