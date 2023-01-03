package logs;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;


public class gamePlay extends JPanel implements KeyListener , ActionListener {

    private boolean play = false ;
    private int score = 0 ;

    private int totalBricks = 21;

    private final Timer timer;
    private final int delay = 8;

    private int playerX = 310;

    private int ballposX = 120;
    private int ballposY = 350;
    private int ballXdir = -1;
    private int ballYdir = -2;

    private MapGenerator map;


    public gamePlay(){

        map = new MapGenerator(3 , 7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay , this );
        timer.start();
    }

    @Override
    public void paint(Graphics g){
        //Image img = null;

        //BACKGROUND
        Color back = new Color(135,206,235);
        g.setColor(back);
        g.fillRect(1 , 1 , 700 , 600);
        
        //Image sky = new ImageIcon("Image/sky.png").getImage();
        //g.drawImage(sky, 760, 600, this);

        //DRAWING MAP
        map.draw((Graphics2D)g);

        //BORADERS
        
        //Image sky = new ImageIcon("Image/sky.png").getImage();
        //g.drawImage(sky, 760, 600, this);
        Color b = new Color(0,0,0);
        g.setColor(b);
        g.fillRect(0 , 0 , 3 , 592);
        g.fillRect(0 , 0 , 692 , 3);
        g.fillRect(691 , 0 , 3 , 592);

        //SCOERS
        g.setColor(Color.black);
        g.setFont(new Font("serif", Font.BOLD , 25));
        g.drawString(""+ score , 590 , 30);

        //paddle
        //Color h = new Color(139,69,19);
        g.setColor(Color.black);
        g.fillRect(playerX ,550 , 100 , 8);

        //ball
        g.setColor(Color.gray);
        g.fillOval(ballposX , ballposY, 20 , 20);


        if (totalBricks <= 0){

            /*play = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.cyan);
            
            g.setFont(new Font("serif", Font.BOLD , 35));
            g.drawString("You Won" , 260 , 300);

            g.setFont(new Font("serif", Font.BOLD , 25));
            g.drawString("Press ENTER to Restart" , 230 , 350);*/
            
             new logOutPage2().setVisible(true);
              //System.exit(0);
             
             
            g.setFont(new Font("serif", Font.BOLD , 25));
            g.drawString("Exit" , 230 , 350);                                                                                 
            
            //System.exit(0);


        }

        if (ballposY > 570){

           /*play = false;
            ballXdir = 0;
            ballYdir = 0;
           
            g.setColor(Color.cyan);
            g.setFont(new Font("serif", Font.BOLD , 35));
            g.drawString("Game Over , Scores" , 190 , 300);

            g.setFont(new Font("serif", Font.BOLD , 25));
            g.drawString("Press ENTER to Restart" , 230 , 350);*/
            
           new logOutPage().setVisible(true);
            //System.exit(0);
            

        }
        

        g.dispose();

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        timer.start();
        if(play){

            if(new Rectangle(ballposX , ballposY , 20 , 20 ).intersects(new Rectangle(playerX , 550 , 100 , 8))){

                ballYdir = -ballYdir;

            }

            B1 : for (int i =0 ; i < map.map.length ; i++){

                for (int j = 0 ; j < map.map[0].length ; j++){

                    if (map.map[i][j] > 0){

                        int brickX = j * map.brickWidth + 80 ;
                        int brickY = i * map.brickHight + 50 ;
                        int brickWidth = map.brickWidth;
                        int brickHight = map.brickHight;

                        Rectangle rect = new Rectangle(brickX , brickY , brickWidth , brickHight);
                        Rectangle ballRect = new Rectangle(ballposX , ballposY , 20 , 20);
                        Rectangle brickRect = rect;

                        if(ballRect.intersects(brickRect)){

                            map.setBrickValue(0 , i , j);
                            totalBricks--;
                            score+=10;

                            if (ballposX + 19 <=brickRect.x || ballposX + 1 >= brickRect.x + brickRect.width){

                                ballXdir = -ballXdir;

                            }else{

                                ballYdir = -ballYdir;

                            }

                            break B1;

                        }

                    }

                }

            }

            ballposX += ballXdir;
            ballposY += ballYdir;
            if(ballposX < 0){

                ballXdir = -ballXdir;

            }

            if(ballposY < 0){

                ballYdir = -ballYdir;

            }

            if(ballposX > 670){

                ballXdir = -ballXdir;

            }
        }


        repaint();

    }

    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_RIGHT){

            if(playerX >= 600){

                playerX = 600;

            }else{

                moveRight();

            }

        }


        if(e.getKeyCode() == KeyEvent.VK_LEFT){

            if(playerX <= 1){

                playerX = 1;

            }else{

                moveLeft();

            }

        }

        if (e.getKeyCode() == KeyEvent.VK_ENTER){

            if (!play){

                play = true;
                ballposX = 120;
                ballposY = 350;
                ballXdir = -1;
                ballYdir = -2;
                playerX = 310;
                score = 0;
                totalBricks = 21;
                map = new MapGenerator(3, 7);

                repaint();

            }

        }

    }

    public void moveRight(){

        play = true;
        playerX+=20;

    }

    public void moveLeft(){

        play = true;
        playerX-=20;

    }

}

