package com.ycr.jse;

import com.ycr.jse.frame.Dir;
import com.ycr.jse.frame.TankFrame;

import java.awt.*;

/**
 * 爆炸
 */
public class Explode {

    private static final int WIDTH = ResourceManager.explodes[0].getWidth();
    private static final int HEIGHT = ResourceManager.explodes[0].getHeight();

    private  int x,y;
    private boolean living = true;
    private TankFrame tankFrame;
    private int step = 0;

    public Explode(int x, int y, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;
    }


    public void paint(Graphics g) {
        g.drawImage(ResourceManager.explodes[step++], x, y, null);
        if (step >= ResourceManager.explodes.length){
            step = 0;
        }
    }


}
