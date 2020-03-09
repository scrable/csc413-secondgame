package src.WorldItems;

import src.World;

import java.awt.*;
import java.awt.image.BufferedImage;

public class InnerWall extends Wall
{
    private static Image img;

    //set by calculating number of walls that will fit in the middle
    private Wall[][] walls = new InnerWall[68][68];

    public Image getImg()
    {
        return img;
    }

    public void setImg(BufferedImage image)
    {
        img = image;
    }

    @Override
    public void drawImage(Graphics g, int x, int y)
    {
        g.drawImage(getImg(), x, y, null);
    }

    @Override
    public void spawn()
    {
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
        for (int i = 0; i < walls.length - 1; i++)
        {
            for (int j = 0; j < walls.length - 1; j++)
            {
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
    public void collisions()
    {

    }

    private void createWalls()
    {
        for (int i = 0; i < 18; i++)
        {
            if (i == 3 || i == 4 || i == 9 || i == 10)
            {
                V_PushableWall tempVPW;
                tempVPW = new V_PushableWall(walls[i][62].getX(), walls[i][62].getY());
                World.worldItems.add(tempVPW);
            }
            else
            {
                World.worldItems.add(walls[i][62]);
            }
        }

        for (int i = 62; i > 50; i--)
        {
            if (i == 58 || i == 59)
            {
                H_PushableWall tempHPW;
                tempHPW = new H_PushableWall(walls[13][i].getX(), walls[13][i].getY());
                World.worldItems.add(tempHPW);
            }
            else
                World.worldItems.add(walls[13][i]);
        }

        for (int i = 62; i > 50; i--)
        {
            if (i == 54 || i == 53)
            {
                H_PushableWall tempHPW;
                tempHPW = new H_PushableWall(walls[18][i].getX(), walls[18][i].getY());
                World.worldItems.add(tempHPW);
            }
            else
            {
                World.worldItems.add(walls[18][i]);
            }
        }

        for (int i = 13; i < 19; i++)
        {
            World.worldItems.add(walls[i][50]);
        }

        for (int i = 65; i >= 50; i--)
        {
            World.worldItems.add(walls[45][i]);
        }

        for (int i = 46; i < 62; i++)
        {
            if (i == 47 || i == 48)
            {
                V_PushableWall tempVPW;
                tempVPW = new V_PushableWall(walls[i][50].getX(), walls[i][50].getY());
                World.worldItems.add(tempVPW);
            }
            else
                World.worldItems.add(walls[i][50]);
        }

        for (int i = 40; i > 20; i--)
        {
            World.worldItems.add(walls[45][i]);
        }

        for (int i = 46; i < 66; i++)
        {
            World.worldItems.add(walls[i][40]);

            if (i == 49 || i == 50 || i == 61 || i == 62)
            {
                V_PushableWall tempVPW;
                tempVPW = new V_PushableWall(walls[i][21].getX(), walls[i][21].getY());
                World.worldItems.add(tempVPW);
            }
            else
                World.worldItems.add(walls[i][21]);
        }

        for (int i = 65; i > 50; i--)
        {
            World.worldItems.add(walls[i][54]);
        }

        for (int i = 55; i < 62; i++)
        {
            World.worldItems.add(walls[51][i]);
        }

        for (int i = 52; i < 58; i++)
        {
            if (i == 53 || i == 54 || i == 55)
            {
                V_PushableWall tempVPW;
                tempVPW = new V_PushableWall(walls[i][61].getX(), walls[i][61].getY());
                World.worldItems.add(tempVPW);
            }
            else
                World.worldItems.add(walls[i][61]);
        }

        for (int i = 55; i < 64; i++)
        {
            World.worldItems.add(walls[57][i]);
        }

        for (int i = 55; i < 66; i++)
        {
            if (i == 56 || i == 57 || i == 63 || i == 64)
            {
                H_PushableWall tempHPW;
                tempHPW = new H_PushableWall(walls[61][i].getX(), walls[61][i].getY());
                World.worldItems.add(tempHPW);
            }
            else
                World.worldItems.add(walls[61][i]);
        }

        for (int i = 3; i < 10; i++)
        {
            for (int j = 50; j < 57; j++)
                World.worldItems.add(walls[i][j]);
        }

        // 3 < 19
        for (int i = 3; i < 29; i++)
        {
            for (int j = 30; j < 36; j++)
                World.worldItems.add(walls[i][j]);
        }

        for (int i = 3; i < 35; i++)
        {
            for (int j = 5; j < 8; j++)
                World.worldItems.add(walls[i][j]);
        }

        // 10<29
        for (int i = 10; i < 29; i++)
        {
            if (i == 18 || i == 19 || i == 20)
            {
                V_PushableWall tempVPW;
                tempVPW = new V_PushableWall(walls[i][11].getX(), walls[i][11].getY());
                World.worldItems.add(tempVPW);

            }
            else
            {
                World.worldItems.add(walls[i][11]);
            }
            World.worldItems.add(walls[i][21]);
        }

        for (int i = 11; i < 22; i++)
        {
            if (i == 15 || i == 16 || i == 17)
            {
                H_PushableWall tempHPW;
                tempHPW = new H_PushableWall(walls[28][i].getX(), walls[28][i].getY());
                World.worldItems.add(tempHPW);
            }
            else
            {
                World.worldItems.add(walls[28][i]);
            }
            World.worldItems.add(walls[10][i]);
        }

        for (int i = 3; i < 7; i++)
        {
            for (int j = 11; j < 22; j++)
                World.worldItems.add(walls[i][j]);
        }

        for (int i = 4; i < 10; i++)
        {
            World.worldItems.add(walls[60][i]);
        }

        for (int i = 57; i < 60; i++)
        {
            World.worldItems.add(walls[i][4]);
            World.worldItems.add(walls[i][9]);
        }

        for (int i = 65; i > 44; i--)
        {
            if (i == 47 || i == 48)
            {
                V_PushableWall tempVPW;
                tempVPW = new V_PushableWall(walls[i][15].getX(), walls[i][15].getY());
                World.worldItems.add(tempVPW);
            }
            else
                World.worldItems.add(walls[i][15]);
        }

        for (int i = 0; i < 15; i++)
        {
            if (i == 1 || i == 2)
            {
                H_PushableWall tempHPW;
                tempHPW = new H_PushableWall(walls[45][i].getX(), walls[45][i].getY());
                World.worldItems.add(tempHPW);
            }
            else
                World.worldItems.add(walls[45][i]);
        }
    }
}
