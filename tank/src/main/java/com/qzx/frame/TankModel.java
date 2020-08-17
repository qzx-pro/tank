package com.qzx.frame;

import com.qzx.abstractFactory.BaseFactory;
import com.qzx.abstractFactory.DefaultFactory;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: qzx
 * @Date: 2020/8/17 - 08 - 17 - 9:28
 * @Description: com.qzx.frame
 * 实现Model和View的分离，将数据全部存放在Model中,数据的显示全部在TankFrame中
 * @version: 1.0
 */
public class TankModel {
    List<Bullet> bullets = new ArrayList<>();//打出的子弹集合
    List<Tank> enemies = new ArrayList<>();//敌人坦克集合
    List<Explode> explodes = new ArrayList<>();//坦克爆炸集合

    BaseFactory factory = DefaultFactory.getFactory();//生成坦克、子弹和爆炸效果的工厂
    //通过工厂创建我方坦克
    Tank tank = (Tank)factory.createTank(TankFrame.GAME_WIDTH/2,TankFrame.GAME_HEIGHT/2,Dir.UP,this,Group.ALLY,ResourceManager.getMyTankU().getWidth(),ResourceManager.getMyTankU().getHeight());

    public TankModel() {
        int initTankCount = Integer.parseInt((String)PropertyManager.get("initTankCount"));
        for (int i = 0; i < initTankCount; i++) {
            //使用工厂创建敌方坦克
            Tank enemy = (Tank)factory.createTank(50+i*70,150,Dir.DOWN,this,Group.ENEMY,ResourceManager.getTankU().getWidth(),ResourceManager.getTankU().getHeight());
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
