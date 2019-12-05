package src.WorldItems;

import src.World;

import java.awt.*;

public class Sword extends WorldItem{
    @Override
    public void drawImage(Graphics g, int x, int y) {
        g.drawImage(getImg(), x, y, null);
    }

    @Override
    public void spawn() {
        this.setX(2060);
        this.setY(1820);
        World.worldItems.add(this);
    }

    @Override
    public void collisions() {

    }
}
