package mini.ldp.preprocesser;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;



public class ColorToBW {

	public static BufferedImage colorToGrayScale(BufferedImage bw)
	{
		
		BufferedImage br=new BufferedImage(bw.getWidth(),bw.getHeight(),bw.getType());
		int val,r,g,b,out;
		Color pixValue,avgValue;
		for (int i = 0; i < bw.getWidth(); i++) {
			for (int j = 0; j < bw.getHeight(); j++) {
				pixValue=new Color(bw.getRGB(i, j));
				r=pixValue.getRed();
				g=pixValue.getGreen();
				b=pixValue.getBlue();
				
				out=(r+g+b)/3;
				
				avgValue= new Color(out,out,out);
				br.setRGB(i, j, avgValue.getRGB());
				
				
				
	
			}
		}
		return br;
	}
}
