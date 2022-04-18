package com.ycr.jse.strategy;

import com.ycr.jse.Bullet;
import com.ycr.jse.Tank;

/**
 * 单个子弹
 */
public class DefaultBulletFire extends FireStrategy<Tank> {
    @Override
    public void fire(Tank t) {
        //设置子弹和坦克的相对位置
        int bulletX = t.getX() + t.WIDTH/2 - Bullet.WIDTH/2;
        int bulletY = t.getY() + t.HEIGHT/2 - Bullet.HEIGHT/2;
        //返回
        new Bullet(bulletX,bulletY,t.getDir(),t.getGroup());
    }
}
