package com.qzx.frame;

import com.qzx.abstractFactory.BaseTank;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Tank extends BaseTank {
    public int x,y;//初始位置
    public Dir dir ;//坦克的初始方向
    private static final int SPEED = Integer.parseInt((String)PropertyManager.get("TANK_SPEED"));//坦克移动的速度
    private boolean moving = true;//标识坦克是否移动,用来实现坦克静止,初始状态没有移动
    public TankFrame tf;
    static int TANK_WIDTH = ResourceManager.getTankU().getWidth();//坦克宽度
    static int TANK_HEIGHT = ResourceManager.getTankU().getHeight();//坦克高度
    boolean isAlive = true;//坦克是否消失(遭到敌方攻击时消失)
    Group group;//当前坦克的敌友标识
    Random random = new Random();//让坦克随机发射子弹
    boolean init = true;//是否是初始状态
    Rectangle recTank = null;//坦克的所处位置的矩形，用来做碰撞检测
    FireStrategy fireStrategy;//坦克的开火策略

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

    public Tank(int x, int y, Dir dir, TankFrame tf, Group group,int TANK_WIDTH ,int TANK_HEIGHT) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;
        Tank.TANK_WIDTH = TANK_WIDTH;
        Tank.TANK_HEIGHT = TANK_HEIGHT;
        recTank = new Rectangle(x,y,TANK_WIDTH,TANK_HEIGHT);
        if (this.group==Group.ENEMY){
            String fs = (String) PropertyManager.get("enemyTankFS");
            try {
                fireStrategy = (FireStrategy) Class.forName(fs).getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else {
            String fs = (String) PropertyManager.get("myTankFS");
            try {
                fireStrategy = (FireStrategy) Class.forName(fs).getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    @Override
    public void paint(Graphics g) {
        if (!this.isAlive) return;//坦克消失不用画出
        switch (dir){
            case LEFT:
                BufferedImage tankL = group == Group.ALLY ? ResourceManager.getMyTankL() : ResourceManager.getTankL();
                g.drawImage(tankL,x,y,null);
                break;
            case RIGHT:
                BufferedImage tankR = group == Group.ALLY ? ResourceManager.getMyTankR() : ResourceManager.getTankR();
                g.drawImage(tankR,x,y,null);
                break;
            case UP:
                BufferedImage tankU = group == Group.ALLY ? ResourceManager.getMyTankU() : ResourceManager.getTankU();
                g.drawImage(tankU,x,y,null);
                break;
            case DOWN:
                BufferedImage tankD = group == Group.ALLY ? ResourceManager.getMyTankD() : ResourceManager.getTankD();
                g.drawImage(tankD,x,y,null);
                break;
        }
        move();//坦克移动
    }

    private void move() {
        if(!isMoving()) return;//没有移动
        if (init&&group==Group.ALLY){
            //我方坦克在初始化时不要移动
            init = false;
            setMoving(false);
            return;
        }
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
        if (group == Group.ENEMY&&random.nextInt(100)>95){
            //敌方随机发射炮弹和改变移动方向
            this.fire();
            this.randomDir();
        }
        //在移动过程中需要做边界检测，不能移动到边界之外
        boundCheck();
        //更新recTank的位置
        recTank.x = x;
        recTank.y = y;
    }

    private void boundCheck() {
        boolean isOutOfBound = false;//判断是否越界
        if (this.x<20){
            x = 20;
            isOutOfBound = true;
        }
        if (this.y<40) {
            y = 40;
            isOutOfBound = true;
        }
        if (this.x>TankFrame.GAME_WIDTH-Tank.TANK_WIDTH-20){
            x = TankFrame.GAME_WIDTH-Tank.TANK_WIDTH-20;
            isOutOfBound = true;
        }
        if (this.y>TankFrame.GAME_HEIGHT-Tank.TANK_HEIGHT-20) {
            y = TankFrame.GAME_HEIGHT-Tank.TANK_HEIGHT-20;
            isOutOfBound = true;
        }
        if (isOutOfBound&&group==Group.ENEMY){
            randomDir();//敌方坦克在越界后直接改变方向
        }
    }

    private void randomDir() {
        this.setDir(Dir.values()[random.nextInt(4)]);
    }

    public void fire() {
        fireStrategy.fire(this);
    }
}
