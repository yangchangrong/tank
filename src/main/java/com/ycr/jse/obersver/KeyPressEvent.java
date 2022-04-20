package com.ycr.jse.obersver;

import com.ycr.jse.frame.TankFrame;

import java.util.Date;

public class KeyPressEvent extends Event<TankFrame> {

    private TankFrame tankFrame;
    private Date pressTime;

    public KeyPressEvent(TankFrame tankFrame, Date pressTime) {
        this.tankFrame = tankFrame;
        this.pressTime = pressTime;
    }

    @Override
    protected TankFrame getSource() {
        return this.tankFrame;
    }
}
