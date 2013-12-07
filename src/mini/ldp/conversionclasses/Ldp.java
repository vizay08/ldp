package mini.ldp.conversionclasses;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;

import javax.imageio.ImageIO;


public class Ldp {
	
	
	
	
	
private static int atSomePoint(BufferedImage image,int row,int col)
{
	if(row>=0&&row<image.getHeight()&&col>=0&&col<image.getWidth())
	{

		
		return new Color(image.getRGB(col,row)).getRed();
	}
else
	{
		return 0;
	}
}



private static int differential(BufferedImage image,int i,int j,int cols,int rows,int angle)
{
	
	
	
	int a1,a2;
	

	if(angle==0)
	{

		
		return atSomePoint(image, i, j)-atSomePoint(image, i, j+1);
		
		
		
		
	}
	else if(angle==45)
	{
		return atSomePoint(image, i, j)-atSomePoint(image, i-1, j+1);
		
	}
	else if(angle==90)
	{
		return atSomePoint(image, i, j)-atSomePoint(image, i-1, j);
		
	}
	else if(angle==135)
	{
		
		return atSomePoint(image, i, j)-atSomePoint(image, i-1, j-1);
		
	}
	else
	return 0;
	
	
}

public static void saveImage(BufferedImage image,String fileName) throws Exception
{
	ImageIO.write(image, "png", new File(fileName+".png"));
}

public static BufferedImage lbpImage(BufferedImage imageIn)
{

	
	int rows=imageIn.getHeight();
	int cols=imageIn.getWidth();
	System.out.println("height: "+rows+"        "+"width: "+cols);
	BufferedImage imageOut=new BufferedImage(cols, rows, imageIn.getType());
	for(int i=0;i<imageIn.getHeight();i++)
	{
		for(int j=0;j<imageIn.getWidth();j++)
		{
			int b=0,p=0,val=0;
			
			b=(atSomePoint(imageIn, i, j)-atSomePoint(imageIn, i-1, j-1)>0)?1:0;
			val+=b*Math.pow(2,p++);

			b=(atSomePoint(imageIn, i, j)-atSomePoint(imageIn, i-1, j)>0)?1:0;
			val+=b*Math.pow(2,p++);

			b=(atSomePoint(imageIn, i, j)-atSomePoint(imageIn, i-1, j+1)>0)?1:0;
			val+=b*Math.pow(2,p++);

			b=(atSomePoint(imageIn, i, j)-atSomePoint(imageIn, i, j+1)>0)?1:0;
			val+=b*Math.pow(2,p++);

			b=(atSomePoint(imageIn, i, j)-atSomePoint(imageIn, i+1, j+1)>0)?1:0;
			val+=b*Math.pow(2,p++);

			b=(atSomePoint(imageIn, i, j)-atSomePoint(imageIn, i+1, j)>0)?1:0;
			val+=b*Math.pow(2,p++);

			b=(atSomePoint(imageIn, i, j)-atSomePoint(imageIn, i+1, j-1)>0)?1:0;
			val+=b*Math.pow(2,p++);

			b=(atSomePoint(imageIn, i, j)-atSomePoint(imageIn, i, j-1)>0)?1:0;
			val+=b*Math.pow(2,p++);
			
			Color out=new Color(val,val,val);
		
			imageOut.setRGB(j, i, out.getRGB());
		}		
	}
	return imageOut;
}
	
	public static BufferedImage ldpImage(BufferedImage imageIn,int angle)
	{
	
		
		int rows=imageIn.getHeight();
		int cols=imageIn.getWidth();
		System.out.println("height: "+rows+"        "+"width: "+cols);
		BufferedImage imageOut=new BufferedImage(cols, rows, imageIn.getType());
		for(int i=0;i<imageIn.getHeight();i++)
		{
			for(int j=0;j<imageIn.getWidth();j++)
			{
				int b=0,p=0,val=0;
				
				b=(differential(imageIn,i,j,rows,cols,angle)*differential(imageIn,i-1,j-1,rows,cols,angle)>0)?1:0;
				val+=b*Math.pow(2,p++);

				b=(differential(imageIn,i,j,rows,cols,angle)*differential(imageIn,i-1,j,rows,cols,angle)>0)?1:0;
				val+=b*Math.pow(2,p++);

				b=(differential(imageIn,i,j,rows,cols,angle)*differential(imageIn,i-1,j+1,rows,cols,angle)>0)?1:0;
				val+=b*Math.pow(2,p++);

				b=(differential(imageIn,i,j,rows,cols,angle)*differential(imageIn,i,j+1,rows,cols,angle)>0)?1:0;
				val+=b*Math.pow(2,p++);

				b=(differential(imageIn,i,j,rows,cols,angle)*differential(imageIn,i+1,j+1,rows,cols,angle)>0)?1:0;
				val+=b*Math.pow(2,p++);

				b=(differential(imageIn,i,j,rows,cols,angle)*differential(imageIn,i+1,j,rows,cols,angle)>0)?1:0;
				val+=b*Math.pow(2,p++);

				b=(differential(imageIn,i,j,rows,cols,angle)*differential(imageIn,i+1,j-1,rows,cols,angle)>0)?1:0;
				val+=b*Math.pow(2,p++);

				b=(differential(imageIn,i,j,rows,cols,angle)*differential(imageIn,i,j-1,rows,cols,angle)>0)?1:0;
				val+=b*Math.pow(2,p++);
				
				Color out=new Color(val,val,val);
			
				imageOut.setRGB(j, i, out.getRGB());
			}		
		}
		return imageOut;
	}
	
}
