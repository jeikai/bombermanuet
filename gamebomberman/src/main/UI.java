package main;

import java.awt.Font;
import java.awt.Graphics2D;
import java.text.DecimalFormat;

public class UI {
	GamePanel gp;
	Graphics2D g2;
	Font arial_40;
	Font arial_80B;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	public boolean gameFinished = false;
	
	double playTime ;
	DecimalFormat dFormat = new DecimalFormat("#0.00");
	
	public UI(GamePanel gp) {
		this.gp = gp;
		arial_40 = new Font("Arial", Font.PLAIN,40);
		arial_80B = new Font("Arial",Font.BOLD,80);
	}
}
