import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class testpane extends JPanel {
	public BufferedImage image;
	
	public testpane() {
		try {
			image = ImageIO.read(new File("board.png"));
		} catch (IOException e) {
			System.out.println("File cant be found.");
		}
	}
	
	public void paintComponent (Graphics g) {
		g.drawImage(image, 0, 0, 1200, 1200, null);
		//   super.paintComponents(g);
		//   g.setColor(Color.BLUE);
		//  g.drawString("tamam", 100, 100);
		  
		 }
}
