package com.ycr.jse.frame;

import com.ycr.jse.Bullet;
import com.ycr.jse.Tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {

    private Tank tank;

    public List<Bullet> getBullets() {
        return bullets;
    }

    private List<Bullet> bullets = new ArrayList<>();


    public static final int GAME_WIDTH = 800;
    public static final int GAME_HEIGHT = 600;

    public TankFrame(){
        setSize(GAME_WIDTH,GAME_HEIGHT);
        setResizable(false);
        setTitle("ycr go!");
        setVisible(true);
        this.tank = new Tank(200,200,Dir.DOWN,true,this);
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
        g.setColor(c);

        tank.paint(g);
        for (int i = 0; i < bullets.size(); i++){
            bullets.get(i).paint(g);

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
                    tank.fire();
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
                tank.setStop(true);
            }else {
                tank.setStop(false);
                if (bl) tank.setDir(Dir.LEFT);
                if (br) tank.setDir(Dir.RIGHT);
                if (bu) tank.setDir(Dir.UP);
                if (bd) tank.setDir(Dir.DOWN);
            }


        }
    }



}
