package com.ycr.jse;

import com.ycr.jse.frame.Dir;
import com.ycr.jse.frame.TankFrame;

import java.awt.*;

/**
 * 子弹
 */
public class Bullet {

    private static final int SPEED = 2;
    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;

    private  int x,y;
    private Dir dir;
    private boolean living = true;
    private TankFrame tankFrame;

    public Bullet(int x, int y, Dir dir,TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
    }

    public void paint(Graphics g) {
//        Color c = g.getColor();
//        g.setColor(Color.RED);
//        g.fillOval(x,y,WIDTH,WIDTH);
//        g.setColor(c);
        Image image = null;
        switch (dir){
            case LEFT:
                image = ResourceManager.bulletL;
                break;
            case RIGHT:
                image = ResourceManager.bulletR;
                break;
            case UP:
                image = ResourceManager.bulletU;
                break;
            case DOWN:
                image = ResourceManager.bulletD;
                break;
            default:
                break;

        }

        g.drawImage(image,x,y,null);
        move(g);

    }

    /**
     * 移动
     * @param g
     */
    private void move(Graphics g) {
        if (!this.living){
            tankFrame.getBullets().remove(this);
        }
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
        }
        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT){
            this.living = false;
        }
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    //判断相撞
    public void collideWith(Tank tank) {
        Rectangle bulletRect = new Rectangle(this.x,this.y,WIDTH,HEIGHT);
        Rectangle tankRect = new Rectangle(tank.getX(),tank.getY(),Tank.WIDTH,Tank.HEIGHT);
        if (bulletRect.intersects(tankRect)){
            //坦克和子弹都必须die
            tank.die();
            this.die();
        }
    }

    private void die() {
        this.living = false;
    }
}
