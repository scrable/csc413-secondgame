package src;

import java.awt.*;
import java.awt.image.BufferedImage;

public class V_PushableWall extends Wall {
    private static Image img;

    void setImg(BufferedImage image) {
        img = image;
    }

    public Image getImg() {
        return img;
    }
    public V_PushableWall(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    public void checkBorder() {
        if (this.getX() < 30) {
            this.setX(30);
        }
        if (this.getX() >= World.SCREEN_WIDTH - 88) {
            this.setX(World.SCREEN_WIDTH - 88);
        }
        if (this.getY() < 40) {
            this.setY(40);
        }
        if (this.getY() >= World.SCREEN_HEIGHT - 80) {
            this.setY(World.SCREEN_HEIGHT - 80);
        }
    }

    @Override
    public void drawImage(Graphics g, int x, int y) {

        g.drawImage(getImg(), x, y, null);
    }

    @Override
    public void spawn() {

    }
}
