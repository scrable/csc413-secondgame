package src.WorldItems;

import src.World;

import java.awt.*;
import java.awt.image.BufferedImage;

public class H_PushableWall extends Wall {
    private static Image img;

    public void setImg(BufferedImage image) {
        img = image;
    }

    public Image getImg() {
        return img;
    }
    public H_PushableWall(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    @Override
    public void drawImage(Graphics g, int x, int y) {
        g.drawImage(getImg(), x, y, null);
    }

    @Override
    public void spawn() {

    }

    @Override
    public void collisions() {
        for (int i = 0; i < World.worldItems.size(); i++) {
            WorldItem item = World.worldItems.get(i);
            if (item instanceof InnerWall) {
                Rectangle thisRectangle = new Rectangle(this.getX() + this.getAx(), this.getY() + this.getAy(), this.getImg().getWidth(null), this.getImg().getHeight(null));
                Rectangle itemRectangle = new Rectangle(item.getX(), item.getY(), item.getImg().getWidth(null), item.getImg().getHeight(null));
                if (thisRectangle.intersects(itemRectangle)) {
                    Rectangle intersection = thisRectangle.intersection(itemRectangle);
                    //from right into something - the -2 is so we have some sort of intersection width
                    if (this.getX() > item.getX() + item.getImg().getWidth(null) - 2) {
                        System.out.println("sean");
                        this.setX((int) intersection.getX() + (int) intersection.getWidth());
                    }
                    //from left into something
                    else if (this.getX() + this.getImg().getWidth(null) >= item.getX()) {
                        this.setX((int) intersection.getX() - this.getImg().getWidth(null));
                    }
                }
            }
        }
    }
}