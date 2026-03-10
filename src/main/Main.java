package main;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		JFrame window = new JFrame();//to create window for game
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//it for when we click on x to close window
		window.setResizable(false);//so that window does not resize itself
		window.setTitle("JavaGame");
		GamePanel gamePanel = new GamePanel();//added to window just like jfram but it has more extra personal code
		window.add(gamePanel);
		window.pack();
		window.setLocationRelativeTo(null);//not specify the location because it will displayed at the center 
		window.setVisible(true);
		
		//in simple program the program gets process when we  do something like pressing button and then its stop and waits for another event
		//but in 2d game program keeps runing
		gamePanel.startGameThread();
	}

}
;