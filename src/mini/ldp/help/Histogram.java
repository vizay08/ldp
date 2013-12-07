package mini.ldp.help;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Histogram {
	int i,j;
	

	private BufferedImage histogram;
	private int binsCount[]=new int[256];

	public Histogram()
	{
		
		
	}
	private  void countBins(BufferedImage image)
	{
		for(i=0;i<256;i++)
			binsCount[i]=0;
		
		
		int width=image.getWidth(),
				height=image.getHeight();
		
		for(int i=0;i<width;i++)
			for(j=0;j<height;j++)
				binsCount[new Color(image.getRGB(i, j)).getRed()]++;		
	}
	
	public String getBinValues()
	{
		String toBeReturned="";
		for(int i=0;i<256;i++)
		{
			toBeReturned+=" "+binsCount[i];
			if(i%50==0)
				toBeReturned+="\n";
		}
		return toBeReturned;
	}
	
	private void countBinsSpatial(BufferedImage image,int startX,int startY,int endX,int endY)
	{
		for(i=0;i<256;i++)
			binsCount[i]=0;
		
		
		int width=image.getWidth(),
				height=image.getHeight();
		
		for(int i=startX;i<endX&&i<width;i++)
			for(j=startY;j<endY&&j<height;j++)
				binsCount[new Color(image.getRGB(i, j)).getRed()]++;		
	}
	
	public int[] getHistogramBins(BufferedImage image) {
		
		countBins(image);
		return binsCount;
	}
	
public int[] getHistogramBins(BufferedImage image,int startX,int startY,int endX,int endY) {
		
		countBinsSpatial(image, startX, startY, endX, endY);
		return binsCount;
	}
	
	public BufferedImage getHistogramImage(BufferedImage image) throws IOException   //image exceeding the ordinary image size 
	{
		long sum = 0;
			int max=0,tempVal;
			
			countBins(image);
			
			for(i=0;i<256;i++)
			{
				tempVal=binsCount[i];
				if(tempVal>max)
					max=tempVal;
				System.out.print(binsCount[i]+" ");
				sum+=tempVal;
			}
			
			System.out.print("\n sum:"+ sum);
			
			
		
			
			histogram =new BufferedImage(255,max+1,image.getType());
			
			Graphics2D g=histogram.createGraphics();
			g.setBackground(Color.black);
			for(i=0;i<256;i++)
				g.drawLine(0,0,i, max-binsCount[i]);
			
			g.dispose();
			
			System.out.println("hist width: "+histogram.getWidth()+" hist height: "+histogram.getHeight());
//			ImageIO.write(histogram, "png", new File("histogram.png"));
			
			return histogram;
	}
}
