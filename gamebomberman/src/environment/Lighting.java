package environment;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class Lighting {
	GamePanel gp;
	BufferedImage darknessFilter;
	
	public Lighting(GamePanel gp, int circleSize) {
		//Tạo ra buffered image
		darknessFilter = new BufferedImage(gp.screenWidth, gp.screenHeight, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = (Graphics2D)darknessFilter.getGraphics();
		
		// tạo ra hình chữ nhật có kích thước bằng cửa số
		Area screenArea = new Area(new Rectangle2D.Double(0, 0, gp.screenWidth, gp.screenHeight));
		
		// lấy giá trị chính giữa màn hình làm tâm vòng tròn
		int centerX = gp.player.screenX + (gp.tileSize)/2;
		int centerY = gp.player.screenY + (gp.tileSize)/2;
		
		// lấy đỉnh toạ độ x và y của vòng tròn
		double x = centerX - (circleSize/2);
		double y = centerY - (circleSize/2);
		
		
		//Tạo ra vòng tròn cho ánh sáng
		Shape circleShape = new Ellipse2D.Double(x, y, circleSize, circleSize);
		
		//Tạo ra vùng sáng
		Area LightArea = new Area(circleShape);
		
		// hình chữ nhật trừ đi vùng sáng tròn
		screenArea.subtract(LightArea);
		
		//Gradiation
		Color color[] = new Color[5];
		float fraction[] = new float[5];
		color[0] = new Color(0, 0, 0, 0f);
		color[1] = new Color(0, 0, 0, 0.25f);
		color[2] = new Color(0, 0, 0, 0.5f);
		color[3] = new Color(0, 0, 0, 0.75f);
		color[4] = new Color(0, 0, 0, 0.98f);
		
		fraction[0] = 0f;
		fraction[1] = 0.25f;
		fraction[2] = 0.5f;
		fraction[3] = 0.75f;
		fraction[4] = 1f;
		
		// paint gradiation
		RadialGradientPaint gPaint = new RadialGradientPaint(centerX, centerY, (circleSize/2), fraction, color);
		
		g2.setPaint(gPaint);
		g2.fill(LightArea);
		
		g2.fill(screenArea);
		
		g2.dispose();
	}
	public void draw(Graphics2D g2) {
		g2.drawImage(darknessFilter, 0, 0, null);
	}
}
