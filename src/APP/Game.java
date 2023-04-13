package APP;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Game {
	public static void main(String[] args) {
		
		JFrame lehioa = new JFrame();
		lehioa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lehioa.setResizable(false);
		lehioa.setTitle("Space Shooter");
		
		
		Pantaila p = new Pantaila();
		lehioa.add(p);
		lehioa.pack();
		lehioa.setLocationRelativeTo(null);
		lehioa.setVisible(true);
		
		p.asiJolasten();
	}
}
