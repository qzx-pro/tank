package com.qzx.frame;

import java.awt.*;

public class Bullet {
    private int x,y;//初始位置
    private Dir dir ;//子弹的初始方向
    private static final int SPEED = 5;//子弹移动的速度
    private static final int BULLET_WIDTH = 10;//子弹的宽度
    private static final int BULLET_HEIGHT = 10;//子弹的高度
    public boolean isAlive = true;//子弹是否消失
    private TankFrame tf;


    public Bullet(int x, int y, Dir dir,TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x, y, BULLET_WIDTH, BULLET_HEIGHT);
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
        if (x<0||x>tf.GAME_WIDTH||y<0||y>tf.GAME_HEIGHT){
            //超过边界，设置子弹消失状态
            isAlive = false;
        }
    }
}
