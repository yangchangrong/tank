package com.ycr.jse.facade;

import java.awt.*;

/**
 * 所有的物体抽象
 */
public abstract class GameObject {

    protected int x,y;
    /**
     * 抽象paint
     * @param g
     */
    public abstract void paint(Graphics g);
    public abstract int getWidth();
    public abstract int getHeight();


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
