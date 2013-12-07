package mini.ldp.facerecognizer;

import java.awt.image.BufferedImage;

import mini.ldp.help.Histogram;

//Spatial Histogram is used to compute spatial histogram of a block
public class SpatialHistogram {
	
	
	
	public static long spatialHistogram(BufferedImage test,BufferedImage base,int startX,int startY,int endX,int endY)
	{
		int testHist[],baseHist[]; // holds 
		Histogram h=new Histogram();
		long ret=0;
		
		
		testHist=h.getHistogramBins(test, startX, startY, endX, endY);
		baseHist=h.getHistogramBins(test, startX, startY, endX, endY);
		for(int i=0;i<256;i++)
			ret+=Math.min(testHist[i], baseHist[i]);
		return ret;
	}
}
