package com.ycr.jse.facade;

import com.ycr.jse.Bullet;
import com.ycr.jse.Explode;
import com.ycr.jse.Tank;

public class BulletTankCollider implements Collider{

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Tank){
            Bullet bullet = (Bullet) o1;
            Tank tank = (Tank) o2;
            collideWith(bullet,tank);
            return true;
        }else if (o2 instanceof Bullet && o1 instanceof Tank){
            collide(o2,o1);
            return true;
        }
        return false;
    }

    //判断相撞
    private void collideWith(Bullet bullet,Tank tank) {
        if (bullet.getGroup().equals(tank.getGroup())){
            return;
        }
        if (bullet.getRet().intersects(tank.getRet())){
            //坦克和子弹都必须die
            tank.die();
            bullet.die();
            //每课子弹die都创建爆炸
            int explodeX = tank.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
            int explodeY = tank.getY() + Tank.HEIGHT/2 - Explode.WIDTH/2;
            bullet.getGm().getGameObjects().add(new Explode(explodeX,explodeY, bullet.getGm()));
        }
    }
}
