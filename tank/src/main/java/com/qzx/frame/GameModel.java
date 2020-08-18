package com.qzx.frame;

import com.qzx.chainOfRes.BulletTankCollider;
import com.qzx.chainOfRes.Collider;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: qzx
 * @Date: 2020/8/17 - 08 - 17 - 20:25
 * @Description: com.qzx.frame
 * 存放该项目中所有的数据,实现model与view层的分离
 * @version: 1.0
 */
public class GameModel {
    Tank tank = new Tank(TankFrame.GAME_WIDTH/2,TankFrame.GAME_HEIGHT/2,Dir.UP,this,Group.ALLY,ResourceManager.getMyTankU().getWidth(),ResourceManager.getMyTankU().getHeight());//我方坦克
    private List<GameObject> objects = new ArrayList<>();//所有对象的集合

    Collider collider = new BulletTankCollider();

    public void add(GameObject gameObject){
        objects.add(gameObject);
    }

    public void remove(GameObject gameObject){
        objects.remove(gameObject);
    }

    public GameModel() {
        //地方坦克
        int initTankCount = Integer.parseInt((String)PropertyManager.get("initTankCount"));
        for (int i = 0; i < initTankCount; i++) {
            Tank enemy = new Tank(50+i*70,150,Dir.DOWN,this,Group.ENEMY,ResourceManager.getTankU().getWidth(),ResourceManager.getTankU().getHeight());
            add(enemy);
        }
    }

    public void paint(Graphics g) {
        //画出我方坦克
        tank.paint(g);
        //画出打出的子弹
        for (int i = 0; i < objects.size(); i++) {
            GameObject object = objects.get(i);
            object.paint(g);
        }
        // 遍历物体之间的碰撞
        for (int i = 0; i < objects.size(); i++) {
            for (int j = i+1; j < objects.size(); j++) {
                collider.collide(objects.get(i),objects.get(j));
            }
        }
    }
}
