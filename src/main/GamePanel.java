package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Player;
import tile.TileManager;
//blue print ... we used JPanel and add our rule also 
public class GamePanel extends JPanel implements Runnable{
	//gonna write screen settings in it
	final int originalTileSize= 16;//size of every character and tile use 16 because its standered 
	final int scale = 3;
	public final int tileSize = originalTileSize * scale;// the OG 16 is to small to make it bigger.
	public final int maxScreenCol=16;
	public final int maxScreenRow=12;
	public final int screenWidth =tileSize*maxScreenCol;
	public final int screenHight = tileSize*maxScreenRow;
	//fps
	int FPS =60;
	
	TileManager tileM =new TileManager(this);//4L 
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	Player player = new Player(this,keyH);
	
	
	
	/*//set players default postion
	int playerX =100;
	int playerY= 100;
	int playerSpeed = 4; done in playerclass(3L)*/
	//constructor run when created it 
	public GamePanel (){
		this.setPreferredSize(new Dimension(screenWidth,screenHight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);//if set it true all the drawing from this component will be done in an offscreen painting buffer
		                             //basically it improve rendering performance
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void run() {
		double drawInterval = 1000000000/FPS;
		double delta =0;
		long lastTime = System.nanoTime();
		long currentTime;
		int drawCount = 0;
		long timer =0;
		while (gameThread !=null) {
			currentTime = System.nanoTime();
			delta +=(currentTime - lastTime)/drawInterval;
			timer+=(currentTime - lastTime);
			lastTime = currentTime;
			if(delta >=1) {
				update();
				repaint();
				delta--;
				drawCount++;
			}
			if (timer >= 1000000000) {
				System.out.println("FPS:"+drawCount);
				drawCount =0;
				timer=0;
			}
			}
		}
	
//	
//	public void run() {
//		
//		//sleep method for seting fps
//		double drawInterval = 1000000000/FPS;//1 seconds /60 mean draw screen 60 times per second
//		
//		double nextDrawTime = System.nanoTime() + drawInterval; //nano give systems current time then we add 1/60 seconds mean after that next pant will happens
//		
//		while(gameThread!=null) {
//			//System.out.println("hi");
//			//long currentTime = System.nanoTime();//to see current time 1 million nanosencds = 1 seconds
//			
//			
//			//upadate : logic for updating the position of character
//			update();
//			
//			//draw: draw the screen with update information
//			repaint();
//			// so if fps is 60 the program  does this (upadate and draw) 60 times per seconds 
//			
//			
//			try {
//				double remainingTime = nextDrawTime - System.nanoTime();// as we know how much update and repaint is fast so there will be remaining time after these methods
//				remainingTime = remainingTime/1000000;//sleep only accept in milli seconds and we had remaining cal in nano so had to convert
//				
//				if(remainingTime <0) {
//					remainingTime = 0;//if the update and rempant method took more time then astimate the sleep dont have to run thats why
//				}
//				Thread.sleep((long) remainingTime);
//			
//				nextDrawTime += drawInterval;
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}//let sleep for remaining time want to write Thread.sleep(remainingTime) but java rull did try catch 
//			
//		}
//	}
	public void update() {
/*		if(keyH.upPressed ==true) {
			playerY -= playerSpeed;
		}
		else if (keyH.downPressed == true) {
			playerY += playerSpeed;
		}else if(keyH.leftPressed == true) {
			playerX -= playerSpeed;
		}else if (keyH.rightPressed==true) {
			playerX += playerSpeed;
			
		}
		gonna make it seprate because in future we gonna write this for different differnt entites*/
		player.update();//that comment code from player specifc class 
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
//		1. public void paintComponent(Graphics g)
//		This is a built-in method from the JPanel class that you are overriding.
//
//		The Graphics g parameter: Think of g as your Paintbrush. It is an object that has "powers" like g.drawRect(), g.drawImage(), or g.setColor(). Without this g, you can't see anything on the screen.
//
//		Automatic Call: You don't usually call this method yourself. Java calls it whenever the window needs to be refreshed (like when it first opens or when you tell it to "repaint").
//	
		Graphics2D g2 = (Graphics2D)g;
		//graphics2d class extends the graphics class to provide more sophisticated control over geometry , coordinate transformation , color management, and text layout
       /* g2.setColor(Color.white);	
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        		gonna make it seprate because in future we gonna write this for different differnt entites*/
       
		tileM.draw(g2);//4L must place before player.draw because its like leyer
		player.draw(g2);
       g2.dispose();
	
	}
	
	
	

}
