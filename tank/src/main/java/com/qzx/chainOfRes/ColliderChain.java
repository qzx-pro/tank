package com.qzx.chainOfRes;

import com.qzx.frame.GameObject;

import java.util.LinkedList;
import java.util.List;

/**
 * @Auther: qzx
 * @Date: 2020/8/18 - 08 - 18 - 17:16
 * @Description: com.qzx.chainOfRes
 * @version: 1.0
 */
public class ColliderChain implements Collider {
    List<Collider> colliders = new LinkedList<>();//Collider集合

    public ColliderChain() {
        colliders.add(new BulletTankCollider());
        colliders.add(new TankTankCollider());
    }

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        for (int i = 0; i < colliders.size(); i++) {
             if (!colliders.get(i).collide(o1,o2)){
                 // 停止继续碰撞
                 return false;
             }
        }
        return true;
    }
}
