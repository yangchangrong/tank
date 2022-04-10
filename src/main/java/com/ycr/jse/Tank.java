package com.ycr.jse;

import com.ycr.jse.frame.Dir;
import com.ycr.jse.frame.TankFrame;

import java.awt.*;

/**
 * 坦克类
 */
public class Tank {

    private int x = 200;
    private int y = 200;
    private Dir dir = Dir.DOWN;
    private boolean stop = true;
    private TankFrame tankFrame;

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    private static int SPEED = 10;

    public Tank(int x, int y, Dir dir,boolean stop,TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.stop = stop;
        this.tankFrame = tankFrame;
    }

    public void paint(Graphics g){
//        g.setColor(Color.YELLOW);
//        g.fillRect(x, y, 50, 50);
        Image image = null;
        switch (dir) {
            case UP:
                image = ResourceManager.tankU;
                break;
            case DOWN:
                image = ResourceManager.tankD;
                break;
            case LEFT:
                image = ResourceManager.tankL;
                break;
            case RIGHT:
                image = ResourceManager.tankR;
                break;
            default:
                break;

        }
        g.drawImage(image,x,y,null);
        move();
    }

    private void move() {
        if (!stop){
            switch (dir) {
                case UP:
                    y -= SPEED;
                    break;
                case DOWN:
                    y += SPEED;
                    break;
                case LEFT:
                    x -= SPEED;
                    break;
                case RIGHT:
                    x += SPEED;
                    break;
                default:
                    break;
            }
        }
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Dir getDir() {
        return dir;
    }


    public void fire() {
        //设置子弹和坦克的相对位置
        int bulletX = this.x + ResourceManager.tankD.getWidth()/2;
        int bullety = this.y + ResourceManager.tankD.getHeight()/2;
        tankFrame.getBullets().add(new Bullet(bulletX,bullety,this.dir,this.tankFrame));
    }
}
