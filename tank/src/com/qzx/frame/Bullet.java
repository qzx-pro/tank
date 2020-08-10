package com.qzx.frame;

import java.awt.*;

public class Bullet {
    private int x,y;//初始位置
    private Dir dir ;//子弹的初始方向
    private static final int SPEED = 5;//子弹移动的速度

    public Bullet(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x, y, 10, 10);
        g.setColor(c);
        move();//子弹移动
    }

    private void move() {
        //根据子弹的方向进行移动
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
}
