/*
    Ladder is created as a class so we can use instanceof to check for collisions
    and load the victory screen
*/



package src.WorldItems;

import src.World;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Ladder extends WorldItem{
    private static Image img;

    public void setImg(BufferedImage image) {
        img = image;
    }

    public Image getImg() {
        return img;
    }

    @Override
    public void drawImage(Graphics g, int x, int y) {
        g.drawImage(getImg(), x, y, null);
    }

    @Override
    public void spawn() {
        this.setX(1900);
        this.setY(217);
        World.worldItems.add(this);
    }

    @Override
    public void collisions() {

    }
}
