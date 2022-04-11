package com.ycr.jse.strategy;

import com.ycr.jse.Bullet;
import com.ycr.jse.Tank;
import com.ycr.jse.frame.Dir;

import java.util.ArrayList;
import java.util.List;

/**
 * 核弹 todo
 */
public class NuclearBulletFire extends FireStrategy<Tank> {
    @Override
    public void fire(Tank t) {
        //设置子弹和坦克的相对位置
        int bulletX = t.getX() + t.WIDTH/2 - Bullet.WIDTH/2;
        int bulletY = t.getY() + t.HEIGHT/2 - Bullet.HEIGHT/2;
        //返回
        List<Bullet> result = new ArrayList<>();
        new Bullet(bulletX,bulletY,t.getDir(),t.getTankFrame(),t.getGroup());
    }
}
