package com.qzx.frame;

import com.qzx.chainOfRes.BulletTankCollider;
import com.qzx.chainOfRes.Collider;
import com.qzx.chainOfRes.ColliderChain;

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
    private static final GameModel INSTANCE = new GameModel();//GameModel设置为单例模式
    Tank tank = new Tank(TankFrame.GAME_WIDTH / 2, TankFrame.GAME_HEIGHT / 2, Dir.UP, Group.ALLY, ResourceManager.getMyTankU().getWidth(), ResourceManager.getMyTankU().getHeight());//我方坦克
    private List<GameObject> objects = new ArrayList<>();//所有对象的集合

    ColliderChain chain = new ColliderChain();//碰撞器链

    public void add(GameObject gameObject) {
        objects.add(gameObject);
    }

    public void remove(GameObject gameObject) {
        objects.remove(gameObject);
    }

    public static GameModel getInstance() {
        return INSTANCE;
    }

    private GameModel() {
    }

    //初始化方法
    public void init() {
        //地方坦克
        int initTankCount = Integer.parseInt((String) PropertyManager.get("initTankCount"));
        for (int i = 0; i < initTankCount; i++) {
            Tank enemy = new Tank(50 + i * 70, 150, Dir.DOWN, Group.ENEMY, ResourceManager.getTankU().getWidth(), ResourceManager.getTankU().getHeight());
            add(enemy);
        }
        new Wall(350, 50, 80, 100);
        new Wall(100, 600, 800, 70);
        new Wall(300, 400, 500, 70);
        new Wall(1400, 200, 100, 800);
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
            for (int j = i + 1; j < objects.size(); j++) {
                chain.collide(objects.get(i), objects.get(j));
            }
        }
    }
}
