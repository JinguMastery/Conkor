import java.awt.Toolkit;

public class Main {

	public final static Toolkit kit = Toolkit.getDefaultToolkit() ;
	public final static String projet = System.getProperty("user.dir") ;
	public final static int largeur = kit.getScreenSize().width, hauteur = kit.getScreenSize().height ;
	private static Menu fen ;
	private static Map map ;
	
	public static void main(String[] args) {
		//map = new Map(new MapBuilder("")) ;
		//map.setVisible(true) ;
		fen = new Menu() ;
		fen.setVisible(true) ;
	}
	
	public static void setMap(String str) {
		fen.setVisible(false) ;
		map = new Map(new MapBuilder(str)) ;
		map.setVisible(true) ;
	}

	public static Menu getFrame() {
		return fen ;
	}

}
