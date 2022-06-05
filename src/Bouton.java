import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JButton;


class Bouton extends JButton {

	private Font fond ;
	private Image im ;
	private EcouteurBouton ecoutBout ;
	private int taillePolice, coordY ;
	
	public Bouton(String lab, int n, int p) {
		super(lab) ;
		ecoutBout = new EcouteurBouton() ;
		taillePolice = n ;
		coordY = p ;
		addActionListener(ecoutBout) ;
	}
	
	public void paintComponent(Graphics g) {
		String str = getText() ;
		im = getImage(str) ;
		g.drawImage(im, 0, 0, null) ;
		fond = new Font("Consolas", Font.BOLD, taillePolice) ;
		g.setFont(fond) ;
		g.setColor(Color.red.darker()) ;
		FontMetrics fM = g.getFontMetrics(fond) ;
		Dimension dim = getSize() ;
		g.drawString(str, (dim.width-fM.stringWidth(str))/2, coordY) ;
		repaint() ;
	}
	
	private static Image getImage(String str) {
		Image texture = Menu.kit.getImage(Menu.repertCourant+"/images/textureMilitaire.jpg") ;
		if (str == "Ordinateur")
			return CalqueImage.appliquerCalque(texture, Menu.kit.getImage(Menu.repertCourant+
					"/images/ordinateur.jpg"), 25, 50, Color.white,1e-2f) ;
		if (str == "Humain")
			return CalqueImage.appliquerCalque(texture, Menu.kit.getImage(Menu.repertCourant+
					"/images/humain.jpg"), 25, 50, Color.white, 1e-2f) ;
		return texture ;
	}
	
}
