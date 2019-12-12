package src;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerLife {
    private static Image img;
    public void setImg(BufferedImage image) {
        img = image;
    }
    public Image getImg() { return img; }
}
