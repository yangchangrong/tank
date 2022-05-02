package com.ycr.jse.facade;

import com.ycr.jse.*;
import com.ycr.jse.frame.Dir;
import com.ycr.jse.responsibleChain.ColliderChain;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 游戏内部逻辑统一对外的门面对象
 */
public class GameModel implements Serializable{


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

    /**
     * 存盘:将GameModel中的所有对象存储在文件中
     * 需要存储的对象需要实现Serializable接口
     */
    public void save(){
        ObjectOutputStream objectOutputStream = null;
        try {
            File f = new File("D:\\downloadTest\\tank\\tank.data");
            objectOutputStream = new ObjectOutputStream(new FileOutputStream(f));
            objectOutputStream.writeObject(myTank);
            objectOutputStream.writeObject(gameObjects);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != objectOutputStream){
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    /**
     * 加载，从tank.data文件中将对象加载到内存中
     * 注意读的顺序，要和写的顺序一致，因为写文件是至上而下的
     */
    public void load() {
        ObjectInputStream objectInputStream = null;
        try {
            File f = new File("D:\\downloadTest\\tank\\tank.data");
            objectInputStream = new ObjectInputStream(new FileInputStream(f));
            myTank = (Tank) objectInputStream.readObject();
            gameObjects = (List<GameObject>) objectInputStream.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (null != objectInputStream){
                try {
                    objectInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
