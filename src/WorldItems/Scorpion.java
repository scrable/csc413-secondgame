package src.WorldItems;

import src.World;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Scorpion extends WorldItem
{
    private static BufferedImage[] movementFrames = new BufferedImage[4];
    String currentDirection = "left";
    int movespeed = 1;
    int imageIndex = 0;
    private long imageChanged = 0;

    public static void ImageFrames(BufferedImage ScorpionFrame0, BufferedImage ScorpionFrame1, BufferedImage ScorpionFrame2, BufferedImage ScorpionFrame3)
    {
        movementFrames[0] = ScorpionFrame0;
        movementFrames[1] = ScorpionFrame1;
        movementFrames[2] = ScorpionFrame2;
        movementFrames[3] = ScorpionFrame3;
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

        if (currentDirection.equals("left"))
        {
            rotation.rotate(0, this.getImg().getWidth(null) / 2.0, this.getImg().getHeight(null) / 2.0);
        }
        else if (currentDirection.equals("right"))
        {
            rotation.rotate(Math.toRadians(180), this.getImg().getWidth(null) / 2.0, this.getImg().getHeight(null) / 2.0);
        }

        g2d.drawImage(getImg(), rotation, null);
    }

    @Override
    public void spawn()
    {
        Scorpion temp_S1 = new Scorpion();
        temp_S1.setImg(movementFrames[0]);
        temp_S1.setX(1790);
        temp_S1.setY(85);
        temp_S1.currentDirection = "left";
        World.updatableScorpion.add(temp_S1);
        World.worldItems.add(temp_S1);

        temp_S1 = new Scorpion();
        temp_S1.setImg(movementFrames[0]);
        temp_S1.setX(1790);
        temp_S1.setY(420);
        temp_S1.currentDirection = "right";
        World.updatableScorpion.add(temp_S1);
        World.worldItems.add(temp_S1);

        temp_S1 = new Scorpion();
        temp_S1.setImg(movementFrames[0]);
        temp_S1.setX(2100);
        temp_S1.setY(650);
        temp_S1.currentDirection = "left";
        World.updatableScorpion.add(temp_S1);
        World.worldItems.add(temp_S1);

        temp_S1 = new Scorpion();
        temp_S1.setImg(movementFrames[0]);
        temp_S1.setX(1555);
        temp_S1.setY(560);
        temp_S1.currentDirection = "right";
        World.updatableScorpion.add(temp_S1);
        World.worldItems.add(temp_S1);

        temp_S1 = new Scorpion();
        temp_S1.setImg(movementFrames[0]);
        temp_S1.setX(1550);
        temp_S1.setY(800);
        temp_S1.currentDirection = "right";
        World.updatableScorpion.add(temp_S1);
        World.worldItems.add(temp_S1);

        temp_S1 = new Scorpion();
        temp_S1.setImg(movementFrames[0]);
        temp_S1.setX(2070);
        temp_S1.setY(924);
        temp_S1.currentDirection = "left";
        World.updatableScorpion.add(temp_S1);
        World.worldItems.add(temp_S1);

        temp_S1 = new Scorpion();
        temp_S1.setImg(movementFrames[0]);
        temp_S1.setX(1555);
        temp_S1.setY(1024);
        temp_S1.currentDirection = "right";
        World.updatableScorpion.add(temp_S1);
        World.worldItems.add(temp_S1);

        temp_S1 = new Scorpion();
        temp_S1.setImg(movementFrames[0]);
        temp_S1.setX(2070);
        temp_S1.setY(1129);
        temp_S1.currentDirection = "left";
        World.updatableScorpion.add(temp_S1);
        World.worldItems.add(temp_S1);

        temp_S1 = new Scorpion();
        temp_S1.setImg(movementFrames[0]);
        temp_S1.setX(1555);
        temp_S1.setY(1124);
        temp_S1.currentDirection = "right";
        World.updatableScorpion.add(temp_S1);
        World.worldItems.add(temp_S1);

        temp_S1 = new Scorpion();
        temp_S1.setImg(movementFrames[0]);
        temp_S1.setX(57);
        temp_S1.setY(2050);
        temp_S1.currentDirection = "left";
        World.updatableScorpion.add(temp_S1);
        World.worldItems.add(temp_S1);

        temp_S1 = new Scorpion();
        temp_S1.setImg(movementFrames[0]);
        temp_S1.setX(57);
        temp_S1.setY(2100);
        temp_S1.currentDirection = "right";
        World.updatableScorpion.add(temp_S1);
        World.worldItems.add(temp_S1);

        temp_S1 = new Scorpion();
        temp_S1.setImg(movementFrames[0]);
        temp_S1.setX(77);
        temp_S1.setY(859);
        temp_S1.currentDirection = "right";
        World.updatableScorpion.add(temp_S1);
        World.worldItems.add(temp_S1);

        temp_S1 = new Scorpion();
        temp_S1.setImg(movementFrames[0]);
        temp_S1.setX(77);
        temp_S1.setY(313);
        temp_S1.currentDirection = "right";
        World.updatableScorpion.add(temp_S1);
        World.worldItems.add(temp_S1);

        temp_S1 = new Scorpion();
        temp_S1.setImg(movementFrames[0]);
        temp_S1.setX(77);
        temp_S1.setY(133);
        temp_S1.currentDirection = "right";
        World.updatableScorpion.add(temp_S1);
        World.worldItems.add(temp_S1);

        temp_S1 = new Scorpion();
        temp_S1.setImg(movementFrames[0]);
        temp_S1.setX(77);
        temp_S1.setY(63);
        temp_S1.currentDirection = "left";
        World.updatableScorpion.add(temp_S1);
        World.worldItems.add(temp_S1);
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

                if (item.getY() + item.getImg().getHeight(null) / 2 > this.getY() && item.getY() < this.getY() + this.getImg().getHeight(null))
                {
                    if (this.currentDirection.equals("right") && item.getX() > this.getX() && item.getX() - this.getX() < 350
                            || this.currentDirection.equals("left") && item.getX() < this.getX() && this.getX() - item.getX() < 350)
                        movespeed = 3;
                    else if (this.currentDirection.equals("left") && item.getX() > this.getX()
                            || this.currentDirection.equals("right") && item.getX() < this.getX())
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

    public void swapDirection()
    {
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
    }
}
