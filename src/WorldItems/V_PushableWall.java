package src.WorldItems;

import src.World;

import java.awt.*;
import java.awt.image.BufferedImage;

public class V_PushableWall extends Wall
{
    private static Image img;

    public V_PushableWall(int x, int y)
    {
        this.setX(x);
        this.setY(y);
    }

    public Image getImg()
    {
        return img;
    }

    public void setImg(BufferedImage image)
    {
        img = image;
    }

    @Override
    public void drawImage(Graphics g, int x, int y)
    {
        g.drawImage(getImg(), x, y, null);
    }

    @Override
    public void spawn()
    {

    }

    @Override
    public void collisions()
    {
        for (int i = 0; i < World.worldItems.size(); i++)
        {
            WorldItem item = World.worldItems.get(i);
            if (item instanceof InnerWall)
            {
                Rectangle thisRectangle = new Rectangle(this.getX() + this.getAx(), this.getY() + this.getAy(), this.getImg().getWidth(null), this.getImg().getHeight(null));
                Rectangle itemRectangle = new Rectangle(item.getX(), item.getY(), item.getImg().getWidth(null), item.getImg().getHeight(null));
                if (thisRectangle.intersects(itemRectangle))
                {
                    Rectangle intersection = thisRectangle.intersection(itemRectangle);
                    //from bottom into something
                    if (this.getY() > item.getY() + item.getImg().getHeight(null) - 2)
                    {
                        this.setY((int) intersection.getY() + (int) intersection.getHeight());
                    }
                    //from top into something
                    else if (this.getY() < item.getY() - this.getImg().getHeight(null) + 2)
                    {
                        this.setY((int) intersection.getY() - this.getImg().getHeight(null));
                    }
                }
            }
        }
    }
}
