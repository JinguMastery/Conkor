import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
class Map extends JFrame {

	public final static int nLig = 10 ;
	public final static int tailleCase = Main.hauteur / nLig ;
	public final static int nCol = Main.largeur / tailleCase ;
	private final static Image imCurseur = Main.kit.getImage(Main.projet+"/images/curseur.png") ;
	private static MapBuilder bMap ;
	private static ArrayList<int[]> posSoldatsR, posSoldatsB ;
	private static ArrayList<ArrayList<JLabel>> tabCases ;
	private static JPanel pan ;
	
	public Map(MapBuilder bMap) {
		setSize(Main.largeur, Main.hauteur) ;
		setUndecorated(true) ;
		Map.bMap = bMap ;
		tabCases = new ArrayList<ArrayList<JLabel>> () ;
		ArrayList<JLabel> liste ;
		byte[][] tabB = bMap.getMap() ;
		int i, j ; byte b ;
		for (i = 0 ; i < nLig ; i++) {   // placement au centre de la carte
			liste = new ArrayList<JLabel> () ;
			for (j = 0 ; j < nCol ; j++) {
				b = tabB[(tabB[0].length - nCol)/2 + j][(tabB.length - nLig)/2 + i] ;
				liste.add(new JLabel(new ImageIcon(Main.projet+"/images/cases/"+b+".png"))) ;
			}
			tabCases.add(liste) ;
		}
		posSoldatsR = new ArrayList<int[]> () ;
		posSoldatsB = new ArrayList<int[]> () ;
		int r1, r2, n, p ;
		boolean isRed = true, invalidPos ;
		for (i = 0 ; i < 40 ; i++) {   // placement aléatoire des soldats sur la carte
			do {
				r1 = (int)(Math.random() * 100) ;
				r2 = (int)(Math.random() * 100) ;
				invalidPos = false ;
				for (j = 0 ; j < posSoldatsR.size() ; j++) {
					n = posSoldatsR.get(j)[0] ; p = posSoldatsR.get(j)[1] ;
					if ((!isRed && (r1 == n || r2 == p)) || (r1 >= n-2 || r1 <= n+2) && (r2 >= p-2 || r2 <= p+2)) {
						invalidPos = true ;
						break ;
					}
				}
				if (!invalidPos)
					for (j = 0 ; j < posSoldatsB.size() ; j++) {
						n = posSoldatsB.get(j)[0] ; p = posSoldatsB.get(j)[1] ;
						if ((isRed && (r1 == n || r2 == p)) || (r1 >= n-2 || r1 <= n+2) && (r2 >= p-2 || r2 <= p+2)) {
							invalidPos = true ;
							break ;
						}
					}
			} while (invalidPos) ;
			if (isRed) {
				posSoldatsR.add(new int[] {r1, r2}) ;
				isRed = false ;
			}
			else {
				posSoldatsB.add(new int[] {r1, r2}) ;
				isRed = true ;
			}
		}

		n = Main.largeur - nCol*tailleCase ; p = Main.hauteur - nLig*tailleCase ;   // création de l'interface de jeu
		int l1 = n / 2, h1 = p / 2, l2, h2 ;
		if (n % 2 == 0.)
			l2 = l1 ;
		else
			l2 = l1 + 1 ;
		if (p % 2 == 0.)
			h2 = h1 ;
		else
			h2 = h1 + 1 ;
		JPanel panGauche = new JPanel(), panDroit = new JPanel(), panSup = new JPanel(), panInf = new JPanel() ;
		Container cont = getContentPane() ;
		cont.setLayout(null) ;
		panGauche.setBackground(Color.black) ;
		panGauche.setBounds(0, h1, l1, Main.hauteur-p) ;
		cont.add(panGauche) ;
		panDroit.setBackground(Color.black) ;
		panDroit.setBounds(Main.largeur-l2, h1, l2, Main.hauteur-p) ;
		cont.add(panDroit) ;
		panSup.setBackground(Color.black) ;
		panSup.setBounds(0, 0, Main.largeur, h1) ;
		cont.add(panSup) ;
		panInf.setBackground(Color.black) ;
		panInf.setBounds(0, Main.hauteur-h2, Main.largeur, h2) ;
		cont.add(panInf) ;
		pan = new JPanel(new GridLayout(nLig, nCol)) {
			
			public void paintComponent(Graphics g) {
				
			}
			
		} ;
		pan.setBounds(l1, h1, Main.largeur-n, Main.hauteur-p) ;
		pan.addMouseListener(new MouseListener() {

			public void mouseClicked(MouseEvent ev) {
			}

			public void mouseEntered(MouseEvent ev) {
			}

			public void mouseExited(MouseEvent ev) {
			}

			public void mousePressed(MouseEvent ev) {
			}

			public void mouseReleased(MouseEvent ev) {
			}
			
		}) ;
		pan.addMouseMotionListener(new MouseMotionAdapter() {
			
			public void mouseDragged(MouseEvent ev) {
				
			}

		}) ;
		
		Cursor curseur = Main.kit.createCustomCursor(imCurseur, new Point(0, 0), "Curseur_militaire") ;
		pan.setCursor(curseur) ;
		cont.add(pan) ;
	}
	
//	private static void getMapArea(int x, int y) {
//		if (x < 0 || x+Map.nCol > l || y < 0 || y+Map.nLig > h)
//			throw new IndexOutOfBoundsException() ;
//		
//	}

}
