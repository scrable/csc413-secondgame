package src.WorldItems;

import src.World;

import java.awt.*;
import java.awt.image.BufferedImage;

public class InnerWall extends Wall {
    private static Image img;

    //set by calculating number of walls that will fit in the middle
    Wall[][] walls = new InnerWall[66][66];

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
//        int tempWidth = img.getWidth(null);
//        int tempHeight = img.getHeight(null);
//
//        int tempPlacement = 0;
//        int counter = 0;
//        for (int i = img.getWidth(null); i < World.SCREEN_HEIGHT/3 + 200; i += tempWidth){
//            if(counter == 4 || counter == 5) {
//                V_PushableWall vpw = new V_PushableWall(i, World.SCREEN_HEIGHT - 200);
//                World.worldItems.add(vpw);
//            }
//            else{
//                InnerWall iw = new InnerWall();
//                iw.setImg(img);
//                iw.setX(i);
//                iw.setY(World.SCREEN_HEIGHT - 200);
//                World.worldItems.add(iw);
//            }
//            tempPlacement = i;
//            counter++;
//        }
//        int tempX = tempPlacement;
//        for(int i = World.SCREEN_HEIGHT - 700; i < World.SCREEN_HEIGHT - 200; i+=img.getHeight(null)){
//            InnerWall iw = new InnerWall();
//            iw.setImg(img);
//            iw.setX(tempX);
//            iw.setY(i);
//            World.worldItems.add(iw);
//        }

        //initialize full array of hidden walls
        for(int i = 0; i < walls.length - 1; i++) {
            for (int j = 0; j < walls.length - 1; j++) {
                InnerWall iw = new InnerWall();
                iw.setImg(img);
                iw.setX(i * img.getWidth(null) + img.getWidth(null));
                iw.setY(j * img.getHeight(null) + img.getHeight(null));
                walls[i][j] = iw;
            }
        }
        createWalls();


    }

    @Override
    public void collisions() {

    }

    private void createWalls(){
        for(int i = 0; i < 18; i++){
            if(i == 3 || i == 4 || i == 9 || i == 10){
                V_PushableWall tempVPW;
                tempVPW = new V_PushableWall(walls[i][62].getX(), walls[i][62].getY());
                World.worldItems.add(tempVPW);
            }
            else{
                World.worldItems.add(walls[i][62]);
            }
        }

        for(int i = 62; i > 50; i--){
            if(i == 58 || i == 59)
            {
                H_PushableWall tempHPW;
                tempHPW = new H_PushableWall(walls[13][i].getX(), walls[13][i].getY());
                World.worldItems.add(tempHPW);
            }
            else
                World.worldItems.add(walls[13][i]);
        }

        for(int i = 62; i > 50; i--){
            if(i == 53 || i == 52){
                H_PushableWall tempHPW;
                tempHPW = new H_PushableWall(walls[18][i].getX(), walls[18][i].getY());
                World.worldItems.add(tempHPW);
            }
            else{
                World.worldItems.add(walls[18][i]);
            }
        }
    }
}
