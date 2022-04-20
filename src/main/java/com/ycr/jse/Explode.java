package com.ycr.jse;

import com.ycr.jse.facade.GameModel;
import com.ycr.jse.facade.GameObject;
import com.ycr.jse.frame.Dir;
import com.ycr.jse.frame.TankFrame;

import java.awt.*;

/**
 * 爆炸
 */
public class Explode extends GameObject {

    public static final int WIDTH = ResourceManager.explodes[0].getWidth();
    public static final int HEIGHT = ResourceManager.explodes[0].getHeight();

    private  int x,y;
    private boolean living = true;
    private int step = 0;

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
        //增加爆炸声(通过开启新的线程，提升性能)
        new Thread(()->new Audio("audio/explode.wav").play()).start();
    }


    public void paint(Graphics g) {
        if (!living){
            GameModel.getInstance().getGameObjects().remove(this);
            return;
        }
        g.drawImage(ResourceManager.explodes[step++], x, y, null);
        if (step >= ResourceManager.explodes.length){
            die();
        }

    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

    public void die(){
        this.living = false;
    }


}
