package com.ycr.jse.facade;

import com.ycr.jse.Bullet;
import com.ycr.jse.Tank;

public class TankTankCollider implements Collider{

    @Override
    public void collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Tank){
            Tank t1 = (Tank) o1;
            Tank t2 = (Tank) o2;
            t1.collideWith(t2);
        }
        return;
    }
}
