package mini.ldp.main;



import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileView;
import javax.swing.plaf.FileChooserUI;

import mini.ldp.conversionclasses.Ldp;
import mini.ldp.facerecognizer.HistogramIntersectionEngine;
import mini.ldp.help.Histogram;

public class MainApplication extends JFrame {
	
	
	private JLabel label;
	private Container container,bgImage;
	private Button start,lbp,write,histCalc,recognizer;
	private String selected=null;
	private BufferedImage testImage,lbpImage,ldp0,ldp45,ldp90,ldp135;
	private String imgDbDir;
	private int lbpHist[],ldp0Hist[],ldp45Hist[],ldp90Hist[],ldp135Hist[];
	private HistogramIntersectionEngine engine;
	

	public MainApplication(String name,String imageDb) throws Exception {
		// TODO Auto-generated constructor stub
		super(name);
		this.imgDbDir = imageDb;
		start = new Button("select target file");
		write=new Button(" write Images");
		lbp= new Button("start conversion");
		histCalc = new Button("calculate hist");
		recognizer = new Button("Start Recognizing");
		this.setIconImage(ImageIO.read(new File("C:\\Users\\vijay\\Desktop\\cover.jpg")));
	//	((JFrame) bgImage).setIconImage(new ImageIcon("C:\\Users\\vijay\\Desktop\\cover.jpg"));
		
		
		start.setCursor(new Cursor(Cursor.HAND_CURSOR));
		write.setBounds(481,342,100,50);  //481
		start.setBounds(81,342,100,50); //81
		histCalc.setBounds(681,342,100,50);  //681
		lbp.setBounds(281,342,100,50); //281
		recognizer.setBounds(881, 342, 100, 50);		
		
		
		container  = new Container();
		container.setBounds(10,10,100,100);
		label= new JLabel("Local Descriptive Pattern");
		label.setBackground(Color.DARK_GRAY);
		this.setBackground(Color.lightGray);
		container.setBackground(Color.BLACK);
		
		
		
		label.setBorder(BorderFactory.createEtchedBorder());
		label.setAlignmentX(20);
		label.setPreferredSize(new Dimension(32, 32));
		label.setBounds(new Rectangle(10,10,512,256));
		label.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
			label.setFont(Font.getFont(Font.SANS_SERIF));
			container.add(label);
			container.add(lbp);
			container.add(start);
			container.add(write);
			container.add(histCalc);
			container.add(recognizer);
			
			
			this.add(container);
			start.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					
					JFileChooser fileChooser=new JFileChooser();
					fileChooser.showOpenDialog(null);
					File f= fileChooser.getSelectedFile();
					selected = f.getAbsolutePath();
					try {
						testImage = ImageIO.read(f);
						engine = new HistogramIntersectionEngine(imgDbDir, testImage);
						
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ImageWindow imageWin=new ImageWindow(f.getName(),f.getAbsolutePath());
				}
			});
			lbp.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					try {
						ImageWindow imageWin=new ImageWindow("lbp "+selected, lbpImage=Ldp.lbpImage(ImageIO.read(new File(selected))));
						ImageWindow imageWinLdp0=new ImageWindow("ldp0 "+selected, ldp0=Ldp.ldpImage(ImageIO.read(new File(selected)),0));
						ImageWindow imageWinLdp45=new ImageWindow("ldp45 "+selected, ldp45=Ldp.ldpImage(ImageIO.read(new File(selected)),45));
						ImageWindow imageWinLdp90=new ImageWindow("ldp90 "+selected, ldp90=Ldp.ldpImage(ImageIO.read(new File(selected)),90));
						ImageWindow imageWinLdp135=new ImageWindow("ldp135 "+selected, ldp135=Ldp.ldpImage(ImageIO.read(new File(selected)),135));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			
			
			write.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					try {
						Ldp.saveImage(lbpImage, "lbp");
						Ldp.saveImage(ldp0, "ldp0");
						Ldp.saveImage(ldp0, "ldp45");
						Ldp.saveImage(ldp0, "ldp90");
						Ldp.saveImage(ldp0, "ldp135");
						JOptionPane.showMessageDialog(null,"images are saved sucessfuly");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,"error in writing images");
						e.printStackTrace();
					}
				}
			});
			
			histCalc.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
				
					Histogram h=new Histogram();
					lbpHist=h.getHistogramBins(lbpImage);
					ldp0Hist=h.getHistogramBins(ldp0);
					ldp45Hist=h.getHistogramBins(ldp45);
					ldp90Hist=h.getHistogramBins(ldp90);
					ldp135Hist=h.getHistogramBins(ldp135);
					
					JOptionPane.showMessageDialog(null, getBinValues(lbpHist),"lbp",0,new ImageIcon(lbpImage));
					JOptionPane.showMessageDialog(null, getBinValues(ldp0Hist),"ldp0",0,new ImageIcon(ldp0));
					JOptionPane.showMessageDialog(null, getBinValues(ldp45Hist),"ldp45",0,new ImageIcon(ldp45));
					JOptionPane.showMessageDialog(null, getBinValues(ldp90Hist),"ldp90",0,new ImageIcon(ldp90));
					JOptionPane.showMessageDialog(null, getBinValues(ldp135Hist),"ldp135",0,new ImageIcon(ldp135));
					
				}
			});
			
			
			
			recognizer.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					// TODO Auto-generated method stub
					try {
						String spath; //recognized image path
						ImageWindow recognized=new ImageWindow("recognized",ImageIO.read(new File(spath = engine.getSimilarImage(0))));
						System.out.println(spath);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			
			
			this.setBounds(0,0,1024,512);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}
	
	
	public String getBinValues(int[] binsCount)
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
}
