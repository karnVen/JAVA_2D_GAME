package tile;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.GamePanel;
public class TileManager {
	
	GamePanel gp;
	Tile[] tile;
	
	int mapTileNum[][];//for map 4L
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[10];//this mean gonna create 10 type of tiles
		
		mapTileNum = new int [gp.maxScreenCol][gp.maxScreenRow];//what we gonna do that ki put all the map data in this array (4L)
		getTileImage();
		loadMap("/maps/map01.txt");//4L
		
	}
	
	public void getTileImage() {
		try {
			tile[0]=new Tile();
			tile[0].image =ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));//created image in tile class
			
			tile[1]=new Tile();
			tile[1].image =ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));//created image in tile class 
		    
			tile[2]=new Tile();
			tile[2].image =ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));//created image in tile class 
		
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap(String filePath) {
		//4L
		try {
			InputStream is = getClass().getResourceAsStream(filePath);//for importing file
			BufferedReader br = new BufferedReader(new InputStreamReader(is));// to read content of it 
			
			int col =0;
			int row=0;
			
			while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
			
				String line = br.readLine();//fist br get the content this one gets the line 
				
				
				while(col<gp.maxScreenCol) {
					String numbers[] = line.split(" ");//get a indivisual number from line and stors in number like number[0] =0,number[1]=1 like that 
					int num = Integer.parseInt(numbers[col]);//use col as an index for nubers [] array
					
					mapTileNum[col][row]= num;//we store the extracted number in the mapTileNum[][]
					col++;// continue this until everything in the numbers[] is stored in the maptilenum[][]
					
				}
				if(col == gp.maxScreenCol) {
					col =0;
					row++;
				
				}
			}
			br.close();

			
		}catch(Exception e) {
			 e.printStackTrace();
			
		}
	}
	public void draw(Graphics2D g2) {
		//g2.drawImage(tile[0].image,0,0,gp.tileSize,gp.tileSize,null);  its not efficiant way to create a tiles so we will create a loop
		int col =0;
		int row=0;
		int x =0;
		int y =0;
		
		
		while(col < gp.maxScreenCol && row < gp.maxScreenRow) {
			
			int tileNum = mapTileNum[col][row];//extract a tile number which is stored in mapTileNum[][]
			g2.drawImage(tile[tileNum].image,x,y,gp.tileSize,gp.tileSize,null); //tileNum works as a index in (tile[tileNum]
			//we tileNum gets 0 then its create grass and if 1 then that tile and goes on 
			col++;
			x+= gp.tileSize;
			
			if(col == gp.maxScreenCol) {
				col=0;
				x=0;
				row++;
				y+= gp.tileSize;
			}
		}
		
	}

}
