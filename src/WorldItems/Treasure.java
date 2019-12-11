package src.WorldItems;

import src.World;

import java.awt.*;

public abstract class Treasure extends WorldItem{
    private int treasureValue;

    public int getTreasureValue() {
        return treasureValue;
    }

    public void setTreasureValue(int treasureValue) {
        this.treasureValue = treasureValue;
    }

    @Override
    public void collisions() {
        for (int i = 0; i < World.worldItems.size(); i++) {
            WorldItem item = World.worldItems.get(i);
            if (item instanceof Player) {
                Rectangle thisRectangle = new Rectangle(this.getX() + this.getAx(), this.getY() + this.getAy(), this.getImg().getWidth(null), this.getImg().getHeight(null));
                Rectangle itemRectangle = new Rectangle(item.getX(), item.getY(), item.getImg().getWidth(null), item.getImg().getHeight(null));
                if (thisRectangle.intersects(itemRectangle)) {
                    ((Player) item).addScore(this.getTreasureValue());
                    World.worldItems.remove(this);
                }
            }
        }
    }
}
