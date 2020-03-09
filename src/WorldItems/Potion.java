package src.WorldItems;

import src.World;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Potion extends WorldItem
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
        Potion temp_p1 = new Potion();
        temp_p1.setX(1180);
        temp_p1.setY(880);
        World.worldItems.add(temp_p1);
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
                    ((Player) item).setHealth(3);
                    World.worldItems.remove(this);
                }
            }
        }
    }
}
