package com.ycr.jse;

import com.ycr.jse.frame.Dir;

import java.awt.*;

/**
 * 子弹
 */
public class Bullet {

    private static final int SPEED = 2;
    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;

    private  int x,y;
    private Dir dir;

    public Bullet(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x,y,WIDTH,WIDTH);
        g.setColor(c);
        move(g, c);

    }

    /**
     * 移动
     * @param g
     * @param c
     */
    private void move(Graphics g, Color c) {
        g.setColor(c);
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
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }
}
