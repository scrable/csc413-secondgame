package src;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Player extends WorldItem{
    private int px;
    private int py;
    private int moveSpeed = 1;
    private boolean UpPressed;
    private boolean DownPressed;
    private boolean RightPressed;
    private boolean LeftPressed;
    private boolean shootPressed;

    private static BufferedImage[] movementFrames = new BufferedImage[4];

    private int imageIndex = 0;
    private long imageChanged = 0;
    private double lastPressed = 0;

    //starting HP
    private int health = 100;

    Player(int x, int y) {
        this.setX(x);
        this.setY(y);

        //check border here to make sure the initial position is valid
        checkBorder();
    }

    public static void ImageFrames(BufferedImage ExplorerFrame0, BufferedImage ExplorerFrame1, BufferedImage ExplorerFrame2, BufferedImage ExplorerFrame3) {
        movementFrames[0] = ExplorerFrame0;
        movementFrames[1] = ExplorerFrame1;
        movementFrames[2] = ExplorerFrame2;
        movementFrames[3] = ExplorerFrame3;
    }

    void toggleUpPressed() {
        this.UpPressed = true;
    }

    void toggleDownPressed() { this.DownPressed = true; }

    void toggleRightPressed() {
        this.RightPressed = true;
    }

    void toggleLeftPressed() {
        this.LeftPressed = true;
    }

    void toggleShootPressed() { shootPressed = true; }

    void unToggleUpPressed() {
        this.UpPressed = false;
    }

    void unToggleDownPressed() {
        this.DownPressed = false;
    }

    void unToggleRightPressed() {
        this.RightPressed = false;
    }

    void unToggleLeftPressed() {
        this.LeftPressed = false;
    }

    void unToggleShootPressed() { this.shootPressed = false; }

    int getPx() {
        return px;
    }

    private void setPx(int px) {
        this.px = px;
    }

    int getPy() {
        return py;
    }

    private void setPy(int py) {
        this.py = py;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }
    boolean update() {
        boolean check = false;
        if (this.UpPressed) {
            this.moveUp();
            check = true;
        }
        if (this.DownPressed) {
            this.moveDown();
            check = true;
        }

        if (this.LeftPressed) {
            this.moveLeft();
            check = true;
        }
        if (this.RightPressed) {
            this.moveRight();
            check = true;
        }
        return check;
    }

    private void moveDown() {
        this.setY(getY() + moveSpeed);
        checkBorder();
    }

    private void moveUp() {
        this.setY(getY() - moveSpeed);
        checkBorder();
    }

    private void moveLeft() {
        this.setX(getX() - moveSpeed);
        checkBorder();
    }

    private void moveRight() {
        this.setX(getX() + moveSpeed);
        checkBorder();
    }

    private void checkBorder() {
        if (this.getX() < 30) {
            this.setX(30);
        }
        if (this.getX() >= World.SCREEN_WIDTH - 88) {
            this.setX(World.SCREEN_WIDTH - 88);
        }
        if (this.getY() < 40) {
            this.setY(40);
        }
        if (this.getY() >= World.SCREEN_HEIGHT - 80) {
            this.setY(World.SCREEN_HEIGHT - 80);
        }
        checkScreenEdge();
    }

    private void checkScreenEdge() {
        this.px = this.getX();
        this.py = this.getY();

        //minimum X
        if (this.getX() < World.SPLITSCREEN_WIDTH / 2) {
            this.setPx(World.SPLITSCREEN_WIDTH / 2);
        }
        //maximum X
        if (this.getX() > World.SCREEN_WIDTH - World.SPLITSCREEN_WIDTH / 2) {
            this.setPx(World.SCREEN_WIDTH - World.SPLITSCREEN_WIDTH / 2);
        }
        //minimum Y
        if (this.getY() < World.SPLITSCREEN_HEIGHT / 2) {
            this.setPy(World.SPLITSCREEN_HEIGHT / 2);
        }
        //maximum Y
        if (this.getY() > World.SCREEN_HEIGHT - World.SPLITSCREEN_HEIGHT / 2) {
            this.setPy(World.SCREEN_HEIGHT - World.SPLITSCREEN_HEIGHT / 2);
        }
    }

    @Override
    public void drawImage(Graphics g, int x, int y) {
        //choose the correct frame
        if (imageIndex <= 3) {
            this.setImg(movementFrames[imageIndex]);
        } else {
            this.setImg(movementFrames[0]);
            imageIndex = 0;
        }

        //reset the time
        if(System.currentTimeMillis() - imageChanged > 150) {
            imageIndex++;
            imageChanged = System.currentTimeMillis();
        }

        //rotate the frames depending on what has been pressed
        AffineTransform rotation = AffineTransform.getTranslateInstance(this.getX(), this.getY());
        Graphics2D g2d = (Graphics2D) g;
        if(this.LeftPressed) {
            lastPressed = Math.toRadians(90);
            rotation.rotate(lastPressed, this.getImg().getWidth(null) / 2.0, this.getImg().getHeight(null) / 2.0);
            g2d.drawImage(getImg(), rotation, null);
        }
        else if(this.RightPressed){
            lastPressed = Math.toRadians(270);
            rotation.rotate(lastPressed, this.getImg().getWidth(null) / 2.0, this.getImg().getHeight(null) / 2.0);
            g2d.drawImage(getImg(), rotation, null);

        }
        else if(this.UpPressed){
            lastPressed = Math.toRadians(180);
            rotation.rotate(lastPressed, this.getImg().getWidth(null) / 2.0, this.getImg().getHeight(null) / 2.0);
            g2d.drawImage(getImg(), rotation, null);
        }
        else if(this.DownPressed){
            lastPressed = Math.toRadians(0);
            rotation.rotate(lastPressed, this.getImg().getWidth(null) / 2.0, this.getImg().getHeight(null) / 2.0);
            g2d.drawImage(getImg(), rotation, null);
        }
        else {
            rotation.rotate(lastPressed, this.getImg().getWidth(null) / 2.0, this.getImg().getHeight(null) / 2.0);
            g2d.drawImage(getImg(), rotation, null);
        }
    }

    @Override
    public void spawn() {
        World.worldItems.add(this);
    }

}
