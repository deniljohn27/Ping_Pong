import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

public class GamePlay extends JPanel implements ActionListener, KeyListener {

    int playerWidth=10,playerHeight=80;
    int xPlayerL=5,yPlayerL=150;
    int xPlayerR=890,yPlayerR=150;

    int xCoBall=20,yCoBall=190,ballWidth=25;
    int ballXspeed=5,ballYspeed=-3;

    Timer time;
    int scoreL=0,scoreR=0,target=10;
    JLabel leftL,rightL;
    boolean start=false;


     GamePlay(){
         leftL=new JLabel();
         rightL=new JLabel();

         this.setLayout(null);
         this.setBackground(Color.BLACK);
         this.setSize(910,402);

         leftL.setText("Score : " + scoreL);
         leftL.setForeground(Color.WHITE);
         leftL.setFont(new Font(Font.SANS_SERIF,Font.BOLD,35));
         leftL.setBounds(300,0,200,35);

         rightL.setText(scoreR+" : Score" );
         rightL.setForeground(Color.WHITE);
         rightL.setFont(new Font(Font.SANS_SERIF,Font.BOLD,35));
         rightL.setBounds(470,0,200,35);





         this.add(leftL);
         this.add(rightL);
         this.setFocusable(true);
         this.addKeyListener(this);


         time=new Timer(11,this);
         time.start();



    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        //player left
        g.setColor(Color.RED);
        g.fillRect(xPlayerL,yPlayerL,playerWidth,playerHeight);


        //player right
        g.setColor(Color.BLUE);
        g.fillRect(xPlayerR,yPlayerR,playerWidth,playerHeight);

        //ball
//        g.setColor(Color.YELLOW);
//        g.fillOval(xCoBall,yCoBall,ballWidth,ballWidth);
         URL iconURLb = getClass().getResource("pball.png");
         Image iconb = new ImageIcon(iconURLb).getImage();
         g.drawImage(iconb,xCoBall,yCoBall,ballWidth,ballWidth,null);

        //center line
        g.setColor(Color.WHITE);
        g.drawLine(455,0,455,402);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        //ball bounce
        if(yCoBall<=0||yCoBall+25>=400){
            ballYspeed=-ballYspeed;
        }
         xCoBall=xCoBall+ballXspeed;
         yCoBall=yCoBall+ballYspeed;
         Rectangle ballCover=new Rectangle(xCoBall,yCoBall,ballWidth,ballWidth);

         //playerR intersects
        Rectangle playerRCover=new Rectangle(xPlayerR,yPlayerR,playerWidth,playerHeight);
        if(playerRCover.intersects(ballCover)){
            ballXspeed=-ballXspeed;

        }
        //playerL intersects
        Rectangle playerLCover=new Rectangle(xPlayerL,yPlayerL,playerWidth,playerHeight);
        if(playerLCover.intersects(ballCover)){
            ballXspeed=-ballXspeed;

        }
        if(xCoBall<0){
            scoreR++;
            rightL.setText(scoreR+" : Score" );
            rightL.setForeground(Color.BLUE);
            ballXspeed=-ballXspeed;
        }
        if(xCoBall>890){
            scoreL++;
            leftL.setText("Score : " +scoreL);
            leftL.setForeground(Color.RED);
            ballXspeed=-ballXspeed;
        }
        //winner
        if(scoreL==target){
            leftL.setText("WINNER");
            leftL.setForeground(Color.GREEN);

            rightL.setText("LOSER");
            rightL.setForeground(Color.RED);
            repaint();
            time.stop();

        }
        if(scoreR==target){
            leftL.setText("LOSER");
            leftL.setForeground(Color.GREEN);

            rightL.setText("WINNER");
            rightL.setForeground(Color.RED);
            repaint();
            time.stop();

        }


         repaint();

    }


    @Override
    public void keyPressed(KeyEvent e) {

         if(e.getKeyCode()==KeyEvent.VK_UP){
             if(yPlayerR<=0){
                 yPlayerR=0;
             }
             else{yPlayerR=yPlayerR-20;}
         }
         if(e.getKeyCode()==KeyEvent.VK_DOWN){
             if(yPlayerR>=320){
                 yPlayerR=320;
             }
             else{yPlayerR=yPlayerR+20;}
         }
         if(e.getKeyCode()==KeyEvent.VK_W){
             if(yPlayerL<=0){
                 yPlayerL=0;
             }
             else{yPlayerL=yPlayerL-20;}
         }
         if(e.getKeyCode()==KeyEvent.VK_S){
             if(yPlayerL>=320){
                 yPlayerL=320;
             }
             else{yPlayerL=yPlayerL+20;}
         }
         if(e.getKeyCode()==KeyEvent.VK_SPACE){
             if(start==false){start=true;time.start();}
             else{start=false;time.stop();}
         }
         if(e.getKeyCode()==KeyEvent.VK_SHIFT){
             yPlayerL=150;
             yPlayerR=150;
             xCoBall=20;
             yCoBall=190;
             scoreL=0;
             leftL.setText("Score : " + scoreL);
             leftL.setForeground(Color.WHITE);
             scoreR=0;
             rightL.setText(scoreR+" : Score" );
             rightL.setForeground(Color.WHITE);
             time.stop();

         }
         repaint();

    }





    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
}
