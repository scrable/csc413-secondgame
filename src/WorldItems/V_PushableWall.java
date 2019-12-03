package src.WorldItems;

import java.awt.*;
import java.awt.image.BufferedImage;

public class V_PushableWall extends Wall {
    private static Image img;

    public void setImg(BufferedImage image) {
        img = image;
    }

    public Image getImg() {
        return img;
    }
    public V_PushableWall(int x, int y) {
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

    }
}