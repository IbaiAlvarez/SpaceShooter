package APP;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Sarrera implements KeyListener
{
	public boolean gora, behera, ezker, eskubi, tiro;

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void keyPressed(KeyEvent e) 
	{
		int botoiKodea = e.getKeyCode();
		if (botoiKodea == KeyEvent.VK_W)  gora = true;
		if (botoiKodea == KeyEvent.VK_S	) behera = true;
		if (botoiKodea == KeyEvent.VK_A)  ezker = true;
		if (botoiKodea == KeyEvent.VK_D)  eskubi = true;
		if (botoiKodea == KeyEvent.VK_SPACE)  tiro = true;		
		
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		int botoiKodea = e.getKeyCode();
		if (botoiKodea == KeyEvent.VK_W)  gora = false;
		if (botoiKodea == KeyEvent.VK_S	) behera = false;
		if (botoiKodea == KeyEvent.VK_A)  ezker = false;
		if (botoiKodea == KeyEvent.VK_D)  eskubi = false;
		if (botoiKodea == KeyEvent.VK_SPACE)  tiro = false;
		
	}
	
	public void resetValues() {
		gora = false;
		behera = false;
		ezker = false;
		eskubi = false;
		tiro = false;
	}
}
