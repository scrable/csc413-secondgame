package src;

import java.awt.*;
import java.awt.image.BufferedImage;

public class InnerWall extends Wall{
    private static Image img;

    void setImg(BufferedImage image) {
        img = image;
    }

    public Image getImg() {
        return img;
    }

    @Override
    public void drawImage(Graphics g, int x, int y) {
        g.drawImage(getImg(), x, y, null);
    }

    @Override
    public void spawn() {
        int tempWidth = img.getWidth(null);
        int tempHeight = img.getHeight(null);

        int counter = 0;
        for (int i = img.getWidth(null); i < World.SCREEN_HEIGHT-1500; i += tempHeight){
            if(counter == 4 || counter == 5) {
                V_PushableWall vpw = new V_PushableWall(i, World.SCREEN_HEIGHT - 300);
                World.worldItems.add(vpw);
            }
            else{
                InnerWall iw = new InnerWall();
                iw.setImg(img);
                iw.setX(i);
                iw.setY(World.SCREEN_HEIGHT - 300);
                World.worldItems.add(iw);
            }
            counter++;
        }

    }
}
