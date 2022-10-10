package breakableTiles;

import main.GamePanel;

public class BreakableWall extends BreakableTile{

	GamePanel gp;
	
	public BreakableWall(GamePanel gp) {
		super(gp);
		this.gp = gp;
		
		down1 = setup("/breakableTiles/breakable_wall");
		destructible = true;
	}

}
