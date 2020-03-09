package src.WorldItems;

import src.World;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;


public class Mummy extends WorldItem
{
    private static BufferedImage[] movementFrames = new BufferedImage[4];
    String currentDirection = "left";
    int movespeed = 1;
    int imageIndex = 0;
    private long imageChanged = 0;
    private long directionChanged = 0;

    public static void ImageFrames(BufferedImage MummyFrame0, BufferedImage MummyFrame1, BufferedImage MummyFrame2, BufferedImage MummyFrame3)
    {
        movementFrames[0] = MummyFrame0;
        movementFrames[1] = MummyFrame1;
        movementFrames[2] = MummyFrame2;
        movementFrames[3] = MummyFrame3;
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
        if (System.currentTimeMillis() - imageChanged > 250)
        {
            imageIndex++;
            imageChanged = System.currentTimeMillis();
        }

        AffineTransform rotation = AffineTransform.getTranslateInstance(this.getX(), this.getY());
        Graphics2D g2d = (Graphics2D) g;

        if (currentDirection.equals("left"))
        {
            rotation.rotate(Math.toRadians(270), this.getImg().getWidth(null) / 2.0, this.getImg().getHeight(null) / 2.0);
        }
        else if (currentDirection.equals("right"))
        {
            rotation.rotate(Math.toRadians(90), this.getImg().getWidth(null) / 2.0, this.getImg().getHeight(null) / 2.0);
        }
        else if (currentDirection.equals("up"))
        {
            rotation.rotate(0, this.getImg().getWidth(null) / 2.0, this.getImg().getHeight(null) / 2.0);
        }
        else if (currentDirection.equals("down"))
        {
            rotation.rotate(Math.toRadians(180), this.getImg().getWidth(null) / 2.0, this.getImg().getHeight(null) / 2.0);
        }
        g2d.drawImage(getImg(), rotation, null);
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
        if (this.currentDirection.equals("left"))
        {
            this.currentDirection = "right";
        }
        else if (this.currentDirection.equals("right"))
        {
            this.currentDirection = "left";
        }
    }

    public void update()
    {
        if (currentDirection.equals("left"))
        {
            this.setX(this.getX() - movespeed);
        }
        else if (currentDirection.equals("right"))
        {
            this.setX(this.getX() + movespeed);
        }
        else if (currentDirection.equals("up"))
        {
            this.setY(this.getY() - movespeed);
        }
        else if (currentDirection.equals("down"))
        {
            this.setY(this.getY() + movespeed);
        }

        if (System.currentTimeMillis() - directionChanged > 550)
        {
            int randomNumber = (int) (Math.random() * 500) + 1;
            if (randomNumber % 4 == 0)
            {
                currentDirection = "left";
            }
            else if (randomNumber % 4 == 1)
            {
                currentDirection = "right";
            }
            else if (randomNumber % 4 == 2)
            {
                currentDirection = "down";
            }
            else if (randomNumber % 4 == 3)
            {
                currentDirection = "up";
            }
            directionChanged = System.currentTimeMillis();
        }
    }

    @Override
    public void spawn()
    {
        Mummy m1;

        m1 = new Mummy();
        m1.setX(1570);
        m1.setY(844);
        m1.setImg(movementFrames[0]);
        World.updatableMummy.add(m1);
        World.worldItems.add(m1);

        m1 = new Mummy();
        m1.setX(1845);
        m1.setY(844);
        m1.setImg(movementFrames[0]);
        World.updatableMummy.add(m1);
        World.worldItems.add(m1);

        m1 = new Mummy();
        m1.setX(2065);
        m1.setY(844);
        m1.setImg(movementFrames[0]);
        World.updatableMummy.add(m1);
        World.worldItems.add(m1);

        m1 = new Mummy();
        m1.setX(2065);
        m1.setY(1230);
        m1.setImg(movementFrames[0]);
        World.updatableMummy.add(m1);
        World.worldItems.add(m1);

        m1 = new Mummy();
        m1.setX(1570);
        m1.setY(1230);
        m1.setImg(movementFrames[0]);
        World.updatableMummy.add(m1);
        World.worldItems.add(m1);

        m1 = new Mummy();
        m1.setX(1845);
        m1.setY(1230);
        m1.setImg(movementFrames[0]);
        World.updatableMummy.add(m1);
        World.worldItems.add(m1);

        m1 = new Mummy();
        m1.setX(1804);
        m1.setY(1925);
        m1.setImg(movementFrames[0]);
        World.updatableMummy.add(m1);
        World.worldItems.add(m1);

        m1 = new Mummy();
        m1.setX(1705);
        m1.setY(1812);
        m1.setImg(movementFrames[0]);
        World.updatableMummy.add(m1);
        World.worldItems.add(m1);

        m1 = new Mummy();
        m1.setX(488);
        m1.setY(1359);
        m1.setImg(movementFrames[0]);
        World.updatableMummy.add(m1);
        World.worldItems.add(m1);

        m1 = new Mummy();
        m1.setX(868);
        m1.setY(1469);
        m1.setImg(movementFrames[0]);
        World.updatableMummy.add(m1);
        World.worldItems.add(m1);

        m1 = new Mummy();
        m1.setX(1023);
        m1.setY(1874);
        m1.setImg(movementFrames[0]);
        World.updatableMummy.add(m1);
        World.worldItems.add(m1);

        m1 = new Mummy();
        m1.setX(520);
        m1.setY(1889);
        m1.setImg(movementFrames[0]);
        World.updatableMummy.add(m1);
        World.worldItems.add(m1);

        m1 = new Mummy();
        m1.setX(451);
        m1.setY(612);
        m1.setImg(movementFrames[0]);
        World.updatableMummy.add(m1);
        World.worldItems.add(m1);

        m1 = new Mummy();
        m1.setX(451);
        m1.setY(472);
        m1.setImg(movementFrames[0]);
        World.updatableMummy.add(m1);
        World.worldItems.add(m1);

        m1 = new Mummy();
        m1.setX(801);
        m1.setY(472);
        m1.setImg(movementFrames[0]);
        World.updatableMummy.add(m1);
        World.worldItems.add(m1);

        m1 = new Mummy();
        m1.setX(801);
        m1.setY(472);
        m1.setImg(movementFrames[0]);
        World.updatableMummy.add(m1);
        World.worldItems.add(m1);
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
                        movespeed = 2;
                    else if (this.currentDirection.equals("up") && item.getY() > this.getY()
                            || this.currentDirection.equals("down") && item.getY() < this.getY())
                    {
                        movespeed = 1;
                    }
                }
                else if (item.getY() + item.getImg().getHeight(null) / 2 > this.getY() && item.getY() < this.getY() + this.getImg().getHeight(null))
                {
                    if (this.currentDirection.equals("right") && item.getX() > this.getX() && item.getX() - this.getX() < 350
                            || this.currentDirection.equals("left") && item.getX() < this.getX() && this.getX() - item.getX() < 350)
                        movespeed = 2;
                }
                else movespeed = 1;

                if (thisRectangle.intersects(itemRectangle))
                {
                    if (((Player) item).scarabActive())
                    {
                        ((Player) item).addScore(500);
                        World.worldItems.remove(this);
                    }
                    else
                    {
                        ((Player) item).setHealth(((Player) item).getHealth() - 1);
                        ((Player) item).respawn();
                    }
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
