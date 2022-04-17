package com.ycr.jse;

import com.ycr.jse.facade.GameModel;
import com.ycr.jse.facade.GameObject;
import com.ycr.jse.frame.Dir;
import com.ycr.jse.frame.TankFrame;

import java.awt.*;

/**
 * 墙
 */
public class Wall extends GameObject {

    private  int x,y;
    private  int width,height;
    private Dir dir;
    private GameModel gm;
    private Rectangle ret = new Rectangle();

    public Wall(int x, int y,int width,int height, GameModel gm) {
        this.x = x;
        this.y = y;
        this.gm = gm;
        this.width = width;
        this.height = height;
        ret.x = this.x;
        ret.y = this.y ;
        ret.width = height;
        ret.height = height;
    }

    public Rectangle getRet() {
        return ret;
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.DARK_GRAY);
        g.fillRect(x,y,width,height);
        g.setColor(c);
    }


}
