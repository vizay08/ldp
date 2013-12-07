package mini.ldp.facerecognizer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import mini.ldp.conversionclasses.Ldp;
import mini.ldp.help.DatabaseSimulator;

public class HistogramIntersectionEngine {

	private String path=null;
	private DatabaseSimulator ds;
	private BufferedImage testImage=null,tempImage;
	private String betRecogImage;
	private File[] files;
	private long tempHist[],startHist[];
	
	public HistogramIntersectionEngine(String path,BufferedImage testImage)
	{
		this.path = path;
		ds=new DatabaseSimulator(path);
		this.testImage = testImage;
	}
	
	
	public boolean isLessThan(long[] startHist2,long[] tempHist2)
	{
		int count=1;
		for(int i=0;i<8;i++)
			if(startHist2[i]<=tempHist2[i])
				count++;
		if(count<=4)
			return true;
		return false;
	}
	
	public String getSimilarImage(int angle) throws IOException
	{
		ds.containsImageFile();
		files=ds.getFiles();
		int start=0;
		for(int i=0;i<files.length;i++)
		{
			if(files[i].toString().endsWith(".png")||files[i].toString().endsWith(".PNG")||files[i].toString().endsWith(".jpg")||files[i].toString().endsWith(".JPG")||files[i].toString().endsWith(".bmp")||files[i].toString().endsWith(".BMP")||files[i].toString().endsWith(".pgm")||files[i].toString().endsWith(".PGM"))
			{
				tempImage=ImageIO.read(files[i]);
				
				startHist= HistogramIntersection.imageSpatialHistogram(Ldp.ldpImage(testImage, angle),Ldp.ldpImage(tempImage, angle));
				
				start=i;
				break;
			
			}
		}
		for(int i=start+1;i<files.length;i++)
		{
			if(files[i].toString().endsWith(".png")||files[i].toString().endsWith(".PNG")||files[i].toString().endsWith(".jpg")||files[i].toString().endsWith(".JPG")||files[i].toString().endsWith(".bmp")||files[i].toString().endsWith(".BMP")||files[i].toString().endsWith(".pgm")||files[i].toString().endsWith(".PGM"))
			{

				tempImage=ImageIO.read(files[i]);
				
				tempHist= HistogramIntersection.imageSpatialHistogram(Ldp.ldpImage(testImage, angle),Ldp.ldpImage(tempImage, angle));
				
				if(isLessThan(startHist, tempHist))
					startHist=tempHist;
				start=i;
			
			}
		}
		return betRecogImage = files[start].getAbsolutePath();
	}
 
}
