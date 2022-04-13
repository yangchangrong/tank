package com.ycr.jse.strategy;

import com.ycr.jse.Bullet;
import com.ycr.jse.Tank;
import com.ycr.jse.frame.Dir;

import java.util.ArrayList;
import java.util.List;

/**
 * 四个方向打出子弹
 */
public class FourDirBulletFire extends FireStrategy<Tank> {
    @Override
    public void fire(Tank t) {
        //设置子弹和坦克的相对位置
        int bulletX = t.getX() + t.WIDTH/2 - Bullet.WIDTH/2;
        int bulletY = t.getY() + t.HEIGHT/2 - Bullet.HEIGHT/2;
        Dir[] dirs = Dir.values();
        for (int i = 0; i < dirs.length; i++) {
//            new Bullet(bulletX,bulletY,dirs[i],t.getTankFrame(),t.getGroup());
            t.getTankFrame().gf.createBullet(bulletX,bulletY,dirs[i],t.getGroup(),t.getTankFrame());
        }
    }
}
