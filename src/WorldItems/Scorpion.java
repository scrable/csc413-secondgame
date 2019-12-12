package src.WorldItems;

import src.World;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Scorpion extends WorldItem {
    String currentDirection = "left";
    int movespeed = 1;

    private static BufferedImage[] movementFrames = new BufferedImage[4];

    @Override
    public void drawImage(Graphics g, int x, int y) {
        g.drawImage(getImg(), x, y, null);
    }

    @Override
    public void spawn() {
        Scorpion temp_S1 = new Scorpion();
        temp_S1.setImg(movementFrames[0]);
        temp_S1.setX(100);
        temp_S1.setY(100);
        World.updatableScorpion.add(temp_S1);
        World.worldItems.add(temp_S1);
    }

    @Override
    public void collisions() {
        //collide with player
        //collide with any kind of walls4
        for (int i = 0; i < World.worldItems.size(); i++) {
            WorldItem item = World.worldItems.get(i);
            if (item instanceof Player) {
                Rectangle thisRectangle = new Rectangle(this.getX() + this.getAx(), this.getY() + this.getAy(), this.getImg().getWidth(null), this.getImg().getHeight(null));
                Rectangle itemRectangle = new Rectangle(item.getX(), item.getY(), item.getImg().getWidth(null), item.getImg().getHeight(null));
                if (thisRectangle.intersects(itemRectangle)) {
                    ((Player) item).setHealth(((Player) item).getHealth() - 1);
                    World.worldItems.remove(this);
                }
            }
            else if(item instanceof Wall){
                Rectangle thisRectangle = new Rectangle(this.getX() + this.getAx(), this.getY() + this.getAy(), this.getImg().getWidth(null), this.getImg().getHeight(null));
                Rectangle itemRectangle = new Rectangle(item.getX(), item.getY(), item.getImg().getWidth(null), item.getImg().getHeight(null));

                if (thisRectangle.intersects(itemRectangle)) {
                    this.swapDirection();
                }
            }
        }
    }

    public static void ImageFrames(BufferedImage ScorpionFrame0, BufferedImage ScorpionFrame1, BufferedImage ScorpionFrame2, BufferedImage ScorpionFrame3){
        movementFrames[0] = ScorpionFrame0;
        movementFrames[1] = ScorpionFrame1;
        movementFrames[2] = ScorpionFrame2;
        movementFrames[3] = ScorpionFrame3;
    }

    public void swapDirection(){
        if(currentDirection.equals("left")){
            currentDirection = "right";
        }
        else if(currentDirection.equals("right")){
            currentDirection = "left";
        }
    }

    public void update(){
        if(currentDirection.equals("left")){
            this.setX(this.getX() - movespeed);
        }
        else if(currentDirection.equals("right")){
            this.setX(this.getX() + movespeed);
        }
    }
}
