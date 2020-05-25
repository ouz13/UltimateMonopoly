import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class syntaxTest {


	public static void main(String[] args) {
		JButton button = new JButton("roll");
		JFrame frame = new JFrame();
		frame.add(button);
		frame.setLayout(null);
		button.setSize(100,100);
		button.setLocation(100, 100);
		frame.setVisible(true);
		
		
	}
}
