package com.ycr.jse.factory;

import com.ycr.jse.Explode;
import com.ycr.jse.Group;
import com.ycr.jse.frame.Dir;
import com.ycr.jse.frame.TankFrame;

/**
 * 默认的抽象工厂实现类
 */
public class DefaultGameFactory extends AbstractGameFactory{
    @Override
    public BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tf) {
        return null;
    }

    @Override
    public BaseExplode createExplode(int x, int y, TankFrame tankFrame) {
        return new Explode(x, y, tankFrame);
    }
}
