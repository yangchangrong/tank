package com.ycr.jse;

import com.ycr.jse.frame.Dir;

import java.awt.*;

/**
 * 坦克类
 */
public class Tank {

    private int x = 200;
    private int y = 200;
    private Dir dir = Dir.DOWN;
    private boolean stop = true;

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    private static int SPEED = 10;

    public Tank(int x, int y, Dir dir,boolean stop) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.stop = stop;
    }

    public void paint(Graphics g){
        g.fillRect(x, y, 50, 50);
        if (!stop){
            switch (dir) {
                case UP:
                    y -= SPEED;
                    break;
                case DOWN:
                    y += SPEED;
                    break;
                case LEFT:
                    x -= SPEED;
                    break;
                case RIGHT:
                    x += SPEED;
                    break;
            }
        }else {
            return;
        }

    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }
}
