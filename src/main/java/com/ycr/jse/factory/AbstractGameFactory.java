package com.ycr.jse.factory;

import com.ycr.jse.Group;
import com.ycr.jse.frame.Dir;
import com.ycr.jse.frame.TankFrame;

/**
 * 游戏的抽象工厂（可以更方便的扩展产品一族（现代战士/魔法战士/未来战士））
 */
public abstract class AbstractGameFactory {

    public abstract BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tf);

    public abstract BaseExplode createExplode(int x, int y, TankFrame tankFrame);

}
