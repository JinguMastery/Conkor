import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


@SuppressWarnings("serial")
class Button extends JButton {

	private final static Image texture = Main.kit.getImage(Main.projet+"/images/texture_militaire.jpg") ;
	private int taillePolice, coordY ;
	
	public Button(String lab, int n, int p) {
		super(lab) ;
		taillePolice = n ; coordY = p ;
		addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent ev) {
				String str = ev.getActionCommand() ;
				switch(str) {
				case "Jouer" :   // menu principal
					Menu.setPlayMenu() ;
					break ;
				case "Quitter" :
					System.exit(0) ;
				case "Ordinateur" : case "Humain" :   // menu Jouer
					Menu.setMapsMenu() ;
					break ;
				case "Options_carte" :
					Menu.setDialog() ;
					break ;
				case "No man's land" :
					Main.setMap(str) ;
				}
			}

		}) ;
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(texture, 0, 0, null) ;
		String str = getText() ;
		Image im ;
		switch(str) {
		case "Options_carte" :
			im = Main.kit.getImage(Main.projet+"/images/options_carte.png") ;
			g.drawImage(im, 5, 5, 90, 90, null) ;
			break ;
		case "Ordinateur" : case "Humain" :
			im = Main.kit.getImage(Main.projet+"/images/"+str+".png") ;
			g.drawImage(im, 25, 50, 150, 150, null) ;
		default :
			Font fond = new Font("Consolas", Font.BOLD, taillePolice) ;
			g.setFont(fond) ;
			g.setColor(Color.red.darker()) ;
			FontMetrics fM = g.getFontMetrics(fond) ;
			Dimension dim = getSize() ;
			g.drawString(str, (dim.width-fM.stringWidth(str))/2, coordY) ;
		}
		
	}
	
}
