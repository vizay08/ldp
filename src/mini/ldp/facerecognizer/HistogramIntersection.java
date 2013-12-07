package mini.ldp.facerecognizer;

import java.awt.image.BufferedImage;

import mini.ldp.help.Histogram;

public class HistogramIntersection {

	private static int numBlocks;
	
	public static void setNumBlocks(int blocks)
	{
		numBlocks = blocks;
	}
	
	public static long[] imageSpatialHistogram(BufferedImage test,BufferedImage base,int blocks)
	{
		setNumBlocks(blocks);
		long result[] = new long[blocks];
		int width=base.getWidth();
		int height=base.getHeight();
		int startX,startY,endX,endY;
		for(int k=0;k<blocks;k++)
		{
			
			
			
		}
		
		
		return result;
	}
	
	public static long[] imageSpatialHistogram(BufferedImage test,BufferedImage base)
	{
		int blocks=8;
		long result[] = new long[8];
		int width=test.getWidth();
		int height=test.getHeight();
		int startX=0,startY=0,endX=width/blocks,endY=height/blocks,tempx,tempy;
		result[0]=SpatialHistogram.spatialHistogram(test, base, startX, startY, startX=endX, endY);
		startX+=1;
		result[1]=SpatialHistogram.spatialHistogram(test, base, startX, startY, startX=2*endX, endY);
		startX+=1;
		result[2]=SpatialHistogram.spatialHistogram(test, base, startX, startY, startX=3*endX, endY);
		startX+=1;
		result[3]=SpatialHistogram.spatialHistogram(test, base, startX, startY, startX=4*endX, endY);
		startX+=1;
		
		
		startX=0;
		startY=height/blocks;
		result[4]=SpatialHistogram.spatialHistogram(test, base, startX, startY, startX=endX, endY);
		startX+=1;
		result[5]=SpatialHistogram.spatialHistogram(test, base, startX, startY, startX=2*endX, endY);
		startX+=1;
		result[6]=SpatialHistogram.spatialHistogram(test, base, startX, startY, startX=3*endX, endY);
		startX+=1;
		result[7]=SpatialHistogram.spatialHistogram(test, base, startX, startY, startX=4*endX, endY);
		startX+=1;
		return result;
	}
}
