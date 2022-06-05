import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;


class Panneau extends JPanel {
	
	private Image im ;
	
	public void paintComponent(Graphics g) {
		im = Menu.kit.getImage(Menu.repertCourant+"/images/fondEcranMenu.jpg") ;
		g.drawImage(im, 0, 0, null) ;
	}

}
