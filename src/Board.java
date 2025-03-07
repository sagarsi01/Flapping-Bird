import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Rectangle;
public  class Board extends JPanel implements ActionListener {
    
    private Image backimage;
    private Image birdimage;
    private Image botpipe;
    private Image toppipe;
    private Timer timer; /// this is used to perform a task continus like a loop (game loop)
    int position=2;
    int antigravity=40;
    int rmove = 2;
    int randomNumber ;
    ArrayList<Tpipe> talpipe = new ArrayList<>();
    ArrayList<Bpipe> balpipe = new ArrayList<>();
    int framecount =0;
    int randombt=0;


    public Board()
    {
        backimage = new ImageIcon(getClass().getResource("./flappybirdbg.png")).getImage();
        birdimage = new ImageIcon(getClass().getResource("./flappybird.png")).getImage(); 
        botpipe = new ImageIcon(getClass().getResource("./bottompipe.png")).getImage();
        toppipe = new ImageIcon(getClass().getResource("./toppipe.png")).getImage();
        
        bird = new Bird(birdimage);

       // tp = new Tpipe(toppipe,randomNumber);

        addnewpipe();
        
        talpipe.add(tp);//talpipe is a loop in so we need to add value to it to draw
        balpipe.add(bp);

        setFocusable(true);
        requestFocusInWindow();     
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    bird.y-=antigravity;
                    System.out.println("Space Key Pressed");
                }
            }
        });

        this.timer = new Timer(1000/60, this); // Calls actionPerformed every 20ms
        this.timer.start();

    }

    class Bird
    {
        int x = 20;
        int y = 320;
        int width = 30;
        int height = 30;
        Image img;
        Bird(Image img)
        {
            this.img = img;
        }
        //getBound()
    public Rectangle getbound()
    {
        return new Rectangle(x,y,width,height);
    }
    }
    Bird bird;
    
    //Pipe

    class Tpipe
    {
        int x = 340;
        int y = 0;
        int width = 90;
        int height ;
        Image img;
        Tpipe(Image img,int height)
        {
            this.img = img;
            this.height = height;
        }
        //getBound()
    public Rectangle getbound()
    {
        return new Rectangle(x,y,width,height);
    }

    }
    Tpipe tp;

    //bottom pipe
    class Bpipe
    {
        int x = 340;
        int y ;
        int width = 90;
        int height ;
        Image img;
        Bpipe(Image img,int height)
        {
            this.img = img;
            this.height = height;
            this.y = 640 - height;
        }
        //getBound()
    public Rectangle getbound()
    {
        return new Rectangle(x,y,width,height + 20);
    }

    }
    Bpipe bp;

    //adding new pipe

        public void addnewpipe()
        {
            randomNumber = (int) (Math.random() * 201) + 150;
            System.out.println(randomNumber);
            randombt = 640-(randomNumber+100);
            System.out.println(randombt);
            tp = new Tpipe(toppipe,randomNumber);
            bp = new Bpipe(botpipe,randombt);
            talpipe.add(tp);
            balpipe.add(bp);
        }
    //Now it time for keylistner

    // public void space()
    // {
    //     addKeyListener(new KeyAdapter() {
    //         @Override    
    //         public void keyPressed(KeyEvent e) {
    //             if (e.getKeyCode() == KeyEvent.VK_SPACE) {
    //                 bird.y-=antigravity;
    //                 System.out.println("Space Key Pressed");
    //             }
    //         }
    //     });
    // }
    

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(backimage,0,0,360,640,null);
        g.drawImage(bird.img,bird.x,bird.y,bird.width,bird.height,null);
        //g.drawImage(tp.img,tp.x,tp.y,tp.width,tp.height,null);
       // g.drawImage(talpipe.img,talpipe.x,talpipe.width,talpipe.height,null);
       for (Tpipe aobj : talpipe) {
        g.drawImage(aobj.img, aobj.x, aobj.y, aobj.width, aobj.height, null);
        }
        for (Bpipe aobj : balpipe)
        {
            g.drawImage(aobj.img, aobj.x, aobj.y, aobj.width, aobj.height, null);
        }
    
        
    }

    

    public void move()
    {
        framecount++;
        //System.out.println(framecount);
        bird.y+=position;
        for (Tpipe aobj : talpipe) 
        {
            aobj.x-=rmove;
        }
        for (Bpipe aobj : balpipe) 
        {
            aobj.x-=rmove;
        }
        //tp.x-=rmove;
        //tp.height = randomNumber;
    }
    public void frame()
    {
        if( framecount %150== 0)
        {
            addnewpipe();
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        //space();
        for (Tpipe pipeobj : talpipe) {
        if(bird.getbound().intersects(pipeobj.getbound()))
        {
            System.out.println("Game is over");
            timer.stop();
        }
        }
        for (Bpipe pipeobj : balpipe) {
            if(bird.getbound().intersects(pipeobj.getbound()))
            {
                System.out.println("Game is over");
                timer.stop();
            }
            }         
        move();
        frame();
        repaint();
        
    }
}

//So her the problem was -how to stop the game after the collision
//first idea was to use the getbound() method to find the bound like x,y,height,width.
///but the problem is that the getbound() doesn't exist for classes.
/// so now I think I should make a getbound() method to get the bound.