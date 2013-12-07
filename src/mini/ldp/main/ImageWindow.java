package mini.ldp.main;


import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImageWindow extends JFrame {

	private JPanel panel;
	private JLabel label;
	public ImageWindow(String name,BufferedImage image)
	{
		super("image "+name);
		
		panel=new JPanel();
		label=new JLabel();
		label.setIcon(new ImageIcon(image));
		
		panel.add(label);
		
		
		this.add(panel);
		this.setBounds(0,0,image.getWidth(),image.getHeight());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	

	
	public ImageWindow(String name,String fileName) {
		super("image "+name);
		
		panel=new JPanel();
		label=new JLabel();
		label.setIcon(new ImageIcon(fileName));
		
		panel.add(label);
		
		
		this.add(panel);
		this.setBounds(0,0,756,512);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	
	
}
