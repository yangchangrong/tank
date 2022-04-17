package com.ycr.jse.facade;

import com.ycr.jse.Tank;

import java.awt.*;

public class TankTankCollider implements Collider{

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Tank){
            Tank t1 = (Tank) o1;
            Tank t2 = (Tank) o2;
            collideWith(t1,t2);
        }
        return false;
    }

    //当敌人的坦克和坦克之间发生碰撞，直接返回原先的位置
    public void collideWith(Tank t1,Tank t2) {
        Rectangle ret1 = t1.getRet();
        Rectangle ret2 = t2.getRet();
        if (ret1.intersects(ret2)){
            t1.back();
            t2.back();
        }
    }
}
