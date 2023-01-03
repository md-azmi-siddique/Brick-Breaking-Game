package logs;

import java.awt.*;

public class MapGenerator {

     public int map[][];
    public int brickWidth;
    public int brickHight;

    public MapGenerator(int row , int col){

        map = new int[row][col];

         for (int[] map1 : map) {
             
             for (int j = 0; j < map[0].length; j++) {
                 
                 map1[j] = 1;
             
             }
         
         }

        brickWidth = 540/col;
        brickHight = 150/row;

    }

    public void draw(Graphics2D g ){

        for (int i = 0 ; i < map.length ; i++){

            for (int j = 0 ; j < map[0].length ; j++){

                if(map[i][j] > 0){
                    
                    Color h = new Color(139,69,19);
                    g.setColor(h);
                    g.fillRect(j * brickWidth + 80 , i * brickHight + 50 , brickWidth , brickHight);
                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.black);
                    g.drawRect(j * brickWidth + 80 , i * brickHight + 50 , brickWidth , brickHight);

                }

            }
        }
        
    }

    public void setBrickValue(int value , int row , int col){

        map[row][col] = value;

    }
    
}
