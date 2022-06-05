import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")
class Menu extends JFrame {
	
	private final static Image imMenu = Main.kit.getImage(Main.projet+"/images/fond_ecran_menu.jpg") ;
	private static Button jouer, aide, succes, options, quitter, ordinateur, humain, optionsCarte ;
	private static Button[] cartes ;
	private static JPanel pan ;
	private static JDialog dialogue ;
	
	public Menu() {
		setTitle("CONKOR") ;
		setSize(1000, 800) ;
		setLocation(Main.largeur/2-500, Main.hauteur/2-400) ;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE) ;
		setResizable(false) ;
		setCursor(new Cursor(Cursor.HAND_CURSOR)) ;
		pan = new JPanel(null) {
			
			public void paintComponent(Graphics g) {
				g.drawImage(imMenu, 0, 0, null) ;
				repaint() ;
			}
			
		} ;
		jouer = new Button("Jouer", 65, 57) ;
		jouer.setBounds(300, 250, 400, 75) ;
		pan.add(jouer) ;
		aide = new Button("Aide", 65, 57) ;
		aide.setBounds(300, 350, 400, 75) ;
		pan.add(aide) ;
		succes = new Button("Succes", 65, 57) ;
		succes.setBounds(300, 450, 400, 75) ;
		pan.add(succes) ;
		options = new Button("Options", 65, 57) ;
		options.setBounds(300, 550, 400, 75) ;
		pan.add(options) ;
		quitter = new Button("Quitter", 65, 57) ;
		quitter.setBounds(300, 650, 400, 75) ;
		pan.add(quitter) ;
		cartes = new Button[6] ;
		Container cont = getContentPane() ;
		cont.setLayout(new BorderLayout()) ;
		cont.add(pan) ;
	}
	
	public static void setPlayMenu() {
		Button[] tabBout = {jouer, aide, succes, options, quitter} ;
		for (int i = 0 ; i < 5 ; i++) 
			tabBout[i].setVisible(false) ;
		ordinateur = new Button("Ordinateur", 30, 30) ;
		ordinateur.setBounds(250, 400, 200, 200) ;
		pan.add(ordinateur) ;
		humain = new Button("Humain", 30, 30) ;
		humain.setBounds(550, 400, 200, 200) ;
		pan.add(humain) ;
		pan.repaint() ;
	}
	
	public static void setMapsMenu() {
		ordinateur.setVisible(false) ;
		humain.setVisible(false) ;
		cartes[0] = new Button("No man's land", 25, 180) ;
		cartes[0].setBounds(75, 275, 200, 200) ;
		pan.add(cartes[0]) ;
		optionsCarte = new Button("Options_carte", 0, 0) ;
		optionsCarte.setBounds(875, 642, 100, 100) ;
		pan.add(optionsCarte) ;
		pan.repaint() ;
	}
	
	public static void setDialog() {
		dialogue = new JDialog(Main.getFrame(), "Parametres des cartes", true) ;
		dialogue.setSize(800, 600) ;
		Container cont = dialogue.getContentPane() ;
		cont.setLayout(new GridBagLayout()) ;
	}
	
}
