package com.ycr.jse;

import com.ycr.jse.facade.GameModel;
import com.ycr.jse.facade.GameObject;
import com.ycr.jse.frame.Dir;
import com.ycr.jse.frame.TankFrame;
import com.ycr.jse.strategy.FireStrategy;
import com.ycr.jse.strategy.DefaultBulletFire;

import java.awt.*;

/**
 * 坦克类
 */
public class Tank extends GameObject {

    private Dir dir = Dir.DOWN;
    private boolean stop = true;
    private boolean living = true;
    private Group group = Group.GOOD;
    private Rectangle ret = new Rectangle();
    private int oldX;
    private int oldY;

    public static final int WIDTH = ResourceManager.goodTankD.getWidth();
    public static final int HEIGHT = ResourceManager.goodTankD.getHeight();
    private static int SPEED = ConfigManager.INSTANCE.getInt("tankSpeed");

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public Tank(int x, int y, Dir dir,boolean stop,Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.stop = stop;
        this.group = group;
        ret.x = this.x;
        ret.y = this.y;
        ret.width = WIDTH;
        ret.height = HEIGHT;
        GameModel.getInstance().add(this);
    }

    public Rectangle getRet() {
        return ret;
    }

    public void paint(Graphics g){
//        g.setColor(Color.YELLOW);
//        g.fillRect(x, y, 50, 50);
        if (!living){
            GameModel.getInstance().getGameObjects().remove(this);
        }
        Image image = null;
        switch (group){
            case GOOD:
                switch (dir) {
                    case UP:
                        image = ResourceManager.goodTankU;
                        break;
                    case DOWN:
                        image = ResourceManager.goodTankD;
                        break;
                    case LEFT:
                        image = ResourceManager.goodTankL;
                        break;
                    case RIGHT:
                        image = ResourceManager.goodTankR;
                        break;
                    default:
                        break;
                }
                break;
            case BAD:
                switch (dir) {
                    case UP:
                        image = ResourceManager.badTankU;
                        break;
                    case DOWN:
                        image = ResourceManager.badTankD;
                        break;
                    case LEFT:
                        image = ResourceManager.badTankL;
                        break;
                    case RIGHT:
                        image = ResourceManager.badTankR;
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;


        }
        g.drawImage(image,x,y,null);
        move();
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

    private void move() {
        oldX = x;
        oldY = y;
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
        //更新rect
        ret.x = this.x;
        ret.y = this.y;
        //敌方坦克
        if (Group.BAD.equals(this.group)){
            //移动过程中发射子弹20%概率
            if ((int)(Math.random() * 10) > 8){
                fire(new DefaultBulletFire());
            }
            //随机方向
            randomDir();
            //对敌方坦克进行边界控制
            int limitLength = Math.max(WIDTH,HEIGHT);
            if (x < limitLength || y < limitLength || x > TankFrame.GAME_WIDTH - limitLength || y > TankFrame.GAME_HEIGHT - limitLength){
                //设置反方向移动
                reverseDir(dir);
            }

        }

    }

    private void randomDir() {
        if ((int)(Math.random() * 100) > 95){
            this.dir = Dir.values()[(int)(Math.random() * 4)];
        }

    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }
    //设置反方向
    public void reverseDir(Dir dir) {
        switch (dir){
            case DOWN:
                this.dir = Dir.UP;
                break;
            case UP:
                this.dir = Dir.DOWN;
                break;
            case LEFT:
                this.dir = Dir.RIGHT;
                break;
            case RIGHT:
                this.dir = Dir.LEFT;
                break;
            default:
                break;
        }
    }

    public void back() {
       this.x = oldX;
       this.y = oldY;
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

    public void fire(FireStrategy fireStrategy) {
        //设置子弹和坦克的相对位置
//        int bulletX = this.x + WIDTH/2 - Bullet.WIDTH/2;
//        int bullety = this.y + HEIGHT/2 - Bullet.HEIGHT/2;
        //策略模式一
        fireStrategy.fire(this);
    }

    public void die() {
        this.living = false;
    }



}
