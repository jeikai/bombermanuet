package breakableTiles;

import entity.Entity;
import main.GamePanel;

public class BreakableTile extends Entity{

	public boolean destructible = false;
	GamePanel gp;
	public BreakableTile(GamePanel gp) {
		super(gp);
		this.gp = gp;
	}

	public void update() {
		
	}
}
