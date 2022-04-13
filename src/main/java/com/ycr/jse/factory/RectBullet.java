package com.ycr.jse.factory;

import com.ycr.jse.*;
import com.ycr.jse.frame.Dir;
import com.ycr.jse.frame.TankFrame;

import java.awt.*;

/**
 * 子弹
 */
public class RectBullet extends BaseBullet {

    private static final int SPEED = ConfigManager.INSTANCE.getInt("bulletSpeed");
    public static final int WIDTH = 20;
    public static final int HEIGHT = 20;

    private  int x,y;
    private Dir dir;
    private boolean living = true;
    private TankFrame tankFrame;
    private Group group = Group.GOOD;
    private Rectangle ret = new Rectangle();

    public RectBullet(int x, int y, Dir dir, TankFrame tankFrame, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
        this.group = group;
        ret.x = this.x;
        ret.y = this.y;
        ret.width = WIDTH;
        ret.height = HEIGHT;
        tankFrame.getBullets().add(this);
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public void paint(Graphics g) {
//        Color c = g.getColor();
//        g.setColor(Color.RED);
//        g.fillOval(x,y,WIDTH,WIDTH);
//        g.setColor(c);
//        Image image = null;
//        switch (dir){
//            case LEFT:
//                image = ResourceManager.bulletL;
//                break;
//            case RIGHT:
//                image = ResourceManager.bulletR;
//                break;
//            case UP:
//                image = ResourceManager.bulletU;
//                break;
//            case DOWN:
//                image = ResourceManager.bulletD;
//                break;
//            default:
//                break;
//
//        }

//        g.drawImage(image,x,y,null);
        Color c = g.getColor();
        g.setColor(Color.green);
        g.fillRect(x,y,20,20);
        g.setColor(c);
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
        //更新rect
        ret.x = this.x;
        ret.y = this.y;
        if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT){
            this.living = false;
        }
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    //判断相撞
    public void collideWith(Tank tank) {
        if (tank.getGroup().equals(this.getGroup())){
            return;
        }
        if (this.ret.intersects(tank.getRet())){
            //坦克和子弹都必须die
            tank.die();
            this.die();
            //每课子弹die都创建爆炸
            int explodeX = tank.getX() + Tank.WIDTH/2 - Explode.WIDTH/2;
            int explodeY = tank.getY() + Tank.HEIGHT/2 - Explode.WIDTH/2;
            tankFrame.getExplodes().add(tankFrame.gf.createExplode(explodeX,explodeY,tankFrame));
        }
    }

    private void die() {
        this.living = false;
    }
}
