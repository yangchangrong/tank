package com.ycr.jse.frame;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {
    int x = 200;
    int y = 200;
    boolean first = true;
    boolean bl = false;
    boolean br = false;
    boolean bu = false;
    boolean bd = false;

    public TankFrame(){
        setSize(800,600);
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


    @Override
    public void paint(Graphics g) {
        System.out.println("paint");
        if (first){
            first = false;
        }else {
            if (bl){
                x -= 10;
            }
            if (br){
                x += 10;
            }
            if (bu){
                y -= 10;
            }
            if (bd){
                y += 10;
            }
        }
        g.fillRect(x,y,50,50);
    }

    class MyKeyListener extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
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
                default:
                    break;
            }
            repaint();


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
        }
    }
}
