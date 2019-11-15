package src;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class World extends JPanel {

    public static final int SCREEN_WIDTH = 2200;
    public static final int SCREEN_HEIGHT = 2200;
    public static final int SPLITSCREEN_WIDTH = 900;
    public static final int SPLITSCREEN_HEIGHT = 700;
    private static Rectangle r;
    private static boolean gameover = false;
    private BufferedImage world;
    private BufferedImage p1w;
    private Graphics2D buffer;
    private JFrame jf;
    public static JLabel label;
    private Map m;
    private UserInput input1;


    //worlditems
//    private H_MovableWall hmw;
//    private V_MovableWall vmw;
//    private playerLife playerLife;
//    private Potion potion;
    private BorderWall borderWall;
    private Player player;

    public static ArrayList<WorldItem> worldItems = new ArrayList<>();
    private static ArrayList<WorldItem> worldItemsToSpawn = new ArrayList<>();

    public static void main(String[] args) {
        World w = new World();
        w.init();
        try {
            while (!gameover) {
                {
                    if(w.player.update()){
                        w.repaint();
//                        w.player.getImg().flush();
                    }

//                    w.repaint(r);
                }
                Thread.sleep(1000 / 144);
            }
        } catch (InterruptedException ignored) {
        }
    }

    private void init() {
        this.jf = new JFrame("Pyramid Panic");
        label = new JLabel();
        this.world = new BufferedImage(World.SCREEN_WIDTH, World.SCREEN_HEIGHT, BufferedImage.TYPE_INT_RGB);

        try {
            //load the background
            m = new Map();
            m.setImg(ImageIO.read(getClass().getResource("/resources/Background2.bmp")));

            //load a player
            player = new Player(100, 100);
            worldItemsToSpawn.add(player);

            //get player images array
            BufferedImage ExplorerFrame0 = ImageIO.read(getClass().getResource("/resources/e0.gif"));
            BufferedImage ExplorerFrame1 = ImageIO.read(getClass().getResource("/resources/e1.gif"));
            BufferedImage ExplorerFrame2 = ImageIO.read(getClass().getResource("/resources/e2.gif"));
            BufferedImage ExplorerFrame3 = ImageIO.read(getClass().getResource("/resources/e3.gif"));
            Player.ImageFrames(ExplorerFrame0, ExplorerFrame1, ExplorerFrame2, ExplorerFrame3);

            //set the border wall image
            borderWall = new BorderWall();
            borderWall.setImg(ImageIO.read(getClass().getResource("/resources/Block.gif")));
            worldItemsToSpawn.add(borderWall);

//            player.setImg(ImageIO.read(getClass().getResource("/resources/Explorer_up.gif")));
//            player.setImg(new ImageIcon(url).getImage());
//            ico = new ImageIcon(url);


//            //load the wall images
//            hmw = new H_MovableWall();
//            hmw.setImg(ImageIO.read(getClass().getResource("/resources/Wall1.gif")));
//            worldItemsToSpawn.add(hmw);
//
//            vmw = new BreakableWall();
//            vmw.setImg(ImageIO.read(getClass().getResource("/resources/Wall2.gif")));
//            worldItemsToSpawn.add(vmw);
//
//            //load lives icon
//            playerLife = new playerLife();
//            playerLife.setImg(ImageIO.read(getClass().getResource("/resources/Heart1.png")));
//
//            //load the image for the healing powerup icon
//            potion = new Potion();
//            potion.setImg(ImageIO.read(getClass().getResource("/resources/Heart2.png")));
//            worldItemsToSpawn.add(potion);
//
//            //load each player winning image
//            p1w = ImageIO.read(getClass().getResource("/resources/p1w.png"));

            for (WorldItem worldItem : worldItemsToSpawn) {
                worldItem.spawn();
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        //spawn instances of keylisteners
        input1 = new UserInput(player, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_ENTER);

        this.jf.setLayout(new BorderLayout());

        //add the world to the jframe
        this.jf.add(this);




        //ensure preferred sizes
        this.jf.pack();

        //add the keylisteners to the jframe
        this.jf.addKeyListener(input1);

        this.jf.setSize(World.SPLITSCREEN_WIDTH, World.SPLITSCREEN_HEIGHT + 30);
        this.jf.setResizable(false);
        jf.setLocationRelativeTo(null);


        this.jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.jf.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        buffer = this.world.createGraphics();
        super.paintComponent(g2);

        //draw the background
        this.m.drawImage(buffer);

        BufferedImage small = world.getSubimage(player.getPx() - SPLITSCREEN_WIDTH/2, player.getPy() - SPLITSCREEN_HEIGHT/2, SPLITSCREEN_WIDTH, SPLITSCREEN_HEIGHT);


        //draw each instance of WorldItem
        for (int i = 0; i < worldItems.size(); i++) {
            WorldItem worldItem = worldItems.get(i);
            worldItem.drawImage(buffer, worldItem.getX(), worldItem.getY());
        }

//        URL url = this.getClass().getResource("/resources/Explorer_up.gif");
//        Image im;
//        im = new ImageIcon(url).getImage();
//        g2.drawImage(im, 100, 100, 100, 100, null);
//        g2.drawImage(player.getImg(), 100, 100, this);
        g2.drawImage(small, 0, 0, null);
        //draw each tank's lives
//        int placement;
//        for (int i = 1; i <= this.player.getLives(); i++) {
//            placement = (playerLife.getImg().getWidth(null) + 10) * i;
//            playerLife.drawImage(g2, placement / 2, 10);
//        }

        //game over screens
//        if (this.tank1.getLives() == 0) {
//            g2.drawImage(p2w, 0, 0, SPLITSCREEN_WIDTH, SPLITSCREEN_HEIGHT, null);
//            gameover = true;
//        }
//        else if(endState){
//
//        }

        //get a rectangle for repainting
      //  r = g.getClipBounds();
    }
}
