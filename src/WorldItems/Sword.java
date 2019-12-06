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
        for (int i = 0; i < World.worldItems.size(); i++) {
            WorldItem item = World.worldItems.get(i);
            if (item instanceof Player) {
                Rectangle thisRectangle = new Rectangle(this.getX() + this.getAx(), this.getY() + this.getAy(), this.getImg().getWidth(null), this.getImg().getHeight(null));
                Rectangle itemRectangle = new Rectangle(item.getX(), item.getY(), item.getImg().getWidth(null), item.getImg().getHeight(null));
                if (thisRectangle.intersects(itemRectangle)) {
                    ((Player) item).setHasSword(true);
                    World.worldItems.remove(this);
                }
            }
        }
    }
}
