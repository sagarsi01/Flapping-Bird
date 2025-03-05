import javax.swing.*;
import java.awt.*;

public class App {
    public static void main(String[] args) throws Exception {
        
        JFrame frame = new JFrame();
        frame.setSize(360,640);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        Board bd = new Board();
        frame.add(bd);

        frame.setVisible(true);
    }
}
