package com.qzx.frame;

import java.awt.*;

public class Tank {
    private int x,y;//初始位置
    private Dir dir ;//坦克的初始方向
    private static final int SPEED = 10;//坦克移动的速度
    private boolean moving = false;//标识坦克是否移动,用来实现坦克静止,初始状态没有移动
    private TankFrame tf;
    private static final int TANK_WIDTH = 50;//坦克宽度
    private static final int TANK_HEIGHT = 50;//坦克高度

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }


    public Tank(int x, int y, Dir dir,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.CYAN);
        g.fillRect(x, y, TANK_WIDTH, TANK_HEIGHT);
        g.setColor(c);
        move();//坦克移动
    }

    private void move() {
        if(!moving) return;//没有移动
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
    }

    public void fire() {
        int x = this.x + TANK_WIDTH/2-5;//发射子弹的初始位置x
        int y = this.y + TANK_HEIGHT/2-5;//发射子弹的初始位置y
        tf.bullets.add(new Bullet(x,y,this.dir,this.tf));
    }
}
