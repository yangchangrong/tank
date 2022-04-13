package com.ycr.jse.factory;

import com.ycr.jse.Audio;
import com.ycr.jse.ResourceManager;
import com.ycr.jse.frame.TankFrame;

import java.awt.*;

public class RetExplode extends BaseExplode{


    private final int x;
    private final int y;
    private boolean living = true;
    private final TankFrame tankFrame;
    private int step = 0;

    public RetExplode(int x, int y, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;
        //增加爆炸声(通过开启新的线程，提升性能)
        new Thread(()->new Audio("audio/explode.wav").play()).start();
    }
    public void die(){
        this.living = false;
    }

    @Override
    public void paint(Graphics g) {
        if (!living){
            this.tankFrame.getExplodes().remove(this);
            return;
        }
        Color c = g.getColor();
        g.setColor(Color.red);
        g.fillRect(x,y,step * 10,step * 10);
        step ++;
        g.setColor(c);
        if (step >= ResourceManager.explodes.length){
            die();
        }
    }
}
