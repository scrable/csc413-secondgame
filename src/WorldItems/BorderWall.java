package src.WorldItems;

import src.World;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BorderWall extends Wall {
    private static Image img;

    public void setImg(BufferedImage image) {
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
        //get the height for a wall once since it is always the same to reduce function calls in for loop
        int tempWidth = img.getWidth(null);
        int tempHeight = img.getHeight(null);

        //draw the walls bordering the world
        for (int i = 0; i < World.SCREEN_WIDTH; i += tempWidth) {
            BorderWall tempWallArea1 = new BorderWall();
            tempWallArea1.setX(i);
            tempWallArea1.setY(World.SCREEN_HEIGHT - tempWidth-8);
            tempWallArea1.setImg(img);

            BorderWall tempWallArea2 = new BorderWall();
            tempWallArea2.setX(i);
            tempWallArea2.setY(0);
            tempWallArea2.setImg(img);

            World.worldItems.add(tempWallArea1);
            World.worldItems.add(tempWallArea2);
        }
        for (int i = 0; i < World.SCREEN_HEIGHT; i += tempHeight) {
            BorderWall tempWallArea1 = new BorderWall();
            tempWallArea1.setX(World.SCREEN_WIDTH - tempWidth-14);
            tempWallArea1.setY(i);
            tempWallArea1.setImg(img);

            BorderWall tempWallArea2 = new BorderWall();
            tempWallArea2.setX(0);
            tempWallArea2.setY(i);
            tempWallArea2.setImg(img);

            World.worldItems.add(tempWallArea1);
            World.worldItems.add(tempWallArea2);
        }
    }
}
