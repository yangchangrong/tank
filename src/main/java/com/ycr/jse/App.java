package com.ycr.jse;

import com.ycr.jse.frame.Dir;
import com.ycr.jse.frame.TankFrame;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        TankFrame tf = new TankFrame();
        int initEnemyTankCount = ConfigManager.INSTANCE.getInt("initEnemyTankCount");
        //添加敌人
        for (int i = 0; i < initEnemyTankCount; i++) {
//            int dirNum = (int)(Math.random() * 4);
//            Dir initDir = Dir.values()[dirNum];
            tf.getEnemyTanks().add(new Tank(200 + 50 * i,200, Dir.DOWN,false,tf,Group.BAD));
        }
        System.out.println( "Hello World!" );
        while (true){
            try {
                Thread.sleep(50);
                tf.repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
