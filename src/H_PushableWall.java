package src;

import java.awt.*;
import java.awt.image.BufferedImage;

public class H_PushableWall extends PushableWall {
    private static Image img;

    void setImg(BufferedImage image) {
        img = image;
    }

    public Image getImg() {
        return img;
    }
    public H_PushableWall(int x, int y) {
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
}