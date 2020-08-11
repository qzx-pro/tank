package com.qzx.frame;

import java.awt.*;
import java.util.Random;

public class Tank {
    private int x,y;//初始位置
    private Dir dir ;//坦克的初始方向
    private static final int SPEED = 1;//坦克移动的速度
    private boolean moving = true;//标识坦克是否移动,用来实现坦克静止,初始状态没有移动
    private TankFrame tf;
    static final int TANK_WIDTH = ResourceManager.tankD.getWidth();//坦克宽度
    static final int TANK_HEIGHT = ResourceManager.tankD.getHeight();//坦克高度
    boolean isAlive = true;//坦克是否消失(遭到敌方攻击时消失)
    Group group;//当前坦克的敌友标识
    Random random = new Random();//让坦克随机发射子弹

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Tank(int x, int y, Dir dir, TankFrame tf, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void paint(Graphics g) {
        if (!this.isAlive) return;//坦克消失不用画出
        switch (dir){
            case LEFT:
                g.drawImage(ResourceManager.tankL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceManager.tankR,x,y,null);
                break;
            case UP:
                g.drawImage(ResourceManager.tankU,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceManager.tankD,x,y,null);
                break;
        }
        move();//坦克移动
    }

    private void move() {
        if(!isMoving()) return;//没有移动
        //根据坦克的方向进行移动
        switch (dir){
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
        }
        if (random.nextInt(10)>8){
            this.fire();
        }
    }

    public void fire() {
        int x = this.x + TANK_WIDTH/2-ResourceManager.bulletD.getWidth()/2;//发射子弹的初始位置x
        int y = this.y + TANK_HEIGHT/2-ResourceManager.bulletD.getHeight()/2;//发射子弹的初始位置y
        tf.bullets.add(new Bullet(x,y,this.dir,this.tf,this.group));
    }
}
