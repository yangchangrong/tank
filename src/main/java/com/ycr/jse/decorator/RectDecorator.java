package com.ycr.jse.decorator;

import com.ycr.jse.facade.GameObject;

import java.awt.*;

/**
 * RectDecorator即是GameObject的子类又聚合GameObject
 */
public class RectDecorator extends GODecorator{


    /**
     * 通过构造方法聚合GameObject
     */
    public RectDecorator(GameObject go) {
        super(go);
    }

    @Override
    public void paint(Graphics g) {
        //1.首先执行传入对象的paint()方法（例如传入tank,先执行tank的paint(g)方法）
        super.paint(g);
        super.setX(super.getGo().getX());
        super.setY(super.getGo().getY());
        //2.后执行装饰的方法，需要加强的方法（例如画一个Rect）
        Color c = g.getColor();
        g.setColor(Color.GREEN);
        g.drawRect(super.getGo().getX(),super.getGo().getY(),getWidth(),getHeight());
        g.setColor(c);

    }

    @Override
    public int getWidth() {
        return super.getGo().getWidth();
    }

    @Override
    public int getHeight() {
        return super.getGo().getHeight();
    }

}
