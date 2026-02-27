package main;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
//blue print ... we used JPanel and add our rule also 
public class GamePanel extends JPanel implements Runnable{
	//gonna write screen settings in it
	final int originalTileSize= 16;//size of every character and tile use 16 because its standered 
	final int scale = 3;
	final int tileSize = originalTileSize * scale;// the OG 16 is to small to make it bigger.
	final int maxScreenCol=16;
	final int maxScreenRow=12;
	final int screenWidth =tileSize*maxScreenCol;
	final int screenHight = tileSize*maxScreenRow;
	
	
	Thread gameThread;
	
	//constructor run when created it 
	public GamePanel (){
		this.setPreferredSize(new Dimension(screenWidth,screenHight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);//if set it true all the drawing from this component will be done in an offscreen painting buffer
		                             //basically it improve rendering performance
		
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void run() {
		
	}
	
	
	

}
