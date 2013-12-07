package mini.ldp.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.TileObserver;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;


import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import mini.ldp.conversionclasses.Ldp;
import mini.ldp.help.DatabaseSimulator;
import mini.ldp.help.Histogram;
import mini.ldp.preprocesser.ColorToBW;

public class MainClass {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		DatabaseSimulator d;
		String path;
		for(;;)
		{
		path=JOptionPane.showInputDialog(null, "Enter the path for base image");
		d=new DatabaseSimulator(path);
		if(d.containsImageFile())
			{
			JOptionPane.showMessageDialog(null, "Path set");
			break;
			}
		JOptionPane.showMessageDialog(null, "Error: Either selected is not a directory or doesn't contain image files");
		}
	
		
		MainApplication base=new MainApplication("base",path);
		
		
	
			}
}


