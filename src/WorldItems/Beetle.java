package src.WorldItems;

import src.World;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Beetle extends WorldItem
{
    private static BufferedImage[] movementFrames = new BufferedImage[4];
    String currentDirection = "up";
    int movespeed = 1;
    int imageIndex = 0;
    private long imageChanged = 0;

    public static void ImageFrames(BufferedImage BeetleFrame0, BufferedImage BeetleFrame1, BufferedImage BeetleFrame2, BufferedImage BeetleFrame3)
    {
        movementFrames[0] = BeetleFrame0;
        movementFrames[1] = BeetleFrame1;
        movementFrames[2] = BeetleFrame2;
        movementFrames[3] = BeetleFrame3;
    }

    @Override
    public void drawImage(Graphics g, int x, int y)
    {
        if (imageIndex <= 3)
        {
            this.setImg(movementFrames[imageIndex]);
        }
        else
        {
            this.setImg(movementFrames[0]);
            imageIndex = 0;
        }

        //reset the time
        if (System.currentTimeMillis() - imageChanged > 50)
        {
            imageIndex++;
            imageChanged = System.currentTimeMillis();
        }

        AffineTransform rotation = AffineTransform.getTranslateInstance(this.getX(), this.getY());
        Graphics2D g2d = (Graphics2D) g;

        if (currentDirection.equals("up"))
        {
            rotation.rotate(0, this.getImg().getWidth(null) / 2.0, this.getImg().getHeight(null) / 2.0);
        }
        else if (currentDirection.equals("down"))
        {
            rotation.rotate(Math.toRadians(180), this.getImg().getWidth(null) / 2.0, this.getImg().getHeight(null) / 2.0);
        }

        g2d.drawImage(getImg(), rotation, null);
    }

    @Override
    public void spawn()
    {
        Beetle temp_b1 = new Beetle();
        temp_b1.setImg(movementFrames[0]);
        temp_b1.setX(62);
        temp_b1.setY(100);
        temp_b1.currentDirection = "down";
        World.updatableBeetle.add(temp_b1);
        World.worldItems.add(temp_b1);

        temp_b1 = new Beetle();
        temp_b1.setImg(movementFrames[0]);
        temp_b1.setX(295);
        temp_b1.setY(320);
        temp_b1.currentDirection = "down";
        World.updatableBeetle.add(temp_b1);
        World.worldItems.add(temp_b1);

        temp_b1 = new Beetle();
        temp_b1.setImg(movementFrames[0]);
        temp_b1.setX(67);
        temp_b1.setY(1954);
        temp_b1.currentDirection = "up";
        World.updatableBeetle.add(temp_b1);
        World.worldItems.add(temp_b1);

        temp_b1 = new Beetle();
        temp_b1.setImg(movementFrames[0]);
        temp_b1.setX(520);
        temp_b1.setY(1764);
        temp_b1.currentDirection = "up";
        World.updatableBeetle.add(temp_b1);
        World.worldItems.add(temp_b1);

        temp_b1 = new Beetle();
        temp_b1.setImg(movementFrames[0]);
        temp_b1.setX(1568);
        temp_b1.setY(1700);
        temp_b1.currentDirection = "down";
        World.updatableBeetle.add(temp_b1);
        World.worldItems.add(temp_b1);

        temp_b1 = new Beetle();
        temp_b1.setImg(movementFrames[0]);
        temp_b1.setX(1917);
        temp_b1.setY(2070);
        temp_b1.currentDirection = "up";
        World.updatableBeetle.add(temp_b1);
        World.worldItems.add(temp_b1);
    }

    public void swapDirection()
    {
        if (this.currentDirection.equals("up"))
        {
            this.currentDirection = "down";
        }
        else if (this.currentDirection.equals("down"))
        {
            this.currentDirection = "up";
        }
    }

    public void update()
    {
        if (currentDirection.equals("up"))
        {
            this.setY(this.getY() - movespeed);
        }
        else if (currentDirection.equals("down"))
        {
            this.setY(this.getY() + movespeed);
        }
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

                if (item.getX() + item.getImg().getHeight(null) / 2 > this.getX() && item.getX() < this.getX() + this.getImg().getWidth(null))
                {
                    if (this.currentDirection.equals("down") && item.getY() > this.getY() && item.getY() - this.getY() < 350
                            || this.currentDirection.equals("up") && item.getY() < this.getY() && this.getY() - item.getY() < 350)
                        movespeed = 3;
                    else if (this.currentDirection.equals("up") && item.getY() > this.getY()
                            || this.currentDirection.equals("down") && item.getY() < this.getY())
                    {
                        movespeed = 1;
                    }
                }
                else movespeed = 1;

                if (thisRectangle.intersects(itemRectangle))
                {
                    ((Player) item).setHealth(((Player) item).getHealth() - 1);
                    ((Player) item).respawn();
                }
            }
            else if (item instanceof Wall)
            {
                Rectangle thisRectangle = new Rectangle(this.getX(), this.getY(), this.getImg().getWidth(null), this.getImg().getHeight(null));
                Rectangle itemRectangle = new Rectangle(item.getX(), item.getY(), item.getImg().getWidth(null), item.getImg().getHeight(null));

                if (thisRectangle.intersects(itemRectangle))
                {
                    this.swapDirection();
                    this.update();
                }
            }
        }
    }
}
