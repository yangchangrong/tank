package com.ycr.jse.strategy;

import com.ycr.jse.Bullet;
import com.ycr.jse.Tank;

import java.util.ArrayList;
import java.util.List;

public class OneBulletFire extends FireStrategy<Bullet, Tank> {
    @Override
    public List<Bullet> fire(Tank t) {
        //设置子弹和坦克的相对位置
        int bulletX = t.getX() + t.WIDTH/2 - Bullet.WIDTH/2;
        int bulletY = t.getY() + t.HEIGHT/2 - Bullet.HEIGHT/2;
        //返回
        List<Bullet> result = new ArrayList<>();
        result.add(new Bullet(bulletX,bulletY,t.getDir(),t.getTankFrame(),t.getGroup()));
        return result;
    }
}
