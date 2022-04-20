package com.ycr.jse.obersver;

import com.ycr.jse.ConfigManager;
import com.ycr.jse.facade.GameModel;
import com.ycr.jse.frame.TankFrame;
import com.ycr.jse.strategy.FireStrategy;

/**
 * 具体监听器 例如Space键press的监听器
 */
public class SpaceKeyPressObserver extends KeyPressObserver{


    //监听到事件之后，发射子弹
    @Override
    public void actionOnObserve(KeyPressEvent event) {
//        TankFrame tankFrame = event.getSource();
        try {
            FireStrategy fireStrategy =  (FireStrategy)Class.forName(ConfigManager.INSTANCE.getString("good.fire.strategy")).newInstance();
            GameModel.getInstance().getMyTank().fire(fireStrategy);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
