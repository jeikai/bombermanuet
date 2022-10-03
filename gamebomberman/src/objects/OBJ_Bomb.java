package objects;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Bomb extends SuperObject{
	boolean showBomb = true;
	public OBJ_Bomb() {
		name = "Bomb";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/bomb.png"));
		}
		catch (IOException e){
			e.printStackTrace();
		}
		collision = false;
	}
	
	
	public void draw(Graphics2D g2, GamePanel gp,int xpos, int ypos) {
		if(showBomb == true) {
			g2.drawImage(image,xpos,ypos,gp.tileSize,gp.tileSize,null);
			timer++;
			if(timer > 120) {
				timer = 0;
				showBomb = false;
			}
		}
		
	}
	
}