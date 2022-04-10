package com.ycr.jse;

import com.ycr.jse.frame.Dir;
import com.ycr.jse.frame.TankFrame;

import java.awt.*;
import java.util.Random;

/**
 * 坦克类
 */
public class Tank {

    private int x = 200;
    private int y = 200;
    private Dir dir = Dir.DOWN;
    private boolean stop = true;
    private TankFrame tankFrame;
    private boolean living = true;
    private Group group = Group.GOOD;

    private static int SPEED = 10;
    public static final int WIDTH = ResourceManager.tankD.getWidth();
    public static final int HEIGHT = ResourceManager.tankD.getHeight();

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public Tank(int x, int y, Dir dir,boolean stop,TankFrame tankFrame,Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.stop = stop;
        this.tankFrame = tankFrame;
        this.group = group;
    }

    public void paint(Graphics g){
//        g.setColor(Color.YELLOW);
//        g.fillRect(x, y, 50, 50);
        if (!living){
            this.tankFrame.getEnemyTanks().remove(this);
        }
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
        //每移动一次开火
        if (Math.random() * 10 > 8){
            fire();
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public void fire() {
        //设置子弹和坦克的相对位置
        int bulletX = this.x + WIDTH/2;
        int bullety = this.y + HEIGHT/2;
        tankFrame.getBullets().add(new Bullet(bulletX,bullety,this.dir,this.tankFrame,this.group));
    }

    public void die() {
        this.living = false;
    }
}
