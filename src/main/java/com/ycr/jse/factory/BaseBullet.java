package com.ycr.jse.factory;

import com.ycr.jse.Tank;

import java.awt.*;

/**
 * 子弹基础类
 */
public abstract class BaseBullet {
    public abstract void paint(Graphics g);

    public abstract void collideWith(Tank tank);
}
