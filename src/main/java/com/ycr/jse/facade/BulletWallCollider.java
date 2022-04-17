package com.ycr.jse.facade;

import com.ycr.jse.Bullet;
import com.ycr.jse.Explode;
import com.ycr.jse.Tank;
import com.ycr.jse.Wall;

public class BulletWallCollider implements Collider{

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Wall){
            Bullet bullet = (Bullet) o1;
            Wall w = (Wall) o2;
            collideWith(bullet,w);
        }else if (o2 instanceof Bullet && o1 instanceof Wall){
            collide(o2,o1);
        }
        return false;
    }

    //判断相撞
    public void collideWith(Bullet bullet,Wall w) {

        if (bullet.getRet().intersects(w.getRet())){
            //子弹都必须die
            bullet.die();
        }
    }
}
