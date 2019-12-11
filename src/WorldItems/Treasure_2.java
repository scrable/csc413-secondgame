package src.WorldItems;

import src.World;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Treasure_2 extends Treasure{
    private static Image img;

    public void setImg(BufferedImage image) {
        img = image;
    }
    public Image getImg() {
        return img;
    }

    public Treasure_2(int x, int y){
        this.setX(x);
        this.setY(y);
        setTreasureValue(100);
    }
    @Override
    public void drawImage(Graphics g, int x, int y) {
        g.drawImage(getImg(), x, y, null);
    }

    @Override
    public void spawn() {
        Treasure_2 temp_t2 = new Treasure_2(2060, 70);
        World.worldItems.add(temp_t2);
        temp_t2 = new Treasure_2(2094, 612);
        World.worldItems.add(temp_t2);
        temp_t2 = new Treasure_2(1830, 1000);
        World.worldItems.add(temp_t2);
        temp_t2 = new Treasure_2(90, 100);
        World.worldItems.add(temp_t2);
        temp_t2 = new Treasure_2(290, 430);
        World.worldItems.add(temp_t2);
        temp_t2 = new Treasure_2(290, 660);
        World.worldItems.add(temp_t2);
        temp_t2 = new Treasure_2(70, 1085);
        World.worldItems.add(temp_t2);
        temp_t2 = new Treasure_2(42, 2057);
        World.worldItems.add(temp_t2);
        temp_t2 = new Treasure_2(42, 2104);
        World.worldItems.add(temp_t2);
        temp_t2 = new Treasure_2(531, 1686);
        World.worldItems.add(temp_t2);
        temp_t2 = new Treasure_2(531, 1964);
        World.worldItems.add(temp_t2);
        temp_t2 = new Treasure_2(1060, 1500);
        World.worldItems.add(temp_t2);
        temp_t2 = new Treasure_2(1770, 1818);
        World.worldItems.add(temp_t2);
        temp_t2 = new Treasure_2(1922, 1818);
        World.worldItems.add(temp_t2);
    }
}
