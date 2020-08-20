package com.qzx.frame;

import java.awt.*;

public class Bullet extends GameObject {
    private Dir dir;//子弹的初始方向
    private static final int SPEED = Integer.parseInt((String) PropertyManager.get("BULLET_SPEED"));//子弹移动的速度
    public static final int BULLET_WIDTH = ResourceManager.getBulletU().getWidth();//子弹的宽度
    public static final int BULLET_HEIGHT = ResourceManager.getBulletU().getHeight();//子弹的高度
    private GameModel gm = GameModel.getInstance();
    Group group;//当前发射的子弹的敌友标识,和发射该子弹的坦克的标识一致
    Rectangle recBullet = null;

    public Group getGroup() {
        return group;
    }

    public Rectangle getRecBullet() {
        return recBullet;
    }

    public GameModel getGm() {
        return gm;
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

    public Bullet(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        recBullet = new Rectangle(x, y, BULLET_WIDTH, BULLET_HEIGHT);
        gm.add(this);//构造子弹的时候直接将子弹添加到集合中
        width = BULLET_WIDTH;
        height = BULLET_HEIGHT;
        isAlive = true;
    }

    @Override
    public void paint(Graphics g) {
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceManager.getBulletL(), x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceManager.getBulletR(), x, y, null);
                break;
            case UP:
                g.drawImage(ResourceManager.getBulletU(), x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceManager.getBulletD(), x, y, null);
                break;
        }
        move();//子弹移动
        if (!isAlive) {
            gm.remove(this);
        }
    }

    private void move() {
        //根据子弹的方向进行移动
        switch (dir) {
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
        if (x < 0 || x > TankFrame.GAME_WIDTH || y < 0 || y > TankFrame.GAME_HEIGHT) {
            //超过边界，设置子弹消失状态
            isAlive = false;
        }
        //更新recBullet的位置
        recBullet.x = x;
        recBullet.y = y;
    }
}
