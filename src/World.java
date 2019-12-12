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
    private static boolean victory = false;
    private BufferedImage world;
    private BufferedImage bottomPanel;
    private BufferedImage scarabCountImage;
    private BufferedImage victoryScreen;
    private BufferedImage gameOverScreen;
    private Graphics2D buffer;
    private JFrame jf;
    public static JLabel label;
    private Map m;
    private UserInput input1;



    //worlditems
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
    public static ArrayList<Scorpion> updatableScorpion = new ArrayList<>();
    public static ArrayList<Beetle> updatableBeetle = new ArrayList<>();
    public static ArrayList<Mummy> updatableMummy = new ArrayList<>();

    public static void main(String[] args) {
        World w = new World();
        w.init();
        try {
            while (!gameover) {
                {
                    w.player.update();

                    //these 3 loops should really be abstracted better but i ran out of time
                    for(int i = 0; i < updatableScorpion.size(); i++) {
                        updatableScorpion.get(i).update();
                    }
                    for(int i = 0; i < updatableBeetle.size(); i++){
                        updatableBeetle.get(i).update();
                    }
                    for(int i = 0; i < updatableMummy.size(); i++){
                        updatableMummy.get(i).update();
                    }
//                        System.out.println("X: " + w.player.getX() + " Y: " + w.player.getY());
                    for(int i = 0; i < worldItems.size(); i++)
                    {
                        worldItems.get(i).collisions();
                    }
                    w.repaint();
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

            //get player images
            BufferedImage ExplorerFrame0 = ImageIO.read(getClass().getResource("/resources/e0.gif"));
            BufferedImage ExplorerFrame1 = ImageIO.read(getClass().getResource("/resources/e1.gif"));
            BufferedImage ExplorerFrame2 = ImageIO.read(getClass().getResource("/resources/e2.gif"));
            BufferedImage ExplorerFrame3 = ImageIO.read(getClass().getResource("/resources/e3.gif"));
            Player.ImageFrames(ExplorerFrame0, ExplorerFrame1, ExplorerFrame2, ExplorerFrame3);

            //get scorpion images
            BufferedImage ScorpionFrame0 = ImageIO.read(getClass().getResource("/resources/scorpion0.gif"));
            BufferedImage ScorpionFrame1 = ImageIO.read(getClass().getResource("/resources/scorpion1.gif"));
            BufferedImage ScorpionFrame2 = ImageIO.read(getClass().getResource("/resources/scorpion2.gif"));
            BufferedImage ScorpionFrame3 = ImageIO.read(getClass().getResource("/resources/scorpion3.gif"));
            Scorpion.ImageFrames(ScorpionFrame0, ScorpionFrame1, ScorpionFrame2, ScorpionFrame3);

            //get beetle images
            BufferedImage BeetleFrame0 = ImageIO.read(getClass().getResource("/resources/beetle0.gif"));
            BufferedImage BeetleFrame1 = ImageIO.read(getClass().getResource("/resources/beetle1.gif"));
            BufferedImage BeetleFrame2 = ImageIO.read(getClass().getResource("/resources/beetle2.gif"));
            BufferedImage BeetleFrame3 = ImageIO.read(getClass().getResource("/resources/beetle3.gif"));
            Beetle.ImageFrames(BeetleFrame0, BeetleFrame1, BeetleFrame2, BeetleFrame3);

            //get mummy images
            BufferedImage MummyFrame0 = ImageIO.read(getClass().getResource("/resources/mummy0.gif"));
            BufferedImage MummyFrame1 = ImageIO.read(getClass().getResource("/resources/mummy1.gif"));
            BufferedImage MummyFrame2 = ImageIO.read(getClass().getResource("/resources/mummy2.gif"));
            BufferedImage MummyFrame3 = ImageIO.read(getClass().getResource("/resources/mummy3.gif"));
            Mummy.ImageFrames(MummyFrame0, MummyFrame1, MummyFrame2, MummyFrame3);

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
            worldItemsToSpawn.add(beetle);


            //set the mummy image
            mummy = new Mummy();
            worldItemsToSpawn.add(mummy);

            //set the potion image
            potion = new Potion();
            potion.setImg(ImageIO.read(getClass().getResource("/resources/Potion.gif")));
            worldItemsToSpawn.add(potion);


            //set the scarab image
            scarab = new Scarab();
            scarab.setImg(ImageIO.read(getClass().getResource("/resources/Scarab.gif")));
            worldItemsToSpawn.add(scarab);


            //set the scorpion image
            scorpion = new Scorpion();
            worldItemsToSpawn.add(scorpion);


            //set the sword image
            sword = new Sword();
            sword.setImg(ImageIO.read(getClass().getResource("/resources/Sword.gif")));
            worldItemsToSpawn.add(sword);

            //set treasure_1 image
            treasure_1 = new Treasure_1(-100, -100);
            treasure_1.setImg(ImageIO.read(getClass().getResource("/resources/Treasure1.gif")));
            worldItemsToSpawn.add(treasure_1);

            //set treasure_2 image
            treasure_2 = new Treasure_2(-100, -100);
            treasure_2.setImg(ImageIO.read(getClass().getResource("/resources/Treasure2.gif")));
            worldItemsToSpawn.add(treasure_2);

            //set the player life image
            playerLife = new PlayerLife();
            playerLife.setImg(ImageIO.read(getClass().getResource("/resources/Lives.gif")));

            //set the ladder image
            ladder = new Ladder();
            ladder.setImg(ImageIO.read(getClass().getResource("/resources/ladder.png")));
            worldItemsToSpawn.add(ladder);

            //load the bottom panel
            bottomPanel = ImageIO.read(getClass().getResource("/resources/Panel.gif"));

            //load the game over screen
            gameOverScreen = ImageIO.read(getClass().getResource("/resources/Background1.bmp"));

            //load the victory screen
            victoryScreen = ImageIO.read(getClass().getResource("/resources/Congratulation.gif"));

            //load scarab count image
            scarabCountImage = ImageIO.read(getClass().getResource("/resources/Scarab.gif"));


            for (WorldItem worldItem : worldItemsToSpawn) {
                worldItem.spawn();
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        //spawn instances of keylisteners
        input1 = new UserInput(player, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_SPACE, KeyEvent.VK_E);

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

        //draw each instance of WorldItem
        for (int i = 0; i < worldItems.size(); i++) {
            WorldItem worldItem = worldItems.get(i);
            worldItem.drawImage(buffer, worldItem.getX(), worldItem.getY());
        }

        int x1 = player.getX() - SPLITSCREEN_WIDTH * 20 / 40;
        if ((player.getX() - SPLITSCREEN_WIDTH * 20 / 40) < 0) {
            x1 = 0;
        } else if ((player.getX() + SPLITSCREEN_WIDTH * 20 / 40) > (SCREEN_WIDTH)) {
            x1 = SCREEN_WIDTH - SPLITSCREEN_WIDTH * 20 / 20;
        }

        int y1 = player.getY() - SPLITSCREEN_HEIGHT / 2;
        if ((player.getY() - SPLITSCREEN_HEIGHT / 2) < 0) {
            y1 = 0;
        } else if ((player.getY() + SPLITSCREEN_HEIGHT / 2) > (SCREEN_HEIGHT)) {
            y1 = SCREEN_HEIGHT - SPLITSCREEN_HEIGHT;
        }

        BufferedImage surroundingPlayer = world.getSubimage(x1, y1, SPLITSCREEN_WIDTH, SPLITSCREEN_HEIGHT);

        if(player.getHasSword() && !player.getSwordActive()){
            int Y_Maximum;
            int Y_Minimum;
            int X_Maximum;
            int X_Minimum;

            if ((player.getX() - SPLITSCREEN_WIDTH / 2) < 0) {
                X_Maximum = Integer.max(SPLITSCREEN_WIDTH / 2 - (SPLITSCREEN_WIDTH / 2 - player.getX()) + SPLITSCREEN_WIDTH / 4, SPLITSCREEN_WIDTH / 4);
                X_Minimum = Integer.max(X_Maximum - SPLITSCREEN_WIDTH / 2, 0);
            } else if ((player.getX() + SPLITSCREEN_WIDTH / 2) > SCREEN_WIDTH) {

                X_Maximum = Integer.min(SPLITSCREEN_WIDTH - (SCREEN_WIDTH - player.getX()) + SPLITSCREEN_WIDTH / 4, SPLITSCREEN_WIDTH);
                X_Minimum = X_Maximum - SPLITSCREEN_WIDTH / 2;

            } else {
                X_Minimum = SPLITSCREEN_WIDTH / 4;
                X_Maximum = SPLITSCREEN_WIDTH * 3 / 4;
            }

            if ((player.getY() - SPLITSCREEN_HEIGHT / 2) < 0) {
                Y_Maximum = Integer.max(SPLITSCREEN_HEIGHT / 2 - (SPLITSCREEN_HEIGHT / 2 - player.getY()) + SPLITSCREEN_HEIGHT / 4, SPLITSCREEN_HEIGHT / 4);
                Y_Minimum = Integer.max(Y_Maximum - SPLITSCREEN_HEIGHT / 2, 0);
            } else if ((player.getY() + SPLITSCREEN_HEIGHT / 2) > SCREEN_HEIGHT) {
                Y_Maximum = Integer.min(SPLITSCREEN_HEIGHT - (SCREEN_HEIGHT - player.getY()) + SPLITSCREEN_HEIGHT / 4, SPLITSCREEN_HEIGHT);
                Y_Minimum = Y_Maximum - SPLITSCREEN_HEIGHT / 2;
            } else {
                Y_Minimum = SPLITSCREEN_HEIGHT / 4;
                Y_Maximum = SPLITSCREEN_HEIGHT * 3 / 4;
            }

            for (int i = 0; i < SPLITSCREEN_WIDTH; i++) {
                for (int j = 0; j < SPLITSCREEN_HEIGHT; j++) {
                    if (i < X_Minimum || i > X_Maximum || j < Y_Minimum || j > Y_Maximum) {
                        surroundingPlayer.setRGB(i, j, 0);
                    }
                }
            }
        }

        g2.drawImage(surroundingPlayer, 0, 0, null);

        g2.drawImage(bottomPanel, SPLITSCREEN_WIDTH/2 - bottomPanel.getWidth()/2, SPLITSCREEN_HEIGHT-bottomPanel.getHeight() - 8, null);

        g2.drawImage(scarabCountImage, 380,SPLITSCREEN_HEIGHT - scarabCountImage.getHeight(null) - 10, null);

        //draw the player score
        g2.setFont(new Font("Calibri", Font.BOLD, 20));
        g2.setColor(Color.black);
        g2.drawString(Integer.toString(player.getScore()), 690, SPLITSCREEN_HEIGHT - 18);
        g2.drawString("x" + player.getScarabCount(), 420, SPLITSCREEN_HEIGHT - 18);

        //draw player's lives count
        for(int i = 0; i < player.getHealth(); i++)
            g2.drawImage(playerLife.getImg(), 210 + 34*i, SPLITSCREEN_HEIGHT - playerLife.getImg().getHeight(null) - 10, null);

        if(victory){
            g2.drawImage(victoryScreen, 0, 0, SPLITSCREEN_WIDTH, SPLITSCREEN_HEIGHT, null);
            gameover = true;
        }
        if(player.getHealth() == 0)
        {
            g2.drawImage(gameOverScreen, 0, 0, SPLITSCREEN_WIDTH, SPLITSCREEN_HEIGHT, null);
            gameover = true;
        }
    }

    public static void setGameoverVictory(boolean victory) {
        World.victory = victory;
    }
}
