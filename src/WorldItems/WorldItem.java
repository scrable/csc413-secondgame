package src.WorldItems;

import src.World;

import javax.swing.*;
import java.awt.*;

public abstract class WorldItem {
    private int a;
    private int x;
    private int y;
    private int ax;
    private int ay;
    private Image img;

    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getAx() {
        return ax;
    }

    public void setAx(int ax) {
        this.ax = ax;
    }

    public int getAy() {
        return ay;
    }

    public void setAy(int ay) {
        this.ay = ay;
    }

    public void checkBorder() {
        if (this.getX() < 30) {
            this.setX(30);
        }
        if (this.getX() >= World.SCREEN_WIDTH - 88) {
            this.setX(World.SCREEN_WIDTH - 88);
        }
        if (this.getY() < 32) {
            this.setY(32);
        }
        if (this.getY() >= World.SCREEN_HEIGHT - 72) {
            this.setY(World.SCREEN_HEIGHT - 72);
        }
    }

    
    public abstract void drawImage(Graphics g, int x, int y);

    public abstract void spawn();

    public abstract void collisions();
}
