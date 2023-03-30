package APP;

import javax.swing.JFrame;

public class Game {
	public static void main(String[] args) {
		
		JFrame lehioa = new JFrame();
		lehioa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lehioa.setResizable(false);
		lehioa.setTitle("Jokua");
		
		Pantaila p = new Pantaila();
		lehioa.add(p);
		lehioa.pack();
		lehioa.setLocationRelativeTo(null);
		lehioa.setVisible(true);
		
		p.asiJolasten();
	}
}
