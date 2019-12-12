package src.WorldItems;

import src.World;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Player extends WorldItem {
    private int px;
    private int py;
    private int initialX;
    private int initialY;
    private int moveSpeed = 5;
    private boolean UpPressed;
    private boolean DownPressed;
    private boolean RightPressed;
    private boolean LeftPressed;
    private boolean shootPressed;
    private boolean scarabPressed;

    private long timeScarabActivated = 0;
    private long timeSwordActivated = 0;

    private boolean hasSword = false;
    private boolean swordActive = false;
    private boolean scarabActive = false;

    private static BufferedImage[] movementFrames = new BufferedImage[4];

    private int imageIndex = 0;
    private long imageChanged = 0;
    private double lastPressed = 0;

    //starting HP
    private int health = 3;

    private int scarabCount = 0;

    private int score = 0;

    public Player(int x, int y) {
        this.setX(x);
        this.setY(y);
        this.initialX = x;
        this.initialY = y;

        //check border here to make sure the initial position is valid
        checkBorder();
    }

    public void setHasSword(boolean hasSword){
        this.hasSword = hasSword;
    }

    public boolean getHasSword(){
        return hasSword;
    }

    public boolean getSwordActive(){
        return swordActive;
    }

    public static void ImageFrames(BufferedImage ExplorerFrame0, BufferedImage ExplorerFrame1, BufferedImage ExplorerFrame2, BufferedImage ExplorerFrame3) {
        movementFrames[0] = ExplorerFrame0;
        movementFrames[1] = ExplorerFrame1;
        movementFrames[2] = ExplorerFrame2;
        movementFrames[3] = ExplorerFrame3;
    }

    public void toggleUpPressed() {
        this.UpPressed = true;
    }

    public void toggleDownPressed() { this.DownPressed = true; }

    public void toggleRightPressed() {
        this.RightPressed = true;
    }

    public void toggleLeftPressed() {
        this.LeftPressed = true;
    }

    public void toggleShootPressed() { shootPressed = true; }

    public void toggleScarabPressed() { scarabPressed = true; }

    public void unToggleUpPressed() {
        this.UpPressed = false;
    }

    public void unToggleDownPressed() {
        this.DownPressed = false;
    }

    public void unToggleRightPressed() {
        this.RightPressed = false;
    }

    public void unToggleLeftPressed() {
        this.LeftPressed = false;
    }

    public void unToggleShootPressed() {
        this.deactivateSword();
        this.shootPressed = false;
    }

    public void unToggleScarabPressed() { scarabPressed = false; }

    private void setPx(int px) {
        this.px = px;
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

    public void addScore(int scoreToAdd){
        score += scoreToAdd;
    }

    public int getScore(){
        return score;
    }

    public int getScarabCount(){
        return scarabCount;
    }

    public void respawn(){
        this.setX(initialX);
        this.setY(initialY);
    }

    public void update() {
        if (this.UpPressed) {
            this.moveUp();
        }
        if (this.DownPressed) {
            this.moveDown();
        }
        if (this.LeftPressed) {
            this.moveLeft();
        }
        if (this.RightPressed) {
            this.moveRight();
        }
        if(this.shootPressed){
            this.activateSword();
        }
        if(this.scarabPressed){
            this.activateScarab();
        }
        else {
            checkScarab();
            checkSword();
        }

        this.checkScreenEdge();
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

    private void activateSword(){
        if(hasSword && !swordActive) {
            this.swordActive = true;
            timeSwordActivated = System.currentTimeMillis();
        }
    }

    private void deactivateSword(){
        this.swordActive = false;
    }

    private void activateScarab() {
        if(this.scarabCount > 0 && !scarabActive) {
            this.scarabActive = true;
            timeScarabActivated = System.currentTimeMillis();
            scarabCount--;
        }
    }

    private void checkScarab(){
        if(System.currentTimeMillis() - timeScarabActivated > 5000){
            scarabActive = false;
        }
    }

    private void checkSword(){
        if(swordActive) {
            if (System.currentTimeMillis() - timeSwordActivated > 1000) {
                if(score - 100 < 0)
                    score = 0;
                else
                    score -= 100;
                timeSwordActivated = System.currentTimeMillis();
            }
        }
    }

    public boolean scarabActive(){
        return scarabActive;
    }

    public void addScarab(){
        this.scarabCount++;
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
        if(this.LeftPressed || this.RightPressed || this.DownPressed || this.UpPressed) {
            //choose the correct frame
            if (imageIndex <= 3) {
                this.setImg(movementFrames[imageIndex]);
            } else {
                this.setImg(movementFrames[0]);
                imageIndex = 0;
            }

            //reset the time
            if (System.currentTimeMillis() - imageChanged > 150) {
                imageIndex++;
                imageChanged = System.currentTimeMillis();
            }
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
        this.setImg(movementFrames[0]);
        World.worldItems.add(this);
    }

    @Override
     public void collisions() {

        for (int i = 0; i < World.worldItems.size(); i++) {
            WorldItem item = World.worldItems.get(i);

            if (item instanceof Wall) {
                Rectangle thisRectangle = new Rectangle(this.getX() + this.getAx(), this.getY() + this.getAy(), this.getImg().getWidth(null), this.getImg().getHeight(null));
                Rectangle itemRectangle = new Rectangle(item.getX(), item.getY(), item.getImg().getWidth(null), item.getImg().getHeight(null));
                if (thisRectangle.intersects(itemRectangle)) {

                    Rectangle intersection = thisRectangle.intersection(itemRectangle);
                    //from bottom into something
                    if (this.getY() >= item.getY() + item.getImg().getHeight(null) - moveSpeed) {
                        if (this.getX() + this.getImg().getWidth(null) <= item.getX() + moveSpeed) {
                            this.setX((int) intersection.getX() - this.getImg().getWidth(null));
                        } else if (this.getX() >= item.getX() + item.getImg().getWidth(null) - moveSpeed) {
                            this.setX((int) intersection.getX());// + (int) intersection.getWidth());

                        } else {
                            if(item instanceof V_PushableWall){
                                item.setY(item.getY() - 1);
                                item.checkBorder();
                             //   this.setY(item.getY() + item.getImg().getHeight(null));
                            }
                           // else
                            this.setY((int) intersection.getY() + (int) intersection.getHeight());
                        }
                    }
                    //from top into something
                    else if (this.getY() <= item.getY() - this.getImg().getHeight(null) + moveSpeed) {
                        if (this.getX() >= item.getX() + item.getImg().getWidth(null) - moveSpeed) {
                            this.setX((int) intersection.getX());// + (int) intersection.getWidth());

                        } else if (this.getX() + this.getImg().getWidth(null) <= item.getX() + moveSpeed) {
                            this.setX((int) intersection.getX() - this.getImg().getWidth(null));
                        } else {
                            if(item instanceof V_PushableWall){
                                item.setY(item.getY() + 1);
                                item.checkBorder();
                             //   this.setY(item.getY() - this.getImg().getHeight(null));
                            }
                           // else
                            this.setY((int) intersection.getY() - this.getImg().getHeight(null));

                        }
                    }
                    //from right into something
                    else if (this.getX() >= item.getX() + item.getImg().getWidth(null) - moveSpeed) {
                        if(item instanceof H_PushableWall){
                            item.setX(item.getX() - 1);
                            item.checkBorder();
                            this.setX(item.getX() + item.getImg().getWidth(null));
                        }
                        this.setX((int) intersection.getX() + (int) intersection.getWidth());
                    }
                    //from left into something
                    else if (this.getX() + this.getImg().getWidth(null) <= item.getX() + moveSpeed) {
                        if(item instanceof H_PushableWall){
                            item.setX(item.getX() + 1);
                            item.checkBorder();
                            this.setX(item.getX() - this.getImg().getWidth(null));
                        }
                        this.setX((int) intersection.getX() - this.getImg().getWidth(null));
                    }
                }
            }
        }
    }
}
