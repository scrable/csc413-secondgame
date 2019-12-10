package src.WorldItems;

import java.awt.*;

public class Scorpion extends WorldItem {
    @Override
    public void drawImage(Graphics g, int x, int y) {
        g.drawImage(getImg(), x, y, null);
    }

    @Override
    public void spawn() {

    }

    @Override
    public void collisions() {
        //collide with player
        //collide with any kind of walls4
    }
}
