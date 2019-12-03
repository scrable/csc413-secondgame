package src;

import src.WorldItems.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class World extends JPanel {

    //multiples of 32, which is wall size
    public static final int SCREEN_WIDTH = 2176;
    public static final int SCREEN_HEIGHT = 2176;
    public static final int SPLITSCREEN_WIDTH = 900;
    public static final int SPLITSCREEN_HEIGHT = 700;
    private static Rectangle r;
    private static boolean gameover = false;
    private BufferedImage world;
    private BufferedImage bottomPanel;
    private BufferedImage p1w;
    private Graphics2D buffer;
    private JFrame jf;
    public static JLabel label;
    private Map m;
    private UserInput input1;



    //worlditems
//    private playerLife playerLife;
//    private Potion potion;
    private BorderWall borderWall;
    private InnerWall innerWall;
    private V_PushableWall vpw;
    private H_PushableWall hpw;
    private Player player;
    private Ladder ladder;
    private Beetle beetle;
    private Mummy mummy;
    private Potion potion;
    private Scarab scarab;
    private Scorpion scorpion;
    private Sword sword;
    private Treasure_1 treasure_1;
    private Treasure_2 treasure_2;
    private PlayerLife playerLife;

    public static ArrayList<WorldItem> worldItems = new ArrayList<>();
    private static ArrayList<WorldItem> worldItemsToSpawn = new ArrayList<>();

    public static void main(String[] args) {
        World w = new World();
        w.init();
        try {
            while (!gameover) {
                {
                    if(w.player.update()){
                        for(int i = 0; i < worldItems.size(); i++)
                        {
                         worldItems.get(i).collisions();
                        }
//                        w.player.collisions();
                        w.repaint();
                    }
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
            player = new Player(1790, 240);
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

            //set the inner wall image
            innerWall = new InnerWall();
            innerWall.setImg(ImageIO.read(getClass().getResource("/resources/Wall1.gif")));
            worldItemsToSpawn.add(innerWall);

            //set the vertical pushable wall image
            vpw = new V_PushableWall(-100, -100);
            vpw.setImg(ImageIO.read(getClass().getResource("/resources/Block_vert.gif")));
            worldItemsToSpawn.add(vpw);

            //set the horizontal wall image
            hpw = new H_PushableWall(- 100, -100);
            hpw.setImg(ImageIO.read(getClass().getResource("/resources/Block_hor.gif")));
            worldItemsToSpawn.add(hpw);

            //set the beetle image
            beetle = new Beetle();


            //set the mummy image
            mummy = new Mummy();


            //set the potion image
            potion = new Potion();


            //set the scarab image
            scarab = new Scarab();


            //set the scorpion image
            scorpion = new Scorpion();


            //set the sword image
            sword = new Sword();


            //set treasure_1 image
            treasure_1 = new Treasure_1();


            //set treasure_2 image
            treasure_2 = new Treasure_2();


            //set the player life image
            playerLife = new PlayerLife();

            //set the ladder image
            ladder = new Ladder();
            ladder.setImg(ImageIO.read(getClass().getResource("/resources/ladder.png")));
            worldItemsToSpawn.add(ladder);

            //load the bottom panel
            bottomPanel = ImageIO.read(getClass().getResource("/resources/Panel.gif"));


            //load the game over screen

            //load the victory screen


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
//        BufferedImage mini = world.getSubimage(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);

        //draw each instance of WorldItem
        for (int i = 0; i < worldItems.size(); i++) {
            WorldItem worldItem = worldItems.get(i);
            worldItem.drawImage(buffer, worldItem.getX(), worldItem.getY());
        }

        g2.drawImage(small, 0, 0, null);

        g2.drawImage(bottomPanel, SPLITSCREEN_WIDTH/2 - bottomPanel.getWidth()/2, SPLITSCREEN_HEIGHT-bottomPanel.getHeight() - 4, null);


        //temp minimap to show layout
//        g2.drawImage(mini, SPLITSCREEN_WIDTH / 2 - SPLITSCREEN_WIDTH / 8 + 10, SPLITSCREEN_HEIGHT - 210, 200, 200, null);

    }

    public static void setGameover(boolean gameover) {
        World.gameover = gameover;
    }
}
