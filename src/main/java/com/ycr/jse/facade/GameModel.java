package com.ycr.jse.facade;

import com.ycr.jse.*;
import com.ycr.jse.frame.Dir;
import com.ycr.jse.responsibleChain.ColliderChain;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 游戏内部逻辑统一对外的门面对象
 */
public class GameModel {

    private Tank myTank = new Tank(400,400, Dir.DOWN,true,this, Group.GOOD);
//    private List<Bullet> bullets = new ArrayList<>();
//    private List<Tank> enemyTanks = new ArrayList<>();
//    private List<Explode> explodes = new ArrayList<>();
    List<GameObject> gameObjects = new ArrayList<>();
//    BulletTankCollider bulletTankCollider = new BulletTankCollider();
//    TankTankCollider tankTankCollider = new TankTankCollider();
    ColliderChain colliderChain = new ColliderChain();


    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public Tank getMyTank() {
        return myTank;
    }

//    public List<Tank> getEnemyTanks() {
//        return enemyTanks;
//    }
//    public void setEnemyTanks(List<Tank> enemyTanks) {
//        this.enemyTanks = enemyTanks;
//    }
//
//    public List<Explode> getExplodes() {
//        return explodes;
//    }
//
//    public void setExplodes(List<Explode> explodes) {
//        this.explodes = explodes;
//    }
//
//    public List<Bullet> getBullets() {
//        return bullets;
//    }

    public GameModel(){
        int initEnemyTankCount = ConfigManager.INSTANCE.getInt("initEnemyTankCount");
        //添加敌人
        for (int i = 0; i < initEnemyTankCount; i++) {
            this.gameObjects.add(new Tank(200 + 150 * i,200, Dir.DOWN,false,this,Group.BAD));
        }
        //添加墙
        this.gameObjects.add(new Wall(50,300,100,100,this));
        this.gameObjects.add(new Wall(400,300,100,100,this));
        this.gameObjects.add(new Wall(300,50,100,100,this));
        this.gameObjects.add(new Wall(300,500,100,100,this));
        //添加碰撞链
        colliderChain.add(new BulletTankCollider());
        colliderChain.add(new TankTankCollider());
        colliderChain.add(new BulletWallCollider());
        colliderChain.add(new TankWallCollider());

    }

    /**
     * 具体画的逻辑
     * @param g
     */
    public void paint(Graphics g) {
//        Color c = g.getColor();
//        g.setColor(Color.WHITE);
//        g.drawString("子弹的数目" + bullets.size(),10,60);
//        g.drawString("敌人的数目" + enemyTanks.size(),10,80);
//        g.drawString("爆炸的数目" + explodes.size(),10,100);
//        g.setColor(c);
        //画自己坦克
        myTank.paint(g);
        //画 (敌人坦克 + 子弹 + 爆炸 + ...)
        for (int j = 0; j < gameObjects.size(); j++) {
            gameObjects.get(j).paint(g);
        }

//        //画子弹
//        for (int i = 0; i < bullets.size(); i++){
//            bullets.get(i).paint(g);
//        }
//        //画爆炸
//        for (int i = 0; i < explodes.size(); i++) {
//            explodes.get(i).paint(g);
//        }
        //判断自己的子弹和敌人坦克相撞
//      判断各种物体的碰撞
        for (int i = 0; i < gameObjects.size(); i++) {
            for (int j = i + 1; j < gameObjects.size(); j++) {
               colliderChain.collide(gameObjects.get(i),gameObjects.get(j));
            }
        }
    }
}
