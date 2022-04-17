package com.ycr.jse.facade;

import com.ycr.jse.Bullet;
import com.ycr.jse.Tank;

public class BulletTankCollider implements Collider{

    @Override
    public void collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Tank){
            Bullet bullet = (Bullet) o1;
            Tank tank = (Tank) o2;
            bullet.collideWith(tank);
        }else if (o2 instanceof Bullet && o1 instanceof Tank){
            collide(o2,o1);
        }
        return;
    }
}
