package mini.ldp.help;

import java.io.File;

import javax.xml.crypto.Data;

public class DatabaseSimulator {

	private String path;
	private File files[];
	public DatabaseSimulator(String path)
	{
		this.path= path;
	}
	
	
	//checking whether string is a directory
	
	public boolean isDirectory()
	{
		return new File(path).isDirectory();
	}
	
	
	public boolean containsImageFile()
	{
		if(!isDirectory())
		return false;
		
		File dir=new File(path);
		
		files=dir.listFiles();
		
		
		for(int i=0;i<files.length;i++)
			if(files[i].toString().endsWith(".png")||files[i].toString().endsWith(".PNG")||files[i].toString().endsWith(".jpg")||files[i].toString().endsWith(".JPG")||files[i].toString().endsWith(".bmp")||files[i].toString().endsWith(".BMP")||files[i].toString().endsWith(".pgm")||files[i].toString().endsWith(".PGM"))
				return true;
		return false;
	}
	
	
	public File[] getFiles()
	{
		return this.files;
	}
	
	
}
