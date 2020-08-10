package com.qzx.frame;

import java.awt.*;

public class Tank {
    private int x,y;//初始位置
    private Dir dir = Dir.STOP;//坦克的初始方向
    private static final int SPEED = 10;//坦克移动的速度

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public void paint(Graphics g) {
        g.fillRect(x, y, 50, 50);
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
}
