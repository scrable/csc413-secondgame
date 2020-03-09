package src.WorldItems;

import src.World;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Treasure_1 extends Treasure
{
    private static Image img;

    public Treasure_1(int x, int y)
    {
        this.setX(x);
        this.setY(y);
        setTreasureValue(25);
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
        Treasure_1 temp_t1 = new Treasure_1(1650, 105);
        World.worldItems.add(temp_t1);
        temp_t1 = new Treasure_1(1650, 300);
        World.worldItems.add(temp_t1);
        temp_t1 = new Treasure_1(1890, 570);
        World.worldItems.add(temp_t1);
        temp_t1 = new Treasure_1(1890, 640);
        World.worldItems.add(temp_t1);
        temp_t1 = new Treasure_1(1565, 1224);
        World.worldItems.add(temp_t1);
        temp_t1 = new Treasure_1(1770, 1224);
        World.worldItems.add(temp_t1);
        temp_t1 = new Treasure_1(1960, 1224);
        World.worldItems.add(temp_t1);
        temp_t1 = new Treasure_1(1960, 1000);
        World.worldItems.add(temp_t1);
        temp_t1 = new Treasure_1(1690, 1009);
        World.worldItems.add(temp_t1);
        temp_t1 = new Treasure_1(1560, 884);
        World.worldItems.add(temp_t1);
        temp_t1 = new Treasure_1(1180, 577);
        World.worldItems.add(temp_t1);
        temp_t1 = new Treasure_1(1316, 500);
        World.worldItems.add(temp_t1);
        temp_t1 = new Treasure_1(1351, 92);
        World.worldItems.add(temp_t1);
        temp_t1 = new Treasure_1(970, 92);
        World.worldItems.add(temp_t1);
        temp_t1 = new Treasure_1(680, 92);
        World.worldItems.add(temp_t1);
        temp_t1 = new Treasure_1(400, 92);
        World.worldItems.add(temp_t1);
        temp_t1 = new Treasure_1(170, 312);
        World.worldItems.add(temp_t1);
        temp_t1 = new Treasure_1(176, 825);
        World.worldItems.add(temp_t1);
        temp_t1 = new Treasure_1(601, 825);
        World.worldItems.add(temp_t1);
        temp_t1 = new Treasure_1(381, 1420);
        World.worldItems.add(temp_t1);
        temp_t1 = new Treasure_1(381, 1935);
        World.worldItems.add(temp_t1);
        temp_t1 = new Treasure_1(66, 1935);
        World.worldItems.add(temp_t1);
        temp_t1 = new Treasure_1(836, 1954);
        World.worldItems.add(temp_t1);
        temp_t1 = new Treasure_1(1126, 1954);
        World.worldItems.add(temp_t1);
        temp_t1 = new Treasure_1(2060, 1688);
        World.worldItems.add(temp_t1);
        temp_t1 = new Treasure_1(1504, 1664);
        World.worldItems.add(temp_t1);
        temp_t1 = new Treasure_1(1712, 1839);
        World.worldItems.add(temp_t1);
        temp_t1 = new Treasure_1(1824, 1839);
        World.worldItems.add(temp_t1);


    }


}
