import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class EcouteurBouton implements ActionListener {

	public void actionPerformed(ActionEvent ev) {
		String str = ev.getActionCommand() ;
		if (str == "Jouer")   // menu principal
			Menu.setMenuJouer() ;
		if (str == "Quitter")
			System.exit(0) ;
		if (str == "Ordinateur" || str == "Humain")   // menu Jouer
			Menu.setMenuCartes() ;
			
	}

}
