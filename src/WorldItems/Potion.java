package src.WorldItems;

import java.awt.*;

public class Potion extends WorldItem {
    @Override
    public void drawImage(Graphics g, int x, int y) {
        g.drawImage(getImg(), x, y, null);
    }

    @Override
    public void spawn() {

    }

    @Override
    public void collisions() {
        //collide with the player
            //remove potion from the world
            //restore health of player to the max 3
    }
}
