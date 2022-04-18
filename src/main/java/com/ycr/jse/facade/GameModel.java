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


    //单例解耦
    private static final GameModel INSTANCE = new GameModel();

    private static Tank myTank = null;

    List<GameObject> gameObjects = new ArrayList<>();

    static ColliderChain colliderChain = new ColliderChain();


    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public Tank getMyTank() {
        return myTank;
    }

    /**
     * 静态代码块，是类加载的最后一步
     */
    static {
        init();
    }

    private GameModel(){
        System.out.println("GameModel 构造方法执行");
    }

    public static void init(){
        //添加自己坦克
        myTank =  new Tank(400,400, Dir.DOWN,true, Group.GOOD);
        int initEnemyTankCount = ConfigManager.INSTANCE.getInt("initEnemyTankCount");
        //添加敌人
        for (int i = 0; i < initEnemyTankCount; i++) {
            new Tank(200 + 150 * i,200, Dir.DOWN,false,Group.BAD);
        }
        //添加墙
        new Wall(50,300,100,100);
        new Wall(400,300,100,100);
        new Wall(300,50,100,100);
        new Wall(300,500,100,100);
        //添加碰撞链
        colliderChain.add(new BulletTankCollider());
        colliderChain.add(new TankTankCollider());
        colliderChain.add(new BulletWallCollider());
        colliderChain.add(new TankWallCollider());
    }

    public void add(GameObject gameObject){
        gameObjects.add(gameObject);
    }
    public static GameModel getInstance(){
        return INSTANCE;
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
