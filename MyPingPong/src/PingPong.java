import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class PingPong extends JFrame {
    PingPong(){
        this.setSize(925,440);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        GamePlay gamePlay=new GamePlay();

        URL iconURL = getClass().getResource("table.png");
        Image icon = new ImageIcon(iconURL).getImage();

        this.setIconImage(icon);
        this.add(gamePlay);
        this.setVisible(true);
    }
}
