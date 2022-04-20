package com.ycr.jse.decorator;

import com.ycr.jse.facade.GameObject;

import java.awt.*;

/**
 * GameObject装饰器
 */
public abstract class GODecorator extends GameObject{
    /**
     * 聚合GameObject
     */
    private GameObject go;

    /**
     * 通过构造方法聚合聚合GameObject
     */
    public GODecorator(GameObject go) {
        this.go = go;
    }

    @Override
    public void paint(Graphics g) {
        go.paint(g);
    }

    public GameObject getGo() {
        return go;
    }
}
