/*
    Ladder is created as a class so we can use instanceof to check for collisions
    and load the victory screen
*/


package src.WorldItems;

import src.World;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Ladder extends WorldItem
{
    private static Image img;

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
        this.setX(1900);
        this.setY(217);
        World.worldItems.add(this);
    }

    @Override
    public void collisions()
    {
        for (int i = 0; i < World.worldItems.size(); i++)
        {
            WorldItem item = World.worldItems.get(i);

            if (item instanceof Player)
            {
                Rectangle thisRectangle = new Rectangle(this.getX() + this.getAx(), this.getY() + this.getAy(), this.getImg().getWidth(null), this.getImg().getHeight(null));
                Rectangle itemRectangle = new Rectangle(item.getX(), item.getY(), item.getImg().getWidth(null), item.getImg().getHeight(null));
                if (thisRectangle.intersects(itemRectangle))
                {
                    if (((Player) item).getHasSword())
                        World.setGameoverVictory(true);
                }
            }
        }
    }
}
