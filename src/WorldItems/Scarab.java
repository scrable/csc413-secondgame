package src.WorldItems;

import src.World;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Scarab extends WorldItem {
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
        Scarab s1 = new Scarab();
        s1.setX(454);
        s1.setY(860);
        World.worldItems.add(s1);

        s1 = new Scarab();
        s1.setX(2110);
        s1.setY(1206);
        World.worldItems.add(s1);
    }

    @Override
    public void collisions() {
        //only collide with player to remove the object from the map
        //add a count to players scarab count
        for (int i = 0; i < World.worldItems.size(); i++) {
            WorldItem item = World.worldItems.get(i);
            if (item instanceof Player) {
                Rectangle thisRectangle = new Rectangle(this.getX() + this.getAx(), this.getY() + this.getAy(), this.getImg().getWidth(null), this.getImg().getHeight(null));
                Rectangle itemRectangle = new Rectangle(item.getX(), item.getY(), item.getImg().getWidth(null), item.getImg().getHeight(null));
                if (thisRectangle.intersects(itemRectangle)) {
                    ((Player) item).addScarab();
                    World.worldItems.remove(this);
                }
            }
        }
    }
}
