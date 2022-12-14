package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

	GamePanel gp;
	public boolean upPressed, downPressed, leftPressed, rightPressed, spacePressed;
	
	public KeyHandler(GamePanel gp) {
		this.gp = gp;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}
	@Override
	public void keyPressed(KeyEvent e) {

		int code = e.getKeyCode();
		// Title screen
		if(gp.gameState == gp.titleState) {
			if (code == KeyEvent.VK_S) {
				gp.ui.commandNum++;
				if(gp.ui.commandNum > 1) gp.ui.commandNum = 0;
			}
			if (code == KeyEvent.VK_W) {
				gp.ui.commandNum--;
				if(gp.ui.commandNum <0) gp.ui.commandNum = 1;
			}
			if(code == KeyEvent.VK_ENTER) {
				if(gp.ui.commandNum == 0) {
					gp.gameState = gp.playState;
					// play music
				}
				if(gp.ui.commandNum == 1) {
					System.exit(0);
				}
			}
		}
		
		// in game
		if(gp.gameState == gp.playState) {
			if (code == KeyEvent.VK_W) {
				upPressed = true;
			}
			if (code == KeyEvent.VK_S) {
				downPressed = true;
			}
			if (code == KeyEvent.VK_A) {
				leftPressed = true;
			}
			if (code == KeyEvent.VK_D) {
				rightPressed = true;
			}
			if (code == KeyEvent.VK_SPACE) {
				spacePressed = true;
			}
			
			if (code == KeyEvent.VK_R) {
				switch (gp.currentMap) {
				case 0: gp.tileM.loadMap("/maps/map01.txt", 0); break;
				case 1: gp.tileM.loadMap("/maps/map02.txt", 1); break;

				}
			}
			if (gp.gameState == gp.playState) {
				if(code == KeyEvent.VK_M) {
					gp.gameState = gp.mapState;
				}
			}
		}
		//GAME OVER STATE
		if (gp.gameState == gp.gameOverState) {
			gameOverState(code);
			
		}
		//Pause State
		if ( gp.gameState == gp.playState) {
			if (code == KeyEvent.VK_P) {
				gp.gameState = gp.pauseState;
				
			}
		}
		else if ( gp.gameState == gp.pauseState) {
			if (code == KeyEvent.VK_ENTER) {
				gp.gameState = gp.playState;
			}
		}
		//Dialogue state
		else if ( gp.gameState == gp.dialogueState) {
			if ( code == KeyEvent.VK_ENTER) {
				gp.gameState = gp.playState;
			}
		}
		
		//GAME WIN STATE
		if(gp.gameState == gp.gameWinState) {
			gameWinState(code);
		}
		//MAP STATE
				else if (gp.gameState == gp.mapState) {
					mapState(code);
				}
		
	}
	public void gameOverState(int code) {
		if(code == KeyEvent.VK_W) {
			gp.ui.commandNum--;
			if(gp.ui.commandNum < 0) {
				gp.ui.commandNum = 1;
			}
		}
		if(code == KeyEvent.VK_S) {
			gp.ui.commandNum++;
			if(gp.ui.commandNum > 1) {
				gp.ui.commandNum = 0;
			}
		}
		if(code == KeyEvent.VK_ENTER) {
			if(gp.ui.commandNum == 0) {
				gp.gameState = gp.playState;
				gp.retry();
			}
			else if(gp.ui.commandNum == 1) {
				gp.currentMap = 0;
				gp.gameState = gp.titleState;
				gp.restart();
			}
			
		}
	}
	public void gameWinState(int code) {
		if(code == KeyEvent.VK_W) {
			gp.ui.commandNum--;
			if(gp.ui.commandNum < 0) {
				gp.ui.commandNum = 1;
			}
		}
		if(code == KeyEvent.VK_S) {
			gp.ui.commandNum++;
			if(gp.ui.commandNum > 1) {
				gp.ui.commandNum = 0;
			}
		}
		if(code == KeyEvent.VK_ENTER) {
			if(gp.ui.commandNum == 0) {
				gp.currentMap = 0;
				gp.gameState = gp.playState;
				gp.restart();
			}
			else if(gp.ui.commandNum == 1) {
//				gp.gameState = gp.titleState;
				System.exit(0);
			}
			
		}
	}
	public void mapState(int code) {
		if(code == KeyEvent.VK_H) {
			gp.gameState = gp.playState;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {

		int code = e.getKeyCode();

		if (code == KeyEvent.VK_W) {
			upPressed = false;
		}
		if (code == KeyEvent.VK_S) {
			downPressed = false;
		}
		if (code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		if (code == KeyEvent.VK_D) {
			rightPressed = false;
		}
		if (code == KeyEvent.VK_SPACE) {
			spacePressed = false;
		}
	}

}
