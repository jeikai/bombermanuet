package tile;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

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
		setup(2,"breakable",true);
			

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
		
		try {
			InputStream is = getClass().getResourceAsStream("/maps/map01.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
				
				String line = br.readLine();
				
				while(col < gp.maxScreenCol) {
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					col++;
				}
				if(col == gp.maxScreenCol) {
					col = 0;
					row++;
				}
			}
			br.close();
			
		}catch (Exception e) {
			
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
