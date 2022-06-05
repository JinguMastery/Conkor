import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;


class ImageTracing {
	
	public static BufferedImage applyTracing(Image im1, Image im2, int x, int y, int[] tabRGB) {
		// applique un calque à une image
		int l1 = im1.getWidth(null), h1 = im1.getHeight(null), l2 = im2.getWidth(null), h2 = im2.getHeight(null) ;
//		if (l1 == -1 || l2 == -1)
//			return null ;
		if (x+l1-1 > l2 || y+h1-1 > h2)
			throw new IndexOutOfBoundsException() ;
		BufferedImage buffIm1 = toBufferedImage(im1), buffIm2 = toBufferedImage(im2), buffIm3 ;
		buffIm3 = new BufferedImage(l2, h2, BufferedImage.TYPE_INT_ARGB) ;
		Integer[] tabRGBInt = new Integer[tabRGB.length] ;
		for (int i = 0 ; i < tabRGB.length ; i++)
			tabRGBInt[i] = Integer.valueOf(tabRGB[i]) ;
		List <Integer> liste = Arrays.asList(tabRGBInt) ;
		for (int i = 0 ; i < l2 ; i++)
			for (int j = 0 ; j < h2 ; j++)
				if (i < x || i > x+l1-1 || j < y || j > y+h1-1)
					buffIm3.setRGB(i, j, buffIm2.getRGB(i, j)) ;
				else
					if (liste.contains(buffIm1.getRGB(i-x, j-y)))
						buffIm3.setRGB(i, j, buffIm2.getRGB(i, j)) ;
					else
						buffIm3.setRGB(i, j, buffIm1.getRGB(i-x, j-y)) ;
		return buffIm3 ;
	}
	
	public static BufferedImage applyTracing(Image im1, Image im2, int x, int y, Color[] tabCouleurs) {
		int[] tabRGB = new int[tabCouleurs.length] ;
		for (int i = 0 ; i < tabCouleurs.length ; i++)
			tabRGB[i] = tabCouleurs[i].getRGB() ;
		return applyTracing(im1, im2, x, y, tabRGB) ;
	}
	
	public static BufferedImage applyTracing(Image im1, Image im2, int x, int y, Color c, float tolerance) {
		if (tolerance < 0. || tolerance > 1.)
			throw new IndexOutOfBoundsException() ;
		int nbCouleurs, rayon, r = c.getRed(), g = c.getGreen(), b = c.getBlue(), inc = 0 ;
		nbCouleurs = (int)(tolerance * tolerance * Math.pow(256, 3)) ;
		rayon = (int)Math.pow(nbCouleurs, 1/3.) / 2 ;
		Color[] tabCouleurs = new Color[(int)Math.pow(2*rayon+1, 3)] ;
		for (int i = r-rayon < 0 ? 0 : (r+rayon > 255 ? 255-2*rayon : r-rayon) ;
		i <= (r-rayon < 0 ? 2*rayon: (r+rayon > 255 ? 255 : r+rayon)) ; i++)
			for (int j = g-rayon < 0 ? 0 : (g+rayon > 255 ? 255-2*rayon : g-rayon) ;
			j <= (g-rayon < 0 ? 2*rayon : (g+rayon > 255 ? 255 : g+rayon)) ; j++)
				for (int k = b-rayon < 0 ? 0 : (b+rayon > 255 ? 255-2*rayon : b-rayon) ;
				k <= (b-rayon < 0 ? 2*rayon : (b+rayon > 255 ? 255 : b+rayon)) ; k++) {
					tabCouleurs[inc] = new Color(i, j, k) ;
					inc++ ;
				}
		return applyTracing(im1, im2, x, y, tabCouleurs) ;
	}
	
	public static BufferedImage applyTracing(Image im1, Image im2, int x, int y, Color[] tabCouleurs, float[] tabTolerances) {
		if (tabCouleurs.length != tabTolerances.length)
			return null ;
		BufferedImage buffIm = applyTracing(im1, im2, x, y, tabCouleurs[0], tabTolerances[0]) ;
		for (int i = 1 ; i < tabCouleurs.length ; i++)
			buffIm = applyTracing(buffIm.getSubimage(x, y, im1.getWidth(null), im1.getHeight(null)),
					im2, x, y, tabCouleurs[i], tabTolerances[i]) ;
		return buffIm ;
	}
	
	public static BufferedImage toBufferedImage(Image im) {
		// convertit une image en une instance de BufferedImage
        
		if (im instanceof BufferedImage)
        	return (BufferedImage)im ;
        else {
        	BufferedImage buffIm = new BufferedImage(im.getWidth(null), im.getHeight(null), BufferedImage.TYPE_INT_ARGB) ;
        	Graphics g = buffIm.createGraphics() ;
        	g.drawImage(im, 0, 0, null) ;
        	g.dispose() ;
        	return buffIm ;
        }
	}
	
	public static BufferedImage toBufferedImage(Image im, Dimension dim) {
		// convertit une image en une instance de BufferedImage avec redimensionnement (par valeurs)
        
		if (im instanceof BufferedImage && dim.width == im.getWidth(null) && dim.height == im.getHeight(null))
        	return (BufferedImage)im ;
        else {
        	im = new ImageIcon(im).getImage() ;
        	BufferedImage buffIm = new BufferedImage(dim.width, dim.height, BufferedImage.TYPE_INT_ARGB) ;
        	Graphics g = buffIm.createGraphics() ;
        	g.drawImage(im, 0, 0, null) ;
        	g.dispose() ;
        	return buffIm ;
        }
	}
	
	public static BufferedImage toBufferedImage(Image im, double x) {
		// convertit une image en une instance de BufferedImage avec redimensionnement (par multiplicateur)
        
		if (im instanceof BufferedImage && x == 1.)
        	return (BufferedImage)im ;
        else {
        	int l = (int)(im.getWidth(null)*x), h = (int)(im.getHeight(null)*x) ;
        	im = new ImageIcon(im).getImage() ;
        	BufferedImage buffIm = new BufferedImage(l, h, BufferedImage.TYPE_INT_ARGB) ;
        	Graphics g = buffIm.createGraphics() ;
        	g.drawImage(im, 0, 0, null) ;
        	g.dispose() ;
        	return buffIm ;
        }
	}
	
}
