package com.ycr.jse.responsibleChain;

import com.ycr.jse.facade.Collider;
import com.ycr.jse.facade.GameObject;

import java.util.LinkedList;
import java.util.List;

public class ColliderChain implements Collider{

    private List<Collider> colliderChain = new LinkedList<>();

    public void add(Collider collider){
        this.colliderChain.add(collider);
    }

    public void addChain(List<Collider> colliderChain){
        this.colliderChain.addAll(colliderChain);
    }


    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        for(Collider collider : colliderChain){
            //如果返回true，o1,o2已经die,则直接结束链条后续
            if (collider.collide(o1, o2)){
                return true;
            }
        }
        return false;
    }
}
