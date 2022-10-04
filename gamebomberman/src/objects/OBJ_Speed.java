package objects;

import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;


public class OBJ_Speed extends SuperObject{
	
	GamePanel gp;
	
	public OBJ_Speed(GamePanel gp){
		name = "Speed";
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/objects/speed.png"));
			uTool.scaleImage(image, gp.tileSize, gp.tileSize);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		collision = true;
	}
}
