
class MapBuilder {

	private byte[][] carte ;
	private int l, h ;
	
	public MapBuilder(String str) {
		int i, j ;
		l = h = 100 ;
		carte = new byte[l][h] ;
		switch(str) {
		case "No man's land" :
			for (i = 0 ; i < l ; i++)
				carte[0][i] = carte[h-1][i] = 1 ;
			for (i = 1 ; i < h-1 ; i++)
				carte[i][0] = carte[i][l-1] = 1 ;
			for (i = 1 ; i < l-1 ; i++)
				for (j = 1 ; j < h-1 ; j++)
					carte[i][j] = 0 ;
			
		}
		
	}

	public byte[][] getMap() {
		return carte ;
	}

	public void setWidth(int n) {
		l = n ;
	}

	public void setHeight(int n) {
		h = n ;
	}
	
}
