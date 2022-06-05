import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.ImageIcon;


class CalqueImage {
	
	public static Image appliquerCalque(Image im1, Image im2, int coordX, int coordY,
			int[] tabRGB) {
		// applique un calque à une image
		int largeurIm1, hauteurIm1, largeurIm2, hauteurIm2 ;
		largeurIm1 = im1.getWidth(null) ;
		hauteurIm1 = im1.getHeight(null) ;
		largeurIm2 = im2.getWidth(null) ;
		hauteurIm2 = im2.getHeight(null) ;
		if (coordX+largeurIm2-1 > largeurIm1 || coordY+hauteurIm2-1 > hauteurIm1)
			return null ;
		BufferedImage buffIm1, buffIm2, buffIm3 ;
		buffIm1 = toBufferedImage(im1) ;
		buffIm2 = toBufferedImage(im2) ;
		buffIm3 = new BufferedImage(largeurIm1, hauteurIm1, BufferedImage.TYPE_INT_RGB) ;
		Integer[] tabRGBInt = new Integer[tabRGB.length] ;
		for (int i = 0 ; i < tabRGB.length ; i++)
			tabRGBInt[i] = Integer.valueOf(tabRGB[i]) ;
		List <Integer> liste = ListUtil.arrayToList(tabRGBInt) ;
		for (int i = 0 ; i < largeurIm1 ; i++)
			for (int j = 0 ; j < hauteurIm1 ; j++)
				if ((i < coordX || i > coordX+largeurIm2-1) || (j < coordY || j > coordY+
						hauteurIm2-1))
					buffIm3.setRGB(i, j, buffIm1.getRGB(i, j)) ;
				else
					if (liste.contains(buffIm2.getRGB(i-coordX, j-coordY)))
						buffIm3.setRGB(i, j, buffIm1.getRGB(i, j)) ;
					else
						buffIm3.setRGB(i, j, buffIm2.getRGB(i-coordX, j-coordY)) ;
		return buffIm3 ;
	}
	
	public static Image appliquerCalque(Image im1, Image im2, int coordX, int coordY,
			int rgb) {
		int[] tabRGB = new int[1] ;
		tabRGB[0] = rgb ;
		return appliquerCalque(im1, im2, coordX, coordY, tabRGB) ;
	}
	
	public static Image appliquerCalque(Image im1, Image im2, int coordX, int coordY,
			Color c) {
		return appliquerCalque(im1, im2, coordX, coordY, c.getRGB()) ;
	}
	
	public static Image appliquerCalque(Image im1, Image im2, int coordX, int coordY,
			Color[] tabCouleurs) {
		int[] tabRGB = new int[tabCouleurs.length] ;
		for (int i = 0 ; i < tabCouleurs.length ; i++)
			tabRGB[i] = tabCouleurs[i].getRGB() ;
		return appliquerCalque(im1, im2, coordX, coordY, tabRGB) ;
	}
	
	public static Image appliquerCalque(Image im1, Image im2, int coordX, int coordY,
			Color c, float tolerance) {
		if (tolerance < 0. || tolerance > 1.)
			return null ;
		int nbCouleurs, rayon, r, g, b, inc ;
		nbCouleurs = (int)(tolerance*tolerance*Math.pow(256, 3)) ;
		rayon = (int)Math.pow(nbCouleurs, 1/3.)/2 ;
		Color[] tabCouleurs = new Color[(int)Math.pow(2*rayon+1, 3)] ;
		r = c.getRed() ;
		g = c.getGreen() ;
		b = c.getBlue() ;
		inc = 0 ;
		for (int i = r-rayon < 0 ? 0 : (r+rayon > 255 ? 255-2*rayon : r-rayon) ;
		i <= (r-rayon < 0 ? 2*rayon: (r+rayon > 255 ? 255 : r+rayon)) ; i++)
			for (int j = g-rayon < 0 ? 0 : (g+rayon > 255 ? 255-2*rayon : g-rayon) ;
			j <= (g-rayon < 0 ? 2*rayon : (g+rayon > 255 ? 255 : g+rayon)) ; j++)
				for (int k = b-rayon < 0 ? 0 : (b+rayon > 255 ? 255-2*rayon : b-rayon) ;
				k <= (b-rayon < 0 ? 2*rayon : (b+rayon > 255 ? 255 : b+rayon)) ; k++) {
					tabCouleurs[inc] = new Color(i, j, k) ;
					inc++ ;
				}
		return CalqueImage.appliquerCalque(im1, im2, coordX, coordY, tabCouleurs) ;
	}
	
	public static BufferedImage toBufferedImage(Image im) {
		// convertit une image en une instance de BufferedImage
        if (im instanceof BufferedImage)
        	return (BufferedImage)im ;
        else {
        	im = new ImageIcon(im).getImage() ;
        	BufferedImage buffIm = new BufferedImage(im.getWidth(null), im.getHeight(null),
        			BufferedImage.TYPE_INT_RGB ) ;
        	Graphics g = buffIm.getGraphics() ;
        	g.drawImage(im, 0, 0, null) ;
        	g.dispose() ;
        	return buffIm ;
        } 
	}
	
	

}
