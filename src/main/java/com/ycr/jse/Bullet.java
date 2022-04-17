package com.ycr.jse;

import com.ycr.jse.facade.GameModel;
import com.ycr.jse.facade.GameObject;
import com.ycr.jse.frame.Dir;
import com.ycr.jse.frame.TankFrame;

import java.awt.*;

/**
 * 子弹
 */
public class Bullet extends GameObject {

    private static final int SPEED = ConfigManager.INSTANCE.getInt("bulletSpeed");
    public static final int WIDTH = ResourceManager.bulletD.getWidth();
    public static final int HEIGHT = ResourceManager.bulletD.getHeight();

    private  int x,y;
    private Dir dir;
    private boolean living = true;
    private GameModel gm;
    private Group group = Group.GOOD;
    private Rectangle ret = new Rectangle();

    public Bullet(int x, int y, Dir dir,GameModel gm,Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.gm = gm;
        this.group = group;
        ret.x = this.x;
        ret.y = this.y ;
        ret.width = WIDTH;
        ret.height = HEIGHT;
        gm.getGameObjects().add(this);
    }

    public GameModel getGm() {
        return gm;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Rectangle getRet() {
        return ret;
    }

    @Override
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
            gm.getGameObjects().remove(this);
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



    public void die() {
        this.living = false;
    }
}
