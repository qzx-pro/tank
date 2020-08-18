package com.qzx.frame;

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
    List<Bullet> bullets = new ArrayList<>();//打出的子弹集合
    List<Tank> enemies = new ArrayList<>();//敌人坦克集合
    List<Explode> explodes = new ArrayList<>();//坦克爆炸集合

    public GameModel() {
        //地方坦克
        int initTankCount = Integer.parseInt((String)PropertyManager.get("initTankCount"));
        for (int i = 0; i < initTankCount; i++) {
            Tank enemy = new Tank(50+i*70,150,Dir.DOWN,this,Group.ENEMY,ResourceManager.getTankU().getWidth(),ResourceManager.getTankU().getHeight());
            enemies.add(enemy);
        }
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹数量:"+bullets.size(),10,60);
        g.drawString("敌方坦克数量:"+enemies.size(),10,80);
        g.drawString("坦克爆炸数量:"+explodes.size(),10,100);
        g.setColor(c);
        //画出我方坦克
        tank.paint(g);
        //画出打出的子弹
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            bullet.paint(g);
            if (!bullet.isAlive){
                //子弹飞出边界，移除子弹
                bullets.remove(bullet);
            }
        }
        //画出敌方坦克
        for (int i = 0; i < enemies.size(); i++) {
            Tank enemy = enemies.get(i);
            enemy.paint(g);
            if (!enemy.isAlive){
                //坦克遭到攻击，移除坦克
                enemies.remove(enemy);
            }
        }
        //画出坦克爆炸效果
        for (int i = 0; i < explodes.size(); i++) {
            Explode explode = explodes.get(i);
            explode.paint(g);
            if (!explode.isAlive){
                explodes.remove(explode);
            }
        }
        //碰撞检测
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            for (int j = 0; j < enemies.size(); j++) {
                //子弹和敌方坦克发生碰撞
                bullet.collideWith(enemies.get(j));
            }
        }
    }
}
