package src.WorldItems;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Treasure_1 extends Treasure {
    private static Image img;

    public void setImg(BufferedImage image) {
        img = image;
    }
    public Image getImg() {
        return img;
    }

    public Treasure_1(int x, int y){
        this.setX(x);
        this.setY(y);
        setTreasureValue(25);
    }
    @Override
    public void drawImage(Graphics g, int x, int y) {

    }

    @Override
    public void spawn() {

    }


}
