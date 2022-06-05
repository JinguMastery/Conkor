import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


class ListUtil {

	public static <T> List <T> arrayToList(T[] tab) {   // convertit un tableau d'objets
														// en une liste
		List <T> liste = new ArrayList <T> () ;
		liste = Arrays.asList(tab) ;
		return liste ;
	}
	
	public static <T> T[] listToArray(List <T> liste) {   // convertit une liste en un tableau
														  // d'objets
		T[] tab = (T[])liste.toArray() ;
		return tab ;
	}
	
}
