package src.WorldItems;

import java.awt.*;

public class Scarab extends WorldItem {
    @Override
    public void drawImage(Graphics g, int x, int y) {
        g.drawImage(getImg(), x, y, null);
    }

    @Override
    public void spawn() {

    }

    @Override
    public void collisions() {
        //only collide with player to remove the object from the map
        //add a count to players scarab count
    }

    public void scarabActivate(){
        //run when userinput == E
    }
}
