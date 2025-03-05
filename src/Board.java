import javax.swing.*;
import java.awt.*;
public  class Board extends JPanel {
    
    private Image backimage;
    private Image birdimage;
    private Image botpipe;
    private Image toppipe;

    public Board()
    {
        backimage = new ImageIcon(getClass().getResource("./flappybirdbg.png")).getImage();
        birdimage = new ImageIcon(getClass().getResource("./flappybird.png")).getImage();
        botpipe = new ImageIcon(getClass().getResource("./bottompipe.png")).getImage();
        toppipe = new ImageIcon(getClass().getResource("./toppipe.png")).getImage();
    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(backimage,0,0,360,640,null);
        g.drawImage(birdimage,0,320,30,30,null);
    }

}
