package com.ycr.jse.frame;

import com.ycr.jse.*;
import com.ycr.jse.strategy.FireStrategy;
import com.ycr.jse.strategy.FourDirBulletFire;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {

    private Tank myTank = new Tank(400,400,Dir.DOWN,true,this, Group.GOOD);
    private List<Bullet> bullets = new ArrayList<>();
    private List<Tank> enemyTanks = new ArrayList<>();
    private List<Explode> explodes = new ArrayList<>();
    public static final int GAME_WIDTH = 1036;
    public static final int GAME_HEIGHT = 768;

    public List<Tank> getEnemyTanks() {
        return enemyTanks;
    }
    public void setEnemyTanks(List<Tank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }

    public List<Explode> getExplodes() {
        return explodes;
    }

    public void setExplodes(List<Explode> explodes) {
        this.explodes = explodes;
    }

    public List<Bullet> getBullets() {
        return bullets;
    }


    public TankFrame(){
        setSize(GAME_WIDTH,GAME_HEIGHT);
        setResizable(false);
        setTitle("ycr go!");
        setVisible(true);
        this.addKeyListener(new MyKeyListener());
        this.addWindowListener(
                new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        System.exit(0);
                    }
                }
        );
    }

    Image offScreenImage = null;
    @Override
    public void update(Graphics g) {
        if (null == offScreenImage) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics graphics = offScreenImage.getGraphics();
        Color c = graphics.getColor();
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        graphics.setColor(c);
        paint(graphics);
        g.drawImage(offScreenImage, 0, 0, null);

    }

    @Override
    public void paint(Graphics g) {
        System.out.println("paint");
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数目" + bullets.size(),10,60);
        g.drawString("敌人的数目" + enemyTanks.size(),10,80);
        g.drawString("爆炸的数目" + explodes.size(),10,100);
        g.setColor(c);
        //画敌人坦克
        for (int j = 0; j < enemyTanks.size(); j++) {
            enemyTanks.get(j).paint(g);
        }

        //画自己坦克
        myTank.paint(g);
        //画子弹
        for (int i = 0; i < bullets.size(); i++){
            bullets.get(i).paint(g);
        }
        //画爆炸
        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }
        //判断自己的子弹和敌人坦克相撞
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < enemyTanks.size(); j++) {
                bullets.get(i).collideWith(enemyTanks.get(j));
            }
        }



    }

    class MyKeyListener extends KeyAdapter{
        boolean bl = false;
        boolean br = false;
        boolean bu = false;
        boolean bd = false;

        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            //监听上下左右键
            switch (keyCode){
                case KeyEvent.VK_LEFT:
                    bl = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    br = true;
                    break;
                case KeyEvent.VK_UP:
                    bu = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bd = true;
                    break;
                case KeyEvent.VK_SPACE:
                    //监听space键盘,press一下发射一课子弹
                    try {
                        FireStrategy fireStrategy =  (FireStrategy)Class.forName(ConfigManager.INSTANCE.getString("good.fire.strategy")).newInstance();
                        myTank.fire(fireStrategy);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    break;
                default:
                    break;
            }
            setDir();


        }

        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode){
                case KeyEvent.VK_LEFT:
                    bl = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    br = false;
                    break;
                case KeyEvent.VK_UP:
                    bu = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bd = false;
                    break;
                default:
                    break;
            }
            setDir();
        }


        public void setDir(){
            if (!bl && !br && !bu && !bd){
                myTank.setStop(true);
            }else {
                myTank.setStop(false);
                if (bl) myTank.setDir(Dir.LEFT);
                if (br) myTank.setDir(Dir.RIGHT);
                if (bu) myTank.setDir(Dir.UP);
                if (bd) myTank.setDir(Dir.DOWN);
            }


        }
    }



}
