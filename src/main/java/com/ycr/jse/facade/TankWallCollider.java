package com.ycr.jse.facade;

import com.ycr.jse.Tank;
import com.ycr.jse.Wall;

import java.awt.*;

public class TankWallCollider implements Collider{

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Wall){
            Tank t = (Tank) o1;
            Wall w = (Wall) o2;
            collideWith(t,w);
        }else if (o1 instanceof Wall && o2 instanceof Tank){
            collide(o2,o1);
        }
        return false;
    }

    public void collideWith(Tank t,Wall w) {
        Rectangle ret1 = t.getRet();
        Rectangle ret2 = w.getRet();
        if (ret1.intersects(ret2)){
            t.back();
        }
    }
}
